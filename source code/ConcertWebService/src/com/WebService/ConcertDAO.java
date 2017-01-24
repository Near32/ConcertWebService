package com.WebService;

import java.util.List;
import javax.sql.DataSource;

public interface ConcertDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   
   /** 
    * This is the method to be used to create
    * a record in the CustomerTable table.
    */
   /*
   public void create(String name, String players, String description, int idHall, String date);
   */
   /** 
    * This is the method to be used to list down
    * a record from the ConcertTable table corresponding
    * to a passed Concert id.
    */
   public Concert getConcert(int id);
   /** 
    * This is the method to be used to list down
    * all the records from the ConcertTable table.
    */
   public List<Concert> getConcertsList();
   /** 
    * This is the method to be used to delete
    * a record from the ConcertTable table corresponding
    * to a passed Concert id.
    */
   public void delete(int id);
   /** 
    * This is the method to be used retrieve
    * the number of rows in ConcertTable table.
    */
   public int getConcertCount();
   /** 
    * This is the method to be used to update
    * a record into the ConcertTable table.
    */
   public void update(int id, int remainingSeats);
}