# LogShip Microservices Project

## Overview

LogShip is a Spring Boot-based microservices logistics platform. It consists of several independent services, each responsible for a specific domain, and an API Gateway for centralized routing and authentication.

## Project Structure

- **api-gateway/**  
  Centralized gateway for routing requests and JWT authentication.

- **order-service/**  
  Manages order creation, updates, and retrieval.

- **shipment-service/**  
  Handles shipment scheduling, pickup, delivery, and tracking.

- **tracker-service/**  
  Provides shipment tracking functionalities.

- **warehouse-service/**  
  Manages warehouses, inventory, and employee assignments.

- **user-serivce/**  
  Handles user management, authentication, and authorization.

---

## API Gateway

- **Purpose:**  
  Routes requests to appropriate microservices and enforces JWT authentication.

- **Endpoints:**  
  - `/auth/**` → user-serivce  
  - `/user/**` → user-serivce  
  - `/order/**` → order-service  
  - `/tracker/**` → tracker-service  
  - `/warehouse/**` → warehouse-service  
  - `/shipment/**` → shipment-service

---

## Service Details & Exposed Endpoints

Below are the main REST endpoints exposed by each microservice via the API Gateway.

---

### **User Service**

- **Purpose:**  
  User registration, authentication, and profile management.

- **Endpoints:** 

| Method | Endpoint                | Description                        | Auth Required | Roles         |
|--------|-------------------------|------------------------------------|--------------|--------------|
| GET    | `/user/`                | Greeting endpoint                  | Yes          | Any          |
| GET    | `/user/getAllUsers`     | List all users                     | Yes          | ADMIN        |
| GET    | `/user/me`              | Get current user info              | Yes          | Any          |
| POST   | `/auth/login`           | User login                         | No           | -            |
| POST   | `/auth/register`        | User registration                  | No           | -            |

---

### **Order Service**

- **Purpose:**  
  Order lifecycle management.

- **Endpoints:**

| Method | Endpoint                | Description                        | Auth Required | Roles         |
|--------|-------------------------|------------------------------------|--------------|--------------|
| POST   | `/order/create`         | Create a new order                 | Yes          | CUSTOMER     |
| POST   | `/order/addWeight`      | Add/update order weight            | Yes          | CUSTOMER     |
| GET    | `/order/{id}`           | Get order details                  | Yes          | Any          |
| GET    | `/order/list`           | List all orders                    | Yes          | Any          |

---

### **Shipment Service**

- **Purpose:**  
  Shipment scheduling, pickup, delivery, and status updates.

- **Endpoints:**

| Method | Endpoint                    | Description                        | Auth Required | Roles         |
|--------|-----------------------------|------------------------------------|--------------|--------------|
| POST   | `/shipment/schedulePickup`  | Schedule a pickup                  | Yes          | MANAGER      |
| POST   | `/shipment/pickup`          | Mark pickup as completed           | Yes          | MANAGER      |
| POST   | `/shipment/createShipment`  | Create a shipment                  | Yes          | MANAGER      |
| POST   | `/shipment/startShipment`   | Start shipment                     | Yes          | MANAGER      |
| POST   | `/shipment/endShipment`     | End shipment                       | Yes          | MANAGER      |
| POST   | `/shipment/scheduleDelivery`| Schedule delivery                  | Yes          | MANAGER      |
| GET    | `/shipment/{id}`            | Get shipment details               | Yes          | Any          |
| GET    | `/shipment/list`            | List all shipments                 | Yes          | Any          |

---

### **Warehouse Service**

- **Purpose:**  
  Warehouse creation, inventory management, and employee assignment.

- **Endpoints:**

| Method | Endpoint                | Description                        | Auth Required | Roles         |
|--------|-------------------------|------------------------------------|--------------|--------------|
| POST   | `/warehouse/create`     | Create a warehouse                  | Yes          | ADMIN        |
| GET    | `/warehouse/{id}`       | Get warehouse details               | Yes          | Any          |
| GET    | `/warehouse/list`       | List all warehouses                 | Yes          | Any          |
| POST   | `/warehouse/addInventory`| Add inventory to warehouse          | Yes          | MANAGER      |
| POST   | `/warehouse/assignEmployee`| Assign employee to warehouse       | Yes          | MANAGER      |

---

### **Tracker Service**

- **Purpose:**  
  Shipment tracking (details not shown in provided code).

- **Endpoints:**

| Method | Endpoint                | Description                        | Auth Required | Roles         |
|--------|-------------------------|------------------------------------|--------------|--------------|
| GET    | `/tracker/{shipmentId}` | Get tracking info for shipment      | Yes          | Any          |
| POST   | `/tracker/update`       | Update tracking status              | Yes          | SYSTEM/ADMIN |

---

**Notes:**
- All endpoints except `/auth/**` require JWT authentication.
- Role-based access is enforced via Spring Security.
- Endpoints may accept/return JSON payloads; see controller classes for details.

---

## How It Works

- **Authentication:**  
  All endpoints (except `/auth/**`) are protected by JWT authentication via the API Gateway.

- **Routing:**  
  The API Gateway forwards requests to the appropriate microservice based on path predicates.

- **Microservices:**  
  Each service is independently deployable and communicates via REST APIs.

---

## Getting Started

1. **Start Kafka** (if required for event-driven features):  
   Run `start kafka.bat`.

2. **Run Services:**  
   Use Maven (`mvnw spring-boot:run`) in each service directory.

3. **Access Endpoints:**  
   Send requests to the API Gateway (default: `http://localhost:8080`).

---

## Technologies Used

- Spring Boot
- Spring Cloud Gateway
- Spring Security (JWT)
- MapStruct (DTO mapping)
- Maven

---

## Database Table Structures

Below are the main tables for each microservice, including field names, types, relevance, and constraints.

---

### **User Service**

| Field Name     | Type         | Relevance                | Constraints/Remarks           |
|----------------|--------------|--------------------------|-------------------------------|
| id             | BIGINT       | Primary Key              | Auto-increment, PK            |
| username       | VARCHAR(50)  | Unique user identifier   | Unique, Not Null              |
| password       | VARCHAR(255) | User password (hashed)   | Not Null                      |
| email          | VARCHAR(100) | Contact info             | Unique, Not Null              |
| role           | VARCHAR(20)  | User role (ADMIN, etc.)  | Not Null                      |
| created_at     | TIMESTAMP    | Audit                    | Default: now()                |

---

### **Order Service**

| Field Name     | Type         | Relevance                | Constraints/Remarks           |
|----------------|--------------|--------------------------|-------------------------------|
| id             | BIGINT       | Primary Key              | Auto-increment, PK            |
| customer_id    | BIGINT       | Reference to user        | FK to user.id                 |
| status         | VARCHAR(20)  | Order status             | Not Null                      |
| weight         | DECIMAL(8,2) | Order weight             | Nullable                      |
| created_at     | TIMESTAMP    | Audit                    | Default: now()                |

---

### **Shipment Service**

| Field Name     | Type         | Relevance                | Constraints/Remarks           |
|----------------|--------------|--------------------------|-------------------------------|
| id             | BIGINT       | Primary Key              | Auto-increment, PK            |
| order_id       | BIGINT       | Reference to order       | FK to order.id                |
| warehouse_id   | BIGINT       | Reference to warehouse   | FK to warehouse.id            |
| status         | VARCHAR(20)  | Shipment status          | Not Null                      |
| scheduled_at   | TIMESTAMP    | Pickup/Delivery schedule | Nullable                      |
| started_at     | TIMESTAMP    | Shipment start time      | Nullable                      |
| ended_at       | TIMESTAMP    | Shipment end time        | Nullable                      |

---

### **Warehouse Service**

| Field Name     | Type         | Relevance                | Constraints/Remarks           |
|----------------|--------------|--------------------------|-------------------------------|
| id             | BIGINT       | Primary Key              | Auto-increment, PK            |
| name           | VARCHAR(100) | Warehouse name           | Unique, Not Null              |
| location       | VARCHAR(255) | Address                  | Not Null                      |
| capacity       | INT          | Max inventory capacity   | Not Null                      |

---

### **Tracker Service**

| Field Name     | Type         | Relevance                | Constraints/Remarks           |
|----------------|--------------|--------------------------|-------------------------------|
| id             | BIGINT       | Primary Key              | Auto-increment, PK            |
| shipment_id    | BIGINT       | Reference to shipment    | FK to shipment.id             |
| status         | VARCHAR(20)  | Tracking status          | Not Null                      |
| updated_at     | TIMESTAMP    | Last update time         | Default: now()                |
| location       | VARCHAR(255) | Current location         | Nullable                      |

---

**Notes:**
- All `id` fields are primary keys and auto-incremented.
- Foreign keys (`customer_id`, `order_id`, `warehouse_id`, `shipment_id`) reference related tables.
- Status fields use enums or predefined values.
- Timestamps are used for auditing and tracking.
- Add indexes on frequently queried fields for performance.

---

## License

See individual service directories for license information.
