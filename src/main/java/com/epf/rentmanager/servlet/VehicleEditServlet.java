package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicles/edit")
public class VehicleEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("vehicle",vehicleService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            String constructeur = request.getParameter("manufacturer");
            int nb_places = Integer.parseInt(request.getParameter("seats"));
            Vehicle vehicle = new Vehicle();
            vehicle.setId(id);
            vehicle.setNb_places(nb_places);
            vehicle.setConstructeur(constructeur);
            vehicleService.edit(vehicle);
            request.setAttribute("vehicle", vehicle);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("../cars");
    }
}