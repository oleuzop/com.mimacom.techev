package com.mimacom.back.config;

import com.mimacom.back.controller._Controller;
import com.mimacom.back.model._Model;
import com.mimacom.back.repository._Repository;
import com.mimacom.back.service._Service;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Configuration
@EntityScan(basePackageClasses = _Model.class)
@ComponentScan(basePackageClasses = {_Service.class, _Controller.class})
@EnableJpaRepositories(basePackageClasses = _Repository.class)
public class BootConfig {
}
