package com.epf.rentmanager.validator;

import com.epf.rentmanager.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.utils.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class ValidatorClient {
    static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    private static ClientService clientService = context.getBean(ClientService.class);
    public static boolean si_non_majeur(Client user) {
        // verifier si l'utilisateur est majeur
        LocalDate birthDate = user.getNaissance();
        LocalDate currentDate = LocalDate.now();
        System.out.println("L'utilisateur a "+Period.between(birthDate, currentDate).getYears() + "ans (il faut 18ans minimun)");
        if (Period.between(birthDate, currentDate).getYears() >= 18){
            return true;
        }else{
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "La personne n'est pas majeur");
            return false;
        }
    }
    public static boolean taille_nom_prenom(Client user) {
        // verifier si le nom et prenom contiennent plus de 3 caracteres
        String nom = user.getNom();
        String prenom = user.getPrenom();
        System.out.println("Nom : "+nom +" Prenom : "+ prenom+ " (le nom et prenom doit avoir au moins 3 caracteres)");
        if (nom.length() >= 3 && prenom.length() >= 3){
            return true;
        }else{
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Le nom ou prenom n'as pas assez de caractères");
            return false;
        }
    }
    public static boolean email_valide(Client user) throws ServiceException {
        // verifier si l'email n'est pas deja utilisé
        List<Client> clients = clientService.findAll();
        List<String> emails = new ArrayList<>();
        for (Client client : clients) {
            emails.add(client.getEmail());
        }
        for (String email : emails) {
            if ( user.getEmail().intern() == email.intern()){
                System.out.println("L'email est deja utilisé");
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "L'email est deja utilisé");
                return false;
            }
        }
        return true;
    }
}
