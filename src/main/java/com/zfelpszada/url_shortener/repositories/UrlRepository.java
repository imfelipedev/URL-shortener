package com.zfelpszada.url_shortener.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zfelpszada.url_shortener.domain.url.Url;

public interface UrlRepository extends JpaRepository<Url, String> {
    Optional<Url> findByShortened(String shortened);
}