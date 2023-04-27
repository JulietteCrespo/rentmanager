package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.validator.ValidatorRent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class RentTest {
    @Test
    void rent_plus_de_7_doit_retouner_true_quand_location_moins_de_7j() {
        Reservation rent = new Reservation(LocalDate.of(2020, Month.NOVEMBER, 9),LocalDate.of(2020, Month.NOVEMBER, 11));
        assertTrue(ValidatorRent.rent_plus_de_7(rent),"La reservation dure moins de 7 jours");
    }
    @Test
    void rent_plus_de_7_doit_retouner_false_quand_location_plus_de_7j() {
        Reservation rent = new Reservation(LocalDate.of(2020, Month.NOVEMBER, 9),LocalDate.of(2020, Month.NOVEMBER, 20));
        assertFalse(ValidatorRent.rent_plus_de_7(rent),"La reservation dure plus de 7 jours");
    }
    @Test
    void date_valide_doit_retouner_true_si_date_debut_plus_petite_que_date_fin() {
        Reservation rent = new Reservation(LocalDate.of(2020, Month.NOVEMBER, 9),LocalDate.of(2020, Month.NOVEMBER, 11));
        assertTrue(ValidatorRent.date_valide(rent),"La date de début est avant la date de fin");
    }
    @Test
    void date_valide_doit_retouner_false_si_date_debut_plus_grande_que_date_fin() {
        Reservation rent = new Reservation(LocalDate.of(2020, Month.NOVEMBER, 9),LocalDate.of(2020, Month.NOVEMBER, 7));
        assertFalse(ValidatorRent.date_valide(rent),"La date de fin est avant la date de debut");
    }
}