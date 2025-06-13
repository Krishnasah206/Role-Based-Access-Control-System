# Role-Based Access Control Journal App

A secure full-stack backend application that manages users and their journal entries, with role-based access control using Spring Boot, Spring Security, JWT, and MongoDB. Also includes Redis caching for weather API integration.

---

## 📌 Features

✅ Public user registration
🔐 **Authentication and Authorization using JWT**
🔒 Role-based access control:

* **ROLE\_USER**: Create, view, update, and delete own journal entries
* **ROLE\_ADMIN**: View and manage all users

📓 Journal Entry CRUD per user
🌦️ **Weather API integration with Redis caching**
🗂️ MongoDB persistence with embedded documents
🔁 Secure password hashing with BCrypt

---

## 🧱 Tech Stack

* **Backend Framework**: Spring Boot
* **Security**: Spring Security + JWT
* **Database**: MongoDB
* **ORM**: Spring Data MongoDB
* **Caching**: Redis
* **Weather API**: External API with Redis caching

---

## 🗃️ MongoDB Schema

### User Entity

```json
{
  "_id": "ObjectId",
  "userName": "string",
  "password": "string (hashed)",
  "roles": ["USER" | "ADMIN"],
  "journalEntries": [JournalEntry]
}
```

### JournalEntry Entity

```json
{
  "id": "ObjectId",
  "title": "string",
  "content": "string",
  "timestamp": "ISO Date"
}
```

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+
* Maven
* MongoDB (local or cloud)
* Redis (local or cloud)
* Postman or HTTP client

---

## 🛠️ Run the Application

### Configuration

Update `application.properties`:

```properties
# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/your_db_name
spring.data.mongodb.database=journalApp

# JWT
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000  # 1 day

# Redis
spring.redis.host=localhost
spring.redis.port=6379
```

### Build and Run

```bash
mvn spring-boot:run
```

---

## 📂 API Endpoints

### 🔓 Public

* `POST /public/create-user` – Register new user
* `GET /public/health-check` – Service health status
* `POST /public/login` – **Get JWT token**

### 🔐 Authenticated (JWT Required in `Authorization: Bearer <token>` header)

* `GET /journal` – Get all journal entries
* `POST /journal` – Create new entry
* `GET /journal/id/{id}` – Get entry by ID
* `PUT /journal/id/{id}` – Update entry
* `DELETE /journal/id/{id}` – Delete entry
* `PUT /user` – Update user details
* `DELETE /user` – Delete own account

### 🛡️ Admin Only

* `GET /admin/all-users` – Get all registered users
* `POST /admin/create-admin-user` – Create new admin

### ☁️ Weather API (Cached with Redis)

* `GET /weather/{city}` – Get weather info for a city (uses Redis caching)

---

## 🔐 Role-Based Access Logic

| Endpoint Group | Access Level       |
| -------------- | ------------------ |
| `/public/**`   | Public             |
| `/journal/**`  | ROLE\_USER         |
| `/user/**`     | ROLE\_USER         |
| `/admin/**`    | ROLE\_ADMIN        |
| `/weather/**`  | ROLE\_USER / ADMIN |

**Configured via `SecurityFilterChain`, `UserDetailsServiceImpl`, and JWT filter.**

---

## 🧪 Sample Credentials

### Register User

```http
POST /public/create-user
{
  "userName": "user1",
  "password": "pass123"
}
```

### Register Admin

```http
POST /admin/create-admin-user
{
  "userName": "admin1",
  "password": "adminpass",
  "roles": ["ADMIN"]
}
```

### Login

```http
POST /public/login
{
  "userName": "user1",
  "password": "pass123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

Use this token in Authorization header:

```
Authorization: Bearer <your_token>
```

---

## 📦 Project Structure

```
journalApp/
├── config/              # Security and Redis config
├── controller/          # REST controllers
├── entity/              # MongoDB entities
├── repository/          # Mongo repositories
├── service/             # Business and JWT logic
├── util/                # JWT utilities, Redis cache helpers
├── resources/
│   └── application.properties
└── JournalAppApplication.java
```

---

## 📝 Notes

* Passwords are securely hashed using BCrypt.
* JWT is used for stateless authentication.
* Redis is used to cache weather API responses for performance and rate-limiting optimization.
* Each user can only access their own journal entries.
* Admins manage user accounts but do not access user journals.

---

## ✅ Future Improvements

* Refresh tokens for JWT
* Email verification
* Pagination and search in journals
* Rate limiting on weather API
* React/Angular frontend

---

## 📄 License

This project is open-source and available under the MIT License.