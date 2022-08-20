package com.bridgelabz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public int updateSalary(String Name, double basic_pay ) throws SQLException {
		Connection connection = JDBCConnection.connectToDatabase();
		PreparedStatement preparedStatement = connection.prepareStatement("update employee_payroll set basic_pay = ? where name = ?");
		preparedStatement.setDouble(1,basic_pay);
		preparedStatement.setString(2,Name);
		int rowsAffected = preparedStatement.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("salary updated successfully!");
		}
		return rowsAffected;
	}


	public static void retrieveAllDataUsingPreparedStatemnt() throws SQLException {
		String select = "SELECT * FROM employee_payroll WHERE start BETWEEN CAST('2018-01-01' AS DATE) AND DATE(NOW());";
		Connection connection = null;
		try {
			connection = JDBCConnection.connectToDatabase();			
			PreparedStatement statement = connection.prepareStatement(select);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String salary = resultSet.getString("basic_pay");
				String start = resultSet.getString("start");

				System.out.println("Employee Details : "+ name + ", " + salary + ", " + start);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
	}
	public void getSumOfSalaryByMaleAndFemale() throws SQLException {
		Connection connection = JDBCConnection.connectToDatabase();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT gender, SUM(basic_pay) FROM employee_payroll GROUP BY gender;");
		System.out.println("gender count SUM(basic_pay)");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2) + "\t");
		}
	}
}
