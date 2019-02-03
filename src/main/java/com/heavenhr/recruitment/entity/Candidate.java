package com.heavenhr.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Candidate {

    @Email
    @Column(name = "candidate", unique = true)
    private String email;

}
