Notification Service is a microservice of the BookNPlay Sports Turf Booking Platform. It is responsible for handling email notifications for various user activities, including user registration, profile updates, password reset, booking confirmation, booking cancellation, and payment-related events. It uses asynchronous processing with Spring’s @Async annotation to send emails without blocking the main application flow. It also includes a scheduler for executing scheduled notification tasks.

Technology Stack: Java 21, Spring Boot, Spring Boot Mail, Spring Scheduling, Spring Async, Maven, and Eureka Client.

Configuration: Service Name: notification-service | Port: 8087 | Eureka Server: http://localhost:8761

The service integrates with other BookNPlay microservices through REST APIs to manage email notifications.
