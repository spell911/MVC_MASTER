/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataBean.UserAccount;
import DataBean.Product;
/**
 *
 * @author Khun_Phichz
 */
public class DBUtils {

    public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
        
        String sql = "SELECT * FROM USER_ACCOUNT WHERE USER_NAME=? AND PASSWORD=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String gender = rs.getString("GENDER");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static UserAccount findUser(Connection conn, String userName) throws SQLException {

        String sql = "SELECT a.USER_NAME, a.PASSWORD, a.GENDER FROM USER_ACCOUNT a WHERE a.USER_NAME = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String password = rs.getString("PASSWORD");
            String gender = rs.getString("GENDER");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }

    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.CODE, a.NAME, a.PRICE from PRODUCT a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            String code = rs.getString("CODE");
            String name = rs.getString("NAME");
            float price = rs.getFloat("PRICE");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.CODE, a.NAME, a.PRICE from PRODUCT a where a.CODE=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("NAME");
            float price = rs.getFloat("PRICE");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update PRODUCT set NAME =?, PRICE=? where CODE=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into PRODUCT(CODE, NAME, PRICE) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete PRODUCT where CODE= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();
    }

}
