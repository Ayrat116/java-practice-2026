package ru.itis.shop.user.application;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id).map(this::toDto);
    }

    public void updateProfile(String email, String newDescription) {
        userRepository.updateProfileByEmail(email, newDescription);
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllByProfileDescription(String description) {
        return userRepository.findAllByProfileDescription(description).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getProfileDescription());
    }
}