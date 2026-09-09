package com.unibrain.service;

import java.util.HashMap;
import java.util.List;

import com.unibrain.Entity.Employee;



public interface EmployeeService {
	public boolean saveEmployee(Employee Employee);
	public void deleteEmployee(Integer id);
	public Employee updateEmployee(Employee employee);
	public Employee getEmployee(Integer id);
	public List<Employee> serach(HashMap<String, String> searchMap,int start);
	//public List<Employee> loadEmp();
	public Boolean isDataExistForEmpId(Integer id);
	
	public List<Employee> loadEmp(int start);
	 public long employeeCount();
	 public long countForSearch(HashMap<String, String> searchMap);
	 
	 public Employee getEmployee1(Integer id);
	 public List<Employee> search(HashMap<String, String> searchMap);
}
