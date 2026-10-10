package com.Plan_API.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Plan_API.Repository.PlanCategoryRepository;
import com.Plan_API.Repository.PlanRepository;
import com.Plan_API.entity.Plan;
import com.Plan_API.entity.PlanCategory;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
	private final PlanRepository planRepo;
		private final PlanCategoryRepository planCategoryRepo;
	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> allCategories = planCategoryRepo.findAll();
		//convert this list to map
		Map<Integer,String> categoryMap=new HashMap<>();
		allCategories.forEach(category->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan save = planRepo.save(plan);
		/*//fresher
		if(save.getPlanId() !=null) {
			return true;
		}else {
			return false;		
		}*/
		/* 1 year devloper
		return save.getPlanId()!=null?true:false;   */
	    return save.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
		
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		//it class intruduce in 1.8  to avoid nullpointer exception 
		Optional<Plan> findbyId = planRepo.findById(planId);
		if(findbyId.isPresent()) {
		   return findbyId.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
	   planRepo.save(plan);  //(upsert)
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		try {
			planRepo.deleteById(planId);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;	
	}

	@Override
	public boolean planStatusChange(Integer planId, String activeStatus) {
			Optional<Plan> byId = planRepo.findById(planId);
		if(byId.isPresent()) {
			Plan plan=byId.get();
			plan.setActiveSwitch(activeStatus);;
			planRepo.save(plan);			
			return true;
		}
			return false;
		
	}

}
