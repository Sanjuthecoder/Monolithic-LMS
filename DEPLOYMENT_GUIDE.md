# Deployment Guide

This guide covers how to deploy the **DLMS Monolithic Application** using **Render** (for Backend & Chatbot) and **Vercel** (for Frontend).

---

## ✅ Prerequisites
1.  **GitHub Repo:** Ensure your code is pushed to a GitHub repository (e.g., `Sanjuthecoder/Delearn-Microservices-LMS-`).
2.  **Secrets:** Have your `.env` values ready (Database URLs, API Keys).

---

## 1. Deploying Backend & Chatbot on Render (Native Runtime)
**Good News:** You do **NOT** need Docker installed on your computer. You simply push your code to GitHub, and Render builds it for you.

### **Step 1: Deploy the Python Chatbot**
1.  Log in to [Render Dashboard](https://dashboard.render.com/).
2.  Click **New +** -> **Web Service**.
3.  Connect your GitHub Repository.
4.  **Settings:**
    -   **Name:** `dlms-chatbot`
    -   **Root Directory:** `Monolithic_LMS/chatbot`
    -   **Runtime:** `Python 3`
    -   **Build Command:** `pip install -r requirements.txt`
    -   **Start Command:** `uvicorn main:app --host 0.0.0.0 --port 10000`
5.  **Environment Variables:**
    -   `HF_TOKEN`: (Your Hugging Face Token)
6.  **Deploy.**
    -   Once deployed, copy the **Service URL** (e.g., `https://dlms-chatbot.onrender.com`).

### **Step 2: Deploy the Java Backend**
1.  Click **New +** -> **Web Service**.
2.  Connect the same GitHub Repository.
3.  **Settings:**
    -   **Name:** `dlms-backend`
    -   **Root Directory:** `Monolithic_LMS/backend`
    -   **Environment:** `Java` (Select `Maven` if asked for build system, or use command below)
    -   **Build Command:** `./mvnw clean package -DskipTests`
    -   **Start Command:** `java -jar target/backend-1.0.0.jar`
    *Note: Verify the jar name in your local `target` folder or use `java -jar target/*.jar` if supported.*
4.  **Environment Variables (Crucial):**
    -   `SPRING_PROFILES_ACTIVE`: `prod`
    -   `TIDB_URL`: (Your MySQL URL from `.env`)
    -   `TIDB_USER`: (Your MySQL User from `.env`)
    -   `TIDB_PASSWORD`: (Your MySQL Password from `.env`)
    -   `COURSE_MONGODB_URI`: (Your MongoDB URL from `.env`)
    -   `JWT_SECRET_KEY`: (Your Secret from `.env`)
    -   `PINATA_API_KEY`: (Your Pinata Key from `.env`)
    -   `PINATA_SECRET_API_KEY`: (Your Pinata Secret from `.env`)
    -   `CHATBOT_SERVICE_URL`: **Paste the Chatbot URL from Step 1** (e.g., `https://dlms-chatbot.onrender.com`).
5.  **Deploy.**
    -   Once finished, copy the **Backend URL** (e.g., `https://dlms-backend.onrender.com`).

---

## 2. Deploying Frontend on Vercel
Vercel is optimized for React apps and is free.

1.  Log in to [Vercel Dashboard](https://vercel.com).
2.  Click **Add New...** -> **Project**.
3.  Import your GitHub Repository.
4.  **Configure Project:**
    -   **Root Directory:** Click "Edit" and select `Monolithic_LMS/frontend`.
    -   **Framework Preset:** Create React App (should auto-detect).
5.  **Environment Variables:**
    -   Name: `REACT_APP_API_GATEWAY_URL` *(Note: React App requires `REACT_APP_` prefix, but your code might use `VITE_` or standard checks. Check your `.env` usage).*
    -   **Value:** Paste your **Render Backend URL** (e.g., `https://dlms-backend.onrender.com`).
    *Important: If you are using Vite, use `VITE_API_BASE_URL`. If Create React App, use `REACT_APP_API_BASE_URL`. Check your `api.js` to see what variable it expects.*
6.  **Deploy.**

---

## 3. Final Verification
1.  Open your **Vercel Frontend URL**.
2.  Login (Requests go to Render Backend).
3.  Chat (Render Backend proxies to Render Chatbot).
4.  View Courses (Render Backend queries MongoDB connection).

**Success!** 🚀
