package com.cambian.emrfhirstub;

import com.cambian.emrfhirstub.exception.StubException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmrFhirStub {

    @Value("${emr_server}")
    private String emr_server;

    @GetMapping("/Practitioner/{id}")
    public Map<String, Object> practitioner(@PathVariable(value = "id") String providerId) {
        /*
        TODO: if connect to real EMR (e.g. OSCAR Classic), uncomment the below lines to call EMR endpoint to get data
        RestTemplate template = new RestTemplate();
        String url = emr_server + "/{endpoint}";
        Map<String, Object> map = template.getForObject(url, Map.class);

        return map;
        */

        try {
            String file = "src/main/resources/static/provider.json";
            String json = readFileAsString(file);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map;
        } catch (Exception e) {
            throw new StubException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/Patient/{id}")
    public Map<String, Object> patient(@PathVariable(value = "id") String clientId) {
        System.out.println("handling /Patient/" + clientId);

        /*
        TODO: if connect to real EMR (e.g. OSCAR Classic), uncomment the below lines to call EMR endpoint to get data
        RestTemplate template = new RestTemplate();
        String url = emr_server + "/{endpoint}";
        Map<String, Object> map = template.getForObject(url, Map.class);

        return map;
        */

        try {
            String file = "src/main/resources/static/client.json";
            String json = readFileAsString(file);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map;
        } catch (Exception e) {
            throw new StubException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Temporary helper method to read mocked json response from file. Can be removed once connect to real EMR.
     *
     * @param file
     * @return
     * @throws Exception
     */
    private String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

}
