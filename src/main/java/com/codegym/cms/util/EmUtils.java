package com.codegym.cms.util;

import com.codegym.cms.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EmUtils {

    private static List<Employee> employees = new ArrayList<Employee>();

    private static final int NUM_EMPLOYEES = 3;

    private static final int MIN_EMPLOYEE_NUM = 10;

    public static List<Employee> buildEmployees() {
        if (employees.isEmpty()) {
            IntStream.range(0, NUM_EMPLOYEES).forEach(n -> {
                employees.add(new Employee());
            });
        }
        return employees;
    }

}
