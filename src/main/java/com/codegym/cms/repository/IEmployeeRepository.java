package com.codegym.cms.repository;


import com.codegym.cms.model.Branch;
import com.codegym.cms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Iterable<Employee> findAllByBranch(Branch branch);


    Page<Employee> findAllByFirstNameContaining(String firstname, Pageable pageable);
    List<Employee> findByOrderByAgeAsc();

}
