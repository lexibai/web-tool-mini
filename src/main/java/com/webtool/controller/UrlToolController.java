package com.webtool.controller;

import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
public class UrlToolController {

    @PostMapping("/encode")
    public ResponseEntity<ApiResponse<Map<String, String>>> encode(@RequestBody Map<String, String> request) {
        String input = request.get("value");
        if (input == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'value' field"));
        }
        String encoded = URLEncoder.encode(input, StandardCharsets.UTF_8);
        return ResponseEntity.ok(ApiResponse.ok(Map.of("original", input, "encoded", encoded)));
    }

    @PostMapping("/decode")
    public ResponseEntity<ApiResponse<Map<String, String>>> decode(@RequestBody Map<String, String> request) {
        String input = request.get("value");
        if (input == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'value' field"));
        }
        try {
            String decoded = URLDecoder.decode(input, StandardCharsets.UTF_8);
            return ResponseEntity.ok(ApiResponse.ok(Map.of("original", input, "decoded", decoded)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid URL-encoded string: " + e.getMessage()));
        }
    }
}
