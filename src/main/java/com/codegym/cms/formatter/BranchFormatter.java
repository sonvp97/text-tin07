package com.codegym.cms.formatter;

import com.codegym.cms.model.Branch;
import com.codegym.cms.service.branch.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class BranchFormatter implements Formatter<Branch> {

    private IBranchService branchService;

    @Autowired
    public BranchFormatter(IBranchService provinceService) {
        this.branchService = provinceService;
    }

    @Override
    public Branch parse(String text, Locale locale) throws ParseException {
        Optional<Branch> branchOptional = branchService.findById(Long.parseLong(text));
        return branchOptional.orElse(null);
    }

    @Override
    public String print(Branch object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
