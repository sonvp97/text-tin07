package com.codegym.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String firstName;

    private double age;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Branch branch;


    public Employee() {}

    public Employee(String code, String firstName, double age, double salary, Branch branch) {
        this.code = code;
        this.firstName = firstName;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public Employee(Long id, String code, String firstName, double age, double salary, Branch branch) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", branch=" + branch +
                '}';
    }
}
