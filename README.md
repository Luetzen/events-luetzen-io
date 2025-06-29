# Event Service

A Spring Boot 3.4.4 application for managing events related to Steam applications.

## Technologies

- Java 21
- Spring Boot 3.4.4
- PostgreSQL 17.5
- Hibernate 7.x
- Liquibase
- MapStruct
- Jakarta Bean Validation
- Springdoc OpenAPI 2
- Docker & Docker Compose

## Getting Started

### Prerequisites

- Java 21
- Docker and Docker Compose (optional, for PostgreSQL mode)

### Running the Application

#### Default Mode (H2 Database)

1. Clone the repository
2. Run the application:

```bash
./mvnw spring-boot:run
```

The application will use an in-memory H2 database and will be available at http://localhost:8080.
The H2 console will be available at http://localhost:8080/h2-console.

#### Development Mode (PostgreSQL Database)

1. Clone the repository
2. Run the application with the dev profile:

```bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
```

Or use Docker Compose:

```bash
docker-compose up
```

The application will be available at http://localhost:8080.

The Swagger UI documentation will be available at http://localhost:8080/swagger-ui.html.

## API Endpoints

### Apps

- `POST /api/v1/apps` - Create a new App
- `GET /api/v1/apps/{id}` - Get an App by ID
- `GET /api/v1/apps/steam/{steamAppId}` - Get an App by Steam App ID

### Events

- `POST /api/v1/events` - Create a new Event
- `GET /api/v1/events/{id}` - Get an Event by ID
- `GET /api/v1/events?appId={steamAppId}` - Get Events by App's Steam App ID

### Teams

- `POST /api/v1/teams` - Create a new Team
- `GET /api/v1/teams/{id}` - Get a Team by ID
- `GET /api/v1/teams/search?name={name}` - Find Teams by name
- `GET /api/v1/teams/members?minMembers={minMembers}` - Find Teams by minimum number of members

## Example Requests

### Create an App

```bash
curl -X POST http://localhost:8080/api/v1/apps \
  -H "Content-Type: application/json" \
  -d '{
    "steamAppId": 570,
    "name": "Dota 2",
    "description": "A popular MOBA game developed by Valve"
  }'
```

### Create an Event

```bash
curl -X POST http://localhost:8080/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{
    "appSteamId": 570,
    "name": "The International 2023",
    "description": "Annual Dota 2 championship tournament",
    "eventType": "TOURNAMENT",
    "startTime": "2023-10-12T10:00:00",
    "endTime": "2023-10-29T22:00:00",
    "location": "Seattle, WA",
    "metadata": {
      "prizePool": "$40,000,000",
      "teams": 18,
      "venue": "Climate Pledge Arena"
    }
  }'
```

### Create a Team

```bash
curl -X POST http://localhost:8080/api/v1/teams \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Team Secret",
    "members": 5
  }'
```

### Get Events by App's Steam App ID

```bash
curl -X GET http://localhost:8080/api/v1/events?appId=570
```

## Project Structure

The project follows a clean architecture with the following layers:

- **Model**: Entity classes
- **Repository**: Data access layer
- **Service**: Business logic layer
- **Controller**: REST API layer
- **DTO**: Data Transfer Objects
- **Mapper**: MapStruct mappers for entity-DTO conversion
- **Exception**: Custom exceptions and global exception handler
- **Config**: Configuration classes

## Database Schema

The database schema is managed by Liquibase and includes the following tables:

- **apps**: Stores information about Steam applications
- **events**: Stores information about events related to apps
- **teams**: Stores information about teams
