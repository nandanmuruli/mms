package com.medicalManagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection conn;
	
	private static final String url = "jdbc:mysql://localhost:3306/doctor_management";
    private static final String user = "root";
    private static final String password = "@tiger123";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL Connector/J driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}



/*
 insert into user_details(full_name, email, password)
id, fullname, email, password

CREATE TABLE `hospital`.`user_details` (
  `id` INT NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);


insert into doctor(fullName,dateOfBirth,qualification,specialist,email,phone,password)
id,  fullName,dateOfBirth,qualification,specialist,email,phone,password

CREATE TABLE `hospital`.`doctor` (
  `id` INT NOT NULL,
  `fullName` VARCHAR(45) NULL,
  `dateOfBirth` VARCHAR(45) NULL,
  `qualification` VARCHAR(45) NULL,
  `specialist` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

insert into appointment(userId, fullName, gender, age, appointmentDate, email, phone, diseases, doctorId, address, status)
id, userId, fullName, gender, age, appointmentDate, email, phone, diseases, doctorId, address, status
CREATE TABLE `hospital`.`appointment` (
  `id` INT NOT NULL,
  `userId` INT NULL,
  `fullName` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `age` VARCHAR(45) NULL,
  `appointmentDate` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `diseases` VARCHAR(45) NULL,
  `doctorId` INT NULL,
  `address` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


insert into specialist (specialist_name)
id,  specialist_name

CREATE TABLE `hospital`.`specialist` (
  `id` INT NOT NULL,
  `specialist_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");


 */
