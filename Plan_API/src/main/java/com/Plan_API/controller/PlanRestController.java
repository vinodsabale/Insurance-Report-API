package com.Plan_API.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Plan_API.entity.Plan;
import com.Plan_API.service.PlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PlanRestController {
	private final PlanService planService;
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories(){
		Map<Integer,String> planServices=planService.getPlanCategories();
		return new ResponseEntity<>(planServices,HttpStatus.OK);
	}
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		boolean savePlan = planService.savePlan(plan);
		String responseMessage="";
		if(savePlan) {
			responseMessage="Plan Saved !";
		}else {
			responseMessage="Plan Not Saved !!";
		}
		return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
	}
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan planById = planService.getPlanById(planId);
		return new ResponseEntity<>(planById,HttpStatus.OK);
	}
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		String message="";
		boolean isDeleted = planService.deletePlanById(planId);
		if(isDeleted) {
			message="Plan is deleted !!";
		}else {
			message="Plan is Not delete !!";
		}
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		String message="";
		boolean isUpdated = planService.updatePlan(plan);
		if(isUpdated) {
			message="Plan is Updated !!";
		}else {
			message="Plan is Not Updated !!";
		}
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	@PutMapping("/status-changed/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
	boolean planStatusChange = planService.planStatusChange(planId, status);	
	String messages="";
	if(planStatusChange) {
		messages="Status changed";
	}else {
		messages="Status is not changed";
	}
	return new ResponseEntity<>(messages,HttpStatus.OK);
	}
	
	
}
