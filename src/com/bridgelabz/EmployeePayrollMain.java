package com.bridgelabz;

import java.sql.SQLException;

public class EmployeePayrollMain {
	public static void main(String[] args) throws EmployeePayrollException, SQLException {
        EmployeePayrollDBService employeePayrollService = new EmployeePayrollDBService();
        System.out.println("Rerieve Employee Data From Database");
        employeePayrollService.retrieveData();
        
        System.out.println("\nUpdated Basic Pay For Employee");
        employeePayrollService.updateSalary("Terisa", 3000000);
	}
}
