package ru.itis.shop.user.api.dto;

public class UserDto {
    private String name;
    private String email;
    private String profileDescription;

    public UserDto(String name, String email, String profileDescription) {
        this.name = name;
        this.email = email;
        this.profileDescription = profileDescription;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Email: " + email + ", Описание: " + profileDescription;
    }
}
