package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.EmployeeEntity;
import com.employee.repo.EmployeeRepo;


@Service
public class EmployeeServiceImplementation implements EmployeeService {
	@Autowired
	private EmployeeRepo empRepo;
	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity emp) {
		double ta = 0.0,
				da = 0.0,
				hra = 0.0,
				pf = 0.0,
				grossSal = 0.0,
				netSal = 0.0,
				sal = 0.0;
		sal = emp.getEmpSalary();
		if(sal< 30000) {
			ta = sal*7/100;
			da = sal*9/100;
			hra = sal*11/100;
			pf = sal*15/100;
		}
		else if(sal>=30000 && sal<50000) {
			ta = sal*12/100;
			da = sal*13/100;
			hra = sal*17/100;
			pf = sal*22/100;
		}
		else {
			ta = sal*17/100;
			da = sal*19/100;
			hra = sal*21/100;
			pf = sal*25/100;
		}
		grossSal = sal+ta+da+hra;
		netSal = grossSal - pf;
		double roundPf = Math.round(pf*100)/100.0;
		double roundTa = Math.round(ta*100)/100.0;
		double roundDa = Math.round(da*100)/100.0;
		double roundHra = Math.round(hra*100)/100.0;
		double roundGross = Math.round(grossSal*100)/100.0;
		double roundNetSal = Math.round(netSal*100)/100.0;
		double roundSal = Math.round(sal*100)/100.0;

		emp.setEmpSalary(roundSal);
		emp.setDa(roundDa);
		emp.setGrossSal(roundGross);
		emp.setNetSal(roundNetSal);
		emp.setPf(roundPf);
		emp.setTa(roundTa);
		emp.setHra(roundHra);


		Integer empId = emp.getEmpId();
		Optional<EmployeeEntity> existingEmployee = empRepo.findById(empId);
		if (existingEmployee.isPresent()) {
			throw new IllegalArgumentException("Employee with ID " + empId + " already exists");
		}
		return empRepo.save(emp);

	}
	@Override
	public EmployeeEntity updateEmp(EmployeeEntity emp,Integer empId) {
		EmployeeEntity emps = empRepo.findById(empId).get();
		emps.setEmpName(emp.getEmpName());
		emps.setEmpDesignation(emp.getEmpDesignation());
		emps.setEmpLocation(emp.getEmpLocation());
		emps.setEmpSalary(emp.getEmpSalary());	
		return empRepo.save(emps);
		}
	@Override
	public boolean deleteEmpRecord(Integer empId) {
		if(empRepo.existsById(empId)) {
		empRepo.deleteById(empId);
		return true;
		}
		else
			return false;

	}
	@Override
	public List<EmployeeEntity> getAllRecords() {
		List<EmployeeEntity> emp = empRepo.findAll();
		return emp;
	}
	@Override
	public EmployeeEntity getOneRecordOfEmp(Integer empId) {
		 
		return empRepo.findById(empId).get();
	}
	@Override
	public void deleteAllEmployeesRecords() {
		empRepo.deleteAll();
		
	}


}
