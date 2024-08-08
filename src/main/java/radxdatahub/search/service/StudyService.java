package radxdatahub.search.service;

import radxdatahub.search.entity.FacetDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface StudyService {

    String searchStudies(String query, String adv, List<FacetDTO> studyFilterQuery, Integer page, Integer size, String prop, String sort);

    void convertSearchStringToCSV(HttpServletResponse response, String s);

    String searchAutocomplete(String query);

}
