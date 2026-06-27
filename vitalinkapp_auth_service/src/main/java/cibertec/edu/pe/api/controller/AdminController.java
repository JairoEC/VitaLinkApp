package cibertec.edu.pe.api.controller;


import cibertec.edu.pe.api.controller.dto.request.CrearMedicoRequest;
import cibertec.edu.pe.model.Rol;
import cibertec.edu.pe.model.Usuario;
import cibertec.edu.pe.repository.RolRepository;
import cibertec.edu.pe.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/crear-medico")
    public String crearMedico(
            @RequestBody CrearMedicoRequest request) {

        if(usuarioRepository.findByUsername(request.getUsername()).isPresent()){
            return "El usuario ya existe";
        }

        Rol rolMedico =
                rolRepository.findByNombre("ROLE_MEDICO")
                        .orElseThrow();

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(rolMedico)
                .build();

        usuarioRepository.save(usuario);

        return "Médico creado correctamente";
    }
}

