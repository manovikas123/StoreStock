# StoreStock

## 📌 Project Overview

StoreStock is a Spring Boot-based application designed to manage and track inventory efficiently. It helps businesses keep track of stock levels, manage orders, and streamline inventory operations.

## 🚀 Features

- User authentication & role-based access control
- Product inventory management (add, update, delete items)
- Order management & tracking
- RESTful API endpoints for integration
- Docker support for containerized deployment
- Database integration with MySQL

## 🛠️ Tech Stack

- **Backend:** Spring Boot, Spring MVC, Spring Security
- **Frontend:** Thymeleaf , HTML , CSS
- **Database:** MySQL
- **Build Tool:** Maven
- **Containerization:** Docker
- **Version Control:** Git & GitHub

## 📥 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-repo/storestock.git
   cd storestock
   ```
2. **Configure the database**
    - Update `application.properties` with your MySQL credentials.
3. **Build & Run the Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the application**
    - Open `http://localhost:8080` in your browser.

## 🐳 Docker Deployment

To run StoreStock using Docker:

```bash
docker build -t storestock .
docker run -p 8080:8080 storestock
```

## 📄 API Endpoints

| Method | Endpoint       | Description            |
| ------ | -------------- | ---------------------- |
| GET    | /products      | Get all products       |
| POST   | /products      | Add a new product      |
| PUT    | /products/{id} | Update product details |
| DELETE | /products/{id} | Remove a product       |

## 📜 License

This project is licensed under the MIT License.

---

Feel free to contribute or reach out for any queries!

