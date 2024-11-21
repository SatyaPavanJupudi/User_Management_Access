# User Access Management System

A web-based application for managing user access to software applications within an organization. The system supports user roles such as Employee, Manager, and Admin, allowing different functionalities based on their roles.

## Features
1. **User Registration (Sign-Up):** Employees can register for the system.
2. **User Login (Authentication):** Users can log in based on their credentials and roles.
3. **Software Management (Admin):** Admins can create new software records.
4. **Access Requests (Employee):** Employees can request access to software.
5. **Approval System (Manager/Admin):** Managers/Admins can approve or reject access requests.

---

## Setup Instructions

### Prerequisites
1. **Java JDK**: Ensure you have Java 8 or higher installed.
2. **PostgreSQL**: Install PostgreSQL and set up the database.
3. **Apache Tomcat**: Install Tomcat (version 9 or higher).
4. **Maven**: Ensure Maven is installed for managing dependencies.

### Steps to Set Up the Project
1. **Clone the Repository**:
    ```bash
    git clone <repository-url>
    cd user-access-management
    ```

2. **Configure the Database**:
    - Create a PostgreSQL database:
      ```sql
      CREATE DATABASE userdb;
      ```
    - Run the provided SQL scripts to create the required tables:
      ```sql
      CREATE TABLE users (
          id SERIAL PRIMARY KEY,
          username VARCHAR(100) UNIQUE NOT NULL,
          password VARCHAR(255) NOT NULL,
          role VARCHAR(50) NOT NULL
      );

      CREATE TABLE software (
          id SERIAL PRIMARY KEY,
          name VARCHAR(255) NOT NULL,
          description TEXT,
          access_levels VARCHAR(255)
      );

      CREATE TABLE requests (
          id SERIAL PRIMARY KEY,
          user_id INT REFERENCES users(id),
          software_id INT REFERENCES software(id),
          access_type VARCHAR(50),
          reason TEXT,
          status VARCHAR(50) DEFAULT 'Pending'
      );
      ```

3. **Update Database Credentials**:
    - Open the `application.properties` file or `RequestServlet.java` and update the database credentials:
      ```properties
      jdbc.url=jdbc:postgresql://localhost:5432/userdb
      jdbc.username=postgres
      jdbc.password=root
      ```

4. **Build the Project**:
    - Use Maven to build the project:
      ```bash
      mvn clean install
      ```

5. **Deploy to Tomcat**:
    - Copy the `WAR` file generated in the `target` folder to the `webapps` directory of your Tomcat installation.
    - Start Tomcat and access the application at:
      ```
      http://localhost:8080/user-access-management
      ```

---

## How to Run the Application

1. **Start PostgreSQL**: Ensure the database server is running.
2. **Start Tomcat**: Run the Tomcat server.
3. **Access the Application**:
   - Open a browser and go to:
     ```
     http://localhost:8080/user-access-management
     ```

4. **Roles and Functionalities**:
   - **Employee**:
     - Register an account.
     - Login and request access to software.
   - **Manager**:
     - View and approve/reject pending access requests.
   - **Admin**:
     - Create software records.
     - Manage access requests.

---

## Project Structure

- **src/main/java**:
  - Contains all servlets and core logic (`SignUpServlet`, `LoginServlet`, `RequestServlet`, etc.).
- **src/main/webapp**:
  - Contains all JSP files for the UI (`signup.jsp`, `login.jsp`, `requestAccess.jsp`, etc.).
- **pom.xml**:
  - Maven configuration for managing dependencies.

---

## Technologies Used

- **Backend**: Java Servlets, PostgreSQL
- **Frontend**: JSP, HTML, CSS, Bootstrap
- **Build Tool**: Maven
- **Server**: Apache Tomcat

---

## Troubleshooting
1. **Database Connection Errors**:
    - Verify that PostgreSQL is running and the credentials in the project match your setup.
2. **Tomcat Deployment Issues**:
    - Ensure the `WAR` file is properly deployed in the `webapps` folder.

---
## **Features and Functionalities**

### **User Registration (Sign-Up)**
- Employees can create an account by providing their **username** and **password**.
- A default role of `Employee` is assigned upon registration.

### **User Login**
- Users can log in using their credentials.
- Based on their role:
  - **Employee**: Redirected to the access request page.
  - **Manager**: Redirected to the pending requests page.
  - **Admin**: Redirected to the software creation page.

### **Software Management (Admin Only)**
- Admins can create new software records with details like **name**, **description**, and **access levels**.

### **Access Requests (Employee)**
- Employees can request access to available software by selecting the **software name**, **access type**, and providing a reason.

### **Approval System (Manager/Admin)**
- Managers/Admins can view pending requests, approve or reject them, and update their status in the database.

---

## **Database Design**

### **users Table**
- **id**: Auto-incremented primary key.
- **username**: Unique identifier for the user.
- **password**: Encrypted user password.
- **role**: Role of the user (Employee, Manager, Admin).

### **software Table**
- **id**: Auto-incremented primary key.
- **name**: Software name.
- **description**: Description of the software.
- **access_levels**: Available access levels (e.g., Read, Write, Admin).

### **requests Table**
- **id**: Auto-incremented primary key.
- **user_id**: Foreign key referencing `users`.
- **software_id**: Foreign key referencing `software`.
- **access_type**: Type of access requested (e.g., Read, Write, Admin).
- **reason**: Reason for the access request.
- **status**: Status of the request (Pending, Approved, Rejected).

---

## **User Roles**

### **Employee**
- Can sign up and request access to software.
- Cannot approve or reject requests.

### **Manager**
- Can view, approve, and reject pending requests.
- Cannot request access or create software.

### **Admin**
- Has all privileges of **Employee** and **Manager**.
- Can create new software.

---
## 1. SignUpServlet

**Functionality**:
- Handles user registration and assigns the default role as Employee.

**Flow**:
1. User submits the Sign-Up form.
2. Servlet inserts data into the `users` table.
3. User is redirected to the Login Page.

**Visual Example**:
![Sign-Up Page](path/to/signup_page.png)

---

## 2. LoginServlet

**Functionality**:
- Authenticates users and redirects them based on their role.

**Flow**:
1. User submits credentials on the Login Page.
2. Servlet validates the user against the `users` table.
3. Based on the role:
   - Employee → `requestAccess.jsp`
   - Manager → `pendingRequests.jsp`
   - Admin → `createSoftware.jsp`

**Visual Example**:
![Login Page](path/to/login_page.png)
![Role-Based Redirection](path/to/role_based_flow.png)

---

## 3. RequestServlet

**Functionality**:
- **GET Request**:
  - Fetches software names for Employees.
  - Fetches pending requests for Managers/Admins.
- **POST Request**:
  - Inserts a new access request into the `requests` table.

**Visual Example**:
![Access Request Page](path/to/access_request_page.png)
![Request Flow](path/to/request_flow.png)

---

## 4. ApprovalServlet

**Functionality**:
- Updates the status of access requests to `Approved` or `Rejected`.

**Flow**:
1. Manager/Admin views pending requests.
2. Manager/Admin clicks Approve/Reject.
3. Servlet updates the request status in the database.

**Visual Example**:
![Pending Requests Page](path/to/pending_requests_page.png)

---

## 5. SoftwareServlet

**Functionality**:
- Allows Admins to add new software records to the system.

**Flow**:
1. Admin fills out the Software Creation Form.
2. Servlet inserts the software details into the `software` table.

**Visual Example**:
![Software Creation Page](path/to/software_creation_page.png)

---

---
## **Deployment Instructions**

### **1. Database**
- Ensure **PostgreSQL** is running, and the `userdb` database is created with the required tables.

### **2. WAR Deployment**
- Deploy the `WAR` file to the `webapps` directory of your Tomcat installation.

### **3. Access the Application**
- Launch the application in your browser:
http://localhost:8080/user-access-management

---



## Author
- **Your Name**: Satya Pavan Jupudi
