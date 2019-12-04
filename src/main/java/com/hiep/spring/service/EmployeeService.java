package com.hiep.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hiep.jpa.data.Employee;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void register(Employee emp) {
		// Save employee
		this.em.persist(emp);
	}

	@Transactional
	public Employee findEmpById(long id){
		Employee employee = this.em.find(Employee.class, id);
		return employee;
	}
	@Transactional
	public List<Employee> getAllEmp(){
		Query query = em.createQuery("select  e from Employee e",Employee.class);
		List<Employee> employees = new ArrayList<Employee>();
		return (List<Employee>) query.getResultList();
	}

	@Transactional
	public void removeEmp(long id){
		Employee employee = em.find(Employee.class, id);
		this.em.remove(employee);
	}
}
