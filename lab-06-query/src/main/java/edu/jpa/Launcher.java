package edu.jpa;

import edu.jpa.entity.Employee;
import edu.jpa.service.DepartmentInfo;
import edu.jpa.service.EntityService;
import edu.jpa.service.EntityService1;
import edu.jpa.service.EntityService2;

import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        sample(new EntityService1());
        sample(new EntityService2());
    }

    private static void sample(EntityService service) {
        final List<Employee> employees = service.getEmployeesByDepartmentName("IT");
        for (Employee employee : employees) {
            System.out.println(
                String.format(
                    "===> {'employee.name'='%1$s', 'department.name'='%2$s', 'company.name'='%3$s'}",
                    employee.getName(),
                    employee.getDepartment().getName(),
                    employee.getDepartment().getCompany().getName()));
        }

        List<DepartmentInfo> infos = service.getDepartmentsInfo();
        for (DepartmentInfo info : infos) {
            System.out.println(
                String.format(
                    "===> 'departmentName'='%1$s', 'employeesCount'='%2$s'",
                    info.getDepartmentName(),
                    info.getEmployeesCount())
            );
        }
    }
}
