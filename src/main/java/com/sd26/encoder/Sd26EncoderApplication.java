package com.sd26.encoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the SD26 Encoder Web Application.
 * <p>
 * This Spring Boot application provides a secure web interface
 * for encoding and decoding text using Apache Commons Codec (Hex and Base64).
 * </p>
 */
@SpringBootApplication
public class Sd26EncoderApplication {

    /**
     * Launches the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Sd26EncoderApplication.class, args);
    }
}
