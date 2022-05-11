package com.editay.bsps.dto;

public class DealerDetailsDto {
	//pojo
	   private Long id;
	   private String username;
	   private String email;
	   private String password;
	   private String firstname;
	   private String middlename;
	   private String surname;
	   private String phonenumber;
	   private String mobile;
	   private String address;
	   private String city;
	   private String state;
	   private String zipcode;
	   private String title;
	   private String token;
	   private byte[] documents;
	   private byte[] driverLicense;
	   private byte[] nin;
	   private byte[] passport;
	   private byte[] utilityBill;
	   private byte[] voteCards;

	   public DealerDetailsDto(Long id, String username, String email, String password, String firstname, String middlename, String surname, String phonenumber, String mobile, String address, String city, String state, String zipcode, String title, byte[] documents, byte[] driverLicense, byte[] nin, byte[] passport, byte[] utilityBill, byte[] voteCards) {
	      this.id = id;
	      this.username = username;
	      this.email = email;
	      this.password = password;
	      this.firstname = firstname;
	      this.middlename = middlename;
	      this.surname = surname;
	      this.phonenumber = phonenumber;
	      this.mobile = mobile;
	      this.address = address;
	      this.city = city;
	      this.state = state;
	      this.zipcode = zipcode;
	      this.title = title;
	      this.documents = documents;
	      this.driverLicense = driverLicense;
	      this.nin = nin;
	      this.passport = passport;
	      this.utilityBill = utilityBill;
	      this.voteCards = voteCards;
	   }

	   public Long getId() {
	      return this.id;
	   }

	   public void setId(Long id) {
	      this.id = id;
	   }

	   public String getUsername() {
	      return this.username;
	   }

	   public void setUsername(String username) {
	      this.username = username;
	   }

	   public String getEmail() {
	      return this.email;
	   }

	   public void setEmail(String email) {
	      this.email = email;
	   }

	   public String getPassword() {
	      return this.password;
	   }

	   public void setPassword(String password) {
	      this.password = password;
	   }

	   public String getFirstname() {
	      return this.firstname;
	   }

	   public void setFirstname(String firstname) {
	      this.firstname = firstname;
	   }

	   public String getMiddlename() {
	      return this.middlename;
	   }

	   public void setMiddlename(String middlename) {
	      this.middlename = middlename;
	   }

	   public String getSurname() {
	      return this.surname;
	   }

	   public void setSurname(String surname) {
	      this.surname = surname;
	   }

	   public String getPhonenumber() {
	      return this.phonenumber;
	   }

	   public void setPhonenumber(String phonenumber) {
	      this.phonenumber = phonenumber;
	   }

	   public String getMobile() {
	      return this.mobile;
	   }

	   public void setMobile(String mobile) {
	      this.mobile = mobile;
	   }

	   public String getAddress() {
	      return this.address;
	   }

	   public void setAddress(String address) {
	      this.address = address;
	   }

	   public String getCity() {
	      return this.city;
	   }

	   public void setCity(String city) {
	      this.city = city;
	   }

	   public String getState() {
	      return this.state;
	   }

	   public void setState(String state) {
	      this.state = state;
	   }

	   public String getZipcode() {
	      return this.zipcode;
	   }

	   public void setZipcode(String zipcode) {
	      this.zipcode = zipcode;
	   }

	   public String getTitle() {
	      return this.title;
	   }

	   public void setTitle(String title) {
	      this.title = title;
	   }

	   public byte[] getDocuments() {
	      return this.documents;
	   }

	   public void setDocuments(byte[] documents) {
	      this.documents = documents;
	   }

	   public byte[] getDriverLicense() {
	      return this.driverLicense;
	   }

	   public void setDriverLicense(byte[] driverLicense) {
	      this.driverLicense = driverLicense;
	   }

	   public byte[] getNin() {
	      return this.nin;
	   }

	   public void setNin(byte[] nin) {
	      this.nin = nin;
	   }

	   public byte[] getPassport() {
	      return this.passport;
	   }

	   public void setPassport(byte[] passport) {
	      this.passport = passport;
	   }

	   public byte[] getUtilityBill() {
	      return this.utilityBill;
	   }

	   public void setUtilityBill(byte[] utilityBill) {
	      this.utilityBill = utilityBill;
	   }

	   public byte[] getVoteCards() {
	      return this.voteCards;
	   }

	   public void setVoteCards(byte[] voteCards) {
	      this.voteCards = voteCards;
	   }

	   public String getToken() {
	      return this.token;
	   }

	   public void setToken(String token) {
	      this.token = token;
	   }
	}
