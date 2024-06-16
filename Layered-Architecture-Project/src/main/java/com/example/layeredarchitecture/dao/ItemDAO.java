package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;

    public boolean existItem(String code) throws SQLException, ClassNotFoundException;

    public String generateNewid() throws SQLException, ClassNotFoundException;

    public void delete(String code) throws SQLException, ClassNotFoundException;

    public void saveItem(String code, String description, BigDecimal unitPrice, Integer qtyOnHand) throws SQLException, ClassNotFoundException;

    public boolean UpdateItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws SQLException, ClassNotFoundException;

    public ItemDTO findItem(String code) throws SQLException, ClassNotFoundException;
}
