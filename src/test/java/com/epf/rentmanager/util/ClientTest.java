package com.epf.rentmanager.util;


import com.epf.rentmanager.validator.ValidatorClient;
import com.epf.rentmanager.model.Client;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class ClientTest {
    @Test
    void si_majeur_doit_retouner_true_quand_l_age_est_plus_grand_que_18() {
        Client legalClient = new Client("Afleck", "Steeve", "steeve.afleck@email.com", LocalDate.of(2001, Month.NOVEMBER, 9));
        assertTrue(ValidatorClient.si_non_majeur(legalClient),"L'âge doit être supérieur à 18 ans");
    }
    @Test
    void si_majeur_doit_retouner_false_quand_l_age_est_plus_petit_que_18() {
        Client illegalClient = new Client("Afleck", "Steeve", "steeve.afleck@email.com", LocalDate.of(2023, Month.NOVEMBER, 9));
        assertFalse(ValidatorClient.si_non_majeur(illegalClient),"L'âge doit être inférieur à 18 ans");
    }
    @Test
    void taille_prenom_doit_retouner_true_quand_superieur_a_3_caractere() {
        Client legalClient = new Client("Afleck", "Steeve", "steeve.afleck@email.com", LocalDate.of(2001, Month.NOVEMBER, 9));
        assertTrue(ValidatorClient.taille_nom_prenom(legalClient),"La taille du prénom est supérieure à 3 caractères");
    }
    @Test
    void taille_prenom_doit_retouner_false_quand_inferieur_a_3_caractere() {
        Client illegalClient = new Client("Afleck", "St", "steeve.afleck@email.com", LocalDate.of(2023, Month.NOVEMBER, 9));
        assertFalse(ValidatorClient.taille_nom_prenom(illegalClient),"La taille du prénom est inférieure à 3 caractères");
    }

    @Test
    void taille_nom_doit_retouner_true_quand_superieur_a_3_caractere() {
        Client legalClient = new Client("Afleck", "Steeve", "steeve.afleck@email.com", LocalDate.of(2001, Month.NOVEMBER, 9));
        assertTrue(ValidatorClient.taille_nom_prenom(legalClient),"La taille du nom est supérieure à 3 caractères");
    }
    @Test
    void taille_nom_doit_retouner_false_quand_inferieur_a_3_caractere() {
        Client illegalClient = new Client("A", "Steeve", "steeve.afleck@email.com", LocalDate.of(2023, Month.NOVEMBER, 9));
        assertFalse(ValidatorClient.taille_nom_prenom(illegalClient),"La taille du nom est inférieure à 3 caractères");
    }
}