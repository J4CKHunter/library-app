package com.test.library.config;

import com.test.library.dto.UserDto;
import com.test.library.model.Role;
import com.test.library.service.UserService;
import org.springframework.boot.CommandLineRunner;
import com.test.library.model.User;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {
    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.createUser(User.builder()
                .username("test1 username")
                .password("test1 password")
                .role(Role.ADMIN)
                .build());

        userService.createUser(User.builder()
                .username("test2 username")
                .password("test password")
                .role(Role.USER)
                .build());


    }
}
