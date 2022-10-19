package com.project.yourjs.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "graduate")
public class Graduate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "graduate_seq")
    private Long graduateSeq;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "graduate_status", nullable = false)
    private boolean graduateStatus;

    @Column(name = "tot_avg_credit", nullable = false)
    private String totAvgCredit;

    @Column(name = "major_avg_credit", nullable = false)
    private String majorAvgCredit;

    @Column(name = "tot_credit", nullable = false)
    private String totCredit;

    @Column(name = "major_credit", nullable = false)
    private String majorCredit;

    @Column(name = "major_name", nullable = false)
    private String majorName;

    @Column(name = "doubl_major_name")
    private String doubleMajorName;

    @Column(name = "sub_major_name")
    private String subMajorName;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "file_src", nullable = true)
    private String fileSrc;

    @CreationTimestamp
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime regDtm;

    @UpdateTimestamp
    @LastModifiedDate
    @Column
    private LocalDateTime modDtm;
}
