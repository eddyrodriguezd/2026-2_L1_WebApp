package edu.pucp.mechatronics.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.pucp.mechatronics.config.DBConnection;
import edu.pucp.mechatronics.interfaces.CRUD;
import edu.pucp.mechatronics.model.Product;

public class ProductDAO implements CRUD<Product> {

    private DBConnection connDB = new DBConnection();

    @Override
    public List<Product> findAll() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try {
            Connection con = connDB.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));

                products.add(product);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return products;
    }

    @Override
    public Product find(int id) {

        Product product = null;

        String sql = "SELECT * FROM product WHERE id = ?";

        try {
            Connection con = connDB.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return product;
    }

    @Override
    public boolean save(Product product) {

        String sql = "INSERT INTO product(name, stock) VALUES (?, ?)";

        try {
            Connection con = connDB.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return false;
    }

    @Override
    public boolean update(Product product) {

        String sql = "UPDATE product SET name = ?, stock = ? WHERE id = ?";

        try {
            Connection con = connDB.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setInt(3, product.getId());

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM product WHERE id = ?";

        try {
            Connection con = connDB.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        return false;
    }
}