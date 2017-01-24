package com.WebService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ConcertMapper implements RowMapper<Concert> {
   public Concert mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Concert concert = new Concert();
	   concert.setId(rs.getInt("idConcert"));
	   concert.setName(rs.getString("name"));
	   concert.setPlayers(rs.getString("players"));
	   concert.setDescription(rs.getString("description"));
	   //concert.setRemainingSeats(rs.getInt("remainingSeats"));
	   concert.setIdHall(rs.getInt("HallTable_idHall"));
	   concert.setDate(rs.getString("date"));      
      return concert;
   }
}