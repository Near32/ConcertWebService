package com.WebService;

public class Concert {
   private String name;
   private String players;
   private String description;
   private String date;
   private String hallName;
   private int remainingSeats;
   private int idHall;
   private int idConcert;

   public Concert()
   {
	   this.idConcert = -1;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   public String getName() {
      return name;
   }

   public void setPlayers(String players) {
      this.players = players;
   }
   public String getPlayers() {
      return players;
   }
   
   public void setDescription(String description) {
      this.description = description;
   }
   public String getDescription() {
      return description;
   }
   
   public void setDate(String date) {
      this.date = date;
   }
   public String getDate() {
      return date;
   }
   
   public void setRemainingSeats(int remainingSeats) {
      this.remainingSeats = remainingSeats;
   }
   
   public int getRemainingSeats() {
      return remainingSeats;
   }
   
   public void setHallName(String hallName) {
      this.hallName = hallName;
   }
   public String getHallName() {
      return hallName;
   }
   
   public void setIdHall(int id) {
      this.idHall = id;
   }
   
   public int getIdHall() {
      return idHall;
   }
   
   public void setId(int id) {
      this.idConcert = id;
   }
   public int getId() {
      return idConcert;
   }
}