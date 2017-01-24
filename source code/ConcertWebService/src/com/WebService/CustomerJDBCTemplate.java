package com.WebService;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerJDBCTemplate implements CustomerDAO {
   @Autowired
	private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void create(String firstName, String lastName, String email, String address, String phoneNumber, String password) {
      String SQL = "insert into CustomerTable (idCustomer,firstName, lastName, email, address, phoneNumber, password) values (?, ?, ?, ?, ?, ?, ?)";
      int id = this.getCustomerMaxId()+1;
      jdbcTemplateObject.update( SQL, id, firstName, lastName, email, address, phoneNumber, password);
      System.out.println("Created Record Name = " + firstName + " " + lastName + " email = " + email + " etc...");
      return;
   }

   public Customer getCustomer(String email, String password)
   {
	   String SQL = "select * from CustomerTable where email = ? and password = ?";
	   Customer customer = new Customer();
	   
	   try
	   {
		   customer = jdbcTemplateObject.queryForObject(SQL,new Object[]{email,password}, new CustomerMapper());
	   }
	   catch(Exception e)
	   {
		   //if an exception is caugth, it means that, for instance, the query return something empty, so the customer does not exist.
		   // we return an invalid customer (by default, handle by the default constructor...).
	   }
			   			
	   return customer;
   }
   
   public Customer getCustomer(int id) {
      String SQL = "select * from CustomerTable where idCustomer = ?";
      Customer customer = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new CustomerMapper());
      return customer;
   }

   public List<Customer> listCustomers() {
      String SQL = "select * from CustomerTable";
      List <Customer> customers = jdbcTemplateObject.query(SQL, 
                                new CustomerMapper());
      return customers;
   }

   	public int getCustomerCount()
   {
	   String SQL = "select count(*) from CustomerTable";
	   int rowCount = jdbcTemplateObject.queryForInt( SQL );
	   return rowCount;
   }
   	
   	public int getCustomerMaxId()
   	{
   		String SQL = "select max(idCustomer) from CustomerTable";
 	   int rowCount = jdbcTemplateObject.queryForInt( SQL );
 	   return rowCount;
   	}
   	
   public void delete(int id){
      String SQL = "delete from CustomerTable where idCustomer = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }

   public void update(int id, String email){
      String SQL = "update CustomerTable set email = ? where idCustomer = ?";
      jdbcTemplateObject.update(SQL, email, id);
      System.out.println("Updated Record with ID = " + id );
      return;
   }

}