package com.p2plending.loanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanserviceApplication.class, args);
        System.out.println("\n******************************* Loan Service Application Running Fine *******************************\n");
    }

}
