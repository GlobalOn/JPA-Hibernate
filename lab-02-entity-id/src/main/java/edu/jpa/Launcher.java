package edu.jpa;

import edu.jpa.entity.DepartmentKey;
import edu.jpa.entity.Department_1;
import edu.jpa.entity.Department_2;
import edu.jpa.entity.Department_3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnits.lab02");

//        init_1(emf.createEntityManager());
//        lookup_1(emf.createEntityManager());

        init_2(emf.createEntityManager());
        lookup_2(emf.createEntityManager());

        init_3(emf.createEntityManager());
        lookup_3(emf.createEntityManager());

//        emf.close();
        try {
            org.h2.tools.Console.main(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*
     * CASE #1: Simple identity
     */

    private static void init_1(EntityManager em) {
        em.getTransaction().begin();

        Department_1 department = new Department_1();
        department.setId(1);
        department.setCompanyName("Luxoft");
        department.setDepartmentName("IT");
        department.setDescription("Information Technology department");

        em.persist(department);
        em.getTransaction().commit();
    }

    private static void lookup_1(EntityManager em) {
        em.getTransaction().begin();

        Department_1 department = em.find(Department_1.class, 1);
        System.out.println("===> Department_2.Description = [" + department.getDescription() + "]");

        em.getTransaction().rollback();
    }

    /*
     * CASE #2: Composite identity - using with @EmbeddedId
     */

    private static void init_2(EntityManager em) {
        em.getTransaction().begin();

        DepartmentKey id = new DepartmentKey();
        id.setCompanyName("Luxoft");
        id.setDepartmentName("IT");

        Department_2 department = new Department_2();
        department.setId(id);
        department.setDescription("Information Technology department");

        em.persist(department);
        em.getTransaction().commit();
    }

    private static void lookup_2(EntityManager em) {
        em.getTransaction().begin();

        DepartmentKey id = new DepartmentKey();
        id.setCompanyName("Luxoft");
        id.setDepartmentName("IT");

        Department_2 department = em.find(Department_2.class, id);
        System.out.println("===> Department_2.Description = [" + department.getDescription() + "]");

        em.getTransaction().rollback();
    }


    /*
     * CASE #3: Composite identity - using with @IdClass
     */

    private static void init_3(EntityManager em) {
        em.getTransaction().begin();

        Department_3 department = new Department_3();
        department.setCompanyName("Luxoft");
        department.setDepartmentName("IT");
        department.setDescription("Information Technology department");

        em.persist(department);
        em.getTransaction().commit();
    }

    private static void lookup_3(EntityManager em) {
        em.getTransaction().begin();

        DepartmentKey id = new DepartmentKey();
        id.setCompanyName("Luxoft");
        id.setDepartmentName("IT");

        Department_3 department = em.find(Department_3.class, id);
        System.out.println("===> Department_3.Description = [" + department.getDescription() + "]");

        em.getTransaction().rollback();
    }
}
