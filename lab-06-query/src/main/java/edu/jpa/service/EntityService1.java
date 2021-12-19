package edu.jpa.service;

import edu.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EntityService1 extends EntityService {

    @Override
    public List<Employee> getEmployeesByDepartmentName(String name) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        String textQuery = "select e from Employee e where e.department.name = :name";
        TypedQuery<Employee> typedQuery = entityManager.createQuery(textQuery, Employee.class);
        typedQuery.setParameter("name", name);
        List<Employee> employeeList = typedQuery.getResultList();
        entityManager.getTransaction().rollback();
        return employeeList;
    }

    @Override
    public List<DepartmentInfo> getDepartmentsInfo() {

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        String textQuery = "select new edu.jpa.service.DepartmentInfo(e.department.name, count(e.department)) " +
                "from Employee e group by e.department.name";

        TypedQuery<DepartmentInfo> typedQuery = entityManager.createQuery(textQuery, DepartmentInfo.class);

        List<DepartmentInfo> departmentInfos = typedQuery.getResultList();

        entityManager.getTransaction().rollback();
        return departmentInfos;
    }
}
