package com.epf.rentmanager.util;


import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.validator.ValidatorVehicle;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class VehicleTest {
    @Test
    void nb_places_doit_retouner_true_quand_plus_petit_que_9_places() {
        Vehicle voiture = new Vehicle("Renault",4);
        assertTrue(ValidatorVehicle.nb_places(voiture),"La voiture a moins de 9 places");
    }
    @Test
    void nb_places_doit_retouner_false_quand_plus_grand_que_9_places() {
        Vehicle voiture = new Vehicle("Renault",10);
        assertFalse(ValidatorVehicle.nb_places(voiture),"La voiture a plus de 9 places");
    }
    @Test
    void nb_places_doit_retouner_false_quand_plus_petit_que_2_places() {
        Vehicle voiture = new Vehicle("Renault",1);
        assertFalse(ValidatorVehicle.nb_places(voiture),"La voiture a moins de 2 places");
    }
    @Test
    void a_un_constructeur_doit_retouner_false_pas_de_constructeur() {
        Vehicle voiture = new Vehicle("",4);
        assertFalse(ValidatorVehicle.a_un_constructeur(voiture),"La voiture n'a pas de constructeur");
    }
    @Test
    void a_un_constructeur_doit_retouner_true_a_un_constructeur() {
        Vehicle voiture = new Vehicle("Renault",4);
        assertTrue(ValidatorVehicle.a_un_constructeur(voiture),"La voiture a un constructeur");
    }
}