package com.WebService;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ConcertJDBCTemplate implements ConcertDAO {
   @Autowired
	private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }
   
   /*
   public void create(String name, String players, String description, int idHall, String date) {
      String SQL = "insert into ConcertTable (idConcert,name, players, description, HallTable_idHall, date) values (?, ?, ?, ?, ?, ?)";
      int id = this.getConcertCount();
      jdbcTemplateObject.update( SQL, id, name, players, description, idHall, date);
      System.out.println("Created Record Name = " + name + " " + players + " description = " + description + " etc...");
      return;
   }
   */

   public Concert getConcert(int id) {
      String SQL = "select * from ConcertTable where idConcert = ?";
      Concert concert = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{id}, new ConcertMapper());
      return concert;
   }

   public String getHallNameForConcert(int idConcert) 
   {
      String SQL0 = "select HallTable_idHall from ConcertTable where idConcert = ?";
      int idHall = jdbcTemplateObject.queryForInt( SQL0, new Object[]{idConcert} );
	   
      String SQL = "select name from HallTable where idHall = ?";
      String name = jdbcTemplateObject.queryForObject(SQL, new Object[]{idHall}, String.class);
      
      return name;
   }
   
   public int getNumberSeatForConcert(int idConcert, String rank)
   {
	   String SQL0 = "select HallTable_idHall from ConcertTable where idConcert = ?";
	   int idHall = jdbcTemplateObject.queryForInt( SQL0, new Object[]{idConcert} ); 
	   
	   String SQL1 = "select seatCapacityRank"+rank+" from HallTable where idHall = ?";
	   int nbrSeatForRank = jdbcTemplateObject.queryForInt( SQL1, new Object[]{idHall} );
	   
	   return nbrSeatForRank;
   }
   
   public List<Concert> getConcertsList() {
      String SQL = "select * from ConcertTable";
      List <Concert> concerts = jdbcTemplateObject.query(SQL, 
                                new ConcertMapper());
      
      for( Concert concert : concerts)
	  {
		  concert.setHallName( this.getHallNameForConcert( concert.getId() ) );
	  }
      
      
      return concerts;
   }

   	public int getConcertCount()
   {
	   String SQL = "select count(*) from ConcertTable";
	   int rowCount = jdbcTemplateObject.queryForInt( SQL );
	   return rowCount;
   }
   	
   public void delete(int id){
      String SQL = "delete from ConcertTable where idConcert = ?";
      jdbcTemplateObject.update(SQL, id);
      System.out.println("Deleted Record with ID = " + id );
      return;
   }

   public void update(int id, int remainingSeats){
      String SQL = "update ConcertTable set remainingSeats = ? where idConcert = ?";
      jdbcTemplateObject.update(SQL, remainingSeats, id);
      System.out.println("Updated Record with ID = " + id );
      return;
   }

}