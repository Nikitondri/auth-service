# AUTH_SERVICE

Welcome to our Spring Boot project! This project consists of three microservices designed to work together seamlessly. Below, you'll find detailed information on each microservice and their functionalities.

## Microservices:

### 1. db-migrations

**Description:** The `db-migrations` microservice is responsible for managing database migrations using Liquibase for MySQL databases.

**Functionality:**
- Handles database schema changes using Liquibase scripts.
- Ensures database schema evolution is managed efficiently and consistently across environments.
- Facilitates versioning and tracking of database changes.

### 2. patient-domain

**Description:** The `patient-domain` microservice serves as a resource OAuth2 server and implements CRUD operations for the `patient` entity. It utilizes JOOQ as the database access layer.

**Functionality:**
- Implements OAuth2 server functionality to secure resources and manage access tokens.
- Provides CRUD operations for managing patient entities.
- Utilizes JOOQ for efficient and type-safe database access.

### 3. gateway-service

**Description:** The `gateway-service` microservice acts as a client OAuth2 server, handling authentication and authorization.

**Functionality:**
- Facilitates user authentication and authorization using OAuth2 protocol.
- Serves as an entry point for clients to access protected resources.
- Manages access tokens and verifies user permissions.

## External Component:

### 4. Keycloak

**Description:** Keycloak is an open-source OAuth2 server used for authentication and authorization.

**Functionality:**
- Provides OAuth2 server capabilities for user authentication and authorization.
- Offers robust security features for managing access to resources.
- Integrates seamlessly with Spring Boot applications for securing APIs and web applications.

## License:

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify it according to your needs.

For more information, please refer to the documentation provided within each microservice's directory.

Happy coding! 🚀
