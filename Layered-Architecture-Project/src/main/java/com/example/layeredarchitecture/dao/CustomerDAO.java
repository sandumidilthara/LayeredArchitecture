package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {


    public ArrayList<CustomerDTO> loadAllCustomer() throws SQLException, ClassNotFoundException;
    public void saveCustomer( String id, String name,String address) throws SQLException, ClassNotFoundException ;

    public void delete( String id ) throws SQLException, ClassNotFoundException ;

    public void update( String name, String address,String id) throws SQLException, ClassNotFoundException;
    public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException ;
    public String generateNewid() throws SQLException, ClassNotFoundException ;
}
