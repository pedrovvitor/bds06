package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    final UserRepository repository;
    final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.repository = userRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public UserDTO getProfileInfo() {
        User user = authService.authenticated();
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(java.lang.String userName) throws UsernameNotFoundException {
        return repository.findByEmail(userName).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }
}
