package com.WebService;

public class Order {
   private int idOrder;
   private int seatA;
   private int seatB;
   private int seatC;
   private int seatD;
   private int seatS;
   private String nameConcert;
   private String date;
   private String nameHall;
   private int idCustomer;
   private int idConcert;

   public Order()
   {
	   this.idOrder = -1;
   }
   
   public void setSeatA(int seatA) {
      this.seatA = seatA;
   }
   public int getSeatA() {
      return seatA;
   }

   public void setSeatB(int seatB) {
      this.seatB = seatB;
   }
   public int getSeatB() {
      return seatB;
   }
   
   public void setSeatC(int seatC) {
      this.seatC = seatC;
   }
   public int getSeatC() {
      return seatC;
   }
   
   public void setSeatD(int seatD) {
      this.seatD = seatD;
   }
   public int getSeatD() {
      return seatD;
   }
   
   public void setSeatS(int seatS) {
      this.seatS = seatS;
   }
   public int getSeatS() {
      return seatS;
   }
   
   public void setNameConcert(String nameConcert) {
      this.nameConcert = nameConcert;
   }
   public String getNameConcert() {
      return nameConcert;
   }
   
   public void setNameHall(String nameHall) {
      this.nameHall = nameHall;
   }
   public String getNameHall() {
      return nameHall;
   }
   
   public void setDate(String date) {
      this.date = date;
   }
   public String getDate() {
      return date;
   }
   
   public void setIdCustomer(int id) {
      this.idCustomer = id;
   }
   
   public int getIdCustomer() {
      return idCustomer;
   }
   
   public void setIdConcert(int id) {
      this.idConcert = id;
   }
   
   public int getIdConcert() {
      return idConcert;
   }
   
   public void setId(int id) {
      this.idOrder = id;
   }
   public int getId() {
      return idOrder;
   }
   
   public int getSeat(String rank)
   {
	   if(rank.equals("A"))
	   {
		   return seatA;
	   }
	   if(rank.equals("B"))
	   {
		   return seatB;
	   }
	   if(rank.equals("C"))
	   {
		   return seatC;
	   }
	   if(rank.equals("D"))
	   {
		   return seatD;
	   }
	   if(rank.equals("S"))
	   {
		   return seatS;
	   }
	   
	   return -1;
   }
}