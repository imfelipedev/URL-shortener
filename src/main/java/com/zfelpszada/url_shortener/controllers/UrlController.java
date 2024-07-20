package com.zfelpszada.url_shortener.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfelpszada.url_shortener.domain.url.Url;
import com.zfelpszada.url_shortener.dtos.url.GetRequestDTO;
import com.zfelpszada.url_shortener.dtos.url.ShortenRequestDTO;
import com.zfelpszada.url_shortener.dtos.url.ShortenResponseDTO;
import com.zfelpszada.url_shortener.services.UrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/")
public class UrlController {
    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/redirect/{params}")
    public ResponseEntity<Void> redirect(@PathVariable GetRequestDTO params) {
        Url url = service.getFromShortened(params);
        URI redirect = URI.create(url.getOriginal());
        return ResponseEntity.status(HttpStatus.FOUND).location(redirect).build();
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponseDTO> shorten(@RequestBody ShortenRequestDTO body) {
        Url url = service.shorten(body);
        String urlShortened = "http://localhost:8080/redirect/" + url.getShortened();
        ShortenResponseDTO response = new ShortenResponseDTO(url.getId(), url.getOriginal(), urlShortened);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
