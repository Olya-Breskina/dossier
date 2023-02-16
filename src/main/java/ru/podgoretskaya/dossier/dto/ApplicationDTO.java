package ru.podgoretskaya.dossier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.podgoretskaya.dossier.dto.entity_enum.ApplicationStatus;

import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private Long applicationID;
    //    private ClientEntity client; должны быть ДТО
//    private CreditEntity credit;
    private ApplicationStatus status;
    private LocalTime creationDate;
    private LoanOfferDTO appliedOffer;
    private String sesCode;
    private List<StatusHistory> statusHistory;
}
