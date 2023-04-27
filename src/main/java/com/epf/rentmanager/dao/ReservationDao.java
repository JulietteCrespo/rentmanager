package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;
@Repository
public class ReservationDao {
	private ReservationDao() {}
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			int id = resultSet.getInt(1);
			ps.close();
			connection.close();
			return id;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	public long delete(int id_reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			ps.setLong(1, id_reservation);
			if(ps.executeUpdate()!=0){
				ps.close();
				connection.close();
				return 1;
			} else{
				ps.close();
				connection.close();
				return 0;
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
	public Reservation findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			long client_id = rs.getLong("client_id");
			long vehicle_id = rs.getLong("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			Reservation reservation = new Reservation(id,client_id,vehicle_id,debut,fin);
			return reservation;
		}catch ( SQLException e ) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			pstatement.setLong(1,clientId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservation.add(new Reservation(id,clientId,vehicle_id,debut,fin));
			}
			return reservation;
		}catch ( SQLException e ) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			pstatement.setLong(1,vehicleId);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservation.add(new Reservation(id,client_id,vehicleId,debut,fin));
			}
			return reservation;
		}catch ( SQLException e ) {
			e.printStackTrace();
			throw new DaoException();
		}
	}
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				long client_id = rs.getLong("client_id");
				long vehicle_id = rs.getLong("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservation.add(new Reservation(id,client_id,vehicle_id,debut,fin));
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservation;
	}
	public long edit(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(UPDATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, reservation.getClient_id());
			ps.setLong(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.setLong(5, reservation.getId());
			ps.execute();
			ps.close();
			connection.close();
			return reservation.getId();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
}
