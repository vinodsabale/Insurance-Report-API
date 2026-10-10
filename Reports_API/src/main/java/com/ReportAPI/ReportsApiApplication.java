package com.ReportAPI;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ReportAPI.entity.EligibiltyDetails;
import com.ReportAPI.repository.EligibilityDetailsRepository;

@SpringBootApplication
public class ReportsApiApplication implements ApplicationRunner {

    @Autowired
    private EligibilityDetailsRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(ReportsApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	 if(repo.count() == 0) {
        EligibiltyDetails d1 = new EligibiltyDetails();
        d1.setName("Vinod");
        d1.setMobile(9876543210L);
        d1.setEmail("vinod@gmail.com");
        d1.setGeneder('M');
        d1.setSsn(123456789L);
        d1.setPlanName("SNAP");
        d1.setPlanStatus("Approved");
        d1.setPlanStrartDate(LocalDate.of(2026, 1, 1));
        d1.setPlanEndDate(LocalDate.of(2026, 12, 31));
        d1.setCreatedBy("Admin");
        d1.setUpdatedBy("Admin");

        EligibiltyDetails d2 = new EligibiltyDetails();
        d2.setName("Rahul");
        d2.setMobile(9876543211L);
        d2.setEmail("rahul@gmail.com");
        d2.setGeneder('M');
        d2.setSsn(123456790L);
        d2.setPlanName("Medicaid");
        d2.setPlanStatus("Denied");
        d2.setPlanStrartDate(LocalDate.of(2026, 2, 1));
        d2.setPlanEndDate(LocalDate.of(2026, 12, 31));
        d2.setCreatedBy("Admin");
        d2.setUpdatedBy("Admin");

        EligibiltyDetails d3 = new EligibiltyDetails();
        d3.setName("Sneha");
        d3.setMobile(9876543212L);
        d3.setEmail("sneha@gmail.com");
        d3.setGeneder('F');
        d3.setSsn(123456791L);
        d3.setPlanName("CCAP");
        d3.setPlanStatus("Approved");
        d3.setPlanStrartDate(LocalDate.of(2026, 3, 1));
        d3.setPlanEndDate(LocalDate.of(2026, 12, 31));
        d3.setCreatedBy("Admin");
        d3.setUpdatedBy("Admin");

        EligibiltyDetails d4 = new EligibiltyDetails();
        d4.setName("Priya");
        d4.setMobile(9876543213L);
        d4.setEmail("priya@gmail.com");
        d4.setGeneder('F');
        d4.setSsn(123456792L);
        d4.setPlanName("QHP");
        d4.setPlanStatus("Pending");
        d4.setPlanStrartDate(LocalDate.of(2026, 4, 1));
        d4.setPlanEndDate(LocalDate.of(2026, 12, 31));
        d4.setCreatedBy("Admin");
        d4.setUpdatedBy("Admin");

        EligibiltyDetails d5 = new EligibiltyDetails();
        d5.setName("Amit");
        d5.setMobile(9876543214L);
        d5.setEmail("amit@gmail.com");
        d5.setGeneder('M');
        d5.setSsn(123456793L);
        d5.setPlanName("Cash");
        d5.setPlanStatus("Approved");
        d5.setPlanStrartDate(LocalDate.of(2026, 5, 1));
        d5.setPlanEndDate(LocalDate.of(2026, 12, 31));
        d5.setCreatedBy("Admin");
        d5.setUpdatedBy("Admin");

        repo.save(d1);
        repo.save(d2);
        repo.save(d3);
        repo.save(d4);
        repo.save(d5);

        System.out.println("5 Records Inserted Successfully...");
    }
    }
}