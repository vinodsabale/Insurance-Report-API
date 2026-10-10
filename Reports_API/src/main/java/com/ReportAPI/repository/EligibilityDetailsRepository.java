package com.ReportAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ReportAPI.entity.EligibiltyDetails;

public interface EligibilityDetailsRepository extends JpaRepository<EligibiltyDetails, Integer> {
	@Query("select distinct(planName) from EligibiltyDetails")
	public List<String> findPlanName();
	@Query("select distinct(planStatus) from EligibiltyDetails")
	public List<String> findPlanStatus();
}
