package cibertec.edu.pe.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/admin")
    public String admin() {
        return "AREA ADMIN";
    }

    @GetMapping("/medico")
    public String medico() {
        return "AREA MEDICO";
    }
}
