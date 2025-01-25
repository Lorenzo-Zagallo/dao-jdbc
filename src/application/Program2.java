package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findById ===");
        Department department = departmentDao.findById(2);
        System.out.println(department);

        System.out.println("\n=== TEST 2: department findBySeller ===");
        Seller seller = new Seller(4, null, null, null, null, null);
        List<Department> list = departmentDao.findBySeller(seller);
        for (Department obj : list) {
            System.out.println(obj.toStringSeller());
        }

        System.out.println("\n=== TEST 3: seller findAll ===");
        List<Department> list1 = departmentDao.findAll();
        for (Department obj : list1) {
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: department insert ===");
        Department department1 = new Department(null, "Audie");
        departmentDao.insert(department1);
        System.out.println("Inserted! New id = " + department1.getId() +
                " - Department = " + department1.getName());

        System.out.println("\n=== TEST 5: department update ===");
        Department department2 = departmentDao.findById(24);
        department2.setName("Bolas");
        departmentDao.update(department2);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 6: department delete ===");
        System.out.print("Enter id for delete test: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete done!");
        sc.close();

        DB.closeConnection();
    }
}
