Project Name: Student Course Management System
TeckStack: Spring boot for development and mySQL for database
I also used four dependencies:
-Web Spring to build REST API.
-JPA to simplify database operation.
-mySQL driver: To translate java request to mySQL protocol
validation. to handle validity of incoming data
In Database configuration first we must tell to spring boot where the database exist
spring.datasource.url=jdbc:mysql://localhost:3306/student_course_db
Next we must connect to the database so we must define usename and password
spring.datasource.username=username
spring.datasource.password=password
folder structure explanation:
controller: handle requests and responses.
Repository:communicate directly with the database and provide different methods.
Entity: Represents a database table in Java as a class.
dto:represent data that is sent or received.
exeption: it handle error
config: configuration class of the Application
How to RUn the Project:
mvn clean install :
mvn spring-boot:run