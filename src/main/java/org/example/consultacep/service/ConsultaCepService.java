package org.example.consultacep.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.example.consultacep.dto.ConsultaCepDTO;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class ConsultaCepService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(ConsultaCepService.class);
    ConsultaCepDTO consultaCepDTO = new ConsultaCepDTO();
    public ConsultaCepDTO getConsultaCepDTO(String cep) throws IOException, InterruptedException{


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        ObjectMapper mapper = new ObjectMapper();
        consultaCepDTO = mapper.readValue(response.body(), ConsultaCepDTO.class);
        logger.info("Consulta realizada com sucesso");

        return consultaCepDTO;
    }
}
