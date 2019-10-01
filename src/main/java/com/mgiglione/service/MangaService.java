package com.mgiglione.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mgiglione.model.Manga;
import com.mgiglione.model.MangaResult;

@Service
public class MangaService {

    Logger logger = LoggerFactory.getLogger(MangaService.class);
    private static final String MANGA_SEARCH_URL="http://localhost:8081/login";

    @Autowired
    RestTemplate restTemplate;
    
    
    public Manga getMangasByTitle(String title) {
        return restTemplate.getForObject(MANGA_SEARCH_URL, Manga.class);
    }

}
