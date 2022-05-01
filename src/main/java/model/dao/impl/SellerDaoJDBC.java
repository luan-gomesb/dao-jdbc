package model.dao.impl;

import com.mysql.cj.exceptions.DeadlockTimeoutRollbackMarker;
import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private Connection conn = null;
    public SellerDaoJDBC(Connection connection) {
        conn = connection;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        String query = "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "WHERE seller.Id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(query);
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                return sellerFromResultSet(rs, dep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        String query = "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "WHERE DepartmentId = ? " +
                "ORDER BY Name ";

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellersList = new ArrayList<>();
        try {
            st = conn.prepareStatement(query);
            st.setInt(1,department.getId());
            rs = st.executeQuery();
            Map<Integer,Department> departmentMap = new HashMap<>();
            while(rs.next()){
                Department dep = departmentMap.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
                }
                Seller newSeller =  sellerFromResultSet(rs,dep);
                sellersList.add(newSeller);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.close();
        }
        return sellersList;
    }

    @Override
    public List<Seller> findAll() {
        String query = "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "ORDER BY Name ";

        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellersList = new ArrayList<>();
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery();
            Map<Integer,Department> departmentMap = new HashMap<>();
            while(rs.next()){
                Department dep = departmentMap.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep = new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
                }
                Seller newSeller =  sellerFromResultSet(rs,dep);
                sellersList.add(newSeller);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.close();
        }
        return sellersList;

    }
    private Seller sellerFromResultSet(ResultSet rs, Department dep) throws  SQLException{
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setDepartment(dep);
        return seller;
    }
}
