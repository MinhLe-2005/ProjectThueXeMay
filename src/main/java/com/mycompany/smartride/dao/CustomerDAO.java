/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.smartride.dao;

import com.mycompany.smartride.dto.Customer;
import com.mycompany.smartride.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LeQuangMinh
 */
public class CustomerDAO implements Serializable, DAO<Customer> {

    private static CustomerDAO instance;
    private Connection conn = DBUtil.makeConnection();

    // Cấm new trực tiếp DAO
    //Chỉ new DAO qua hàm static getInstance() để quản lí được số object/instance đã new - SINGLETON DESIGN PATTERN
    private CustomerDAO() {
    }

    public static CustomerDAO getInstance() {

        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        PreparedStatement stm;
        ResultSet rs;
        try {
            String sql = "SELECT * from \"Customer\"";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void createNewCustomer(String IdentityCard, String IdentityCardImage, String IssuedOnDate, String ExpDate, String TypeCard, int TypeID, int AccountID) {
        String sql = "INSERT INTO \"Customer\" (\n"
                + "    \"IdentityCard\",\n"
                + "    \"IdentityCardImage\",\n"
                + "    \"IssuedOnDate\",\n"
                + "    \"ExpDate\",\n"
                + "    \"TypeCard\",\n"
                + "    \"TypeID\",\n"
                + "    \"AccountID\"\n"
                + ") VALUES (\n"
                + "  ?,?,?,?,?,?,? );";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, IdentityCard);
            ps.setString(2, IdentityCardImage);
            ps.setString(3, IssuedOnDate);
            ps.setString(4, ExpDate);
            ps.setString(5, TypeCard);
            ps.setInt(6, TypeID);
            ps.setInt(7, AccountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCustomer(String IdentityCard, String IdentityCardImage, String IssuedOnDate, String ExpDate, String TypeCard, int CustomerId) {
        String sql = "UPDATE \"Customer\" "
                + "SET \"IdentityCard\" = ?, \"IdentityCardImage\" = ?, "
                + "\"IssuedOnDate\" = ?, \"ExpDate\" = ?, \"TypeCard\" = ? "
                + "WHERE \"CustomerID\" = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, IdentityCard);
            st.setString(2, IdentityCardImage);
            st.setString(3, IssuedOnDate);
            st.setString(4, ExpDate);
            st.setString(5, TypeCard);
            st.setInt(6, CustomerId);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Customer t) {
        String sql = "UPDATE \"Customer\" "
                + "SET \"IdentityCard\" = ?, \"IdentityCardImage\" = ?, "
                + "\"IssuedOnDate\" = ?, \"ExpDate\" = ?, \"TypeCard\" = ? "
                + "WHERE \"CustomerID\" = ?";

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, t.getIdentityCard());
            st.setString(2, t.getIdentityCardImage());
            st.setString(3, t.getIssuedOnDate());
            st.setString(4, t.getExpDate());
            st.setString(5, t.getTypeCard());
            st.setInt(6, t.getCustomerId());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Customer getCustomerbyAccountID(int id) {
        String sql = " SELECT * FROM \"Customer\"\n"
                + " WHERE \"AccountID\" = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                return customer;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Customer getCustomerbyID(int id) {
        String sql = " SELECT * FROM \"Customer\"\n"
                + " WHERE \"CustomerID\" = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                return customer;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Map<Integer, Customer> getCustomersMappedByAccountId() {
        String sql = "SELECT \n"
                + "\"CustomerID\",\n"
                + "\"IdentityCard\",\n"
                + "\"IdentityCardImage\",\n"
                + "TO_CHAR(\"IssuedOnDate\", 'DD-MM-YYYY') AS \"IssuedOnDate\",\n"
                + "TO_CHAR(\"ExpDate\", 'DD-MM-YYYY') AS \"ExpDate\",\n"
                + "\"TypeCard\",\n"
                + "\"TypeID\",\n"
                + "\"AccountID\"\n"
                + "FROM \n"
                + "\"Customer\"";
        Map<Integer, Customer> customerMap = new HashMap<>();

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                customerMap.put(customer.getAccountId(), customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return customerMap;
    }
    
    

    @Override
    public void delete(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
    }
}
