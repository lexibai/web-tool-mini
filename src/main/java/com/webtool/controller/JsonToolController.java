package com.webtool.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/json")
public class JsonToolController {

    private final ObjectMapper objectMapper = new ObjectMapper().enable(
            com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);

    @PostMapping("/format")
    public ResponseEntity<ApiResponse<String>> format(@RequestBody String json) {
        try {
            Object parsed = objectMapper.readValue(json, Object.class);
            String formatted = objectMapper.writeValueAsString(parsed);
            return ResponseEntity.ok(ApiResponse.ok(formatted));
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid JSON: " + e.getOriginalMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse<Map<String, Object>>> validate(@RequestBody String json) {
        try {
            objectMapper.readValue(json, Object.class);
            return ResponseEntity.ok(ApiResponse.ok(Map.of("valid", true)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.ok(ApiResponse.ok(Map.of(
                    "valid", false,
                    "error", e.getOriginalMessage(),
                    "line", e.getLocation() != null ? e.getLocation().getLineNr() : -1,
                    "column", e.getLocation() != null ? e.getLocation().getColumnNr() : -1
            )));
        }
    }
}
