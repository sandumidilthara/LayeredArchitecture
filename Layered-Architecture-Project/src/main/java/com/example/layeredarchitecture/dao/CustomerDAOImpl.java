package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements  CustomerDAO {
@Override
public ArrayList<CustomerDTO> loadAllCustomer() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    Statement stm = connection.createStatement();
    ResultSet rst = stm.executeQuery("SELECT * FROM Customer");


    ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
    while (rst.next()) {

        CustomerDTO customerDTO = new CustomerDTO(
                rst.getString("id"),
                rst.getString("name"),
                rst.getString("address")
        );

        customerDTOS.add(customerDTO);


    }
    return customerDTOS;
}
@Override
public void saveCustomer( String id, String name,String address) throws SQLException, ClassNotFoundException {



    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
    pstm.setString(1, id);
    pstm.setString(2, name);
    pstm.setString(3, address);
    pstm.executeUpdate();




}

public void delete( String id ) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
    pstm.setString(1, id);
    pstm.executeUpdate();
}

public void update( String name, String address,String id) throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
    pstm.setString(1, name);
    pstm.setString(2, address);
    pstm.setString(3, id);
    pstm.executeUpdate();

}


public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException {



    Connection connection = DBConnection.getDbConnection().getConnection();
    PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
    pstm.setString(1, id);
    return pstm.executeQuery().next();



}

public String generateNewid() throws SQLException, ClassNotFoundException {
    Connection connection = DBConnection.getDbConnection().getConnection();
    ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    if (rst.next()) {
        String id = rst.getString("id");
        int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
        return String.format("C00-%03d", newCustomerId);
    } else {
        return "C00-001";
    }

}
}
