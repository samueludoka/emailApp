package org.applicationsmart.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class EmailApp {
    @Id
    private String id;
    private String domainName;
    private List<Mail> mail = new ArrayList<>();
    private boolean isLogIn = false;
    private String userId;
}
