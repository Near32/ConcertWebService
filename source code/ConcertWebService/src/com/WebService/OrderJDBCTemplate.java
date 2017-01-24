package com.WebService;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderJDBCTemplate implements OrderDAO {
   @Autowired
	private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void create(int seatA, int seatB, int seatC, int seatD, int seatS, int idCustomer, int idConcert) {
      String SQL = "insert into OrderTable (idOrder, quantitySeatA, quantitySeatB, quantitySeatC, quantitySeatD, quantitySeatS, CustomerTable_idCustomer, ConcertTable_idConcert) values (?, ?, ?, ?, ?, ?, ?, ?)";
      int id = this.getMaxId()+1;
      jdbcTemplateObject.update( SQL, id, seatA, seatB, seatC, seatD, seatS, idCustomer, idConcert);
      return;
   }
   
   public String getNameConcert(int idConcert)
   {
	   String SQL = "select name from ConcertTable where idConcert = ?";
	   String name = jdbcTemplateObject.queryForObject( SQL, new Object[]{idConcert}, String.class  );
	   return name;
   }
   
   public String getDateConcert(int idConcert)
   {
	   String SQL = "select date from ConcertTable where idConcert = ?";
	   String date = jdbcTemplateObject.queryForObject( SQL, new Object[]{idConcert}, String.class  );
	   return date;
   }
   
   public String getNameHall(int idConcert)
   {
	   String SQL0 = "select HallTable_idHall from ConcertTable where idConcert = ?";
	   int idHall = jdbcTemplateObject.queryForInt(SQL0, new Object[]{idConcert});
	   String SQL = "select name from HallTable where idHall = ?";
	   String name = jdbcTemplateObject.queryForObject( SQL, new Object[]{idHall}, String.class  );
	   return name;
   }
   
   public Order getOrder(int id) 
   {
      String SQL = "select * from OrderTable where idOrder = ?";
      Order order = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new OrderMapper());
      order.setNameConcert( this.getNameConcert( order.getIdConcert() ) );
	  order.setDate( this.getDateConcert( order.getIdConcert() ) );
	  order.setNameHall( this.getNameHall( order.getIdConcert() ) );
	  
      return order;
   }
   
   public List<Order> getOrdersListFromCustomer(int idCustomer)
   {
      String SQL = "select * from OrderTable where CustomerTable_idCustomer = ?";
      List <Order> orders = jdbcTemplateObject.query(SQL, 
    		  			new Object[]{idCustomer}, new OrderMapper());
      
      for( Order order : orders)
	  {
		  order.setNameConcert( this.getNameConcert( order.getIdConcert() ) );
		  order.setDate( this.getDateConcert( order.getIdConcert() ) );
		  order.setNameHall( this.getNameHall( order.getIdConcert() ) );
	  }
      
      return orders;
   }
   
   public List<Order> getOrdersListForConcert(int idConcert)
   {
      String SQL = "select * from OrderTable where ConcertTable_idConcert = ?";
      List <Order> orders = jdbcTemplateObject.query(SQL, 
    		  			new Object[]{idConcert}, new OrderMapper());
      
      for( Order order : orders)
	  {
		  order.setNameConcert( this.getNameConcert( order.getIdConcert() ) );
		  order.setDate( this.getDateConcert( order.getIdConcert() ) );
		  order.setNameHall( this.getNameHall( order.getIdConcert() ) );
	  }
      
      return orders;
   }

   	public int getOrderCount()
   {
	   String SQL = "select count(*) from OrderTable";
	   int rowCount = jdbcTemplateObject.queryForInt( SQL );
	   return rowCount;
   }
   	
	public int getMaxId()
	{
	   String SQL = "select max(idOrder) from OrderTable";
	   int rowCount = jdbcTemplateObject.queryForInt( SQL );
	   return rowCount;
	}
   	
   public void delete(int id){
      String SQL = "delete from OrderTable where idOrder = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }
}