package radxdatahub.search.controller;

import radxdatahub.search.service.StudyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudyService studyService;

    @Test
    public void getStudiesWithDefaultShouldReturnStudies() throws Exception{
        String mockSearchResponse = "{\"response\": \"mock response\"}";
        when(studyService.searchStudies(
                anyString(), anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(mockSearchResponse);
        this.mockMvc.perform(get("/studies")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getStudiesWithTextParameterShouldReturnStudies() throws Exception{
        String mockSearchResponse = "mock response";
        when(studyService.searchStudies(
                anyString(), anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(mockSearchResponse);
        this.mockMvc.perform(get("/studies?q=test")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getStudiesWithPaginationParameterShouldReturnStudies() throws Exception{
        String mockSearchResponse = "{\"response\": \"mock response\"}";
        when(studyService.searchStudies(
                anyString(), anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(mockSearchResponse);
        this.mockMvc.perform(
                get("/studies?page=1&size=20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getStudiesWithSortingParameterShouldReturnStudies() throws Exception{
        String mockSearchResponse = "{\"response\": \"mock response\"}";
        when(studyService.searchStudies(
                anyString(), anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
                .thenReturn(mockSearchResponse);
        this.mockMvc.perform(
                        get("/studies?prop=phs&asc=false"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void getStudiesWithFilterParameterShouldReturnStudies() throws Exception{
//        String mockSearchResponse = "{\"response\": \"mock response\"}";
//        when(studyService.searchStudies(
//                anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
//                .thenReturn(mockSearchResponse);
//        this.mockMvc.perform(
//                        get("/studies?facets=%5B%7B%22name%22:%22status%22,%22facets%22:%5B%22draft%22,%20%22submitted%22%5D%7D%5D"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }

//    @Test
//    public void getStudiesWithFilterParameterShouldReturnStudies() throws Exception{
//        String mockSearchResponse = "{\"response\": \"mock response\"}";
//        when(studyService.searchStudies(
//                anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
//                .thenReturn(mockSearchResponse);
//        this.mockMvc.perform(
//                        get("/studies?facets=[{\"name\":\"status\",\"facets\":[\"draft\",\"submitted\"]}]"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }

//    @Test
//    public void getStudiesWithFilterParameterShouldReturnStudies() throws Exception{
//        String mockSearchResponse = "{\"response\": \"mock response\"}";
//        when(studyService.searchStudies(
//                anyString(), anyList(), anyInt(), anyInt(), anyString(), anyString()))
//                .thenReturn(mockSearchResponse);
//        this.mockMvc.perform(
//                        get("/studies?facets=[{name:status,facets:[draft,submitted]}]"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }

}
