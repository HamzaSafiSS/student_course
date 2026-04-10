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
service:Handles business logic like handling different conditions.
How to RUn the Project:
mvn clean install :
mvn spring-boot:run
ER(Entity Relation) Diagram
+-------------------+
|      Student      |
+-------------------+
| id (PK)           |
| firstName         |
| lastName          |
| email (unique)    |
| age               |
+-------------------+
JPA Annotations:Are Annotations used to create a relation between java objects and database
some JPA used in this projects
@Id -> used to tell us that the column is primary key
@Entity -> Used to define a table
@Column -> Used to define column of the table
DAY-3
1. Why Service Layer Is Important
The service layer contains the business logic of the application.
Makes the application easier to maintain and test
2. Difference Between Repository and Service
  Repository: Handles database operations
  Service: Contains business logic and validations
3. Business Rule Explanation
  Email must be unique for each student
   If a student ID does not exist, a custom exception is thrown
DAY 4
1. API endpoint Table
   | Method | Endpoint           | Description        |
   | ------ | ------------------ | ------------------ |
   | POST   | /api/students      | Create new student |
   | GET    | /api/students      | Get all students   |
   | GET    | /api/students/{id} | Get student by ID  |
   | PUT    | /api/students/{id} | Update student     |
   | DELETE | /api/students/{id} | Delete student     |

2. HTTP Method Explanation
   | Method | Purpose                  |
   | ------ | ------------------------ |
   | GET    | Retrieve data            |
   | POST   | Create new resource      |
   | PUT    | Update existing resource |
   | DELETE | Remove resource          |

3. Status Code Explanation 
200 - Request Successfull
201 - Resource Created
204 - Resource Deleted
404 - Resource not Found
In General We have 5 but I will add here only 3
1. 2XX = Successfully handled.
2. 4XX = Client Problem
3. 5XX = Server error

DAY-5
Importance of DTO
DTO Control Data Exchange between Client and server
Allows Validation

| Entity                     | DTO                             |
| -------------------------- | ------------------------------- |
| Represents database table  | Represents API request/response |
| Used by repository         | Used by controller              |
| Contains persistence logic | Contains validation rules       |

Validation Annotation comes from Jakarta Bean Validation and used to automatically validate incoming API request.
It ensures invalid data is rejected nefore it reaches database.

Global exception handling captures validation errors and returns a consistent structured response to the client.

DAY-6
1. OneToMany relationship is a relation when One entity can relate with many others
but each of those relates back to only one.
2. A foreign key is a column in one table that refers to the primary key in another table.
3. | Type    | Meaning                                       |
   | ------- | --------------------------------------------- |
   | ALL     | Applies all operations (save, delete, update) |
   | PERSIST | Save child when parent saved                  |
   | REMOVE  | Delete child when parent deleted              |

DAY-7
API DOCUMENTATION
STUDENT API
| Method | Endpoint           | Description                           |
| ------ | ------------------ | ------------------------------------- |
| POST   | /api/students      | Create student                        |
| GET    | /api/students      | Get students (pagination + filtering) |
| GET    | /api/students/{id} | Get student by ID                     |
| PUT    | /api/students/{id} | Update student                        |
| DELETE | /api/students/{id} | Delete student         

COURSE API
| Method | Endpoint                   | Description              |
| ------ | -------------------------- | ------------------------ |
| POST   | /api/students/{id}/courses | Add course to student    |
| GET    | /api/students/{id}/courses | Get courses of a student |

Pagination & Filtering
| Endpoint                               | Description            |
| -------------------------------------- | ---------------------- |
| GET /api/students?page=0&size=5        | Pagination             |
| GET /api/students?age=24               | Filtering              |
| GET /api/students?age=24&page=0&size=5 | Pagination + Filtering |
Sample JSON Request
Create Student
{
"firstName": "Abel",
"lastName": "Adisu",
"email": "abel@example.com",
"age": 24
}

Update Student

{
"firstName": "Abel",
"lastName": "Updated",
"email": "abel@example.com",
"age": 25
}

Add Course to Student
{
"name": "Spring Boot",
"description": "Backend development course"
}

Sample Error Response
I try to add
{
"firstName": "",
"lastName": "Safi",
"age": 2
}
Error I face
{
"errors": [
"First name must not be blank"
],
"timestamp": "2026-03-21T12:15:06.0106492",
"status": 400
}

Database Schema Explanation
Student Table.
| Column     | Type    | Description        |
| ---------- | ------- | ------------------ |
| id         | Long    | Primary Key        |
| first_name | String  | Student first name |
| last_name  | String  | Student last name  |
| email      | String  | Unique email       |
| age        | Integer | Student age        |

Courses Table

| Column      | Type   | Description                         |
| ----------- | ------ | ----------------------------------- |
| id          | Long   | Primary Key                         |
| name        | String | Course name                         |
| description | String | Course description                  |
| student_id  | Long   | Foreign key referencing students.id |

Relationship
One Student many courses

How TO Run Locally
Click the Run button

Then open http://localhost:8090

Future Improvements
1. Add authentication
2. Make role based
3. Improve entity relationship
4. Add another entity

PHASE-2

Day-1

1.Auditing lifecycle in JPA
JPA auditing automatically tracks entity creation and update information such as timestamps and users.
2.Difference: @Entity vs @MappedSuperclass
@Entity creates a database table
@MappedSuperclass shares fields across entities without creating a table
3.Enum mapping strategies
Enums can be stored as:

STRING → readable (recommended)
ORDINAL → numeric (not recommended)
4.Embedded vs separate table
Embedded:Embedded objects allow grouping fields inside an entity without creating a separate table.
Separate Table: Creating a new table.

DAY-2

1. JPQL vs Native SQL
JPQL uses entity names and fields
Native SQL uses actual database tables

2. When to Use Projections
We Use projections when:

We only need specific fields
We want better performance

3. Query Performance Considerations
   We should Use pagination to limit data
   We should Use projections to reduce response size
   We should Avoid unnecessary full entity loading
DAY-3
1.Transaction Lifecycle

Transaction Start
↓
Execute DB Operations
↓
Success → Commit 
Failure → Rollback
2.LAZY vs EAGER
| Type  | Description                    |
| ----- | ------------------------------ |
| LAZY  | Loads data only when accessed  |
| EAGER | Loads related data immediately |

3. N+1 Problem
Occurs when 1 query for parent + N queries for child

Example :
Get all students → 1 query
Get courses for each student → N queries
Total = N+1 queries
Solution
@Query("SELECT s FROM Student s JOIN FETCH s.courses")

Loads everything in one query

4. CASCADE TYPES
   | Type    | Description                         |
   | ------- | ----------------------------------- |
   | PERSIST | Save child when parent is saved     |
   | REMOVE  | Delete child when parent is deleted |

DAY-4
1. What is Specification Pattern
Specification is a way to build dynamic database queries using reusable conditions.
2. Why Dynamic Queries Matter
   Avoid writing many methods like: findByAgeAndStatusAndName
Instead:
Build queries dynamically based on user input
3. Clean API Response Design
Standard response:
{
"data": [],
"message": "Success",
"status": 200
}
4. Logging and Debugging Queries
Enable logging to:
See generated SQL queries
Debug performance issues
Detect N+1 problems