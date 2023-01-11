package com.codegym.cms.controller;


import com.codegym.cms.model.Branch;
import com.codegym.cms.model.Employee;
import com.codegym.cms.service.employee.IEmployeeService;
import com.codegym.cms.service.branch.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



import static org.springframework.util.StringUtils.hasText;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IBranchService branchService;

    @ModelAttribute("branches")
    public Iterable<Branch> branches(){
        return branchService.findAll();
    }


    @GetMapping("/create-employee")
    public ModelAndView showCreateForm() {
        System.out.println("Execute Controller");
        ModelAndView modelAndView = new ModelAndView("/employee/create");
        modelAndView.addObject("employee", new Employee());
//        modelAndView.addObject("employee", new Employee(MIN_EMPLOYEE_NUM + n + 1, "Spring in Action"));
        return modelAndView;
    }

    @PostMapping("/create-employee")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/create");
//        modelAndView.addObject("employee", new Employee(MIN_EMPLOYEE_NUM + n + 1, "Spring in Action"));
        modelAndView.addObject("message", "New Employee created successfully");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }
//    @PostMapping("/create-customer")
//    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
//        employeeService.save(customer);
//        ModelAndView modelAndView = new ModelAndView("/customer/create");
//        modelAndView.addObject("customer", new Customer());
//        modelAndView.addObject("message", "New customer created successfully");
//        return modelAndView;
//    }

    @GetMapping("/employees")
    public ModelAndView listEmployees(@RequestParam("search") Optional<String> search, Pageable pageable){
//        Page<Employee> employees;
//        if(search.isPresent()){
//            employees = employeeService.findAllByFirstNameContaining(search.get(), pageable);
//        } else {
//            employees = employeeService.findAll(pageable);
//        }
        List<Employee> employees = employeeService.findByFirstNameOrderBySeatNumberAsc(String.valueOf(Sort.by(Sort.Direction.ASC, "age")));
        ModelAndView modelAndView = new ModelAndView("/employee/list");

        modelAndView.addObject("employees", employees);
        return modelAndView;
    }



    @GetMapping("/edit-employee/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/employee/edit");
            modelAndView.addObject("employee", employee.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-employee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/employee/edit");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("message", "Employee updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-employee/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/employee/delete");
            modelAndView.addObject("employee", employee.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-employee")
    public String deleteCustomer(@ModelAttribute("employee") Employee employee) {
        employeeService.remove(employee.getId());
        return "redirect:employees";
    }
    @GetMapping("AddByName")
    public ModelAndView addByName(@RequestParam("name") Employee employee){
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("edit");
        return modelAndView;
    }

//    @GetMapping("views/list")
//    public String paginate(Model model, @RequestParam("p") Optional<Integer>p) {
//        Pageable pageable= PageRequest.of(p.orElse(0),3);
//        Page<Customer> list= customerService.findAll((pageable));
//        model.addAttribute("customer", list);
//        return "views-customers";
//    }
//    @GetMapping("findAll")
//    public ModelAndView findAll(@PageableDefault(value=3)Pageable pageable){
//        ModelAndView modelAndView = new ModelAndView("list");
//        Page<Customer> customers = customerService.findAll(pageable);
//        modelAndView.addObject("customers", customers);
//        return  modelAndView;
//    }

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(
//            Model model,
//            @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size) {
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Page<Customer> customerPage = customerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("customerPage", customerPage);
//
//        int totalPages = customerPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return "list";
//    }
//@RequestMapping(value = "/list", method = RequestMethod.GET)
//public String listCustomers(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//    final int currentPage = page.orElse(1);
//    final int pageSize = size.orElse(5);
//
//    Page<Customer> customerPage = customerService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
//
//    model.addAttribute("customerPage", customerPage);
//
//    int totalPages = customerPage.getTotalPages();
//    if (totalPages > 0) {
//        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                .boxed()
//                .collect(Collectors.toList());
//        model.addAttribute("pageNumbers", pageNumbers);
//    }
//
//    return "list";
//}
    @GetMapping("list")
    public String search(ModelMap model,
                         @RequestParam(name="firstName", required = false) String firstName,
                         @RequestParam("page") Optional<Integer>page,
                         @RequestParam("size") Optional<Integer>size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("firstName"));
        Page<Employee> resultPage=null;
        if (hasText(firstName)){
            resultPage = employeeService.findAllByFirstNameContaining(firstName, pageable);
            model.addAttribute("firstName", firstName);
        } else {
            resultPage = employeeService.findAll(pageable);
        }
        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage-2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) start = end - 5;
                else if (start ==1) end = start + 5;
            }
            List<Integer>pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("employeePage", resultPage);
        return "/employees/list";
    }
}
