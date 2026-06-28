package cibertec.edu.pe.service.cita;


import cibertec.edu.pe.dto.CitaCreateDto;
import cibertec.edu.pe.dto.CitaResponseDto;
import cibertec.edu.pe.model.cita.Cita;
import cibertec.edu.pe.model.enums.DiaSemanaEnum;
import cibertec.edu.pe.model.medico.Disponibilidad;
import cibertec.edu.pe.model.medico.Medico;
import cibertec.edu.pe.model.paciente.Paciente;
import cibertec.edu.pe.repository.cita.CitaRepository;
import cibertec.edu.pe.repository.medico.DisponibilidadRepository;
import cibertec.edu.pe.repository.medico.MedicoRepository;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitaService {

    private final Resend resend = new Resend("re_4GCPVVHJ_nASr1JFfT6qy2D8YnkGLMrE6");
    private final String CORREO_ORIGEN = "jespinozac96@gmail.com";
    private final TemplateEngine templateEngine;
    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final DisponibilidadRepository disponibilidadRepository;
    private final EntityManager entityManager;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public List<CitaResponseDto> listarTodasDto(){

        return citaRepository.findAll()
                .stream()
                .map(this::convertirDto)
                .toList();

    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public CitaResponseDto buscarCitaDto(Long id){

        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("CITA NO ENCONTRADA"));

        return convertirDto(cita);
    }

    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    @Transactional
    public Cita reservarCita(CitaCreateDto dto) {
        // 1. Validar regla de negocio (bloques de 30 min)
        int minuto = dto.getFechaHora().getMinute();
        if (minuto != 0 && minuto != 30) {
            throw new RuntimeException("Las citas deben programarse cada 30 minutos (ej: 09:00, 09:30).");
        }

        // 2. Validar disponibilidad (¿El médico trabaja a esa hora?)
        String diaSemanaIngles = dto.getFechaHora().getDayOfWeek().name();
        DiaSemanaEnum diaSemana = DiaSemanaEnum.fromEnglishName(diaSemanaIngles);
        LocalTime hora = dto.getFechaHora().toLocalTime();

        boolean existeDisponibilidad = disponibilidadRepository.existsByMedicoAndDiaAndHora(
                dto.getMedicoId(), diaSemana, hora
        );
        if (!existeDisponibilidad) throw new RuntimeException("El médico no atiende a esta hora.");

        // 3. Validar colisión (¿Ya hay alguien reservado en ese slot?)
        if (citaRepository.existsByMedicoIdAndFechaHora(dto.getMedicoId(), dto.getFechaHora())) {
            throw new RuntimeException("Este horario ya está ocupado.");
        }

        // 4. Crear y guardar
        Cita nuevaCita = new Cita();
        nuevaCita.setPaciente(entityManager.getReference(Paciente.class, dto.getPacienteId()));
        nuevaCita.setMedico(entityManager.getReference(Medico.class, dto.getMedicoId()));
        nuevaCita.setFechaHora(dto.getFechaHora());
        nuevaCita.setMotivo(dto.getMotivo());
        nuevaCita.setEstado("CONFIRMADA");

        return citaRepository.save(nuevaCita);
    }

    public Cita actualizar(Long id, Cita citaActualizada) {
        return citaRepository.findById(id).map(cita -> {
            cita.setPaciente(citaActualizada.getPaciente());
            cita.setMedico(citaActualizada.getMedico());
            cita.setFechaHora(citaActualizada.getFechaHora());
            cita.setMotivo(citaActualizada.getMotivo());
            cita.setEstado(citaActualizada.getEstado());
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    //HORARIOS DISPONIBLES:
    public List<LocalTime> obtenerHorariosLibres(Long medicoId, LocalDate fecha) {
        //LIMITES DEL DIA PARA BUSCAR CITAS
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);
        //OBTENER RANGOS LABORALES
        String diaIngles = fecha.getDayOfWeek().name();
        DiaSemanaEnum dia = DiaSemanaEnum.fromEnglishName(diaIngles);
        List<Disponibilidad> rangos = disponibilidadRepository.findByMedicoIdAndDiaSemana(medicoId, dia);
        //OBTENER CITAS CONFIRMADAS
        List<Cita> citasOcupadas = citaRepository.findByMedicoIdAndFechaHoraBetween(medicoId, inicio, fin);
        List<LocalTime> horasOcupadas = citasOcupadas.stream()
                .map(c -> c.getFechaHora().toLocalTime())
                .toList();

        List<LocalTime> libres = new ArrayList<>();

        for (Disponibilidad rango : rangos) {
            LocalTime actual = rango.getHoraInicio();
            while (actual.isBefore(rango.getHoraFin())) {

                // Comparamos el slot contra la lista de horas ocupadas, no contra la lista de Citas
                if (!horasOcupadas.contains(actual)) {
                    libres.add(actual);
                }
                actual = actual.plusMinutes(30);
            }
        }
        return libres;
    }

    //NOTIFICACION CORREO
    @Async
    public void enviarCorreo(String emailCliente, CitaResponseDto cita) {
        Context context = new Context();
        context.setVariable("cita", cita);

        String htmlContent = templateEngine.process("/cita-generada", context);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to(CORREO_ORIGEN)
                .subject("Confirmación de cita médica #" + cita.getId())
                .html(htmlContent)
                .build();
        try {
            log.info("--------PREPARANDO ENVIO----------");
            resend.emails().send(params);
            log.info("--------CORREO ENVIADO----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CitaResponseDto convertirDto(Cita cita) {

        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new NotFoundException("MEDICO NO ENCONTRADO"));

        return CitaResponseDto.builder()
                .id(cita.getId())
                .estado(cita.getEstado())
                .motivo(cita.getMotivo())
                .fechaHora(cita.getFechaHora())
                .nombrePaciente(cita.getPaciente().getNombres())
                .correoPaciente(cita.getPaciente().getCorreo())
                .dniPaciente(cita.getPaciente().getDni())
                .fechaNacimiento(cita.getPaciente().getFechaNacimiento())
                .nombreMedico(medico.getNombres())
                .apellidoMedico(medico.getApellidos())
                .especialidad(medico.getEspecialidad().getNombre())
                .build();

    }
}
