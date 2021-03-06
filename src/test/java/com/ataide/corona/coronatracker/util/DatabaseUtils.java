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

    private static void executeSqlStatements(Connection connection, String sqlCommand) {
        logger.info("Executing sql statement: {}", sqlCommand);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearDatabaseData (DataSource dataSource) {
        try {
            final Connection connection = dataSource.getConnection();
            List.of(
                    "DELETE FROM VISITED_STORE",
                    "DELETE FROM DIAGNOSED",
                    "DELETE FROM HEALTH_UNITY",
                    "DELETE FROM STORE",
                    "DELETE FROM PERSON",
                    "DELETE FROM USER_TABLE"
            ).forEach(delete -> executeSqlStatements(connection, delete));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
