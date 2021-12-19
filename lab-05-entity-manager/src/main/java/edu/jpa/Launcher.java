package edu.jpa;

import edu.jpa.entity.Company;
import edu.jpa.service.CompanyService;

import java.sql.SQLException;

public class Launcher {
    public static void main(String[] args) {

        CompanyService service = new CompanyService();
        Company company = service.getCompany(1);

        company.setName("Internation Business Machines (IBM)");
        service.saveCompany(company);

    }
}
