package com.employee.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.EmployeeEntity;
import com.employee.service.EmployeeService;


@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService empService;

	@PostMapping("/save")
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeEntity emp) {


		try {
			empService.saveEmployee(emp);

			return ResponseEntity.status(HttpStatus.CREATED).body("reocrd inserted sucessfully");

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PutMapping("/update/{empId}")
	public  ResponseEntity<?> updateEmp( @RequestBody EmployeeEntity emp,@PathVariable Integer empId) {
		empService.updateEmp(emp, empId);
		return ResponseEntity.ok("employee record updated successfully...");

	}
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId) {

		boolean isDeletedRecord = empService.deleteEmpRecord(empId);
		if(isDeletedRecord) 
			return ResponseEntity.ok("Employee Record deleted successfully..");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Employee Record Found,plz try with valid data");

	}
	@GetMapping("/getall")
	public ResponseEntity<?> getAllRec(){
		List<EmployeeEntity> employee =  empService.getAllRecords();
		if(employee.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee records found..");
		}
		return ResponseEntity.ok(employee);

	}
	@GetMapping("/getone/{empId}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer empId) {
		try {
			EmployeeEntity employee =empService.getOneRecordOfEmp(empId);
			return ResponseEntity.ok(employee);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee ID not found , please try with valid credentials...");
		}
	}
	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAllEmployeeRecords(){

		List<EmployeeEntity> employee =  empService.getAllRecords();
		if(employee.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee records found..");

		}
		else
			empService.deleteAllEmployeesRecords();
		return ResponseEntity.ok("All records deleted..");


 
	}


}


