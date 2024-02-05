package org.applicationsmart.dtos.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String age;
    private String phoneNumber;
    private String password;
    private String domainName;
}
