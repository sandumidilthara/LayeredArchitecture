package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrderDetailDAOImpl {


    public boolean saveOrderDetails(String orderId, List<OrderDetailDTO> orderDetailDTOS ) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetails(oid,itemCode,qty,unitPrice) VALUES(?,?,?,?)");
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
            pstm.setObject(1, orderId);
            pstm.setObject(2, orderDetailDTO.getItemCode());
            pstm.setObject(3, orderDetailDTO.getQty());
            pstm.setObject(4, orderDetailDTO.getUnitPrice());

        }
        if(pstm.executeUpdate()!=1){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
}

}