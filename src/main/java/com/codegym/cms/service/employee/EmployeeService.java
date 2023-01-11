package com.codegym.cms.service.employee;

import com.codegym.cms.model.Branch;
import com.codegym.cms.model.Employee;
import com.codegym.cms.repository.IEmployeeRepository;
import com.codegym.cms.util.EmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Iterable<Employee> findAllByBranch(Branch branch) {
        return employeeRepository.findAllByBranch(branch);
    }



    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return employeeRepository.findAllByFirstNameContaining(firstname, pageable);
    }

    final private List<Employee> employees = EmUtils.buildEmployees();

    public Page<Employee> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Employee> list;

        if (employees.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, employees.size());
            list = employees.subList(startItem, toIndex);
        }

        Page<Employee> employeePage
                = new PageImpl<Employee>(list, PageRequest.of(currentPage, pageSize), employees.size());

        return employeePage;
    }

    @Override
    public List<Employee> findByFirstNameOrderBySeatNumberAsc(String name) {
        return employeeRepository.findByOrderByAgeAsc();
    }

}
