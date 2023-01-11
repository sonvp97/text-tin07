package com.codegym.cms.repository;

import com.codegym.cms.model.Branch;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends PagingAndSortingRepository<Branch, Long> {
}
