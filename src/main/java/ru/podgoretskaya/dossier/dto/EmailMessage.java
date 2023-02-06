package ru.podgoretskaya.dossier.dto;

import lombok.Data;

@Data
public class EmailMessage {
    private String address;
    private Theme theme;
    private Long applicationId;
}