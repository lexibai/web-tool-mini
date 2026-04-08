package com.webtool.controller;

import com.webtool.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;

@RestController
@RequestMapping("/api/hash")
public class HashToolController {

    @PostMapping
    public ResponseEntity<ApiResponse<Map<String, String>>> hash(@RequestBody Map<String, String> request) {
        String input = request.get("value");
        if (input == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Missing 'value' field"));
        }
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        Map<String, String> result = Map.of(
                "md5", digest(bytes, "MD5"),
                "sha1", digest(bytes, "SHA-1"),
                "sha256", digest(bytes, "SHA-256")
        );
        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    private String digest(byte[] input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return HexFormat.of().formatHex(md.digest(input));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Hash algorithm not available: " + algorithm, e);
        }
    }
}
