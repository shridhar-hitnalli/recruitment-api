package com.heavenhr.recruitment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by shridhar on 02/02/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"offer", "candidate"})})
public class Application implements Serializable {

    private static final long serialVersionUID = 868985044185736724L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer")
    private Offer offer;

    @Embedded
    @NotNull(message = "Candidate cannot be null")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private ApplicationStatus status;

    @Column(name = "resume")
    private String resumeText;

}
