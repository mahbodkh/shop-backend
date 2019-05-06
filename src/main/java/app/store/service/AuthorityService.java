package app.store.service;

import app.store.persistence.repository.AuthorityRepository;
import app.store.service.dto.AuthorityDto;
import app.store.service.mapper.AuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    private final Logger log = LoggerFactory.getLogger(BankService.class);

    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;


    public boolean isExistByName(String name) {
        return authorityRepository.existsById(name);
    }

    public void createAuthority(AuthorityDto authorityDto) {
        authorityRepository.save(authorityMapper.toEntity(authorityDto));
    }
}
