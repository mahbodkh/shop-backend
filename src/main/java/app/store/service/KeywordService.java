package app.store.service;

import app.store.persistence.domain.Keyword;
import app.store.persistence.repository.KeywordRepository;
import app.store.service.dto.KeywordDto;
import app.store.service.mapper.KeywordMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class KeywordService {
    private final Logger log = LoggerFactory.getLogger(KeywordService.class);

    private final KeywordRepository keywordRepository;
    private final KeywordMapper keywordMapper;

    public KeywordService(KeywordRepository keywordRepository, KeywordMapper keywordMapper) {
        this.keywordRepository = keywordRepository;
        this.keywordMapper = keywordMapper;
    }


    public String createKeyword(KeywordDto keywordDto) {
        Keyword keyword = keywordMapper.toEntity(keywordDto);
        log.debug("Save Information for Keyword: {}", keywordDto);
        return keywordRepository.save(keyword).getId().toString();
    }

    public Optional<KeywordDto> getKeywordByName(String name) {
        return Optional.ofNullable(keywordRepository.findOneByName(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(keywordMapper::toDto);
    }

    public Optional<List<KeywordDto>> getKeywordByName(List<String> name) {
        return Optional.of(keywordRepository.findAllByName(name))
                .map(keyword -> {
                    try {
                        return keyword.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).map(keywordMapper::toDto);
    }

    public void deleteKeyword(String id) {
        keywordRepository.findOneById(new ObjectId(id)).ifPresent(keyword -> {

            // TODO: 2019-02-11 delete to keyword from all of products
            keywordRepository.delete(keyword);
            log.debug("Deleted Keyword: {}", keyword);
        });
    }

    public Optional<KeywordDto> updateKeyword(KeywordDto keywordDto, String id) {
        return Optional.of(keywordRepository.findOneById(new ObjectId(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(keyword -> {
                    keyword.setName(keywordDto.getName());
                    keyword.setDescription(keywordDto.getDescription());
                    keyword.setLanguage(keywordDto.getLanguage());

                    //
                    Keyword res = keywordRepository.save(keyword);
                    log.debug("Changed Information for Keyword: {}", keyword);
                    return res;
                }).map(keywordMapper::toDto);
    }

    public Page<KeywordDto> getKeywords(Pageable pageable) {
        return keywordRepository.findAll(pageable)
                .map(keywordMapper::toDto);
    }

}
