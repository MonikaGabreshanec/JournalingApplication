package com.finki.journalingapplication.service.impl;

import com.finki.journalingapplication.model.User;
import com.finki.journalingapplication.model.exceptionPassword.PasswordValidator;
import com.finki.journalingapplication.model.exceptionPassword.types.PasswordNotMatchException;
import com.finki.journalingapplication.model.exceptionPassword.types.Username0rPasswordDoesntMatchException;
import com.finki.journalingapplication.model.exceptionPassword.types.UsernameExistsException;
import com.finki.journalingapplication.model.exceptionPassword.types.UsernameInPasswordException;
import com.finki.journalingapplication.repository.UserRepository;
import com.finki.journalingapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return findById(id);
    }



    @Override
    public User registerAccount(String name, String surname, String username, String password, String rpassword) {
        if (!password.equals(rpassword)) throw new PasswordNotMatchException("The passwords didn't match!");
        PasswordValidator.isValid(password);
        if (userRepository.findUserByUsername(username.strip()).isPresent())
            throw new UsernameExistsException(String.format("%s exists, try different username", username));
        if (password.toLowerCase().contains(username.toLowerCase()))
            throw new UsernameInPasswordException("Your password shouldn't contain your username!");

        return userRepository.save(new User(name, surname, username, passwordEncoder.encode(password)));
    }

    @Override
    public User loginAccount(String username, String password) {
        Optional<User> currentUser = userRepository.findUserByUsername(username);
        if (currentUser.isEmpty() || !passwordEncoder.matches(password, currentUser.get().getPassword())) {
            throw new Username0rPasswordDoesntMatchException("Your username or password don't match!");
        }
        return currentUser.get();
    }

    @Override
    public User updatePassword(String username, String newPassword) {
        PasswordValidator.isValid(newPassword);
        User user = userRepository.findUserByUsername(username).get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }
}
