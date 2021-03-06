package com.ataide.corona.coronatracker.util;

import com.ataide.corona.coronatracker.domain.StoreServiceTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);

    public static void clearDatabaseTables(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            List<String> tableNames = getTableNames(connection);
            clear(tableNames, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static List<String> getTableNames(Connection connection) throws SQLException {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(
                connection.getCatalog(), null, null, new String[]{"TABLE"});

        while (rs.next()) {
            tableNames.add(rs.getString("TABLE_NAME"));
        }
        return tableNames;
    }

    private static void clear(List<String> tableNames, Connection connection) throws SQLException {
        Statement statement = buildSqlStatement(tableNames, connection);

        logger.debug("Executing SQL");
        statement.executeBatch();
    }

    private static Statement buildSqlStatement(List<String> tableNames, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        statement.addBatch("SET FOREIGN_KEY_CHECKS = 0");
        addDeleteStatements(tableNames, statement);
        statement.addBatch("SET FOREIGN_KEY_CHECKS = 1");

        return statement;
    }

    private static void addDeleteStatements(List<String> tableNames, Statement statement) {
        tableNames.forEach(tableName -> {
            try {
                statement.addBatch("DELETE FROM " + tableName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void executeInsertStatements(Connection connection, String rowInsert) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(rowInsert);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
