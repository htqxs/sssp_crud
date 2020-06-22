package com.atguigu.sssp.service;

import com.atguigu.sssp.entity.Employee;
import com.atguigu.sssp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 获取分页对象
     * @param pageNum 当前页
     * @param pageSize 每页显示记录数
     * @return 对应的page对象
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Employee> getPage(Integer pageNum, Integer pageSize){
        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);
        return employeeRepository.findAll(pageable);
    }

    /**
     * 通过用户名获取Employee对象
     * @param lastName 传入的用户名
     * @return 对应的Employee对象
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee getByLastName(String lastName) {
        return employeeRepository.getByLastName(lastName);
    }

    /**
     * 保存用户
     * @param employee 要保存的用户对象
     */
    @Transactional
    public void add(Employee employee){
        if(employee.getId() == null) {
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }

    /**
     * 根据id获取Employee对象
     * @param id 用户的id
     * @return 对应的Employee对象
     */
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee get(Integer id){
        return employeeRepository.getOne(id);
    }

    /**
     * 根据id删除用户
     * @param id 用户的id
     */
    public void delete(Integer id){
        employeeRepository.deleteById(id);
    }
}
