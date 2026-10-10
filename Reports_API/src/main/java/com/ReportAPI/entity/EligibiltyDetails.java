package com.ReportAPI.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class EligibiltyDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer eligibilityId;
	
	private String name;
	
	private Long mobile;
	
	private String email;
	
	private Character geneder;
	
	private Long ssn;
	
	private String planName;
	
	private String planStatus;
	
	private LocalDate planStrartDate;
	
	private LocalDate planEndDate;
	
	private String createdBy;
	
	private String updatedBy;
	
}
