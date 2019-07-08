package com.code4ro.legalconsultation.login.service;

import com.code4ro.legalconsultation.login.exception.ValidationException;
import com.code4ro.legalconsultation.login.model.ApplicationUser;
import com.code4ro.legalconsultation.login.payload.SignUpRequest;
import com.code4ro.legalconsultation.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApplicationUser save(SignUpRequest signUpRequest) throws ValidationException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ValidationException("Duplicate username!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ValidationException("Duplicate email!");
        }
        ApplicationUser applicationUser = new ApplicationUser(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        return userRepository.save(applicationUser);
    }

}
