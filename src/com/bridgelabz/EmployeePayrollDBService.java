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
}
