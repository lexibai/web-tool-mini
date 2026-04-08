package com.webtool.controller;

import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/uuid")
public class UuidToolController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, String>>> generate() {
        String uuid = UUID.randomUUID().toString();
        return ResponseEntity.ok(ApiResponse.ok(Map.of("uuid", uuid)));
    }

    @GetMapping("/batch")
    public ResponseEntity<ApiResponse<Map<String, Object>>> generateBatch(
            @RequestParam(defaultValue = "5") int count) {
        if (count < 1 || count > 100) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Count must be between 1 and 100"));
        }
        String[] uuids = new String[count];
        for (int i = 0; i < count; i++) {
            uuids[i] = UUID.randomUUID().toString();
        }
        return ResponseEntity.ok(ApiResponse.ok(Map.of("count", count, "uuids", uuids)));
    }
}
