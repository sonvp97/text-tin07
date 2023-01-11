//package com.codegym.cms.converter;
//
//import com.codegym.cms.model.Employee;
//import com.codegym.cms.util.EmUtils;
//import org.springframework.core.convert.converter.Converter;
//
//public class EmployeeConverter implements Converter<String, Employee> {
//
//    private String department;
//    public EmployeeConverter(String department){
//        this.department = department;
//    }
//    @Override
//    public Employee convert(String name) {
//        Employee employee = new Employee(EmUtils.MIN_EMPLOYEE_NUM + n + 1, "Spring in Action");
//        employee.setFirstName(name.replace("",department));
//        return employee;
//    }
//}
