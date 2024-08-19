package org.example.consultacep;

import org.example.consultacep.dto.ConsultaCepDTO;
import org.example.consultacep.service.ConsultaCepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/consulta-cep")
public class ConsultaCepController {

    ConsultaCepService consultaCepService = new ConsultaCepService();

    @GetMapping("/cep")
    public ResponseEntity<ConsultaCepDTO> getConsultaCep(@RequestParam String cep) {
        try {
            ConsultaCepDTO consultaCepDTO = consultaCepService.getConsultaCepDTO(cep);
            return ResponseEntity.ok(consultaCepDTO); // Retorna o DTO como resposta da API
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna um erro 500 em caso de falha
        }
    }
}
