package com.heavenhr.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by shridhar on 02/02/19.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table
public class Offer implements Serializable {

    private static final long serialVersionUID = -5274294747887818148L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Job Title can not be empty.")
    private String jobTitle;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column
    private Integer numberOfApplications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    private List<Application> applications;
}