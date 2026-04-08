package com.webtool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebToolMiniApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // JSON tool tests
    @Test
    void jsonFormat_validJson_returnsFormatted() throws Exception {
        mockMvc.perform(post("/api/json/format")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"key\":\"value\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isString());
    }

    @Test
    void jsonFormat_invalidJson_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/json/format")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{invalid}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void jsonValidate_validJson_returnsValid() throws Exception {
        mockMvc.perform(post("/api/json/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.valid").value(true));
    }

    @Test
    void jsonValidate_invalidJson_returnsInvalid() throws Exception {
        mockMvc.perform(post("/api/json/validate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{bad json}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.valid").value(false));
    }

    // URL tool tests
    @Test
    void urlEncode_returnsEncodedValue() throws Exception {
        mockMvc.perform(post("/api/url/encode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"hello world\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.encoded").value("hello+world"));
    }

    @Test
    void urlDecode_returnsDecodedValue() throws Exception {
        mockMvc.perform(post("/api/url/decode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"hello+world\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.decoded").value("hello world"));
    }

    // Base64 tool tests
    @Test
    void base64Encode_returnsEncodedValue() throws Exception {
        mockMvc.perform(post("/api/base64/encode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.encoded").value("aGVsbG8="));
    }

    @Test
    void base64Decode_returnsDecodedValue() throws Exception {
        mockMvc.perform(post("/api/base64/decode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"aGVsbG8=\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.decoded").value("hello"));
    }

    @Test
    void base64Decode_invalidInput_returnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/base64/decode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"!!!invalid!!!\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    // UUID tool tests
    @Test
    void uuidGenerate_returnsUuid() throws Exception {
        mockMvc.perform(get("/api/uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uuid").isString())
                .andExpect(jsonPath("$.data.uuid", matchesPattern(
                        "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")));
    }

    @Test
    void uuidBatch_returnsMultipleUuids() throws Exception {
        mockMvc.perform(get("/api/uuid/batch?count=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.count").value(3))
                .andExpect(jsonPath("$.data.uuids", hasSize(3)));
    }

    @Test
    void uuidGenerate_multipleCallsReturnUniqueValues() throws Exception {
        Set<String> uuids = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            String response = mockMvc.perform(get("/api/uuid"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            // extract the uuid value from JSON
            String uuid = response.replaceAll(".*\"uuid\":\"([^\"]+)\".*", "$1");
            uuids.add(uuid);
        }
        assertEquals(10, uuids.size(), "Each generated UUID should be unique");
    }

    // Timestamp tool tests
    @Test
    void timestampNow_returnsCurrentTime() throws Exception {
        mockMvc.perform(get("/api/timestamp/now"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.epochMillis").isNumber())
                .andExpect(jsonPath("$.data.epochSeconds").isNumber())
                .andExpect(jsonPath("$.data.utc").isString());
    }

    @Test
    void timestampConvert_secondsInput_returnsConverted() throws Exception {
        mockMvc.perform(post("/api/timestamp/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"timestamp\":0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.utc").value("1970-01-01 00:00:00"));
    }

    // Hash tool tests
    @Test
    void hash_returnsAllHashes() throws Exception {
        mockMvc.perform(post("/api/hash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.md5").value("5d41402abc4b2a76b9719d911017c592"))
                .andExpect(jsonPath("$.data.sha1").value("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d"))
                .andExpect(jsonPath("$.data.sha256").value(
                        "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824"));
    }
}
