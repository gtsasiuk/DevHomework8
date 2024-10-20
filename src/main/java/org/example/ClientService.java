package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static final String CREATE_SQL = "INSERT INTO CLIENT (name) VALUES (?)";
    private static final String SELECT_BY_ID_SQL = "SELECT name FROM CLIENT WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE CLIENT SET name = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM CLIENT WHERE id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name FROM CLIENT";

    private void validateId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID має бути позитивним числом.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        } else if (name.length() < 2 || name.length() > 255) {
            throw new IllegalArgumentException("Name must be between 2 and 255 characters");
        }
    }

    public long create(String name) throws SQLException {
        validateName(name);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("Client creation failed, no ID received");
                }
            }
        }
    }

    public String getById(long id) throws SQLException {
        validateId(id);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("NAME");
                } else {
                    throw new SQLException("Client with ID " + id + " not found.");
                }
            }
        }
    }

    public void setName(long id, String name) throws SQLException {
        validateId(id);
        validateName(name);

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed: Client with ID " + id + " not found.");
            }
        }
    }

    public void deleteById(long id) throws SQLException {
        validateId(id);
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Delete failed: Client with ID " + id + " not found.");
            }
        }
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    clients.add(new Client(rs.getLong(1), rs.getString(2)));
                }
            }
        }

        return clients;
    }
}
