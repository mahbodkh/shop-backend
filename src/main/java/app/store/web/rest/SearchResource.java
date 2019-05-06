package app.store.web.rest;


import app.store.service.SearchService;
import app.store.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchResource {
    private final Logger log = LoggerFactory.getLogger(SearchResource.class);

    private final SearchService searchService;


    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List> getProductSearch(@Valid @RequestParam("data") String value) {
        log.debug("");
        List simpleQuery = searchService.getSimpleQuery(value);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("search.query", value)).body(simpleQuery);
    }
}
