package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/rents")
public class RentListServlet extends HomeServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ReservationService reservationService = ReservationService.getInstance();
    private VehicleService vehicleService = VehicleService.getInstance();
    private ClientService clientService = ClientService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.setAttribute("reservations",reservationService.findAll());
            request.setAttribute("vehicles",vehicleService);
            request.setAttribute("clients",clientService);

        } catch (ServiceException e) {
            e.printStackTrace();
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }

}