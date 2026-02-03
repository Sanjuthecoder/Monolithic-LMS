# Deployment Guide

This guide covers how to deploy the **DLMS Monolithic Application** using **Render** (for Backend & Chatbot) and **Vercel** (for Frontend).

---

## ✅ Prerequisites

1.  **GitHub Repo:** Ensure your code is pushed to a GitHub repository.
2.  **Secrets:** Have your `.env` values ready (Database URLs, API Keys).

---

## 1. Deploying Chatbot on Render (Python Native)

1.  Log in to [Render Dashboard](https://dashboard.render.com/).
2.  Click **New +** -> **Web Service**.
3.  Connect your GitHub Repository.
4.  **Settings:**
    -   **Name:** `dlms-chatbot`
    -   **Root Directory:** `Monolithic_LMS/chatbot`
    -   **Runtime:** `Python 3`
    -   **Build Command:** `pip install -r requirements.txt`
    -   **Start Command:** `uvicorn main:app --host 0.0.0.0 --port $PORT`
5.  **Environment Variables:**
    -   `HF_TOKEN`: (Your Hugging Face Token)
    -   `PYTHON_VERSION`: `3.10.0` (Optional, recommends a specific version)
6.  **Deploy.**
    -   Once deployed, copy the **Service URL** (e.g., `https://dlms-chatbot.onrender.com`).

---

## 2. Deploying Backend on Render (Java Native)

1.  Click **New +** -> **Web Service**.
2.  Connect the same GitHub Repository.
3.  **Settings:**
    -   **Name:** `dlms-backend`
    -   **Root Directory:** `Monolithic_LMS/backend`
    -   **Runtime:** **Docker** (Select `Docker` from the list. It works perfectly with the included `Dockerfile`).
    -   **Plan:** Free (or as needed)
    -   *Note: Docker will automatically build using the configuration in your source code.*
4.  **Environment Variables (Crucial):**
    -   `SPRING_PROFILES_ACTIVE`: `prod`
    -   `TIDB_URL`: (Your MySQL URL from `.env`)
    -   `TIDB_USER`: (Your MySQL User)
    -   `TIDB_PASSWORD`: (Your MySQL Password)
    -   `COURSE_MONGODB_URI`: (Your MongoDB URL)
    -   `JWT_SECRET_KEY`: (Your JWT Secret)
    -   `PINATA_API_KEY`: (Your Pinata Key)
    -   `PINATA_SECRET_API_KEY`: (Your Pinata Secret)
    -   `CHATBOT_SERVICE_URL`: **Paste the Chatbot URL from Step 1** (e.g., `https://dlms-chatbot.onrender.com`)
    -   `CORS_ALLOWED_ORIGINS`: (Optional) Comma-separated list of allowed frontend URLs (e.g., `https://monolithic-lms.onrender.com,http://localhost:3000`). *Note: The code now defaults to including your Render frontend, but this overrides it.*
5.  **Deploy.**
    -   Once finished, copy the **Backend URL** (e.g., `https://dlms-backend.onrender.com`).

---

## 3. Deploying Frontend on Vercel

1.  Log in to [Vercel Dashboard](https://vercel.com).
2.  Click **Add New...** -> **Project**.
3.  Import your GitHub Repository.
4.  **Configure Project:**
    -   **Root Directory:** Click "Edit" and select `Monolithic_LMS/frontend`.
    -   **Framework Preset:** Create React App.
5.  **Environment Variables:**
    -   **Name:** `REACT_APP_API_GATEWAY_URL`
    -   **Value:** Paste your **Backend URL** from Step 2 (e.g., `https://dlms-backend.onrender.com`) - **IMPORTANT:** Do NOT include a trailing slash `/`.
6.  **Deploy.**
7.  Once done, check your Vercel URL.

---

## 3b. ALTERNATIVE: Deploying Frontend on Render (Static Site)

**If you prefer to keep everything on Render (or if Vercel fails), use this method:**

1.  Log in to [Render Dashboard](https://dashboard.render.com/).
2.  Click **New +** -> **Static Site**.
3.  Connect your GitHub Repository.
4.  **Settings:**
    -   **Name:** `dlms-frontend`
    -   **Root Directory:** `Monolithic_LMS/frontend`
    -   **Build Command:** `npm install; npm run build`
    -   **Publish Directory:** `build`
5.  **Environment Variables:**
    -   `REACT_APP_API_GATEWAY_URL`: Paste your **Backend URL** (e.g., `https://dlms-backend.onrender.com`)
6.  **Deploy.**

---

## 4. Final Verification

1.  Open your **Vercel Frontend URL**.
2.  **Login:** This verifies the connection to the Backend and MySQL.
3.  **Chat:** This verifies Backend -> Chatbot communication.
4.  **Courses:** This verifies Backend -> MongoDB communication.

**Troubleshooting Tips:**
- **Backend 502/503:** Check Render logs. If it says "Port binding failed", ensure your properties file uses `${PORT:8080}` (We fixed this in `application.properties`).
- **CORS Errors:** Ensure your Backend allows the Vercel domain. You might need to add an env var `CORS_ALLOWED_ORIGINS` to the Backend with your Vercel URL.
