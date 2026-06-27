package com.sd26.encoder.service;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * Service layer providing encoding and decoding operations
 * using Apache Commons Codec.
 * <p>
 * Supports Hex (hexadecimal) and Base64 encoding/decoding.
 * All operations use UTF-8 charset for string conversion.
 * </p>
 */
@Service
public class CodecService {

    /**
     * Encodes a plain text string into a hexadecimal string.
     *
     * @param input the plain text to encode (must not be null)
     * @return the hexadecimal encoded string
     * @throws IllegalArgumentException if input is null
     */
    public String encodeHex(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return Hex.encodeHexString(bytes);
    }

    /**
     * Decodes a hexadecimal string back to plain text.
     *
     * @param hexInput the hexadecimal string to decode (must not be null)
     * @return the decoded plain text string
     * @throws IllegalArgumentException if hexInput is null or invalid hex data
     */
    public String decodeHex(String hexInput) {
        if (hexInput == null) {
            throw new IllegalArgumentException("Hex input must not be null");
        }
        try {
            byte[] decodedBytes = Hex.decodeHex(hexInput);
            return new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Invalid hexadecimal input: " + e.getMessage(), e);
        }
    }

    /**
     * Encodes a plain text string into a Base64 string.
     *
     * @param input the plain text to encode (must not be null)
     * @return the Base64 encoded string
     * @throws IllegalArgumentException if input is null
     */
    public String encodeBase64(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Decodes a Base64 string back to plain text.
     *
     * @param base64Input the Base64 string to decode (must not be null)
     * @return the decoded plain text string
     * @throws IllegalArgumentException if base64Input is null or invalid Base64 data
     */
    public String decodeBase64(String base64Input) {
        if (base64Input == null) {
            throw new IllegalArgumentException("Base64 input must not be null");
        }
        if (!Base64.isBase64(base64Input)) {
            throw new IllegalArgumentException("Invalid Base64 input: string is not valid Base64 encoded data");
        }
        byte[] decodedBytes = Base64.decodeBase64(base64Input);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
