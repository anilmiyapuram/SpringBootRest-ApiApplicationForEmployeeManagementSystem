package com.employee.service;

import java.util.List;


import com.employee.entity.EmployeeEntity;

public interface EmployeeService {

	public EmployeeEntity saveEmployee(EmployeeEntity emp);
	public EmployeeEntity updateEmp(EmployeeEntity emp,Integer empId);
	public boolean deleteEmpRecord(Integer empId);
	public List<EmployeeEntity> getAllRecords();
	public EmployeeEntity getOneRecordOfEmp(Integer empId);
	public void deleteAllEmployeesRecords();
	
	
	
	
}
