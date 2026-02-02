# DLMS (Decentralized Learning Management System) - Monolithic Hybrid Architecture

## 📌 Project Overview
DLMS is a comprehensive Learning Management System designed to provide a seamless experience for Students, Instructors, and Admins. It leverages a **Hybrid Monolithic Architecture**, combining a robust **Spring Boot** backend with a specialized **Python AI Chatbot**, all interacting with a **React** frontend.

### 🚀 Key Features
-   **User Roles:** Separate portals for Students, Instructors, and Admin.
-   **Course Management:** Create, update, delete, and view courses with rich media content.
-   **Media Streaming:** Secure video streaming using **IPFS / Pinata**.
-   **AI Chatbot:** Context-aware AI assistant (powered by **Hugging Face**) that helps students with course queries.
-   **Enrollment & Progress:** Track student enrollments and lesson completion.
-   **Secure Authentication:** JWT-based stateless authentication with Role-Based Access Control (RBAC).

---

## 🏗 Architecture & Tech Stack

### 1. **Backend (Java Spring Boot)**
The core business logic resides in a single monolithic Spring Boot application.
-   **Framework:** Spring Boot 3.2.0
-   **Database (Relational):** MySQL / TiDB (User Auth, Enrollments)
-   **Database (NoSQL):** MongoDB (Courses, Media Metadata)
-   **Security:** Spring Security + JWT
-   **Communication:**
    -   Exposes REST APIs on Port `8080`.
    -   Proxies Chatbot requests to the Python service.

### 2. **AI Chatbot Service (Python)**
A specialized microservice for AI inference.
-   **Framework:** FastAPI
-   **AI Model:** Hugging Face Inference API (Qwen/Mistral)
-   **Function:** Fetches course context dynamically and answers user queries.
-   **Port:** Runs on Port `8000` (Internal).

### 3. **Frontend (React)**
A modern, responsive user interface.
-   **Framework:** React (Create React App)
-   **Styling:** Material UI (MUI) @ v5
-   **State Management:** React Hooks
-   **Port:** Runs on Port `3000`.

### 4. **Infrastructure & Deployment**
-   **Docker:** Containerized setup for consistent environments.
-   **Storage:** IPFS (Pinata) for decentralized media storage.

---

## 📂 Project Structure
```
Monolithic_LMS/
├── backend/            # Spring Boot Monolith (Auth, Course, Media, Enrollment)
│   ├── src/main/java/com/dlms/backend/
│   │   ├── auth/       # User & Security Logic
│   │   ├── course/     # Course Management
│   │   ├── media/      # IPFS & Media Handling
│   │   ├── enrollment/ # Student Progress
│   │   └── chatbot/    # Proxy Controller for AI
│   └── src/main/resources/application.properties # Unified Config
│
├── chatbot/            # Python FastAPI Service
│   ├── main.py         # App Entry Point & AI Logic
│   └── course_fetcher.py # Context Retrieval Logic
│
├── frontend/           # React Application
│   ├── src/components/ # UI Components
│   └── public/
│
└── docker-compose.yml  # Orchestration for Local Development
```

---

## ⚡ How to Run Locally

### Prerequisites
-   Docker Desktop (Running)
-   Node.js & npm
-   Java 17+
-   Python 3.10+

### Option 1: Using Docker (Recommended)
This starts MySQL, MongoDB, Backend, Chatbot, and Frontend in one go.
```bash
cd Monolithic_LMS
docker-compose up --build
```
*Access the app at `http://localhost:3000`*

### Option 2: Manual Startup
If you prefer running services individually:

**1. Backend**
```bash
cd backend
./mvnw spring-boot:run
```

**2. Chatbot**
```bash
cd chatbot
pip install -r requirements.txt
python main.py
```

**3. Frontend**
```bash
cd frontend
npm install
npm start
```

---

## 🔒 Environment Configuration
**NEVER commit the `.env` file.**
Create a `.env` file in the root `Monolithic_LMS` folder with the following secrets:

```env
# Database
TIDB_URL=jdbc:mysql://...
TIDB_USER=...
TIDB_PASSWORD=...

# MongoDB
COURSE_MONGODB_URI=mongodb+srv://...

# Security
JWT_SECRET_KEY=...

# AI & Media
PINATA_API_KEY=...
PINATA_SECRET_API_KEY=...
HF_TOKEN=...
```
