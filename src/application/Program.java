package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Scanner sc = new Scanner(System.in);
        menu();
        int n = sc.nextInt();

        if (n == 1) {

            String entities = "Seller";
            String entities2 = "Department";
            menuEntities(entities, entities2);
            int operation = sc.nextInt();

            if (operation == 1) {
                System.out.println("\nAll Sellers\n");
                List<Seller> list = sellerDao.findAll();
                for (Seller obj : list) {
                    System.out.println(obj);
                }

            } else if (operation == 2) {
                System.out.println("\nSeller by Department\n");
                System.out.print("Write the Department ID: ");
                int id = sc.nextInt();
                Department department = new Department(id, null);
                List<Seller> list = sellerDao.findByDepartment(department);
                for (Seller obj : list) {
                    System.out.println(obj);
                }

            } else if (operation == 3) {
                System.out.println("\nNew Seller\n");

                System.out.print("Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Birth Date (dd/MM/yyyy): ");
                String date = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDate = sdf.parse(date);
                System.out.print("Base Salary: ");
                double baseSalary = sc.nextDouble();
                System.out.print("Department ID: ");
                int departmentId = sc.nextInt();

                Department department = new Department(departmentId, null);
                Seller seller = new Seller(null, name, email, birthDate, baseSalary, department);
                sellerDao.insert(seller);
                System.out.println("Inserted! New id = " + seller.getId());

            } else if (operation == 4) {
                System.out.println("\nFind Seller by ID\n");

                System.out.print("Write the ID to find: ");
                int id = sc.nextInt();
                Seller seller = sellerDao.findById(id);
                System.out.println(seller);

            } else if (operation == 5) {
                System.out.println("\nUpdate Seller\n");

                System.out.print("Write the ID to update: ");
                int id = sc.nextInt();
                Seller seller = sellerDao.findById(id);
                System.out.println("What do you want to update?");
                System.out.println("1 - Name");
                System.out.println("2 - Email");
                System.out.println("3 - Birth Date");
                System.out.println("4 - Base Salary");
                System.out.println("5 - Department");
                int update = sc.nextInt();

                sc.nextLine();
                if (update == 1) {
                    System.out.print("Update Name: ");
                    String name = sc.nextLine();
                    seller.setName(name);

                } else if (update == 2) {
                    System.out.print("Update Email: ");
                    String email = sc.nextLine();
                    seller.setEmail(email);

                } else if (update == 3) {
                    System.out.print("Update Birth Date: ");
                    String date = sc.nextLine();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date birthDate = sdf.parse(date);
                    seller.setBirthDate(birthDate);

                } else if (update == 4) {
                    System.out.print("Update Base Salary: ");
                    double baseSalary = sc.nextDouble();
                    seller.setBaseSalary(baseSalary);

                } else if (update == 5) {
                    System.out.print("Update Department (ID): ");
                    int departmentId = sc.nextInt();
                    Department dep = new Department(departmentId, null);
                    seller.setDepartment(dep);
                }
                sellerDao.update(seller);
                System.out.println("Update completed!");

            } else if (operation == 6) {
                System.out.println("\nDelete Seller\n");

                System.out.print("Enter a Seller ID for delete: ");
                int id = sc.nextInt();
                sellerDao.deleteById(id);
                System.out.println("Delete done!");
            }


        } else if (n == 2) {

            String entities = "Department";
            String entities2 = "Seller";
            menuEntities(entities, entities2);
            int operation = sc.nextInt();

            if (operation == 1) {
                System.out.println("\nAll Departments\n");
                List<Department> list = departmentDao.findAll();
                for (Department obj : list) {
                    System.out.println(obj);
                }

            } else if (operation == 2) {
                System.out.println("\nDepartment by Seller\n");
                System.out.print("Write the Department ID: ");
                int id = sc.nextInt();
                Seller seller = new Seller(id, null, null, null, null, null);
                List<Department> list = departmentDao.findBySeller(seller);
                for (Department obj : list) {
                    System.out.println(obj);
                }

            } else if (operation == 3) {
                System.out.println("\nNew Department\n");

                System.out.print("Name: ");
                sc.nextLine();
                String name = sc.nextLine();

                Department department = new Department(null, name);
                departmentDao.insert(department);
                System.out.println("Inserted! New id = " + department.getId() +
                        " - Department = " + department.getName());

            } else if (operation == 4) {
                System.out.println("\nFind Department by ID\n");

                System.out.print("Write the ID to find: ");
                int id = sc.nextInt();
                Department department = departmentDao.findById(id);
                System.out.println(department);

            } else if (operation == 5) {
                System.out.println("\nUpdate Department\n");

                System.out.print("Write the ID to update: ");
                int id = sc.nextInt();
                Department department = departmentDao.findById(id);
                System.out.println("What do you want to update?");
                System.out.println("1 - ID");
                System.out.println("2 - Name");
                int update = sc.nextInt();

                sc.nextLine();
                if (update == 1) {
                    System.out.print("Update ID: ");
                    int id2 = sc.nextInt();
                    department.setId(id2);
                } else if (update == 2) {
                    System.out.print("Update Name: ");
                    String name = sc.nextLine();
                    department.setName(name);
                }
                departmentDao.update(department);
                System.out.println("Update completed!");

            } else if (operation == 6) {
                System.out.println("\nDelete Department\n");

                List<Department> list = departmentDao.findAll();
                for (Department obj : list) {
                    System.out.println(obj);
                }

                System.out.print("Enter a Department ID for deletion: ");
                int id = sc.nextInt();
                departmentDao.deleteById(id);
                System.out.println("Delete done!");
            }

        }

        sc.close();
        DB.closeConnection();
    }

    public static void menu() {
        System.out.println("\nEnter the query of number: ");
        System.out.println("1 - Seller");
        System.out.println("2 - Department\n");
        System.out.print("-> ");
    }

    public static void menuEntities(String entities, String entities2) {
        System.out.println("Choose the " + entities + " operation: ");
        System.out.println("1 - Find All " + entities + "s");
        System.out.println("2 - Find " + entities + " by " + entities2);
        System.out.println("3 - Create " + entities);
        System.out.println("4 - Read " + entities + " (Find by ID)");
        System.out.println("5 - Update " + entities);
        System.out.println("6 - Delete " + entities);
    }
}