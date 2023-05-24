package jdbc.functions;
//program to execute the data from table to java program


	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.List;

	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.RowMapper;
	import org.springframework.jdbc.datasource.DriverManagerDataSource;

	public class TestDepartmentJdbcTemplate {
		static DriverManagerDataSource datasource =new DriverManagerDataSource();
		static JdbcTemplate template = null;
		
		static void getConnection() {//method for connection to database
			datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			datasource.setUrl("jdbc:mysql://localhost:3306/soujdatabase");
			datasource.setUsername("root");
			datasource.setPassword("Aishwarya@12041996");
		template=new JdbcTemplate(datasource); //no need of writing in all methods if it is mentioned in connection
		}
		static void insertData(int deptno,String name,String loc) {
			
			String insert_query="Insert into Departments"
					+ "(deptno,name,location) values"
				    + " (?,?,?)";
			int result=template.update(insert_query,deptno,name,loc);
			if(result>0) {
				System.out.println(result+"row(s) has been inserted successfully");
			}
		}
		
		static void fetchAllRecords() {
			String select_all="SELECT * FROM DEPARTMENTS";
	
			RowMapper<Departments>rowmapper= new RowMapper<Departments>() {//getting data from sql database to java using rowmapping
				
				public Departments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer deptno=rs.getInt("deptno");
				String name=rs.getString("name");
				String location=rs.getString("location");
					return new Departments(deptno, name, location) ;
				}
			};
			
			List<Departments>dpt=template.query(select_all, rowmapper); // select_all gives all records so we create list//
		System.out.println("Dept No.\t\tName\t\tLocation");
		for(Departments d:dpt) {
			System.out.println(d.deptno+"\t\t"+d.name+"\t\t"+d.location);
		}
		}
		public static void main(String[] args) {
			getConnection();
			fetchAllRecords();
		}
	}

