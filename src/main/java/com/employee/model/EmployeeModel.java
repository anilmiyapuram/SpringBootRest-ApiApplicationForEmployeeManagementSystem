package com.employee.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class EmployeeModel {
	
	private String empName;
	private String empDesignation;
	private double empSalary;
	private String empLocation;
	private double ta;
	private double da;
	private double hra;
	private double pf;
	private double grossSal;
	private double netSal;

}
