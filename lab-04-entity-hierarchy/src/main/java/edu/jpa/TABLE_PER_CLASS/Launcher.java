package edu.jpa.TABLE_PER_CLASS;

import edu.jpa.TABLE_PER_CLASS.entity.Customer;
import edu.jpa.TABLE_PER_CLASS.entity.Employee;
import edu.jpa.TABLE_PER_CLASS.entity.Executive;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnits.lab04.TABLE_PER_CLASS");
        init(emf.createEntityManager());
        sample(emf.createEntityManager());

        try {
            org.h2.tools.Console.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static void init(EntityManager em) {
        em.getTransaction().begin();

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Customer #1 (10% discount).");
        customer.setDiscount(10.0);
        em.persist(customer);

        Employee employee = new Employee();
        employee.setId(2);
        employee.setName("Employee #1.");
        employee.setSalary(1000.0);
        em.persist(employee);

        Executive executive = new Executive();
        executive.setId(3);
        executive.setName("Executive #1.");
        executive.setSalary(2000.0);
        executive.setBonus(30.0);
        em.persist(executive);

        em.getTransaction().commit();
    }


    private static void sample(EntityManager em) {
        em.getTransaction().begin();

        Executive executive = em.find(Executive.class, 3);
        System.out.println(executive.getName());

        em.getTransaction().rollback();
    }
}
