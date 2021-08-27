# cooperativism-vote-service
Technical challenge: Rest API for a vote service

## Requirements:
- docker >= 17.12.0+
- docker-compose
- Apache Maven 3.6.3
- JDK 1.8
- Internet connection to API integration

## Quick Start database
- Go inside of directory and run this command `docker-compose up -d`, if you got an error, run it in admin mode (sudo)

## How to start the Cooperativism Vote Service application

1. Run `mvn clean install` to build the application
2. Start application with `java -jar target/cooperativism-vote-service-1.0.jar server etc/config.yml`
3. To check that your application is running enter url `http://localhost:9090`

## Services
- Host: `localhost:9090`

|Service path | Description|
|--------------|------------|
| /cooperativism-vote-service/vote/{sessionId}/{cpf}/{choice} | Register a vote in a session |
| /cooperativism-vote-service/voting-agenda/get-agenda/{id} | Query a specific agenda |
| /cooperativism-vote-service/voting-agenda/{description} | Create an agenda with description |
| /cooperativism-vote-service/voting-session/open/{agendaId} | Open a voting session on a specific agenda with default time (60s)|
| /cooperativism-vote-service/voting-session/open/{agendaId}/{time} | Open a voting session on a specific agenda with specific time in seconds |

**Observations:** 
- the API `https://user-info.herokuapp.com/users/{cpf}` does not work fine, so some time it's necessary to repeat the vote request.
- At the specification, it's unclear if I can open only one session per agenda, so I don't treat this. However, 
opening many sessions with the same agenda does not make sense since we can vote only once per agenda. Actually, for this
reason I do restrict the vote for session and not agenda.

## Architecture

The application follows a 2-layer implementation, where the first is from the API, where I developed the voting service, 
and I treated requested in the challenge were carried out. The second layer refers to the data layer composed of a docker running a Postgres server.

Abstractly, the figure below exemplifies the connection and interaction between the layers:

![alt text](https://raw.githubusercontent.com/Leonild/cooperativism-vote-service/master/images/rest-api.png)

The database relationship is simple, with three tables to store the (1) agenda, (2) session, and (3) vote. In this 
relationship, the session table has the agenda id such foreign key, and the voting table has the session-id such foreign key. 
In the following image, I drew an entity-relationship diagram.

![alt text](https://raw.githubusercontent.com/Leonild/cooperativism-vote-service/master/images/ER-diagram.png)
 
 
## Technologies
- <a href="https://www.dropwizard.io/en/latest/">Dropwizard</a> - Java framework for developing RESTful API.
- <a href="https://jdbi.org/">JDBI3</a> - a convenience library built on top of JDBC.
- <a href="https://maven.apache.org/">Maven</a> - Dependency Management
- <a href="https://www.postgresql.org/">PostgreSQL</a> - object-relational database system.
- <a href="https://docs.docker.com/compose/">Docker Compose</a> - a tool for defining and running multi-container Docker applications.
