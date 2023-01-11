package com.codegym.cms.service.employee;

import com.codegym.cms.model.Branch;
import com.codegym.cms.model.Employee;
import com.codegym.cms.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService extends IGeneralService<Employee> {
    Iterable<Employee> findAllByBranch(Branch branch);
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findAllByFirstNameContaining(String firstname, Pageable pageable);
    Page<Employee> findPaginated(Pageable pageable);

    List<Employee> findByFirstNameOrderBySeatNumberAsc(String name);
}
