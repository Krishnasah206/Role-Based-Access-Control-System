````markdown
# 🛡️ Role-Based Access Control Journal App

A secure full-stack backend application that manages **users** and their **journal entries**, with **role-based access control** using Spring Boot, Spring Security, and MongoDB.

---

## 📌 Features

- ✅ Public user registration
- 🔐 Authentication using HTTP Basic Auth
- 🔒 Role-based access:
  - `ROLE_USER`: Create, view, update, and delete own journal entries
  - `ROLE_ADMIN`: View and manage all users
- 📓 Journal Entry CRUD (per user)
- 🗂️ MongoDB persistence with embedded documents
- 🔁 Secure password hashing with BCrypt

---

## 🧱 Tech Stack

- **Backend Framework**: Spring Boot
- **Security**: Spring Security
- **Database**: MongoDB
- **ORM**: Spring Data MongoDB

---

## 🗃️ MongoDB Schema

### `User` Entity
```json
{
  "_id": "ObjectId",
  "userName": "string",
  "password": "string (hashed)",
  "roles": ["USER" | "ADMIN"],
  "journalEntries": [JournalEntry]
}
````

### `JournalEntry` Entity

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
* MongoDB (running locally or cloud)
* Postman or HTTP client (for testing)

---

### 🛠️ Run the Application

1. **Configure MongoDB in** `application.properties`

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/your_db_name
spring.data.mongodb.database=journalApp
```

2. **Build and Run**

```bash
mvn spring-boot:run
```

---

## 📂 API Endpoints

### 🔓 Public

* `POST /public/create-user`: Register new user
* `GET /public/health-check`: Service health status

### 🔐 Authenticated User

* `GET /journal`: Get all journal entries
* `POST /journal`: Create new entry
* `GET /journal/id/{id}`: Get entry by ID
* `PUT /journal/id/{id}`: Update entry
* `DELETE /journal/id/{id}`: Delete entry
* `PUT /user`: Update user details
* `DELETE /user`: Delete own account

### 🛡️ Admin Only

* `GET /admin/all-users`: Get all registered users
* `POST /admin/create-admin-user`: Create new admin

---

## 🔐 Role-Based Access Logic

| Endpoint Group | Access Level |
| -------------- | ------------ |
| `/public/**`   | Public       |
| `/journal/**`  | `ROLE_USER`  |
| `/user/**`     | `ROLE_USER`  |
| `/admin/**`    | `ROLE_ADMIN` |

Configured via `SecurityFilterChain` and `UserDetailsServiceImpl`.

---

## 🧪 Sample Credentials

```json
POST /public/create-user
{
  "userName": "user1",
  "password": "pass123"
}

POST /admin/create-admin-user
{
  "userName": "admin1",
  "password": "adminpass",
  "roles": ["ADMIN"]
}
```

---

## 📦 Project Structure

```
journalApp/
├── config/              # Spring Security config
├── controller/          # REST controllers
├── entity/              # MongoDB entities
├── repository/          # Mongo repositories
├── service/             # Business logic
├── resources/
│   └── application.properties
└── JournalAppApplication.java
```

---

## 📝 Notes

* Passwords are securely hashed using BCrypt.
* All operations require Basic Auth with valid user credentials.
* Each user can only access their own journal entries.
* Admins cannot access or modify journal entries directly.

---

## ✅ Future Improvements

* JWT authentication
* Frontend integration
* Email verification
* Pagination and search for journals

---

## 📄 License

This project is open-source and available under the MIT License.

```
