package com.mgiglione.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.mgiglione.model.MangaResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgiglione.model.Manga;
import com.mgiglione.service.MangaService;

@RestController
@RequestMapping(value = "/manga")
public class MangaController {

    Logger logger = LoggerFactory.getLogger(MangaController.class);

    @Autowired
    private MangaService mangaService;   

    @RequestMapping(value = "/async/{title}", method = RequestMethod.GET)
    @Async
    public CompletableFuture<Manga> searchASync(@PathVariable(name = "title") String title) {
        logger.info(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(mangaService.getMangasByTitle(title));
    }
    @RequestMapping(value = "/async2/{title}", method = RequestMethod.GET)
    @Async
    public CompletableFuture<Manga> searchASync2(@PathVariable(name = "title") String title) {
        logger.info(Thread.currentThread().getName());
        return CompletableFuture.completedFuture(mangaService.getMangasByTitle(title));
    }

    @RequestMapping(value = "/sync/{title}", method = RequestMethod.GET)
    public @ResponseBody Manga searchSync(@PathVariable(name = "title") String title) {
        logger.info(Thread.currentThread().getName());

        return mangaService.getMangasByTitle(title);
    }

}
