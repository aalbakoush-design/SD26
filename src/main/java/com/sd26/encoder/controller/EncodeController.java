package com.sd26.encoder.controller;

import com.sd26.encoder.service.CodecService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for the encoding/decoding application.
 * <p>
 * Provides endpoints for the web UI to encode and decode text
 * using Hex and Base64 formats.
 * </p>
 */
@Controller
public class EncodeController {

    private final CodecService codecService;

    /**
     * Constructs the controller with the required CodecService.
     *
     * @param codecService the service for encoding/decoding operations
     */
    public EncodeController(CodecService codecService) {
        this.codecService = codecService;
    }

    /**
     * Displays the main page of the application.
     *
     * @return the view name for the index page
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handles encoding requests from the web form.
     *
     * @param input    the text to encode
     * @param type     the encoding type ("hex" or "base64")
     * @param model    the Spring MVC model
     * @return the view name with result attributes
     */
    @PostMapping("/encode")
    public String encode(
            @RequestParam("input") String input,
            @RequestParam("type") String type,
            Model model) {
        try {
            String result;
            if ("hex".equalsIgnoreCase(type)) {
                result = codecService.encodeHex(input);
            } else if ("base64".equalsIgnoreCase(type)) {
                result = codecService.encodeBase64(input);
            } else {
                model.addAttribute("error", "Invalid encoding type. Use 'hex' or 'base64'.");
                model.addAttribute("input", input);
                return "index";
            }
            model.addAttribute("result", result);
            model.addAttribute("input", input);
            model.addAttribute("operation", "encode");
            model.addAttribute("type", type);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("input", input);
        }
        return "index";
    }

    /**
     * Handles decoding requests from the web form.
     *
     * @param input    the text to decode
     * @param type     the decoding type ("hex" or "base64")
     * @param model    the Spring MVC model
     * @return the view name with result attributes
     */
    @PostMapping("/decode")
    public String decode(
            @RequestParam("input") String input,
            @RequestParam("type") String type,
            Model model) {
        try {
            String result;
            if ("hex".equalsIgnoreCase(type)) {
                result = codecService.decodeHex(input);
            } else if ("base64".equalsIgnoreCase(type)) {
                result = codecService.decodeBase64(input);
            } else {
                model.addAttribute("error", "Invalid decoding type. Use 'hex' or 'base64'.");
                model.addAttribute("input", input);
                return "index";
            }
            model.addAttribute("result", result);
            model.addAttribute("input", input);
            model.addAttribute("operation", "decode");
            model.addAttribute("type", type);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("input", input);
        }
        return "index";
    }
}
