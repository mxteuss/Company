# Microservices Architecture - Spring Boot + RabbitMQ

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Microservices-brightgreen)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Message%20Broker-orange)
![Status](https://img.shields.io/badge/Project%20Status-In%20Development-yellow)

This project demonstrates a clean microservices architecture using **Spring Boot** with asynchronous communication powered by **RabbitMQ**.
The system simulates registering employees and sending automatic email notifications when new employees are created.

---

# 🌐 Architecture Overview
The solution contains **two independent services**:

## 1️⃣ Employee Service

* Exposes REST endpoints to manage employees.
* Saves employee data to its own database.
* Publishes events to RabbitMQ every time a new employee is created.

## 2️⃣ Email Service

* Listens to messages coming from RabbitMQ.
* Processes the received event.
* Saves a record simulating an email delivery.

---

# 🔗 Communication Flow

```
┌──────────────────┐      Message      ┌───────────────────┐
│ Employee Service │ ─────────────────▶│   RabbitMQ Queue   │
└──────────────────┘                   └─────────▲─────────┘
                                                  │
                                                  │ Consumes
                                         ┌────────┴────────┐
                                         │  Email Service   │
                                         └──────────────────┘
```

This architecture ensures:
✔ Decoupled services
✔ Asynchronous event handling
✔ Independent failure isolation

---

# 🧰 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Spring AMQP**
* **RabbitMQ**
* **H2 / PostgreSQL**
* **Maven**

---

# 📁 Project Structure

```
microservices
├── employee-service
│   ├── controller
│   ├── domain
│   ├── dto
│   ├── repository
│   ├── service
│   └── config
└── email-service
    ├── consumer
    ├── model
    ├── dto
    ├── repository
    ├── service
    └── config
```

Each service is completely isolated and follows clean separation of responsibility.

---

# ▶ Running the Project

## 1️⃣ Start RabbitMQ

If using Docker:

```bash
docker run -d \
 --hostname rabbit \
 --name rabbitmq \
 -p 5672:5672 \
 -p 15672:15672 \
 rabbitmq:3-management
```

Access management console:

```
http://localhost:15672
```

Default login:

```
user: guest
pass: guest
```

---

## 2️⃣ Start Each Service

Enter each project folder and run:

```bash
mvn spring-boot:run
```

Or run via your IDE.

---

# 🧪 Testing the Flow

### Create an Employee

```bash
POST http://localhost:8080/employees
Content-Type: application/json

{
  "nome": "Mateus",
  "email": "mateus@example.com",
  "cargo": "Desenvolvedor"
}
```

Expected result:

* Employee is saved.
* Message is published to RabbitMQ.
* Email Service consumes the message.
* Email is logged as "sent".

---

# 📌 Next Improvements

Some planned enhancements:

* API Gateway (Spring Cloud Gateway / Kong)
* Distributed monitoring (Grafana + Prometheus)
* Distributed tracing (Zipkin / Sleuth)
* Dead-Letter Queues for failed events
* Testing strategies:

  * Unit tests
  * Integration tests
  * Contract tests

---

# 👤 Author

Project built for educational purposes, demonstrating message-driven microservices with Spring Boot and RabbitMQ.

---

