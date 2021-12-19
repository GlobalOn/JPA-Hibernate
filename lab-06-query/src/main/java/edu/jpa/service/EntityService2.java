package edu.jpa.service;

import edu.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EntityService2 extends EntityService {
    @Override
    public List<Employee> getEmployeesByDepartmentName(String name) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> e = criteriaQuery.from(Employee.class);

        criteriaQuery.
                where(criteriaBuilder.
                        equal(e.get("department").get("name"),
                                criteriaBuilder.parameter(String.class, "name")));

        entityManager.getTransaction().begin();
        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Employee> employeeList = typedQuery.getResultList();
        entityManager.getTransaction().rollback();
        return employeeList;
    }

    @Override
    public List<DepartmentInfo> getDepartmentsInfo() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DepartmentInfo> criteriaQuery = criteriaBuilder.createQuery(DepartmentInfo.class);

        Root<DepartmentInfo> e = criteriaQuery.from(DepartmentInfo.class);

        criteriaQuery.
                select(criteriaBuilder.
                        construct(DepartmentInfo.class,
                                e.get("department").get("name"), criteriaBuilder.count(e.get("department"))));

        criteriaQuery.groupBy(e.get("department"));
        entityManager.getTransaction().begin();
        TypedQuery<DepartmentInfo> typedQuery = entityManager.createQuery(criteriaQuery);
        List<DepartmentInfo> departmentInfos = typedQuery.getResultList();
        entityManager.getTransaction().rollback();
        return departmentInfos;
    }
}
