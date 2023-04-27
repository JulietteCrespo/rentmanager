package com.epf.rentmanager.validator;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidatorRent {
    static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static VehicleService vehicleService = context.getBean(VehicleService.class);
    private static ReservationService reservationService = context.getBean(ReservationService.class);
    public static boolean jour_libre(Reservation reservation) throws ServiceException {
        // verifier que la voiture n'est pas réservé 2 fois le même jour
        List<Reservation> rents = reservationService.findResaByVehicleId(reservation.getVehicle_id());
        List<LocalDate> dates = new ArrayList<>();
        for (Reservation rent : rents) {
            for(int i = 0; i< Period.between(rent.getDebut(), rent.getFin()).getDays()+1; i++){
                dates.add(rent.getDebut().plusDays(i));
            }
        }
        for (LocalDate date : dates) {
            if ( reservation.getDebut().equals(date) || reservation.getFin().equals(date) ){
                System.out.println("La voiture est deja reservé se jour");
                return false;
            }
        }
        return true;
    }
    public static boolean rent_plus_de_7(Reservation rent) {
        // vérifier que la voiture n'est pas louée plus de 7 jours pas même utilisateur
        System.out.println("La voiture ne peut pas louée plus de 7 jours par le même utilisateur, le demande est de " + Period.between(rent.getDebut(), rent.getFin()).getDays()+"jours");
        return
                Period.between(rent.getDebut(), rent.getFin()).getDays() <=7;
    }
    public static boolean date_valide(Reservation rent) {
        // vérifier que les dates de debut et fin correspondent
        return
                rent.getDebut().isBefore(rent.getFin());
    }
    public static boolean plus_de_30j(Reservation reservation) throws ServiceException {
        // verifier que la voiture n'est pas réservé 30 jours de suite sans pause
        List<Reservation> rents = reservationService.findResaByVehicleId(reservation.getVehicle_id());
        List<LocalDate> dates = new ArrayList<>();
        for (Reservation rent : rents) {
            for(int i = 0; i< Period.between(rent.getDebut(), rent.getFin()).getDays()+1; i++){
                dates.add(rent.getDebut().plusDays(i));
            }
        }
        for(int i = 0; i< Period.between(reservation.getDebut(), reservation.getFin()).getDays()+1; i++){
            dates.add(reservation.getDebut().plusDays(i));
        }
        Collections.sort(dates);
        int count=0;
        for (int i = 0; i< dates.size(); i++) {
            if ( dates.get(i).equals(dates.get(0).plusDays(i)) ){
                count++;
            }else{
                count=0;
            }
        }
        System.out.println(count);
        if(count > 30){
            System.out.println("La voiture a été reservée plus de 30j de suite");
            return false;
        }else{
            return true;
        }
    }
}
