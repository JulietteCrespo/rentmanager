package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_places());
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

	public long delete(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setLong(1, vehicle.getId());
			ps.execute();
			ps.close();
			connection.close();
			return vehicle.getId();

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pstatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			String constructeur = rs.getString("constructeur");
			int nbPlaces = rs.getInt("nb_places");
			Vehicle vehicle = new Vehicle(id,constructeur,nbPlaces);
			return vehicle;
		}catch ( SQLException e ) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);

			while(rs.next()){
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");
				vehicles.add(new Vehicle(id,constructeur,nbPlaces));
			}

		}catch (SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return vehicles;
		
	}



}