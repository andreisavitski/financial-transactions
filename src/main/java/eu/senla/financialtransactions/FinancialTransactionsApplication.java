package eu.senla.financialtransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class FinancialTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialTransactionsApplication.class, args);
    }

}
