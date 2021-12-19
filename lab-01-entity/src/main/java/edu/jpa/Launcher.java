package edu.jpa;

import edu.jpa.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        // create EntityManager instance
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnits.lab01");
        EntityManager em = emf.createEntityManager();

        // start transaction
        em.getTransaction().begin();


        Company company = new Company();
        company.setId(1);
        company.setName("Company #1");
        company.setAddress("Company #1 address");
        em.persist(company);

        company = new Company();
        company.setId(2);
        company.setName("Company #2");
        company.setAddress("Company #2 address");
        em.persist(company);


        // commit transaction
        em.getTransaction().commit();

//        emf.close();

        try {
            org.h2.tools.Console.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
