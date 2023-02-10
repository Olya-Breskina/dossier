package ru.podgoretskaya.dossier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.podgoretskaya.dossier.dto.entity_enum.ApplicationStatus;
import ru.podgoretskaya.dossier.dto.entity_enum.ChangeType;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StatusHistory {
    private ApplicationStatus status;
    private LocalDateTime time;
    private ChangeType changeType;
}
