# StayEase

Here’s a README template for StayEase API. This README explains the purpose, setup, and usage of each endpoint in your API.

StayEase API

StayEase API is a RESTful service for managing a hotel room booking process. The service includes user authentication, hotel management, booking management, and check-in/check-out functionality. This project is built using Spring Boot, MySQL for persistence, JWT for session management, and Gradle for dependency management.

Features

	•	User authentication and role-based authorization
	•	Hotel creation, deletion, and updating (Admin only)
	•	Room booking, check-in, and check-out (Hotel Manager only)
	•	Secure API endpoints with JWT token validation

Table of Contents

	1.	Installation
	2.	Configuration
	3.	API Documentation
	4.	Testing
	5.	Contributing

Installation

	1.	Clone the repository:

git clone https://github.com/yourusername/StayEase.git


	2.	Navigate into the project directory:

cd stayease-api


	3.	Build the project with Gradle:

./gradlew build


	4.	Run the application:

./gradlew bootRun



Configuration

	1.	Update application.properties with your MySQL database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/stayease
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password


	2.	JWT configuration and authentication settings can also be adjusted in application.properties.

API Documentation

Authentication

Each request (other than authentication itself) requires a valid JWT token in the Authorization header.

Admin Controller

POST /admin/hotel

	•	Description: Creates a new hotel.
	•	Permissions: Admin only
	•	Request Body:

{
  "name": "Hotel Name",
  "location": "City Name",
  ...
}


	•	Response: 201 Created if successful.

DELETE /admin/hotel/{id}

	•	Description: Deletes a hotel by ID.
	•	Permissions: Admin only
	•	Response: 200 OK if successful.

Hotel Manager Controller

PUT /manager/hotel/

	•	Description: Updates details of an existing hotel.
	•	Permissions: Hotel Manager only
	•	Request Body:

{
  "id": 1,
  "name": "Updated Hotel Name",
  ...
}


	•	Response: 200 OK if successful.

PUT /manager/checkIn/{id}

	•	Description: Checks in a user for a booking.
	•	Permissions: Hotel Manager only
	•	Request Body:

{
  "email": "user@example.com",
  "password": "password123"
}


	•	Response: 200 OK if successful, 403 Forbidden if authentication fails.

PUT /manager/checkOut/{id}

	•	Description: Checks out a user for a booking.
	•	Permissions: Hotel Manager only
	•	Request Body:

{
  "email": "user@example.com",
  "password": "password123"
}


	•	Response: 200 OK if successful, 403 Forbidden if authentication fails.

PUT /manager/hotel/{id}/{email}

	•	Description: Cancels a booking for a user by booking ID and user email.
	•	Permissions: Hotel Manager only
	•	Response: 200 OK if successful.
