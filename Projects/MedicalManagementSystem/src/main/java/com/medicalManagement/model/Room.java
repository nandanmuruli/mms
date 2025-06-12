package com.medicalManagement.model;

import java.time.LocalDateTime;

public class Room {
	private int roomId;
	private String roomAvailability;
	private String roomType;
	private String roomNumber;
	private int patientId;
	private String patientName;
	private LocalDateTime checkIn; // FIX: Renamed from CheckIn
	private LocalDateTime checkOut; // FIX: Renamed from CheckOut

	public Room(int roomId, String roomAvailability, String roomType, String roomNumber) {
		this.roomId = roomId;
		this.roomAvailability = roomAvailability;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
	}

	public Room() {}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(String roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public LocalDateTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDateTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDateTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDateTime checkOut) {
		this.checkOut = checkOut;
	}
}