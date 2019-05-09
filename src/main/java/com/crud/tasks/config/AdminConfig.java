package com.crud.tasks.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Data
public class AdminConfig {

    @Value("${spring.mail.username}")
    private String adminMail;

//    @Value("#{environment.Email}")
//    private String adminMail;

    @Value("#{environment.Password}")
    private String password;
}
