package com.Plan_API.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Plan_API.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{

}
