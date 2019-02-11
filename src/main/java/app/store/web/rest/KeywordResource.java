package app.store.web.rest;


import app.store.service.KeywordService;
import app.store.service.dto.KeywordDto;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.PaginationUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class KeywordResource {
    private final Logger log = LoggerFactory.getLogger(KeywordResource.class);

    private final KeywordService keywordService;

    public KeywordResource(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/keyword")
    public ResponseEntity<KeywordDto> getKeywordWithName(@Valid @RequestBody String name) {
        log.debug("REST request to get Keyword by name : {}", name);
        Optional<KeywordDto> result = keywordService.getKeywordByName(name);
        return ResponseUtil.wrapOrNotFound(result,
                HeaderUtil.createAlert("keyword.get", result.get().getId()));
    }


    @PostMapping("/keyword")
    public ResponseEntity<String> createKeyword(@Valid @RequestBody KeywordDto keywordDto) throws URISyntaxException {
        log.debug("REST request to save Keyword: {}", keywordDto);
        String keywordId = keywordService.createKeyword(keywordDto);
        return ResponseEntity.created(new URI("/api/keyword/" + keywordId))
                .headers(HeaderUtil.createAlert("keyword.created", ""))
                .body(keywordId);
    }


    @PutMapping("/keyword/{id}")
    public ResponseEntity<KeywordDto> updateKeyword(@Valid @RequestBody KeywordDto keywordDto, @Valid @PathVariable String id) throws URISyntaxException {
        log.debug("REST request to update Keyword by id: {}", id);
        Optional<KeywordDto> result = keywordService.updateKeyword(keywordDto, id);
        return ResponseEntity.created(new URI("/api/keyword/" + result.get().getId()))
                .headers(HeaderUtil.createAlert("keyword.updated", ""))
                .body(result.get());
    }

    @DeleteMapping("/keyword/{id}")
    public ResponseEntity<String> deleteKeyword(@Valid @PathVariable String id) {
        log.debug("REST request to delete Keyword by id: {}", id);
        keywordService.deleteKeyword(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("product.deleted", id))
                .build();
    }

    @GetMapping("/keyword/list")
    public ResponseEntity<List<KeywordDto>> getKeywordByList(@Valid @RequestBody List<String> names) throws URISyntaxException {
        log.debug("REST request to get Keyword by list name: {}", names);
        Optional<List<KeywordDto>> result = keywordService.getKeywordByName(names);
        return ResponseEntity.created(new URI("/api/keyword/" + ""))
                .headers(HeaderUtil.createAlert("keywords.getlist", ""))
                .body(result.get());
    }

    @GetMapping("/keyword/all")
    public ResponseEntity<List<KeywordDto>> getKeywordByList(Pageable pageable) {
        log.debug("REST request to get all Keyword by pageable: {}", pageable);
        Page<KeywordDto> keywords = keywordService.getKeywords(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(keywords, "/api/keyword");
        return new ResponseEntity<>(keywords.getContent(), headers, HttpStatus.OK);
    }


}
