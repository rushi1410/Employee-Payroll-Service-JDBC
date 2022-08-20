package com.bridgelabz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
	public List<EmployeePayrollData> retrieveData() throws EmployeePayrollException {
		try {
			List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
			Connection connection = JDBCConnection.connectToDatabase();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM employee_payroll");
			while (resultSet.next()) {
				String ID = resultSet.getString("Id");
				String Name = resultSet.getString("Name");
				String salary = resultSet.getString("basic_pay");

				System.out.println("Employee Details : " + ID + ", " + Name + ", " + salary);
			}
			connection.close();
			;
			return employeePayrollDataList;
		} catch (Exception e) {
			throw new EmployeePayrollException();
		}
	}
}
