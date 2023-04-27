package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VehicleServiceTest {
    private VehicleService vehicleService;
    private VehicleDao vehicleDao;
    @BeforeEach
    void init(){
        vehicleDao = mock(VehicleDao.class);
        vehicleService = new VehicleService(vehicleDao);
    }
    @Test
    void findAll_doit_echouer_lorsque_dao_lance_une_exception() throws DaoException {
        when(this.vehicleDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> vehicleService.findAll());
    }
    @Test
    void findById_succes() throws DaoException {
        Vehicle vehicle = new Vehicle(1,"Renault", 4);
        when(this.vehicleDao.findById(1)).thenReturn(vehicle);
        Vehicle findByIdVehicle = vehicleDao.findById(1);
        assertEquals(vehicle, findByIdVehicle);
    }
    @Test
    void findAll_succes() throws DaoException {
        List<Vehicle> listeVehicle = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            listeVehicle.add(new Vehicle());
        }
        when(this.vehicleDao.findAll()).thenReturn(listeVehicle);
        assertEquals(listeVehicle, vehicleDao.findAll());
    }
}



