# Ejercicio BCI

Este api, proporciona la funcionalidad de ABM para un cliente y sus lineas telefonicas.

## Maven

### Instalación 

mvn clean install

### Inicio de aplicacion

java -jar .\target\demo-0.0.1-SNAPSHOT.jar

## Gradle

### Instalación

gradle clean build

### Inicio de aplicacion

gradlew.bat bootRun

### Diagrama de DB

![BCI_Diagram](https://github.com/damiansosa512/BCI/assets/19752827/602848a9-7c3c-43c8-a72e-3d1b187a0077)


## Metodo para obtener el Swagger

curl --location 'http://localhost:8009/swagger-ui/index.html' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208'


## Definicion del API

curl --location 'http://localhost:8009/v3/api-docs' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208'


## Metodos para probar el ABM

### Ingreso de un usuario nuevo

curl --location 'http://localhost:8009/api-user/v1/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuIiwiaWF0IjoxNzA5NjQ4MDExLCJhdXRob3JpdGllcyI6IkFETUlOIiwiZXhwIjoxNzA5NjUxNjExfQ.jUxkHOTNPjvXF3FdcRE9yrutZhNn3Y4TgptGLrhdwU0' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208' \
--data-raw '{
    "name": "juan",
    "email": "juanLopez@gmail.com",
    "password": "admin",
    "phones": [
        {
            "number": "1130759383",
            "citycode": "1879",
            "countrycode": "54"
        },
        {
            "number": "1158490018",
            "citycode": "1881",
            "countrycode": "54"
        }
    ],
    "rol":{
        "name":"ADMIN",
        "isActive":true
    }
}'


### Modificacion de una usuario nuevo

curl --location --request PUT 'http://localhost:8009/api-user/v1/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuIiwiaWF0IjoxNzA5NjQ4MDExLCJhdXRob3JpdGllcyI6IkFETUlOIiwiZXhwIjoxNzA5NjUxNjExfQ.jUxkHOTNPjvXF3FdcRE9yrutZhNn3Y4TgptGLrhdwU0' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208' \
--data-raw '{
    "name": "juan",
    "email": "juanLopez@gmail.com",
    "password": "admin",
    "phones": [
        {
            "number": "1130759383",
            "citycode": "1879",
            "countrycode": "54"
        },
        {
            "number": "1158490018",
            "citycode": "1881",
            "countrycode": "54"
        },
        {
            "number": "1188378239",
            "citycode": "100",
            "countrycode": "54"
        }        
    ],
    "rol":{
        "name":"ADMIN",
        "isActive":true
    }
}'


### Obtencion de un usuario previamente insertado

curl --location 'http://localhost:8009/api-user/v1/92b56d48-83c9-42a7-a086-a7a6b684dd74' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208'


### Eliminacion de un uausrio previamente insertado

curl --location --request DELETE 'http://localhost:8009/api-user/v1/3cf3a4db-a06e-4e79-bcca-5542f88e0060' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208'


## Metodos para obtener el token

curl --location --request POST 'http://localhost:8009/api-auth/v1/?user=juan&pass=admin' \
--header 'Cookie: JSESSIONID=1EB4055B5BA27D1FF70261DC98EF5208' \
--data ''
