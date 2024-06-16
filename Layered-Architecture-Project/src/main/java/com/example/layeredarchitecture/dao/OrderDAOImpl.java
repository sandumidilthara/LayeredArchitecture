package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderDAOImpl {

    public String generateNewid() throws SQLException, ClassNotFoundException {


        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Orders(oid,date,customerID) VALUES(?,?,?)");
        pstm.setObject(1, orderId);
        pstm.setObject(2, orderDate);
        pstm.setObject(3, customerId);
        if(pstm.executeUpdate()!=1){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }
    public void getOrderId(String orderId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM Orders WHERE oid=?");
        stm.setString(1, orderId);
       /*if order id already exist*/
        if (stm.executeQuery().next()) {
 }
}


}





