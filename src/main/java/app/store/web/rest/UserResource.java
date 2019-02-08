package app.store.web.rest;


import app.store.persistence.domain.User;
import app.store.persistence.repository.UserRepository;
import app.store.service.UserService;
import app.store.service.dto.UserDto;
import app.store.web.rest.error.*;
import app.store.web.rest.util.HeaderUtil;
import app.store.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    private final UserRepository userRepository;

    public UserResource(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDto);
        if (userDto.getMobile() == null)
            throw new MobileNotFoundException();
        else if (userService.isMobileExists(userDto.getMobile()).isPresent())
            throw new MobileAlreadyUsedException();
        else if (userDto.getEmail() == null)
            throw new EmailNotFoundException();
        else if (userService.isEmailExists(userDto.getEmail()).isPresent())
            throw new EmailAlreadyUsedException();
        else {
            Optional<User> user = userService.createUser(userDto);
            //todo send email and sms (notification)

            return ResponseEntity.created(new URI("/api/user/" + user.get().getId()))
                    .headers(HeaderUtil.createAlert("userManagement.created", user.get().getLogin()))
                    .build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        log.debug("REST request to get User : {}", id);
        return ResponseUtil.wrapOrNotFound(
                userService.getUser(id)
                        .map(UserDto::new));
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateCart(@Valid @RequestBody UserDto userDto, @PathVariable String id) {
        log.debug("REST request to update User : {} with id : {}", userDto, id);
        if (id == null)
            throw new BadRequestAlertException("User ID is null", "UserDto", "updateUser");
        else if (userDto == null || !userService.isExists(id))
            throw new CartNotFoundException();
        else {
            Optional<UserDto> result = userService.updateUser(userDto, id);
            UserDto existingUser = result.get();
            if (result.isPresent() && (!existingUser.getMobile().equals(userService.isMobileExists(existingUser.getMobile())))) {
                throw new MobileAlreadyUsedException();
            }
            Optional<User> resultEmail = userRepository.findOneByEmailIgnoreCase(existingUser.getEmail().toLowerCase());
            if (resultEmail.isPresent() && (!resultEmail.get().getId().equals(resultEmail.get().getEmail()))) {
                throw new EmailAlreadyUsedException();
            }
            return ResponseUtil.wrapOrNotFound(result,
                    HeaderUtil.createAlert("userManagement.updated", userDto.getMobile().toString()));
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        log.debug("REST request to delete User : {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("user.deleted", id)).build();
    }


}
