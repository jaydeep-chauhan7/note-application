
# Notes Application
### RESTful API to CRUD operations on notes
- User can perform CRUD operations on Notes
- Note access are restricted to the owner only
- User and Note has OneToMany Associations
- Sample Data available in data.sql file

### Steps to run Note Application
- Install MySQL database and create notesdb database
- Insert sample data in notesdb Database
- Run spring boot application
- Basic HTTP Authentication: User can login using User available in users table 
- Perform CRUD operations on notes via POSTMAN


### Find the cURL commands for creating user and perform CRUD operations on notes.
### Get All Notes
curl --location 'http://localhost:8080/api/notes' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

### Get Note by Id
curl --location 'http://localhost:8080/api/notes/2' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

### Update Note by Id
curl --location --request PUT 'http://localhost:8080/api/notes/2' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3' \
--data '{
    "title": "Spring HV",
    "content": "A quick reminder to study AWS."
}'

### Delete Note by Id
curl --location --request DELETE 'http://localhost:8080/api/notes/1' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3'

### Create Note
curl --location 'http://localhost:8080/api/notes' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjFAZXhhbXBsZS5jb206cGFzczEyM0Eh' \
--header 'Cookie: JSESSIONID=21FB637DF1AF83ECAE29E0DD024AA8C3' \
--data '{
    "title": "Spring HV",
    "content": "A quick reminder to study AWS."
}'
