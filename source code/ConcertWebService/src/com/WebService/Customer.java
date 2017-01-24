package com.WebService;

public class Customer {
   private String firstName;
   private String lastName;
   private String email;
   private String address;
   private String phoneNumber;
   private int idCustomer;
   private String password;

   public Customer()
   {
	   this.idCustomer = -1;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getFirstName() {
      return firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getLastName() {
      return lastName;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   public String getEmail() {
      return email;
   }
   
   public void setAddress(String address) {
      this.address = address;
   }
   public String getAddress() {
      return address;
   }
   
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }
   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setId(Integer id) {
      this.idCustomer = id;
   }
   public int getId() {
      return idCustomer;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   public String getPassword() {
      return password;
   }
}