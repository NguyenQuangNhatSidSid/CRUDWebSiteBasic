/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class FoodDAO {

    private static final String SEARCH = "SELECT id, name, price,description,price,cookingTime,status FROM tblFoods Where name like ?";
    private static final String DELETE = "UPDATE tblFoods SET status='false' Where id=?";
    private static final String DUPLICATE = "SELECT id  FROM  tblFoods WHERE id= ?";
    private static final String UPDATE = " UPDATE tblFoods SET name=?,description=?,price=?,cookingTime=?,status=? WHERE id=? ";
    private static final String CREATE = " INSERT INTO tblFoods ( id, name,description,price,cookingTime,status )VALUES(?,?,?,?,?,?) ";
    

    public List<FoodDTO> getListFood(String search) throws SQLException {
        List<FoodDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getInt("price");
                    int cookingTime = rs.getInt("cookingTime");
                    boolean status = rs.getBoolean("status");
                    list.add(new FoodDTO(id, name, description, price, cookingTime, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteProduct( String id) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(DELETE);
//                ptm.setBoolean(1, status);
                ptm.setString(1, id);
                ptm.executeUpdate();
                return check = true;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(FoodDTO food) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //UPDATE tblFoods SET name=?,description=?,price=?,cookingTime=?,status=? WHERE id=? ";
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, food.getName());
                ptm.setString(2, food.getDescription());
                ptm.setFloat(3, food.getPrice());
                ptm.setInt(4, food.getCookingTime());
                ptm.setBoolean(5, food.isStatus());
                ptm.setString(6, food.getId());

                check = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
     public boolean checkDup(String id) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean create(FoodDTO food) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                // INSERT INTO tblFoods ( id, name,description,price,cookingTime,status )VALUES(?,?,?,?,?,?) ";
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, food.getId());
                ptm.setString(2, food.getName());
                ptm.setString(3, food.getDescription());
                ptm.setFloat(4, food.getPrice());
                ptm.setInt(5, food.getCookingTime());
                ptm.setBoolean(6, food.isStatus());
                
                check = ptm.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    

}
