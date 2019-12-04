package com.hiep.prime.faces.beans;

import com.hiep.jpa.data.Employee;
import com.hiep.spring.service.EmployeeService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ViewScoped
public class EmployeeBean {
    @ManagedProperty("#{employeeService}")
    private EmployeeService employeeService;

    private Employee employee = new Employee();

    private List<Employee> employees= new ArrayList<Employee>();
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String register() {
        // Calling Business Service
        this.employeeService.register(employee);
        // Add message
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The Employee "+this.employee.getEmployeeName()+" Is Registered Successfully"));
        return "";
    }
    public String findEmp() {
        this.employee = this.employeeService.findEmpById(this.id);
        if (employee == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Not found!"));
        }

        return "";
    }
    public List<Employee> getAll(){
        List<Employee> employees = this.employeeService.getAllEmp();
        return employees;
    }
    public void remove(long id) {
        // Calling Business Service
        this.employeeService.removeEmp(id);
    }
}
