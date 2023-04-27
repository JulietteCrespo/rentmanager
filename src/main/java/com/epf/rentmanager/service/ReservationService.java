package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
    private ReservationDao reservationDao;
    ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
    public long create(Reservation reservation) throws ServiceException {
        try{
            return this.reservationDao.create(reservation);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {
        try{
            return this.reservationDao.findResaByClientId(clientId);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {
        try{
            return this.reservationDao.findResaByVehicleId(vehicleId);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public List<Reservation> findAll() throws ServiceException {
        try{
            return this.reservationDao.findAll();
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public long count() throws ServiceException {
        return findAll().size();

    }
    public Reservation findById(long id) throws ServiceException {

        try{
            return this.reservationDao.findById(id);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public long delete(int Id_reservation) throws ServiceException {
        try{
            return this.reservationDao.delete(Id_reservation);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
    public long edit(Reservation reservation) throws ServiceException {
        try{
            return this.reservationDao.edit(reservation);
        }catch(DaoException e) {
            e.printStackTrace();
            throw  new ServiceException();
        }
    }
}
