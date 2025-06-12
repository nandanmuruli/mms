package com.medicalManagement.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.medicalManagement.model.Appointment;
import com.medicalManagement.model.Room;

public interface AppointmentDAO {
	List<Appointment> getAppointments(int pageNo, int pageSize) throws SQLException;
    Appointment getAppointmentById(int appointmentId) throws SQLException;
    boolean addAppointment(Appointment appointment) throws SQLException;
    boolean updateAppointment(Appointment appointment) throws SQLException;
    boolean deleteAppointment(int appointmentId) throws SQLException;
    int getTotalAppointments() throws SQLException;

    // Room booking related
    List<Room> getRoomsByType(String roomType, int pageNo, int pageSize) throws SQLException;
    Room getRoomById(int roomId) throws SQLException;
    boolean addRoom(Room room) throws SQLException;
    boolean updateRoom(Room room) throws SQLException;
    boolean deleteRoom(int roomId) throws SQLException;

    // Check availability
    boolean isDoctorAvailable(int doctorId, Date slot) throws SQLException;
    boolean isRoomAvailable(int roomId, Date slot) throws SQLException;
	List<Appointment> getAllAppointments(int page, int size);
}
