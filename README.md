# Notes Application
### Steps to run Note Application
- Configure JDK 17
- Install MySQL database and create notesdb database
- Insert sample data in notesdb Database (data.sql)
- Run spring boot application
- Basic HTTP Authentication: User can login using User available in users table
  
### Simple Web User Interface (Thymeleaf)
- http://localhost:8080/login
- Login with email and password from user table
- Listout all notes of current user
- Current User can CRUD Operation on Notes in browser

### Dockerization of Note Application
- Configure Docker
- Open the project directory in a terminal and execute the command:
     docker-compose up --build
- Two Docker containers will start (MySQL and the Spring Boot application)
- Connect to the MySQL container and insert sample data using data.sql
- http://localhost:8080/login
- Log in using the email and password from the user table
- The logged-in user can perform CRUD operations on notes through the browser
  
### RESTful API to CRUD operations on notes
- User can perform CRUD operations on notes via POSTMAN
- Note access are restricted to the owner only
- User and Note has OneToMany Associations
- Sample Data available in data.sql file
  
### cURL commands CRUD operations on notes.
#### Get All Notes
curl --location 'http://localhost:8080/api/notes' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

#### Get Note by Id
curl --location 'http://localhost:8080/api/notes/2' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

#### Update Note by Id
curl --location --request PUT 'http://localhost:8080/api/notes/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3' \
--data '{
    "title": "Spring HV",
    "content": "A quick reminder to study AWS."
}'

#### Delete Note by Id
curl --location --request DELETE 'http://localhost:8080/api/notes/1' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

#### Create Note
curl --location 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3' \
--data '{
    "title": "Spring HV",
    "content": "A quick reminder to study AWS."
}'
