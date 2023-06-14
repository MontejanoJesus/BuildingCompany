package org.example.buildingcompany.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.buildingcompany.classes.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParsing {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        // Deserialize
        String json = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\employees.json")));
        Employee employee = objectMapper.readValue(json, Employee.class);

        // Serialize
        objectMapper.writeValue(new File("src\\main\\resources\\write.json"), employee);

    }
}
