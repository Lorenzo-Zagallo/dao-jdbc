# Java JDBC Project with SQL Server

## Overview
This is a Java project that demonstrates how to connect to a SQL Server database using JDBC. The project follows the DAO (Data Access Object) pattern for better organization and separation of concerns. It includes basic CRUD operations for `Department` and `Seller` entities.

---

## Project Structure
```
.idea/            # IntelliJ IDEA project settings
out/              # Compiled output files
src/              # Source code
  application/    # Entry point classes
    Program       # Main program for testing DAO operations and prompt interface

  db/             # Database utility classes
    DB            # Handles database connections
    DbException   # Custom exception for database errors
    DbIntegrityException # Custom exception for integrity errors

  model/          # Core business logic
    dao/          # DAO interfaces and implementations
      impl/       # DAO implementations using JDBC
        DepartmentDaoJDBC # Implementation for Department
        SellerDaoJDBC     # Implementation for Seller
      DaoFactory    # Factory class to get DAO instances
      DepartmentDao # DAO interface for Department
      SellerDao     # DAO interface for Seller

  entities/       # Entity classes representing database tables
    Department    # Represents a department
    Seller        # Represents a seller

.gitignore        # Git ignore file for unnecessary files
.dao-jdbc.iml     # IntelliJ module file
db.properties     # Database connection configuration file
```

---

## Features
- Database connection handling with JDBC
- DAO pattern for separation of concerns
- CRUD operations for `Department` and `Seller` entities
- Custom exceptions for handling database errors

---

## Prerequisites
- Java 8 or higher
- SQL Server installed and running
- JDBC driver
- IntelliJ IDEA (or any Java IDE)

---

## Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Open the project in IntelliJ IDEA.

3. Set up the database connection in `db.properties`:
   ```properties
   db.url=jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName
   db.user=yourUsername
   db.password=yourPassword
   ```

4. Make sure the SQL Server database is running and the `Department` and `Seller` tables are created.

5. Run the `Program` or `Program2` class to test the application.

---

## Usage
1. Open the `Program` class in `application` package.
2. Run the program to test the database operations.

---

## DAO Pattern
### Interfaces
- `DepartmentDao`: Defines methods for `Department` CRUD operations.
- `SellerDao`: Defines methods for `Seller` CRUD operations.

### Implementations
- `DepartmentDaoJDBC`: JDBC implementation for `Department`.
- `SellerDaoJDBC`: JDBC implementation for `Seller`.

### Factory
- `DaoFactory`: Provides instances of DAO implementations.

---

## Author

- Name: Lorenzo Zagallo
- GitHub: [[GitHub]](https://github.com/Lorenzo-Zagallo/)
- LinkedIn: [[LinkedIn]](https://www.linkedin.com/in/lorenzo-zagallo-07654a2b9/)


If you have any questions, feel free to open an [issue](https://github.com/Lorenzo-Zagallo/dao-jdbc/issues).

