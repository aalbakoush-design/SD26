package com.sd26.encoder.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CodecService Unit Tests")
class CodecServiceTest {

    private final CodecService service = new CodecService();

    // ======== HEX ENCODING ========
    @Nested
    @DisplayName("Hex Encoding")
    class HexEncodingTests {
        @Test void testEncodeHex_plain() {
            assertEquals("48656c6c6f", service.encodeHex("Hello"));
        }
        @Test void testEncodeHex_empty() {
            assertEquals("", service.encodeHex(""));
        }
        @Test void testEncodeHex_special() {
            assertTrue(service.encodeHex("SD26!@#$%").matches("[0-9a-fA-F]+"));
        }
        @Test void testEncodeHex_unicode() {
            assertTrue(service.encodeHex("مرحبا").length() > 0);
        }
        @Test void testEncodeHex_null() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.encodeHex(null));
        }
    }

    // ======== HEX DECODING ========
    @Nested
    @DisplayName("Hex Decoding")
    class HexDecodingTests {
        @Test void testDecodeHex_valid() {
            assertEquals("Hello", service.decodeHex("48656c6c6f"));
        }
        @Test void testDecodeHex_empty() {
            assertEquals("", service.decodeHex(""));
        }
        @Test void testDecodeHex_null() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.decodeHex(null));
        }
        @Test void testDecodeHex_invalid() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.decodeHex("ZZXXYY"));
        }
        @Test void testDecodeHex_oddLength() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.decodeHex("486"));
        }
    }

    // ======== BASE64 ENCODING ========
    @Nested
    @DisplayName("Base64 Encoding")
    class Base64EncodingTests {
        @Test void testEncodeBase64_plain() {
            assertEquals("SGVsbG8=", service.encodeBase64("Hello"));
        }
        @Test void testEncodeBase64_empty() {
            assertEquals("", service.encodeBase64(""));
        }
        @Test void testEncodeBase64_special() {
            assertTrue(org.apache.commons.codec.binary.Base64.isBase64(
                    service.encodeBase64("SD26!@#$%")));
        }
        @Test void testEncodeBase64_null() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.encodeBase64(null));
        }
    }

    // ======== BASE64 DECODING ========
    @Nested
    @DisplayName("Base64 Decoding")
    class Base64DecodingTests {
        @Test void testDecodeBase64_valid() {
            assertEquals("Hello", service.decodeBase64("SGVsbG8="));
        }
        @Test void testDecodeBase64_empty() {
            assertEquals("", service.decodeBase64(""));
        }
        @Test void testDecodeBase64_null() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.decodeBase64(null));
        }
        @Test void testDecodeBase64_invalid() {
            assertThrows(IllegalArgumentException.class,
                    () -> service.decodeBase64("Not valid Base64!!!"));
        }
    }

    // ======== ROUNDTRIP INTEGRITY ========
    @Nested
    @DisplayName("Roundtrip Integrity")
    class RoundtripTests {
        @ParameterizedTest(name = "Hex: [{0}]")
        @ValueSource(strings = {"Hello","SD26","","αβγ","!@#$","  sp  "})
        @DisplayName("Hex encode->decode preserves original")
        void hexRoundtrip(String s) {
            assertEquals(s, service.decodeHex(service.encodeHex(s)));
        }

        @ParameterizedTest(name = "B64: [{0}]")
        @ValueSource(strings = {"Hello","SD26","","αβγ","!@#$","  sp  "})
        @DisplayName("Base64 encode->decode preserves original")
        void base64Roundtrip(String s) {
            assertEquals(s, service.decodeBase64(service.encodeBase64(s)));
        }
    }
}
