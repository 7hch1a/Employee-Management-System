# Employee Management System (EMS) - API Documentation

## Overview

The Employee Management System (EMS) is a comprehensive application designed to facilitate efficient management of employee data. This document provides details about the API endpoints and how to interact with them.

## API Base URL

The API base URL for accessing the endpoints is: http://localhost:8082/api

## Authentication

The EMS API does not require authentication for accessing its endpoints.

## Endpoints

### Employee Endpoints

#### Get All Employees

- **URL**: `/employees`
- **Method**: `GET`
- **Description**: Retrieves a list of all employees.
- **Parameters**: None
- **Response**: JSON array containing employee details.

#### Get Employee by ID

- **URL**: `/employees/{id}`
- **Method**: `GET`
- **Description**: Retrieves details of a specific employee by ID.
- **Parameters**: `id` (Employee ID)
- **Response**: JSON object containing employee details.

#### Add New Employee

- **URL**: `/employees`
- **Method**: `POST`
- **Description**: Adds a new employee to the system.
- **Request Body**: JSON object with employee details.
- **Response**: JSON object containing the added employee's details.

#### Update Employee

- **URL**: `/employees/{id}`
- **Method**: `PUT`
- **Description**: Updates details of an existing employee.
- **Parameters**: `id` (Employee ID)
- **Request Body**: JSON object with updated employee details.
- **Response**: JSON object containing the updated employee's details.

#### Delete Employee

- **URL**: `/employees/{id}`
- **Method**: `DELETE`
- **Description**: Deletes an employee from the system.
- **Parameters**: `id` (Employee ID)
- **Response**: Success message indicating deletion.

### Swagger API Documentation

The API documentation is available using Swagger UI, which provides an interactive interface for exploring and testing the endpoints.

- **URL**: [Swagger UI](http://localhost:8082/api/swagger-ui.html)

## Database Configuration

The EMS application uses MySQL as its database. Below are the database configurations:

- **URL**: `jdbc:mysql://localhost:3306/ems`
- **Username**: `root`
- **Password**: (No password provided)

## Additional Notes

- **Show SQL**: SQL queries generated by JPA/Hibernate are displayed (`spring.jpa.show-sql=true`).
- **Automatic DDL Generation**: Hibernate automatically updates the database schema (`spring.jpa.hibernate.ddl-auto=update`).
- **Port**: The application runs on port `8082`.
- **API Documentation Configuration**: Swagger UI is enabled with custom configurations for better usability.

Feel free to explore the API endpoints and utilize the functionalities provided by the Employee Management System. If you encounter any issues or have questions, please refer to the documentation or contact the system administrator for assistance.

