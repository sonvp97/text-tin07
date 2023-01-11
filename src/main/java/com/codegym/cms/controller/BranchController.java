package com.codegym.cms.controller;


import com.codegym.cms.model.Branch;
import com.codegym.cms.model.Employee;
import com.codegym.cms.service.employee.IEmployeeService;
import com.codegym.cms.service.branch.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/branches")
    public ModelAndView listBranches() {
        Iterable<Branch> branches = branchService.findAll();
        ModelAndView modelAndView = new ModelAndView("/branch/list");
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

    @GetMapping("/create-branch")
    public ModelAndView showCreateForm() {
        System.out.println("Execute Branch Controller");
        ModelAndView modelAndView = new ModelAndView("/branch/create");
        modelAndView.addObject("branch", new Branch());
        return modelAndView;
    }

    @PostMapping("/create-branch")
    public ModelAndView saveBranch(@ModelAttribute("branch") Branch branch) {
        branchService.save(branch);

        ModelAndView modelAndView = new ModelAndView("/branch/create");
        modelAndView.addObject("branch", new Branch());
        modelAndView.addObject("message", "New branch created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-branch/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Branch> branch = branchService.findById(id);
        if (branch.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/branch/edit");
            modelAndView.addObject("branch", branch.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-branch")
    public ModelAndView updateProvince(@ModelAttribute("branch") Branch branch) {
        branchService.save(branch);
        ModelAndView modelAndView = new ModelAndView("/branch/edit");
        modelAndView.addObject("branch", branch);
        modelAndView.addObject("message", "branch updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-branch/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Branch> branch = branchService.findById(id);
        if (branch.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/branch/delete");
            modelAndView.addObject("province", branch.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-branch")
    public String deleteProvince(@ModelAttribute("province") Branch branch) {
        branchService.remove(branch.getId());
        return "redirect:branches";
    }

    @GetMapping("/view-branch/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Branch> branchOptional = branchService.findById(id);
        if(!branchOptional.isPresent()){
            return new ModelAndView("/error.404");
        }

        Iterable<Employee> branches = employeeService.findAllByBranch(branchOptional.get());

        ModelAndView modelAndView = new ModelAndView("/branch/view");
        modelAndView.addObject("branch", branchOptional.get());
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }
}
