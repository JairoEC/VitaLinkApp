package cibertec.edu.pe.config;


import cibertec.edu.pe.model.Rol;
import cibertec.edu.pe.model.Usuario;
import cibertec.edu.pe.repository.RolRepository;
import cibertec.edu.pe.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Rol adminRol = rolRepository.findByNombre("ROLE_ADMIN")
                    .orElseGet(() ->
                            rolRepository.save(
                                    Rol.builder()
                                            .nombre("ROLE_ADMIN")
                                            .build()
                            ));

            Rol medicoRol = rolRepository.findByNombre("ROLE_MEDICO")
                    .orElseGet(() ->
                            rolRepository.save(
                                    Rol.builder()
                                            .nombre("ROLE_MEDICO")
                                            .build()
                            ));

            if(usuarioRepository.findByUsername("admin").isEmpty()) {

                Usuario admin = Usuario.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .rol(adminRol)
                        .build();

                usuarioRepository.save(admin);
            }
        };
    }
}
