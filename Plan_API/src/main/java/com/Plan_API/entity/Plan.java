package com.Plan_API.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Plan {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="PLAN_ID")
		private Integer planId;
		
		@Column(name="PLAN_NAME")
		private String plan_Name;
		
		@Column(name="PLAN_START_DATE")
		private LocalDate planStartDate;
		@Column(name="PLAN_END_DATE")
		private LocalDate plan_end_date;
		@Column(name="PLAN_CATEGORY_ID")
		private Integer planCategoryId;
		
		@Column(name="ACTIVE_SWITCH")
		private String activeSwitch;
		
		@Column(name="CREATED_BY")
		private String createdBy;
		
		@Column(name="UPDATED_BY")
		private String updatedBy;
		
		@Column(name="CREATE_DATE",updatable = false)
		@CreationTimestamp
		private LocalDate createDate;
		
		@Column(name="UPDATE_DATE",insertable = true)
		@UpdateTimestamp
		private LocalDate updateDate;
	
}
