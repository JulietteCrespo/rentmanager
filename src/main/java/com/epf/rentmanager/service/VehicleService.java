package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	private VehicleDao vehicleDao;
	public VehicleService( VehicleDao vehicleDao){
		this.vehicleDao = vehicleDao;
	}
	public long create(Vehicle vehicle) throws ServiceException {
		try{
			return this.vehicleDao.create(vehicle);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public Vehicle findById(long id) throws ServiceException {
		if (id<0){
			throw new ServiceException("l'id est inferieur a 0");
		}
		try{
			return this.vehicleDao.findById(id);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public List<Vehicle> findAll() throws ServiceException {
		try{
			return this.vehicleDao.findAll();
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public long count() throws ServiceException {
		return findAll().size();

	}
	public long delete(int id_vehicle) throws ServiceException {
		try{
			return this.vehicleDao.delete(id_vehicle);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public long edit(Vehicle vehicle) throws ServiceException {
		try{
			return this.vehicleDao.edit(vehicle);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
}
