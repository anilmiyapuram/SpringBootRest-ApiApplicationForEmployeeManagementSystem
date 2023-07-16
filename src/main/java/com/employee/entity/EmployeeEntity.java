package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class EmployeeEntity {

	@Id
	@GeneratedValue
	private int empId;
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
