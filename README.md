# Wallet-Transaction
A backend wallet transaction system using Spring Boot, PostgreSQL and @Transactional


# Wallet-Transaction-Service
A simple backend wallet transaction system built using **Spring Boot, PostgreSQL, Spring Data JPA, and `@Transactional`.
The project demonstrates how money can be transferred between two users while maintaining database transaction consistency.


## Features

* Create and manage wallet users
* Store user wallet balances in PostgreSQL
* Transfer money between two users
* Debit amount from the sender
* Credit amount to the receiver
* Use `@Transactional` for transaction management
* Automatically rollback the transaction when an operation fails
* Custom transaction manager configuration
* Simple REST API for wallet transfers



## Tech Stack

* **Java 17**
* **Spring Boot 4.1.1**
* **Spring Web MVC**
* **Spring Data JPA**
* **PostgreSQL**
* **Lombok**
* **Maven**



## Project Structure

```text
Wallet-Transaction-Service/
│
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── backend/
│   │               └── service/
│   │                   └── wallet_transaction_service/
│   │                       │
│   │                       ├── config/
│   │                       │   └── TransactionManagerConfig.java
│   │                       │
│   │                       ├── controller/
│   │                       │   └── WalletController.java
│   │                       │
│   │                       ├── entity/
│   │                       │   └── User.java
│   │                       │
│   │                       ├── repository/
│   │                       │   └── UserRepository.java
│   │                       │
│   │                       ├── service/
│   │                       │   ├── UserService.java
│   │                       │   └── WalletService.java
│   │                       │
│   │                       └── WalletTransactionServiceApplication.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── backend/
│                   └── service/
│                       └── wallet_transaction_service/
│                           └── WalletTransactionServiceApplicationTests.java
│
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```



## How It Works

The wallet transfer process is simple:
Sender
   │
   ▼
Debit Amount
   │
   ▼
Transaction
   │
   ▼
Credit Amount
   │
   ▼
Receiver


The transfer operation is handled using Spring's `@Transactional` annotation.

@Transactional
public void transfer(Long senderId, Long receiverId, Double amount) {
    userService.debit(senderId, amount);
    userService.credit(receiverId, amount);
}

```
Both the debit and credit operations are executed as part of the same database transaction.
If an operation fails, the transaction can be rolled back so that the database does not remain in an incomplete state.


API Endpoint
Transfer Money

Method: POST
Endpoint:
/wallet/transfer

### Request Parameters

| Parameter    | Type   | Description                    |
| ------------ | ------ | ------------------------------ |
| `senderId`   | Long   | ID of the user sending money   |
| `receiverId` | Long   | ID of the user receiving money |
| `amount`     | Double | Amount to transfer             |

### Example Request :
POST http://localhost:8080/wallet/transfer?senderId=1&receiverId=2&amount=500


### Successful Response
Transfer completed

### Failed Response
Transfer failed: <error message>

## Database
This project uses **PostgreSQL** for storing wallet user information and balances.

### User Entity
The `User` entity contains:

| Field     | Type   | Description            |
| --------- | ------ | ---------------------- |
| `id`      | Long   | Unique user ID         |
| `name`    | String | User name              |
| `balance` | Double | Current wallet balance |


## Transaction Management
The project uses `@Transactional` to manage wallet transfers.
The transaction follows this flow:

Start Transaction
       │
       ▼
Debit Sender
       │
       ▼
Credit Receiver
       │
       ▼
Transaction Successful
       │
       ▼
     COMMIT



If an error occurs:

Start Transaction
       │
       ▼
Debit Sender
       │
       ▼
Error Occurs
       │
       ▼
   ROLLBACK

This helps maintain consistency between the sender's and receiver's wallet balances.



## Getting Started
1. Clone the Repository

git clone <your-repository-url>
Move into the project directory:
cd Wallet-Transaction-Service

2. Configure PostgreSQL

Create a PostgreSQL database for the project.
Then configure your database connection in:
src/main/resources/application.properties

#Example configuration:

spring.datasource.url=jdbc:postgresql://localhost:5432/....
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Replace the database name, username, and password with your PostgreSQL configuration.

### 3. Run the Application

Using Maven Wrapper:

Windows
mvnw.cmd spring-boot:run

Linux / macOS
./mvnw spring-boot:run

The application will start at:
http://localhost:8080

## Example :
Suppose there are two users:

User 1
Name: Alice
Balance: ₹2000

User 2
Name: Bob
Balance: ₹1000

If Alice transfers ₹500 to Bob:

Alice
₹2000 → ₹1500

Bob
₹1000 → ₹1500

The transfer is handled within a single transaction.


## Learning Purpose:
This project was created to practice and understand:

* Spring Boot REST APIs
* Spring Data JPA
* PostgreSQL integration
* Service-layer architecture
* Repository pattern
* Database transactions
* `@Transactional`
* Transaction commit and rollback
* Custom transaction manager configuration

## Author
Ghanshyam Pal

