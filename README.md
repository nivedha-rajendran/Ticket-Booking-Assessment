# Ticket Booking Assessment
# Project Description
The Ticket Booking Assessment repository is an implementation of a simple ticket booking system. 
It provides functionalities for purchasing tickets, fetching user receipt details, retrieving a list of tickets by user email, updating user seat allocation details, and deleting user details. The API aims to facilitate common operations related to ticket booking within a system.

# Contents
The "Ticket Booking Assessment" repository includes the following components:

Controllers: Handle various HTTP endpoints for ticket booking operations.
Services: Implement business logic for ticket booking functionalities.
Models: Represent entities such as TicketBooking and TicketBookingDto.
Configuration files: Set up the Spring Boot application.
Test cases: Ensure the correctness of implemented functionalities.

# Setup
To set up the Ticket Booking Assessment project locally, follow these steps:

Clone the repository.
Open the project in your preferred Java IDE.
Run the application using ./gradlew bootRun.
Access the Swagger UI for API documentation: Swagger UI

# API Usage
To interact with the API-related files and components provided by this repository, refer to the following guidelines:

Purchase a Ticket: Use the /ticket-booking/ endpoint with a POST request, providing the necessary ticket details in the request body.

Fetch User Receipt : Utilize the /ticket-booking/user-receipt-detail endpoint with a GET request, providing the ticket ID as a parameter.

Get All Tickets by User MailId: Access the /ticket-booking/ticket-list endpoint with a GET request, providing the user's email as a parameter.

Update Ticket Detail: Make use of the /ticket-booking/update-seat-number/{ticketId} endpoint with a PUT request, providing the ticket ID and new seat number.

Delete User : Utilize the /ticket-booking/delete-user endpoint with a DELETE request, providing the user's username as a parameter.

### Ticket Booking Controller Payloads

### Create Product (POST) 

Payload : RequestBody

        {
        "from": "mumbai",
        "to": "chennai",
        "userName": "rima",
        "userEmail": "nivedha@",
        "pricePaid": 20,
        "section": "b"
        }

### Fetch Ticket Detail (GET)

Payload 
ticketId  : 1

### Fetch Ticket List By MailId (GET)

Payload 
email : nivedha@gmail.com

### Update Ticket Detail (PUT)

Payload 
ticketId  : 1
seatNumber : 97

### Delete User (DELETE)

Payload 
userName : Nivedha
