package radxdatahub.search.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import radxdatahub.search.entity.FacetDTO;
import radxdatahub.search.util.RequestValidator;
import radxdatahub.search.service.StudyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studies")
public class StudyController {

    private final StudyService studyService;

    private TypeReference<List<FacetDTO>> typeReference;
    private ObjectMapper mapper;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
        typeReference = new TypeReference<List<FacetDTO>>() {};
        mapper = new ObjectMapper();
    }

    @Validated
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchStudies(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "", required = false) String adv,
            @RequestParam(defaultValue = "[]") String facets,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "title") String prop,
            @RequestParam(defaultValue = "asc") String sort)
            throws Exception {
        RequestValidator.validateStringRequestParams(List.of(q, adv, facets, prop, sort));
        List<FacetDTO> facetsList = mapper.readValue(facets, typeReference);
        return new ResponseEntity<>(
                studyService.searchStudies(q, adv, facetsList, page, size, prop,  sort),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/csv", produces = MediaType.APPLICATION_JSON_VALUE)
    public void searchStudiesToCSV(HttpServletResponse response,
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "", required = false) String adv,
            @RequestParam(defaultValue = "[]") String facets,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "title") String prop,
            @RequestParam(defaultValue = "asc") String sort)
            throws Exception {
        RequestValidator.validateStringRequestParams(List.of(q, adv, facets, prop, sort));
        List<FacetDTO> facetsList = mapper.readValue(facets, typeReference);

        // size is hard-coded to 999 to show all results by default 
        String esResult = studyService.searchStudies(q, adv, facetsList, page, 999, prop,  sort);
        studyService.convertSearchStringToCSV(response, esResult);
    }

    @GetMapping(value = "/autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchAutocomplete(@RequestParam(defaultValue = "") String q) {
        RequestValidator.validateStringRequestParams(List.of(q));
        return ResponseEntity.ok(studyService.searchAutocomplete(q));
    }

}
