package com.nestiq.auth.dto;

import com.nestiq.auth.entity.Role;

public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private UserDto user;

    // Getters
    public String getAccessToken() { return accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public UserDto getUser() { return user; }

    // Setters
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public void setUser(UserDto user) { this.user = user; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String accessToken;
        private String refreshToken;
        private UserDto user;

        public Builder accessToken(String accessToken) { this.accessToken = accessToken; return this; }
        public Builder refreshToken(String refreshToken) { this.refreshToken = refreshToken; return this; }
        public Builder user(UserDto user) { this.user = user; return this; }

        public AuthResponse build() {
            AuthResponse response = new AuthResponse();
            response.accessToken = this.accessToken;
            response.refreshToken = this.refreshToken;
            response.user = this.user;
            return response;
        }
    }

    // UserDto static inner class
    public static class UserDto {
        private Long id;
        private String name;
        private String email;
        private Role role;
        private String flatNumber;
        private String phone;

        // Getters
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public Role getRole() { return role; }
        public String getFlatNumber() { return flatNumber; }
        public String getPhone() { return phone; }

        // Setters
        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }
        public void setRole(Role role) { this.role = role; }
        public void setFlatNumber(String flatNumber) { this.flatNumber = flatNumber; }
        public void setPhone(String phone) { this.phone = phone; }

        // Builder
        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private Long id;
            private String name;
            private String email;
            private Role role;
            private String flatNumber;
            private String phone;

            public Builder id(Long id) { this.id = id; return this; }
            public Builder name(String name) { this.name = name; return this; }
            public Builder email(String email) { this.email = email; return this; }
            public Builder role(Role role) { this.role = role; return this; }
            public Builder flatNumber(String flatNumber) { this.flatNumber = flatNumber; return this; }
            public Builder phone(String phone) { this.phone = phone; return this; }

            public UserDto build() {
                UserDto dto = new UserDto();
                dto.id = this.id;
                dto.name = this.name;
                dto.email = this.email;
                dto.role = this.role;
                dto.flatNumber = this.flatNumber;
                dto.phone = this.phone;
                return dto;
            }
        }
    }
}