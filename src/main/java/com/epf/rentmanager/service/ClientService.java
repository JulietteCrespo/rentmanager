package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	private ClientDao clientDao;
	ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	public long delete(int Id_client) throws ServiceException {
		try{
			return this.clientDao.delete(Id_client);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public long create(Client client) throws ServiceException {
		try{
			return this.clientDao.create(client);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public long edit(Client client) throws ServiceException {
		try{
			return this.clientDao.edit(client);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public Client findById(long id) throws ServiceException {
		if (id<0){
			throw new ServiceException("l'id est inferieur a 0");
		}
		try{
			return this.clientDao.findById(id);
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public List<Client> findAll() throws ServiceException {
		try{
			return this.clientDao.findAll();
		}catch(DaoException e) {
			e.printStackTrace();
			throw  new ServiceException();
		}
	}
	public long count() throws ServiceException {
		return findAll().size();
	}
}
