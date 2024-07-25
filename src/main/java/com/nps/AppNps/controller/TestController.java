package com.nps.AppNps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/error")
    public String generateError() {
        throw new RuntimeException("Generated Error for Testing");
    }
}
