package com.unibrain.Dao;

import java.util.HashMap;
import java.util.List;

import com.unibrain.Entity.Employee;

public interface EmployeeDao {

	public boolean saveEmployee(Employee employee);

	void deleteEmployee(Integer id);

	public Employee updateEmployee(Employee employee);
	public List<Employee> serach(HashMap<String, String> searchMap,int start);

	public  Employee getEmployee(Integer id);
	public Boolean isDataExistForEmpId(Integer id);

	public long employeeCount();

	public List<Employee> loadEmp(int start);

	public long countForSearch(HashMap<String, String> searchMap);
	public  Employee getEmployee1(Integer id);
	
	public List<Employee> search(HashMap<String, String> searchMap);

}
