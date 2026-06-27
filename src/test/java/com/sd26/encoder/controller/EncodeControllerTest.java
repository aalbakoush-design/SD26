package com.sd26.encoder.controller;

import com.sd26.encoder.service.CodecService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("EncodeController Unit Tests")
class EncodeControllerTest {

    @Mock
    private CodecService codecService;

    @Mock
    private Model model;

    @InjectMocks
    private EncodeController controller;

    @Test
    @DisplayName("GET / should return index view")
    void index_shouldReturnIndexView() {
        assertEquals("index", controller.index());
    }

    @Nested
    @DisplayName("POST /encode")
    class EncodeEndpointTests {

        @Test
        @DisplayName("encode with hex type")
        void encode_withHexType() {
            when(codecService.encodeHex("Hello")).thenReturn("48656c6c6f");
            String view = controller.encode("Hello", "hex", model);
            assertEquals("index", view);
            verify(model).addAttribute("result", "48656c6c6f");
            verify(model).addAttribute("operation", "encode");
        }

        @Test
        @DisplayName("encode with base64 type")
        void encode_withBase64Type() {
            when(codecService.encodeBase64("Hello")).thenReturn("SGVsbG8=");
            String view = controller.encode("Hello", "base64", model);
            assertEquals("index", view);
            verify(model).addAttribute("result", "SGVsbG8=");
            verify(model).addAttribute("operation", "encode");
        }

        @Test
        @DisplayName("encode with uppercase HEX type")
        void encode_withHexType_uppercase() {
            when(codecService.encodeHex("Test")).thenReturn("54657374");
            String view = controller.encode("Test", "HEX", model);
            assertEquals("index", view);
            verify(model).addAttribute("result", "54657374");
        }

        @Test
        @DisplayName("encode with invalid type returns error")
        void encode_withInvalidType() {
            String view = controller.encode("Hello", "invalid", model);
            assertEquals("index", view);
            verify(model).addAttribute(eq("error"), contains("Invalid encoding type"));
        }

        @Test
        @DisplayName("encode when service throws returns error")
        void encode_whenServiceThrows() {
            when(codecService.encodeHex(anyString()))
                    .thenThrow(new IllegalArgumentException("Encoding failed"));
            String view = controller.encode("bad", "hex", model);
            assertEquals("index", view);
            verify(model).addAttribute(eq("error"), eq("Encoding failed"));
        }
    }

    @Nested
    @DisplayName("POST /decode")
    class DecodeEndpointTests {

        @Test
        @DisplayName("decode with hex type")
        void decode_withHexType() {
            when(codecService.decodeHex("48656c6c6f")).thenReturn("Hello");
            String view = controller.decode("48656c6c6f", "hex", model);
            assertEquals("index", view);
            verify(model).addAttribute("result", "Hello");
            verify(model).addAttribute("operation", "decode");
        }

        @Test
        @DisplayName("decode with base64 type")
        void decode_withBase64Type() {
            when(codecService.decodeBase64("SGVsbG8=")).thenReturn("Hello");
            String view = controller.decode("SGVsbG8=", "base64", model);
            assertEquals("index", view);
            verify(model).addAttribute("result", "Hello");
            verify(model).addAttribute("operation", "decode");
        }

        @Test
        @DisplayName("decode with invalid type returns error")
        void decode_withInvalidType() {
            String view = controller.decode("48656c6c6f", "unsupported", model);
            assertEquals("index", view);
            verify(model).addAttribute(eq("error"), contains("Invalid decoding type"));
        }

        @Test
        @DisplayName("decode when service throws returns error")
        void decode_whenServiceThrows() {
            when(codecService.decodeHex(anyString()))
                    .thenThrow(new IllegalArgumentException("Decoding failed"));
            String view = controller.decode("ZZZZ", "hex", model);
            assertEquals("index", view);
            verify(model).addAttribute(eq("error"), eq("Decoding failed"));
        }
    }
}
