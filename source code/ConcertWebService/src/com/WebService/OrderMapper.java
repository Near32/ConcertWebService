package com.WebService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderMapper implements RowMapper<Order> {
   public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
	   Order order = new Order();
	   order.setId(rs.getInt("idOrder"));
	   order.setSeatA(rs.getInt("quantitySeatA"));
	   order.setSeatB(rs.getInt("quantitySeatB"));
	   order.setSeatC(rs.getInt("quantitySeatC"));
	   order.setSeatD(rs.getInt("quantitySeatD"));
	   order.setSeatS(rs.getInt("quantitySeatS"));
	   order.setIdCustomer(rs.getInt("CustomerTable_idCustomer"));
	   order.setIdConcert(rs.getInt("ConcertTable_idConcert"));
      return order;
   }
}