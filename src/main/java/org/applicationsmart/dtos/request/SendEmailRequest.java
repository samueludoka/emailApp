package org.applicationsmart.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailRequest {
    private String domainName;
    private String title;
    private String body;
    private String reciepentEmail;
}
