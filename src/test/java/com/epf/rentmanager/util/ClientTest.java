package com.ensta.rentmanager.util;

import com.epf.rentmanager.model.Client;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClientTest {
    @Test
    void isLegal_should_return_true_when_age_is_over_18() {

        Client legalUser = new Client("John", "Doe", "john.doe@ensta.fr", 20);

        assertTrue(Client.isLegal(legalUser));

    }
}
