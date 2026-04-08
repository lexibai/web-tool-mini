package com.webtool.controller;

import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/base64")
public class Base64ToolController {

    @PostMapping("/encode")
    public ResponseEntity<ApiResponse<Map<String, String>>> encode(@RequestBody Map<String, String> request) {
        String input = request.get("value");
        if (input == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'value' field"));
        }
        String encoded = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok(ApiResponse.ok(Map.of("original", input, "encoded", encoded)));
    }

    @PostMapping("/decode")
    public ResponseEntity<ApiResponse<Map<String, String>>> decode(@RequestBody Map<String, String> request) {
        String input = request.get("value");
        if (input == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'value' field"));
        }
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(input);
            String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
            return ResponseEntity.ok(ApiResponse.ok(Map.of("original", input, "decoded", decoded)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid Base64 string: " + e.getMessage()));
        }
    }
}
