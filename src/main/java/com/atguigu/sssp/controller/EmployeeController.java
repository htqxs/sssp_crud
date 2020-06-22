package com.atguigu.sssp.controller;

import com.atguigu.sssp.entity.Department;
import com.atguigu.sssp.entity.Employee;
import com.atguigu.sssp.service.DepartmentService;
import com.atguigu.sssp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNumStr, Model model){
        int pageNum = 1;

        try {
            //对pageNum的校验
            pageNum = Integer.parseInt(pageNumStr);
            if(pageNum < 1){
                pageNum = 1;
            }
        } catch (NumberFormatException ignored) {}

        Page<Employee> page = employeeService.getPage(pageNum, 5);
        model.addAttribute("page", page);

        return "emp/list";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String add(Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
        return "emp/add";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidaLastName", method = RequestMethod.POST)
    public String validateLastName(@RequestParam(value = "lastName", required = true) String lastName){
        Employee employee = employeeService.getByLastName(lastName);
        if(employee == null){
            return "0";
        }
        return "1";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee){
        employeeService.add(employee);
        return "redirect:emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model){
        Employee employee = employeeService.get(id);
        model.addAttribute("employee", employee);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Model model){
        if(id != null) {
            Employee employee = employeeService.get(id);
            //hibernate不支持持久化对象id值的更改
            employee.setDepartment(null);
            model.addAttribute("emp", employee);
        }
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(@ModelAttribute("emp") Employee employee){
        employeeService.add(employee);
        return "redirect:emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id){
        employeeService.delete(id);
        return "success";
    }
}
