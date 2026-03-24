# Todo App Deployment using Docker and Kubernetes

This project demonstrates **end-to-end deployment of a Todo Application** using:

- **Docker** for containerization  
- **Docker Compose** for container management  
- **Kubernetes (Minikube)** for container orchestration  

---

# Project Structure

```
todo-app-end-to-end/
└── frontend/
└── backend/
    └── todoApp/
```

---

# Build the jar file of application

Navigate to the backend application directory:

```bash
cd todo-app-end-to-end/backend/todoApp
```

Run the command:

```bash
mvn clean package -DskipTests
```

Now the jar file of the todo app is built in target folder


# 1. Build and Run using Docker

## Using Only Docker Files (Without using yaml file)

Navigate to the frontend directory:

```bash
cd todo-app-end-to-end/frontend
```
### Build Frontend Image

```bash
docker build -t todo-frontend .
```

Navigate to the backend directory:

```bash
cd todo-app-end-to-end/backend/todoApp
```
### Build Backend Image

```bash
docker build -t todo-backend .
```

---

## Pull MySQL Image

```bash
docker pull mysql
```

This pulls the latest MySQL image from Docker Hub.

---

# Docker Networking

To enable communication between containers, create a **custom bridge network**:

```bash
docker network create todo-network
```

---

# Run Containers

## 1. Run MySQL Container

Run MySQL first so backend can connect to it:

```bash
docker run --name mysql -d \
--network todo-network \
-p 3307:3306 \
-e MYSQL_ROOT_PASSWORD=your_password \
-e MYSQL_HOST=mysql \
-e MYSQL_DATABASE=todo_db \
mysql
```

### Important Notes

- `MYSQL_ROOT_PASSWORD` → Required to access MySQL  
- `MYSQL_HOST` → Specifies MySQL as host  
- `MYSQL_DATABASE` → Automatically creates database  

# Now you could check for the database using 

```bash
docker exec -it todo-mysql mysql -u root -p
```

By this you will get into mysql and could verify the database is created or not

Use command:

```bash
show databases;
```

```bash
use todo_db;
```

⚠️ Ensure:
- Username, password, and database name match backend configuration  
- Same credentials must be used in backend  

---

## 2. Run Backend Container

```bash
docker run --name todo-backend -d \
-p 8080:80 \
--network todo-network \
-e DB_URL=jdbc:mysql://mysql:3306/todo_db \
-e DB_USERNAME=root \
-e DB_PASSWORD=your_password \
todo-backend
```

### Note
- Environment variables are passed from command line as docker compose is not used
- These values must match your backend code configuration  

---

## 3. Run Frontend Container

```bash
docker run --name todo-frontend -d \
--network todo-network \
-p 1313:80 \
todo-frontend
```

---

# Verify Network Connectivity

Check if all containers are connected to the network:

```bash
docker network inspect todo-network
```

---

# Access the Application

Once all containers are running, open:

```
http://localhost:1313
```
---

# Using Docker Compose

```bash
cd todo-app-end-to-end
```

Run the following command:

```bash
docker compose up --build
```

Once all the containers are up and running, you can access the application at:

```
http://localhost:1313
```

---

# Conclusion

In this project, we successfully:

- Built frontend and backend Docker images  
- Pulled and configured MySQL container  
- Created a custom Docker network  
- Enabled communication between containers  
- Deployed a full-stack Todo application using docker compose

This demonstrates a **complete containerized application setup using Docker**.

---