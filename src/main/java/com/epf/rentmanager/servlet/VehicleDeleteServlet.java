package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
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
import java.util.List;


@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    private VehicleService vehicleService;
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
        try {
            List<Reservation> rents = reservationService.findResaByVehicleId(Integer.valueOf(request.getParameter("id")));
            for (Reservation rent : rents) {
                reservationService.delete((int) rent.getId());
            }
            vehicleService.delete(Integer.parseInt(request.getParameter("id").toString()));
        } catch (NumberFormatException | ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("../cars");
    }
}
