package com.unibrain.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibrain.Dao.EmployeeDaoImp;
import com.unibrain.Dao.EmployeeDao;
import com.unibrain.Entity.Employee;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Override
	public Boolean isDataExistForEmpId(Integer id) {

		return employeeDao.isDataExistForEmpId(id);
	}

	private static final String Employee = null;
	@Autowired
	private EmployeeDaoImp employeeDao;

	@Override
	public boolean saveEmployee(Employee Employee) {
		return employeeDao.saveEmployee(Employee);
	}

	public Employee getEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public void deleteEmployee(Integer id) {
		employeeDao.deleteEmployee(id);

	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);

	}

	@Override
	public Employee getEmployee(Integer id) {
		return employeeDao.getEmployee(id);
	}

	/*
	 * @Override
	 * 
	 * public List<Employee> loadEmp() {
	 * 
	 * return employeeDao.loadEmp(); }
	 */
	@Override
	public List<Employee> serach(HashMap<String, String> searchMap, int start) {
		return employeeDao.serach(searchMap, start);
	}

	@Override
	public List<Employee> loadEmp(int start) {

		return employeeDao.loadEmp(start);
	}

	@Override
	public long employeeCount() {
		return employeeDao.employeeCount();
	}

	@Override
	public long countForSearch(HashMap<String, String> searchMap) {
		return employeeDao.countForSearch(searchMap);
	}

	@Override
	public com.unibrain.Entity.Employee getEmployee1(Integer id) {
	
			return employeeDao.getEmployee1(id);
		}

	@Override
	public List<Employee> search(HashMap<String, String> searchMap) {
		return employeeDao.search(searchMap);
	}
	}


