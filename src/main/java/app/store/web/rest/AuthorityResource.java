package app.store.web.rest;


import app.store.service.AuthorityService;
import app.store.service.dto.AuthorityDto;
import app.store.web.rest.error.AuthorityAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthorityResource {
    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);


    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    private final AuthorityService authorityService;

    @PostMapping("/authority")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthority(AuthorityDto authorityDto) {
        log.debug("REST request to save authority : {}", authorityDto);
        if (authorityService.isExistByName(authorityDto.getName())) {
            throw new AuthorityAlreadyUsedException();
        }
        authorityService.createAuthority(authorityDto);
    }
}
