# Proyecto Spring Boot con Maven

Este proyecto es una aplicación Spring Boot configurada con Maven. A continuación, se detallan los pasos para levantar el proyecto y ejecutar los tests unitarios.

## Requisitos Previos


- JDK 17
- Maven
- Git

## Instalación y Ejecución

Seguir los soguientes pasos para clonar y ejecutar el proyecto:

### 1️⃣ Clonar el repositorio
```sh
 git clone https://github.com/afernandez4/smartjob-repo.git
 cd smartjob-repo
```

### 2️⃣ Compilar el proyecto
```sh
mvn clean install
```

### 3️⃣ Ejecutar la aplicación
```sh
mvn spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`.

## Ejecutar Tests Unitarios
Para ejecutar los tests unitarios, usar el siguiente comando:

```sh
mvn test
```

---

## API Endpoints

Los endpoints están distribuidos en dos grupos: 

**1. Autenticación**

**2. Usuarios**

A continuación, se describen los endpoints expuestos en esta API.

## Autenticación

Para este caso, existen dos usuarios precargados:

**Usuario 1**
```json
{
    "username": "abc1@abc.com",
    "password": "Password1"
}
```

**Usuario 2**
```json
{
    "username": "abc2@abc.com",
    "password": "Password2"
}
```

### 🔹 **1️⃣ Login**
**Endpoint:** `POST /auth/login`

#### **Request (JSON)**
```json
{
    "username": "abc1@abc.com",
    "password": "Password1"
}
```

#### **Response (JSON)**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmMxQGFiYy5jb20iLCJpYXQiOjE3Mzg1MzQ3NzQsImV4cCI6MTczODUzODM3NH0.BsuKpKFJkNlxm10hGTKnQ6xaSTOjQhfdGD-rt9mc_2g"
}
```

El token recuperado debe ser enviado en los servicios de **Usuarios** en el header Authorization, de la siguiente manera:

Authorization = Bearer {el token anterior}

## Usuarios

### 🔹 **1️⃣ Crear un usuario**
**Endpoint:** `POST /users`

#### **Request (JSON)**
```json
{
    "name": "Pepito",
    "email": "ab425@abc.com",
    "password": "passwordA1",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

#### **Response (JSON)**
```json
{
    "id": "41aac57d-eb33-40e6-af3c-733c5edde7ab",
    "email": "ab425@abc.com",
    "createdDate": "2025-02-02T22:20:34.655+00:00",
    "lastSessionDate": "2025-02-02T22:20:34.655+00:00",
    "modifiedDate": null,
    "token": null,
    "active": true
}
```

### 🔹 **2️⃣ Obtener lista de usuarios**
**Endpoint:** `GET /users`

#### **Response (JSON)**
```json
[
    {
        "id": "cffefd71-46e2-4178-be04-5526f34d2456",
        "email": "abc1@abc.com",
        "createdDate": "2025-02-02T22:18:22.505+00:00",
        "lastSessionDate": "2025-02-02T22:20:39.883+00:00",
        "modifiedDate": null,
        "token": "token-init",
        "active": false
    },
    {
        "id": "3696c30c-bf01-4068-9130-82b6d1c37287",
        "email": "abc2@abc.com",
        "createdDate": "2025-02-02T22:18:22.594+00:00",
        "lastSessionDate": "2025-02-02T22:18:22.594+00:00",
        "modifiedDate": null,
        "token": "token-init",
        "active": false
    },
    {
        "id": "41aac57d-eb33-40e6-af3c-733c5edde7ab",
        "email": "ab425@abc.com",
        "createdDate": "2025-02-02T22:20:34.655+00:00",
        "lastSessionDate": "2025-02-02T22:20:34.655+00:00",
        "modifiedDate": null,
        "token": null,
        "active": true
    }
]
```



