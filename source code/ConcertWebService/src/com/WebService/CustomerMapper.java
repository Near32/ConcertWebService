package com.WebService;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {
   public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
      Customer customer = new Customer();
      customer.setId(rs.getInt("idCustomer"));
      customer.setFirstName(rs.getString("firstName"));
      customer.setLastName(rs.getString("lastName"));
      customer.setEmail(rs.getString("email"));
      customer.setAddress(rs.getString("address"));
      customer.setPhoneNumber(rs.getString("phoneNumber"));
      customer.setPassword(rs.getString("password"));
      return customer;
   }
}