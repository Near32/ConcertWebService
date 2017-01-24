package com.WebService;

import java.util.List;
import javax.sql.DataSource;

public interface OrderDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the OrderTable table.
    */
   public void create( int seatA, int seatB, int seatC, int seatD, int seatS, int idCustomer, int idConcert);
   /** 
    * This is the method to be used to list down
    * a record from the OrderTable table corresponding
    * to a passed Order id.
    */
   public Order getOrder(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the OrderTable table related to a given customer.
    */
   public List<Order> getOrdersListFromCustomer(int idCustomer);
   /** 
    * This is the method to be used to delete
    * a record from the OrderTable table corresponding
    * to a passed Order id.
    */
   public void delete(int id);
   /** 
    * This is the method to be used retrieve
    * the number of rows in OrderTable table.
    */
   public int getOrderCount();
   /** 
    * This is the method to be used retrieve
    * the maximal id in OrderTable table.
    */
   public int getMaxId();
}