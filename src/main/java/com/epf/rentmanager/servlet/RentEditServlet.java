package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rents/edit")
public class RentEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("rent",reservationService.findById(id));
            request.setAttribute("vehicles",vehicleService.findAll());
            request.setAttribute("clients",clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            int vehicle_id = Integer.valueOf(request.getParameter("car"));
            int client_id = Integer.valueOf(request.getParameter("client"));
            String debut = request.getParameter("begin");
            String fin = request.getParameter("end");
            Reservation reservation = new Reservation();
            reservation.setId(id);
            reservation.setClient_id(client_id);
            reservation.setVehicle_id(vehicle_id);
            reservation.setDebut(LocalDate.parse(debut));
            reservation.setFin(LocalDate.parse(fin));
            reservationService.edit(reservation);
            request.setAttribute("rent", reservation);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../rents");
    }
}