package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validator.ValidatorRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/rents/create")
public class RentCreateServlet extends RentListServlet {
    private static final long serialVersionUID = 1L;
    ValidatorRent validatorRent;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException
    {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.setAttribute("vehicles",vehicleService.findAll());
            request.setAttribute("clients",clientService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest   request,   HttpServletResponse response)
            throws       ServletException,       IOException       {
        try {
            final Reservation reservation = new Reservation();
            int vehicle_id = Integer.valueOf(request.getParameter("car"));
            int client_id = Integer.valueOf(request.getParameter("client"));
            String debut = request.getParameter("begin");
            String fin = request.getParameter("end");
            reservation.setClient_id(client_id);
            reservation.setVehicle_id(vehicle_id);
            reservation.setDebut(LocalDate.parse(debut));
            reservation.setFin(LocalDate.parse(fin));
            if(validatorRent.jour_libre(reservation) && validatorRent.plus_de_30j(reservation) && validatorRent.rent_plus_de_7(reservation) && validatorRent.date_valide(reservation)){
                reservationService.create(reservation);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("../rents");
    }
}