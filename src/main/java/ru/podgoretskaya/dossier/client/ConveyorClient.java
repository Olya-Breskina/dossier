package ru.podgoretskaya.dossier.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.podgoretskaya.dossier.dto.ApplicationDTO;


@FeignClient(name = "deal", url = "http://localhost:8081")
public interface ConveyorClient {
    @GetMapping(value = "/deal/admin/application/{applicationId}")
    ApplicationDTO getApplication(@PathVariable Long applicationId);
//    @PostMapping(value = "/document/{applicationId}/sign")
//  void sign(@PathVariable Long applicationId);
}
