package com.techchallenge4.ms_cliente.bdd;

import com.techchallenge4.ms_cliente.MsClienteApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = MsClienteApplication.class)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
}
