package edu.jpa.service;

import edu.jpa.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CompanyService {
    private static EntityManagerFactory emf = null;

    static {
        emf = Persistence.createEntityManagerFactory("persistenceUnit.jpa-lab-05"); // create EntityManagerFactory instance here
        init();
    }

    public Company getCompany(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Company company = em.find(Company.class, id);
        em.getTransaction().rollback();
        return company;
    }

    public void saveCompany(Company company) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(company);
        em.getTransaction().commit();
    }

    private static void init() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        final Company company = new Company();
        company.setName("Microsoft");
        em.persist(company);
        em.getTransaction().commit();
    }
}
