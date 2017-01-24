package com.WebService;

import java.util.List;
import javax.sql.DataSource;

public interface CustomerDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the CustomerTable table.
    */
   public void create(String firstName, String lastName, String email, String address, String phoneNumber, String password);
   /** 
    * This is the method to be used to list down
    * a record from the CustomerTable table corresponding
    * to a passed Customer email and password.
    */
   public Customer getCustomer(String email, String password);
   /** 
    * This is the method to be used to list down
    * a record from the CustomerTable table corresponding
    * to a passed Customer id.
    */
   public Customer getCustomer(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the CustomerTable table.
    */
   public List<Customer> listCustomers();
   /** 
    * This is the method to be used to delete
    * a record from the CustomerTable table corresponding
    * to a passed Customer id.
    */
   public void delete(int id);
   /** 
    * This is the method to be used retrieve
    * the number of rows in CustomerTable table.
    */
   public int getCustomerCount();
   /** 
    * This is the method to be used to update
    * a record into the CustomerTable table.
    */
   public void update(int id, String email);
}