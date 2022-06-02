package org.example.model;

import javax.validation.constraints.*;

public class Person {
    private static int counter = 0;

    private int id;

    @Min(value = 1, message = "Salary should be > 0")
    private Integer salary;

    @Size(min = 2, max = 15, message = "Name should contain 2 to 15 symbols!")
    private String name;

    @Min(value = 1, message = "Age should be > 0")
    private Integer age;

    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Please, enter proper email!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Person() {
        this.id = counter;
        ++counter;
    }
}
