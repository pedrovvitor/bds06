package com.devsuperior.movieflix.resourcers;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getProfileInfo() {
        UserDTO dto = userService.getProfileInfo();
        return ResponseEntity.ok().body(dto);
    }
}
