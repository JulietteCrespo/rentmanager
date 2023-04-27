package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/users/delete")
public class ClientDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    ReservationService reservationService;
    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException
    {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            List<Reservation> rents = reservationService.findResaByClientId(Integer.valueOf(request.getParameter("id")));
            for (Reservation rent : rents) {
                reservationService.delete((int) rent.getId());
            }
            clientService.delete(Integer.parseInt(request.getParameter("id").toString()));
        } catch (NumberFormatException | ServiceException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("../users");
    }
}
