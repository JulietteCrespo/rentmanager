package com.ensta.rentmanager.service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientDao clientDao;

    @Test
    void findAll_should_fail_when_dao_throws_exception() throws DaoException {

        when(this.clientDao.findAll()).thenThrow(DaoException.class);

        assertThrows(ServiceException.class, () -> clientService.findAll());

    }

}