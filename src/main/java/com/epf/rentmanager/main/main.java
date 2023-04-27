package com.epf.rentmanager.main;


import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;


public class main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static ClientService clientService = context.getBean(ClientService.class);
    public static void main (String[] args){
        try{
            System.out.println(clientService.findById(1));
        }catch (ServiceException e){
            e.printStackTrace();
        }
    }
}
