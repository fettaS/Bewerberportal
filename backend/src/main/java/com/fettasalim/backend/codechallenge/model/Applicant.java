package com.fettasalim.backend.codechallenge.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "applicants")
public class Applicant {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {

        OPEN,
        ACCEPTED,
        DECLINED
    }
}

