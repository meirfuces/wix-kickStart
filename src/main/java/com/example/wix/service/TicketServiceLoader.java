package com.example.wix.service;

import com.example.wix.entities.TicketEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_PATH = "src/main/resources/data.json";
    public static List<TicketEntity> loadEntities() {

        try {
            return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<TicketEntity>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list if there is an error
    }
}
