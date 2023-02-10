package ru.podgoretskaya.dossier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.podgoretskaya.dossier.client.ConveyorClient;

@SpringBootApplication
@EnableFeignClients(clients = ConveyorClient.class)
public class DossierApplication {

    public static void main(String[] args) {
        SpringApplication.run(DossierApplication.class, args);
    }

}
