package ru.podgoretskaya.dossier.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "deal", url = "http://localhost:8081")
public interface ConveyorClient {
    @PostMapping(value = "/deal/document/{applicationId}/send")
   String send (@PathVariable Long applicationId);

}
