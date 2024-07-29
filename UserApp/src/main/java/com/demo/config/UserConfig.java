package com.demo.config;

import com.demo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    public List<User> users() {
        return new ArrayList<User>();
    }
}
