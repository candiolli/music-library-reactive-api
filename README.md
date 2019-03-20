# music-library-reactive-api

This project is a Spring Boot microservice and it controls a Music Library. Was be created for pratice the knowledge in reative programming.
This project run with JDK 11.
 
Reative modules os Spring Boot:
 
 ```python
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
 </dependency>
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-webflux</artifactId>
 </dependency>
 ```

 
# Installation
 
```bash
mvn clean install
``` 
 
# Install local Mongodb

Pull a mongo docker image
```bash
 docker pull mongo
```

Run mongo expose the original port
```bash
docker run --name demo-mongo -p 27017:27017 -d mongo
```

# Run
 
```bash
mvn sprint-boot:run 
```
or
```bash
java -jar target\music-library-reactive-api-0.0.1-SNAPSHOT.jar
```  

# Test 
```bash
curl http://localhost:8080/music/ | json_pp
```