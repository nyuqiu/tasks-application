package com.crud.tasks.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Data
public class AdminConfig {

    @Value("${admin.mail}")
    private String adminMail;

}
