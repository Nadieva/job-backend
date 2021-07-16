package com.nadieva.job.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String position;

    private String company;

    private String status;

    private String note;

    //private String fileDownloadUri;

    @Column(name = "application_datetime")
    private Date applicationDate;

    @Column(name = "last_updated_datetime")
    private Date lastUpdatedDateTime;

}
