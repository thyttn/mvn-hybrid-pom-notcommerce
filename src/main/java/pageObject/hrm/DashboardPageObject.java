package pageObject.hrm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.MySqLConnUtils;
import pageUIs.hrm.CommonPageUI;

public class DashboardPageObject extends BasePage {
	WebDriver driver;

	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getEmpListInDB() {
		ArrayList<String> empList = new ArrayList<>();
		String sql = "SELECT * FROM automationfc.employee;";

		try {
			Connection conn = MySqLConnUtils.getMySQLConnection();
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				empList.add(rs.getString("FIRST_NAME"));
				System.out.println("FIRST_NAME: " + rs.getString("FIRST_NAME"));	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empList.size();
	}
}