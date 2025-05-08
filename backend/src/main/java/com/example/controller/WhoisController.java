package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.WhoisLookupService;

@RestController
@RequestMapping("/api/whois")
@CrossOrigin(origins = "*")
public class WhoisController {

    @Autowired
    private WhoisLookupService whoisLookupService;

    @GetMapping
    public ResponseEntity<?> getWhoisData(@RequestParam String domain, @RequestParam String type) {
        try {
            String rawJson = whoisLookupService.fetchWhoisData(domain);

            if ("domain".equals(type)) {
                return ResponseEntity.ok(whoisLookupService.parseDomainInfo(rawJson));
            } else if ("contact".equals(type)) {
                return ResponseEntity.ok(whoisLookupService.parseContactInfo(rawJson));
            } else {
                return ResponseEntity.badRequest().body("Invalid type.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
