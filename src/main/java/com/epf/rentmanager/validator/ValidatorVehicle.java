package com.epf.rentmanager.validator;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValidatorVehicle {
    static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static VehicleService vehicleService = context.getBean(VehicleService.class);
    private static ReservationService reservationService = context.getBean(ReservationService.class);
    public static boolean nb_places(Vehicle vehicle) {
        // verifier que le nombre de places de la voiture est compris entre 2 et 9
        int nb_places = vehicle.getNb_places();
        System.out.println("Le nombre de places de la voiture doit etre compris entre 2 et 9, ici le nombre de places est de "+nb_places);
        return
                nb_places >= 2 && nb_places<=9;
    }
    public static boolean a_un_constructeur(Vehicle vehicle) {
        // verifier que la voiture a un constructeur
        String constructeur = vehicle.getConstructeur();
        System.out.println("La voiture doit contenir un constructeur, ici le constructeur est :"+constructeur);
        return
                constructeur.length() >= 1;
    }
}
