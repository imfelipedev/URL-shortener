package com.zfelpszada.url_shortener.services;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.zfelpszada.url_shortener.domain.url.Url;
import com.zfelpszada.url_shortener.dtos.url.ShortenRequestDTO;
import com.zfelpszada.url_shortener.dtos.url.GetRequestDTO;
import com.zfelpszada.url_shortener.repositories.UrlRepository;

@Service
public class UrlService {
    private final UrlRepository repository;
    
    private static final int URL_LENGTH = 5;

    private static final char[] ALPHANUMERIC = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public Url getFromShortened(GetRequestDTO params) {
        String shortened = params.shortened();
        Optional<Url> optionalUrl = repository.findByShortened(shortened);
        if ( !optionalUrl.isPresent() ) {
            throw new RuntimeException("URL not found");
        }

        return optionalUrl.get();
    }

    public Url shorten(ShortenRequestDTO body) {
        String original = body.url();
        String shortened = this.getRandomUrl();
        Url url = new Url();
        url.setOriginal(original);
        url.setShortened(shortened);
        repository.save(url);
        return url;
    }

    private String getRandomUrl() {
        String url = "";
        Random random = getRandom();
        for (int i = 0; i < URL_LENGTH; i++) {
            int index = random.nextInt(ALPHANUMERIC.length);
            url += ALPHANUMERIC[index];
        }

        return url;
    }

    private Random getRandom() {
        Long timestamp = new Date().getTime();
        return new Random(timestamp);
    }
}