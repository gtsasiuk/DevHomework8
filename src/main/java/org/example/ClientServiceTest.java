package org.example;

import java.sql.SQLException;

public class ClientServiceTest {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        try {
            long clientId = clientService.create("NextGen Enterprises");
            System.out.println("Client created with ID: " + clientId);

            String clientName = clientService.getById(clientId);
            System.out.println("Client name with ID " + clientId + ": " + clientName);

            clientName = "Quantum Dynamics";
            clientService.setName(clientId, clientName);
            System.out.println("Client name updated with ID " + clientId + ": " + clientName);

            System.out.println("List of clients:");
            clientService.listAll().forEach(System.out::println);

            clientService.deleteById(clientId);
            System.out.println("Client deleted with ID: " + clientId);
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error IllegalArgument: " + e.getMessage());
        }
    }
}