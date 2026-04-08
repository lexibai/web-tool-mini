package com.webtool.controller;

import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/timestamp")
public class TimestampToolController {

    private static final DateTimeFormatter ISO_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));

    @GetMapping("/now")
    public ResponseEntity<ApiResponse<Map<String, Object>>> now() {
        long epochMillis = System.currentTimeMillis();
        long epochSeconds = epochMillis / 1000;
        String formatted = ISO_FORMATTER.format(Instant.ofEpochMilli(epochMillis));
        return ResponseEntity.ok(ApiResponse.ok(Map.of(
                "epochMillis", epochMillis,
                "epochSeconds", epochSeconds,
                "utc", formatted
        )));
    }

    @PostMapping("/convert")
    public ResponseEntity<ApiResponse<Map<String, Object>>> convert(@RequestBody Map<String, Object> request) {
        Object tsObj = request.get("timestamp");
        if (tsObj == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'timestamp' field"));
        }
        try {
            long epochMillis;
            long value = Long.parseLong(tsObj.toString());
            // Distinguish between seconds and milliseconds: seconds are < 10^11
            if (value < 100_000_000_000L) {
                epochMillis = value * 1000;
            } else {
                epochMillis = value;
            }
            String formatted = ISO_FORMATTER.format(Instant.ofEpochMilli(epochMillis));
            return ResponseEntity.ok(ApiResponse.ok(Map.of(
                    "epochMillis", epochMillis,
                    "epochSeconds", epochMillis / 1000,
                    "utc", formatted
            )));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Invalid timestamp: must be a numeric value"));
        }
    }
}
