package com.mgiglione.service.test.unit;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.mgiglione.controller.MangaController;
import com.mgiglione.model.Manga;
import com.mgiglione.service.MangaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MangaControllerUnitTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    MangaController mangaController;

    @MockBean
    MangaService mangaService;
    
    /**
     * List of samples mangas
     */
    private   Manga  mangas;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.mangaController).build();// Standalone context


        mangas = new Manga();


    }

    @Test
    public void testSearchSync() throws Exception {
        
        // Mocking service
        when(mangaService.getMangasByTitle(any(String.class))).thenReturn(mangas);

        mockMvc.perform(get("/manga/sync/ken").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    }

    @Test
    public void testSearchASync() throws Exception {
       

        // Mocking service
        when(mangaService.getMangasByTitle(any(String.class))).thenReturn(mangas);

        MvcResult result = mockMvc.perform(get("/manga/async/ken").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(request().asyncStarted())
            .andDo(print())
            // .andExpect(status().is2xxSuccessful()).andReturn();
            .andReturn();

        // result.getRequest().getAsyncContext().setTimeout(10000);

        mockMvc.perform(asyncDispatch(result))
            .andDo(print())
            .andExpect(status().isOk());

    }
}
