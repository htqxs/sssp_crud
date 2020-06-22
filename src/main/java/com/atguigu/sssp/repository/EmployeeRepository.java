package com.atguigu.sssp.repository;

import com.atguigu.sssp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * 根据lastName查询Employee对象
     * @param lastName lastName
     * @return Employee对象
     */
    Employee getByLastName(String lastName);
}
