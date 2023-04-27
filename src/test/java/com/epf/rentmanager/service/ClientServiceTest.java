package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;
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

public class ClientServiceTest {
    private ClientService clientService;
    private ClientDao clientDao;
    @BeforeEach
    void init(){
        clientDao = mock(ClientDao.class);
        clientService = new ClientService(clientDao);
    }
    @Test
    void findAll_doit_echouer_lorsque_dao_lance_une_exception() throws DaoException {
        when(this.clientDao.findAll()).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }
    @Test
    void findById_doit_echouer_lorsque_dao_lance_une_exception() throws DaoException {
        when(this.clientDao.findById(0)).thenThrow(DaoException.class);
        assertThrows(ServiceException.class, () -> clientService.findById(0));
    }
    @Test
    void findById_succes() throws DaoException {
        Client client = new Client(1,"Afleck", "Steeve", "steeve.afleck@email.com", LocalDate.of(2000, Month.NOVEMBER, 9));
        when(this.clientDao.findById(1)).thenReturn(client);
        Client findByIdClient = clientDao.findById(1);
        assertEquals(client, findByIdClient);
    }
    @Test
    void findAll_succes() throws DaoException {
        List<Client> listeClient = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            listeClient.add(new Client());
        }
        when(this.clientDao.findAll()).thenReturn(listeClient);
        assertEquals(listeClient, clientDao.findAll());
    }
}


