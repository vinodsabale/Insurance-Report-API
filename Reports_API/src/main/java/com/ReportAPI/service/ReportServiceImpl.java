package com.ReportAPI.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ReportAPI.entity.EligibiltyDetails;
import com.ReportAPI.entity.SearchRequest;
import com.ReportAPI.entity.SearchResponse;
import com.ReportAPI.repository.EligibilityDetailsRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService{
	private final  EligibilityDetailsRepository eligdetRepo;

	public ReportServiceImpl(EligibilityDetailsRepository eligdetRepo) {
		this.eligdetRepo = eligdetRepo;
	}
	@Override
	public List<String> getUniquePlanName() {
		return eligdetRepo.findPlanName();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return eligdetRepo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		List<SearchResponse> response=new ArrayList<>();
		EligibiltyDetails quiryBuilder=new EligibiltyDetails();
		String planName=request.getPlanName();
		if(planName!=null && !planName.isEmpty()) {
			quiryBuilder.setPlanName(planName);
		}
		String planStatus=request.getPlanStatus();
		if(planStatus!=null && !planStatus.isEmpty()) {
			quiryBuilder.setPlanStatus(planStatus);
		}
		LocalDate planStartDate=request.getPlanStartDate();
		if(planStartDate !=null) {
			quiryBuilder.setPlanStrartDate(planStartDate);
		}
		LocalDate planEndDate=request.getPlanEndDate();
		if(planEndDate !=null) {
			quiryBuilder.setPlanEndDate(planEndDate);
		}
		Example<EligibiltyDetails> example=Example.of(quiryBuilder);
		List<EligibiltyDetails> entites=eligdetRepo.findAll(example);
		for(EligibiltyDetails entity: entites) {
			SearchResponse serch=new SearchResponse();
			BeanUtils.copyProperties(entity,serch);
			response.add(serch);
		}
		return response;
	}
	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {

	    List<EligibiltyDetails> details = eligdetRepo.findAll();

	    HSSFWorkbook workbook = new HSSFWorkbook();

	    HSSFSheet sheet = workbook.createSheet("Eligibility Details");

	    HSSFRow headerRow = sheet.createRow(0);

	    headerRow.createCell(0).setCellValue("Name");
	    headerRow.createCell(1).setCellValue("Mobile");
	    headerRow.createCell(2).setCellValue("Gender");
	    headerRow.createCell(3).setCellValue("SSN");
	    headerRow.createCell(4).setCellValue("Plan Name");
	    headerRow.createCell(5).setCellValue("Plan Status");
	    headerRow.createCell(6).setCellValue("Plan Start Date");

	    int rowNum = 1;

	    for (EligibiltyDetails detail : details) {

	        HSSFRow row = sheet.createRow(rowNum++);

	        row.createCell(0).setCellValue(detail.getName());

	        row.createCell(1).setCellValue(detail.getMobile());

	        row.createCell(2).setCellValue(
	                String.valueOf(detail.getGeneder()));

	        row.createCell(3).setCellValue(detail.getSsn());

	        row.createCell(4).setCellValue(detail.getPlanName());

	        row.createCell(5).setCellValue(detail.getPlanStatus());

	        row.createCell(6).setCellValue(
	                detail.getPlanStrartDate() != null
	                        ? detail.getPlanStrartDate().toString()
	                        : "");
	    }

	    for (int i = 0; i < 7; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    ServletOutputStream out = response.getOutputStream();

	    workbook.write(out);

	    workbook.close();

	    out.flush();
	    out.close();
	}
/*
	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {

	    List<EligibiltyDetails> details = eligdetRepo.findAll();

	    HSSFWorkbook workBook = new HSSFWorkbook();
	    HSSFSheet sheet = workBook.createSheet("Eligibility Details");

	    // Header Row
	    HSSFRow headerRow = sheet.createRow(0);

	    headerRow.createCell(0).setCellValue("Name");
	    headerRow.createCell(1).setCellValue("Mobile");
	    headerRow.createCell(2).setCellValue("Gender");
	    headerRow.createCell(3).setCellValue("SSN");
	    headerRow.createCell(4).setCellValue("Plan Name");
	    headerRow.createCell(5).setCellValue("Plan Status");
	    headerRow.createCell(6).setCellValue("Plan Start Date");

	    // Date Style
	    HSSFCellStyle dateCellStyle = workBook.createCellStyle();
	    HSSFDataFormat dateFormat = workBook.createDataFormat();
	    dateCellStyle.setDataFormat(dateFormat.getFormat("dd-MM-yyyy"));

	    int dataRowIndex = 1;

	    for (EligibiltyDetails detail : details) {

	        HSSFRow dataRow = sheet.createRow(dataRowIndex);

	        dataRow.createCell(0).setCellValue(detail.getName());
	        dataRow.createCell(1).setCellValue(detail.getMobile());
	        dataRow.createCell(2).setCellValue(detail.getGeneder());
	        dataRow.createCell(3).setCellValue(detail.getSsn());
	        dataRow.createCell(4).setCellValue(detail.getPlanName());
	        dataRow.createCell(5).setCellValue(detail.getPlanStatus());
	        dataRow.createCell(6).setCellValue(detail.getPlanStrartDate());

	        // Date Cell
	        HSSFCell dateCell = dataRow.createCell(6);

	        if (detail.getPlanStrartDate() != null) {
	            dateCell.setCellValue(detail.getPlanStrartDate());
	            dateCell.setCellStyle(dateCellStyle);
	        }

	        dataRowIndex++;
	    }

	    // Auto Size Columns
	    for (int i = 0; i <= 7; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    ServletOutputStream ops = response.getOutputStream();

	    workBook.write(ops);

	    workBook.close();
	    ops.close();
	}	
*/
	@Override
	
	public void generatePdf(HttpServletResponse response) throws Exception {

	    List<EligibiltyDetails> entities = eligdetRepo.findAll();

	    Document document = new Document(PageSize.A2.rotate());

	    PdfWriter.getInstance(document, response.getOutputStream());

	    document.open();

	    // Title
	    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
	    titleFont.setSize(18);
	    titleFont.setColor(Color.RED);

	    Paragraph p = new Paragraph("Search Report", titleFont);
	    p.setAlignment(Paragraph.ALIGN_CENTER);

	    document.add(p);

	    // Table
	    PdfPTable table = new PdfPTable(7);
	    table.setWidthPercentage(100f);
	    table.setSpacingBefore(10);

	    table.setWidths(new float[] {
	            2.0f, 3.5f, 1.5f,
	            3.0f, 2.5f, 1.5f, 3.0f
	    });

	    // Header Font
	    Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	    headerFont.setColor(Color.WHITE);

	    // Header Cells
	    String[] headers = {
	            "Name",
	            "Mobile",
	            "Gender",
	            "SSN",
	            "Plan Name",
	            "Plan Status",
	            "Plan Start Date"
	    };

	    for (String header : headers) {

	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.RED);
	        cell.setPadding(5);

	        cell.setPhrase(new Phrase(header, headerFont));

	        table.addCell(cell);
	    }

	    // Data Rows
	    for (EligibiltyDetails entity : entities) {
	        table.addCell(entity.getName());
	        table.addCell(String.valueOf(entity.getMobile()));

	        table.addCell(String.valueOf(entity.getGeneder()));

	        table.addCell(String.valueOf(entity.getSsn()));

	        table.addCell(entity.getPlanName());

	        table.addCell(entity.getPlanStatus());

	        table.addCell(entity.getPlanStrartDate()+ "");
	    }

	    document.add(table);

	    document.close();
	}

}
