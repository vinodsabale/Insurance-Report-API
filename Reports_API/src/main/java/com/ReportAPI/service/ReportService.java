package com.ReportAPI.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ReportAPI.entity.SearchRequest;
import com.ReportAPI.entity.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;


public interface ReportService {
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
	public List<SearchResponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response) throws Exception;
}
