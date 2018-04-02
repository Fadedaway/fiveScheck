package com.magic.jovi;

import com.magic.jovi.repositories.support.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by fanjiawei on 2018/3/30
 */
@EnableJpaRepositories(basePackages = {"com.magic.jovi.repositories"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
