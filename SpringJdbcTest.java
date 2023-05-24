package jdbc.functions;
import java.util.Scanner;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class SpringJdbcTest {
	static DriverManagerDataSource datasource = new DriverManagerDataSource();
	static JdbcTemplate template=null;
	private static Scanner scan;
	
	static void establishConnection() {
	
		datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/soujdatabase");
		datasource.setUsername("root");
		datasource.setPassword("Aishwarya@12041996");
		
	}
	static void insertData(int deptno,String name,String loc) {
		template = new JdbcTemplate(datasource);
		String query = "INSERT INTO DEPARTMENTS (deptno,name,location) values"
				+ "(?,?,?)";
		int result = template.update(query,deptno,name,loc);
		if(result >0) {
			System.out.println(result + " row(s) has been inserted successfully.");
		}
		
	}
	static void updateDeptLoc(String newval) {
		template = new JdbcTemplate(datasource);
		String query = "UPDATE DEPARTMENTS SET location = ?";
		
		int result = template.update(query,newval);
		if(result >0) {
			System.out.println(result + " row(s) has been inserted successfully.");
		}
		
	}
	public static void main(String[] args) {
		establishConnection();
		scan = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("Enter the department id");
			int deptno=scan.nextInt();
			System.out.println("Enter the department name");
		String name=scan.next();
		System.out.println("Enter the department location");
		String location=scan.next();
		insertData(deptno,name,location);
		}
		while(true);
		
		}
	
	}


