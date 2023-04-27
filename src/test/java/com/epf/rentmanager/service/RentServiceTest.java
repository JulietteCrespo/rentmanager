package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;
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

public class RentServiceTest {
    private ReservationService reservationService;
    private ReservationDao reservationDao;
    @BeforeEach
    void init(){
        reservationDao = mock(ReservationDao.class);
        reservationService = new ReservationService(reservationDao);
    }
    @Test
    void findAll_doit_echouer_lorsque_dao_lance_une_exception() throws DaoException {
        when(this.reservationDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> reservationService.findAll());
    }
    @Test
    void findById_succes() throws DaoException {
        Reservation reservation = new Reservation(1,1,1,LocalDate.of(2020, Month.NOVEMBER, 9),LocalDate.of(2020, Month.NOVEMBER, 11));
        when(this.reservationDao.findById(1)).thenReturn(reservation);
        Reservation findByIdVehicle = reservationDao.findById(1);
        assertEquals(reservation, findByIdVehicle);
    }
    @Test
    void findAll_succes() throws DaoException {
        List<Reservation> listeReservation = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            listeReservation.add(new Reservation());
        }
        when(this.reservationDao.findAll()).thenReturn(listeReservation);
        assertEquals(listeReservation, reservationDao.findAll());
    }
}
