
# Domain and Contact Information Lookup App

This is a full-stack web application that allows users to search for domain registration and contact information using the WhoisXML API.

##  Tech Stack

- **Frontend**: React + Material UI
- **Backend**: Spring Boot (Java)
- **API**: WhoisXML API

---

##  Features

- Search domain info (e.g., registrar, creation date, etc.)
- Search contact info (e.g., registrant name, contact email, etc.)
- Responsive design with Material UI
- Loading spinner during API calls

---

## 🛠️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/whois-lookup-app.git
cd whois-lookup-app
```

### 2. Backend Setup (Spring Boot)

```bash
cd backend
```

#### Set API Key

Before running, set your WhoisXML API key as an environment variable:
```Open the file:
- backend/src/main/java/com/example/service/WhoisLookupService.java
```

```Locate the following line near the top:
- private static final String API_KEY = "at_p9oMvlyBm5T9xfClFR2M9e4uQq48b";
```

```Replace the value with your own API key from WhoisXML API:

- private static final String API_KEY = "YOUR_NEW_API_KEY_HERE";
```

#### Build and Run Backend

```bash
./mvnw spring-boot:run
```
(Or `mvn spring-boot:run` if Maven is installed globally)

The backend should start on: `http://localhost:8080`

### 3. Frontend Setup (React)

```bash
cd frontend
npm install
npm start
```

The frontend runs at: `http://localhost:3000`

Make sure the backend is running for the API calls to work.

---

## ⚙️ Deployment

### Deploy Backend

You can deploy the Spring Boot app to services like:
- Heroku
- Render
- Railway
- EC2

Make sure to set the `WHOIS_API_KEY` as an environment variable in your deployment platform.

### Deploy Frontend

You can deploy your React app to:
- Vercel
- Netlify
- GitHub Pages (with build setup)

---

## 🔐 API Key Security

Make sure **not** to hardcode the API key in your codebase. Always use environment variables to manage secrets.

---

## 📄 License

MIT License
