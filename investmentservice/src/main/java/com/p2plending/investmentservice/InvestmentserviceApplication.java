package com.p2plending.investmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.p2plending.investmentservice.repository")
@EntityScan(basePackages = "com.p2plending.investmentservice.model")
public class InvestmentserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestmentserviceApplication.class, args);
        System.out.println("******************************************* Investment Service Application *****************************************");
    }

}
