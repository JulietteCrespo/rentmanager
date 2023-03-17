package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/rents/create")
public class RentCreateServlet extends RentListServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private VehicleService vehicleService = VehicleService.getInstance();
    private ClientService clientService = ClientService.getInstance();

    private ReservationService reservationService = ReservationService.getInstance();


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
            reservation.setClient_id(vehicle_id);
            reservation.setVehicle_id(client_id);
            reservation.setDebut(LocalDate.parse(debut));
            reservation.setFin(LocalDate.parse(fin));

            reservationService.create(reservation);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

        // traitement du formulaire (appel à la méthode de sauvegarde)
        // }
    }

}
