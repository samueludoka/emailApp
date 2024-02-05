package org.applicationsmart.data.model;

import lombok.Data;
import org.applicationsmart.utils.MailType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Mail {
    @Id
    private String id;
    private String title;
    private String message;
    private LocalDate dateCreated = LocalDate.now();
    private MailType mailType;
}
