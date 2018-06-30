package com.kodilla.jdbc;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class testUpdateBestsellers {
    @Test
    public void testUpdateVipLevels() throws SQLException {

        //Given
        DbManager dbManager = DbManager.getInstance();
        String sqlUpdate = "UPDATE BOOKS SET BESTSELLERS=NULL";
        Statement statement = dbManager.getConnection().createStatement();
        statement.executeUpdate(sqlUpdate);

        //When
        String sqlProcedureCall = "CALL UpdateBestsellers";
        statement.execute(sqlProcedureCall);

        //Then
        String sqlCheckTable = "SELECT COUNT(*) AS HOW_MANY FROM BOOKS WHERE BESTSELLERS=NULL";
        ResultSet resultSet = statement.executeQuery(sqlCheckTable);
        int howMany = -1;
        System.out.println("Checking value before resultSet: " + howMany);
        if (resultSet.next()) {
            howMany = resultSet.getInt("HOW_MANY");
        }
        System.out.println("Checking value after resultSet: " + howMany);
        assertEquals(0, howMany);
    }
}
