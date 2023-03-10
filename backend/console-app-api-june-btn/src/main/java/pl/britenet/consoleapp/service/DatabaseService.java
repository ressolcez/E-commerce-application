package pl.britenet.consoleapp.service;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseService {
    private HikariDataSource hikariDataSource;

    private void configureDataSource() {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/shop_database");
            config.setUsername("root");
            config.setPassword("");
            this.hikariDataSource = new HikariDataSource(config);
    }

    public DatabaseService() throws ConnectException {
        try {
            this.configureDataSource();
        }catch (Exception e){
            throw new ConnectException("Błąd połączenia z MySql");
        }
    }

    public void performDML(String dml) {
        try (Connection connection = this.hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(dml)) {
             statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public<T> Optional<T> performSQL(String sql, ResultParser<T> resultParser) {
        try (Connection connection = this.hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(resultParser.parse(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
