package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                   "INSERT INTO department " +
                        "(name) " +
                        "VALUES " +
                        "(?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE department " +
                            "SET name = ? " +
                            "WHERE id = ? ");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE id = ?");

            st.setInt(1, id);

            int rows = st.executeUpdate();

            if (rows == 0) {
                throw new DbException("Nothing has changed.");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()) {
                Department obj = new Department();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                return obj;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * from department");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("id"));
                dep.setName(rs.getString("name"));
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findBySeller(Seller seller) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT department.id as DepartmentId, department.name as DepartmentName, " +
                            "seller.id as SellerId, seller.name as SellerName, seller.email as SellerEmail, " +
                            "seller.birthDate as SellerBirthDate, seller.baseSalary as SellerBaseSalary " +
                            "FROM department " +
                            "INNER JOIN seller ON department.id = seller.DepartmentId " +
                            "WHERE seller.id = ? " +
                            "ORDER BY seller.name"
            );

            st.setInt(1, seller.getId()); // configurando o id do departamento

            rs = st.executeQuery();

            List<Department> listDepartment = new ArrayList<>();

            Map<Integer, Department> mapDepartment = new HashMap<>();

            while (rs.next()) {
                int departmentId = rs.getInt("DepartmentId");

                Department department = mapDepartment.get(departmentId);

                if (department == null) {
                    department = new Department();
                    department.setId(departmentId);
                    department.setName(rs.getString("DepartmentName"));
                    department.setSeller(new ArrayList<>()); // Inicializa a lista de vendedores
                    mapDepartment.put(departmentId, department);
                    listDepartment.add(department);
                }

                int sellerId = rs.getInt("SellerId");
                Seller sellerObj = new Seller();
                sellerObj.setId(sellerId);
                sellerObj.setName(rs.getString("SellerName"));
                sellerObj.setEmail(rs.getString("SellerEmail"));
                sellerObj.setBirthDate(rs.getDate("SellerBirthDate"));
                sellerObj.setBaseSalary(rs.getDouble("SellerBaseSalary"));
                sellerObj.setDepartment(department);

                department.getSeller().add(sellerObj);
            }
            return listDepartment;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
