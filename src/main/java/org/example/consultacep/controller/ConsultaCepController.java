package org.example.consultacep.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.consultacep.dto.ConsultaCepDTO;
import org.example.consultacep.service.ConsultaCepService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@Slf4j
@RestController
@RequestMapping("/consulta-cep")
public class ConsultaCepController {

    ConsultaCepService consultaCepService = new ConsultaCepService();
    Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultaCepController.class);
    @GetMapping("/cep")
    public ResponseEntity<ConsultaCepDTO> getConsultaCep(@RequestParam String cep) {
        try {
            ConsultaCepDTO consultaCepDTO = consultaCepService.getConsultaCepDTO(cep);
            logger.info("Consulta realizada com sucesso");
            return ResponseEntity.ok(consultaCepDTO);
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao realizar a consulta");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
