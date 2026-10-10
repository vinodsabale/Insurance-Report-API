package com.ReportAPI.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ReportAPI.entity.SearchRequest;
import com.ReportAPI.entity.SearchResponse;
import com.ReportAPI.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReportController {
			
			private final ReportService reportService;
			/**
			 *  based on report service
			 * @return all plan Names
			 */
			@GetMapping("/plans")
			public ResponseEntity<List<String>> getAllPlans(){
				List<String> uniquePlanName = reportService.getUniquePlanName();
				return new ResponseEntity<>(uniquePlanName,HttpStatus.OK);
			}
			
			@GetMapping("/statues")
			public ResponseEntity<List<String>> getPlanStatus(){
				List<String> planStatus=reportService.getUniquePlanStatus();
				return new ResponseEntity<>(planStatus,HttpStatus.OK);
			}
			@PostMapping("/searches")
			public ResponseEntity<List<SearchResponse>> searchPlans(@RequestBody SearchRequest request){
				 List<SearchResponse> search = reportService.search(request);
				 return new ResponseEntity<>(search,HttpStatus.OK);
			}/*
			  @GetMapping("/excel")
			    public void exportExcel(HttpServletResponse response) throws Exception {

			        response.setContentType("application/vnd.ms-excel");
			        String headerKey="HttpHeaders.CONTENT_DISPOSITION";
			        String headerValue="attachment; filename=Eligibility_Report.xls";
			        response.setHeader(headerKey,headerValue);

			        reportService.generateExcel(response);
			  }*/
			  @GetMapping("/excel")
			  public void exportExcel(HttpServletResponse response) throws Exception {

			      response.setContentType("application/vnd.ms-excel");

			      response.setHeader(
			              "Content-Disposition",
			              "attachment; filename=Eligibility_Report.xls");

			      reportService.generateExcel(response);
			  }
			@GetMapping("/pdf")
			public void pdfReport(HttpServletResponse response) throws Exception{
				response.setContentType("application/pdf");
				String headerKey="Content-Disposition";
				String headerValue="attachment;filename=Eligibility_Report.pdf";
				response.setHeader(headerKey, headerValue);

				reportService.generatePdf(response);
			}
}
