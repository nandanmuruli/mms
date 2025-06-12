package com.medicalManagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.medicalManagement.model.Appointment;
import com.medicalManagement.model.Room;
import com.medicalManagement.utils.DBConnection;

public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public List<Appointment> getAppointments(int pageNo, int pageSize) throws SQLException {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointment ORDER BY slot LIMIT ? OFFSET ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (pageNo - 1) * pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment app = mapRowToAppointment(rs);
                list.add(app);
            }
        }
        return list;
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM appointment WHERE appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRowToAppointment(rs);
            }
        }
        return null;
    }

    @Override
    public boolean addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointment(slot, patient_id, patient_name, visit_description, doctor_id, doctor_name, specialist, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(appointment.getSlot().getTime()));
            if (appointment.getPatientId() != null) ps.setInt(2, appointment.getPatientId());
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, appointment.getPatientName());
            ps.setString(4, appointment.getVisitDescription());
            if (appointment.getDoctorId() != null) ps.setInt(5, appointment.getDoctorId());
            else ps.setNull(5, Types.INTEGER);
            ps.setString(6, appointment.getDoctorName());
            ps.setString(7, appointment.getSpecialist());
            ps.setString(8, appointment.getStatus());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointment SET slot = ?, patient_id = ?, patient_name = ?, visit_description = ?, doctor_id = ?, doctor_name = ?, specialist = ?, status = ? WHERE appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(appointment.getSlot().getTime()));
            if (appointment.getPatientId() != null) ps.setInt(2, appointment.getPatientId());
            else ps.setNull(2, Types.INTEGER);
            ps.setString(3, appointment.getPatientName());
            ps.setString(4, appointment.getVisitDescription());
            if (appointment.getDoctorId() != null) ps.setInt(5, appointment.getDoctorId());
            else ps.setNull(5, Types.INTEGER);
            ps.setString(6, appointment.getDoctorName());
            ps.setString(7, appointment.getSpecialist());
            ps.setString(8, appointment.getStatus());
            ps.setInt(9, appointment.getAppointmentId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public int getTotalAppointments() throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointment";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // NOTE: It is better practice to have Room methods in a separate RoomDAO
    @Override
    public List<Room> getRoomsByType(String roomType, int pageNo, int pageSize) throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE room_type = ? ORDER BY room_id LIMIT ? OFFSET ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, roomType);
            ps.setInt(2, pageSize);
            ps.setInt(3, (pageNo - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoom_id(rs.getInt("room_id"));
                room.setRoom_type(rs.getString("room_type"));
                room.setRoom_number(rs.getString("room_number"));
                room.setRoom_availability(rs.getString("room_availability"));
                list.add(room);
            }
        }
        return list;
    }

    @Override
    public Room getRoomById(int roomId) throws SQLException {
        String sql = "SELECT * FROM room WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Room room = new Room();
                room.setRoom_id(rs.getInt("room_id"));
                room.setRoom_type(rs.getString("room_type"));
                room.setRoom_number(rs.getString("room_number"));
                room.setRoom_availability(rs.getString("room_availability"));
                return room;
            }
        }
        return null;
    }

    @Override
    public boolean addRoom(Room room) throws SQLException {
        String sql = "INSERT INTO room(room_type, room_number, room_availability) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, room.getRoom_type());
            ps.setString(2, room.getRoom_number());
            ps.setString(3, room.getRoom_availability());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateRoom(Room room) throws SQLException {
        String sql = "UPDATE room SET room_type = ?, room_number = ?, room_availability = ? WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, room.getRoom_type());
            ps.setString(2, room.getRoom_number());
            ps.setString(3, room.getRoom_availability());
            ps.setInt(4, room.getRoom_id());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteRoom(int roomId) throws SQLException {
        String sql = "DELETE FROM room WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean isDoctorAvailable(int doctorId, Date slot) throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND slot = ? AND status = 'confirmed'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setTimestamp(2, new Timestamp(slot.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // available if no appointment at that slot
            }
        }
        return false;
    }

    @Override
    public boolean isRoomAvailable(int roomId, Date slot) throws SQLException {
        // NOTE: This assumes a table named 'room_booking' exists.
        String sql = "SELECT COUNT(*) FROM room_booking WHERE room_id = ? AND slot = ? AND status = 'booked'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            ps.setTimestamp(2, new Timestamp(slot.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    // FIX: Corrected and simplified the getAllAppointments method.
    // It now reuses the getAppointments method for consistency.
    @Override
    public List<Appointment> getAllAppointments(int page, int size) throws SQLException {
        // This method can simply call the other paginated method.
        return getAppointments(page, size);
    }

    private Appointment mapRowToAppointment(ResultSet rs) throws SQLException {
        Appointment app = new Appointment();
        app.setAppointmentId(rs.getInt("appointment_id"));
        app.setSlot(rs.getTimestamp("slot")); // Using getTimestamp to get both date and time
        app.setPatientId(rs.getInt("patient_id"));
        app.setPatientName(rs.getString("patient_name"));
        app.setVisitDescription(rs.getString("visit_description"));
        app.setDoctorId(rs.getInt("doctor_id"));
        app.setDoctorName(rs.getString("doctor_name"));
        app.setSpecialist(rs.getString("specialist"));
        app.setStatus(rs.getString("status"));
        return app;
    }
}