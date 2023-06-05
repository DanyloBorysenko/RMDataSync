<h1 align="center">Rick-and-Morty-app</h1>

## :pencil: Project description :pencil:
This is a Spring Boot application that synchronizes movie character data from an external API 
and stores it in a PostgreSQL database. It provides REST API endpoints to retrieve random movie characters, 
search characters by name, and fetch characters in a paginated manner.
## :fire: Features :fire:
+ **Periodically synchronizes movie character data from the Rick and Morty API and saves it in the database.**
+ **Retrieves a random movie character from the database.**
+ **Searches movie characters by name and returns a list of matching characters.**
+ **Retrieves movie characters in a paginated manner based on the provided page, count, and sort parameters.**
## :computer: Technologies used :computer:
+ Spring Boot
+ Spring Web
+ Spring Data JPA
+ PostgreSQL
+ Log4j2
+ HttpClient
+ Docker
+ Junit5
+ Mockito
+ Liquibase
+ Lombok
+ Swagger
+ Testcontainers
+ Rest Assured
## Instalation ##
1. Make sure Docker is installed on your machine. 
You can download and install Docker Desktop from the official Docker website: https://www.docker.com/get-started.
2. Download the Docker Compose file (docker-compose.yml) and the .env file from 
https://drive.google.com/drive/folders/1JOxC48pFmBgxdrCHJwgMqscreON-Mze7?usp=sharing
3. Open your Command Prompt or terminal. Navigate to the directory where you downloaded the Docker Compose file 
and the .env file.
4. Run the following command to start the containers:
```
docker-compose up
```
5. This command will read the docker-compose.yml file and start the necessary services.
Docker Compose will automatically download the required images (if not already present locally) and start the containers.
Once the containers are up and running, you can access the app by opening a web browser and navigating to http://localhost:6868. 
This is the address where the app is exposed.If you encounter any conflicts with the port 6868, make sure you don't have any other 
applications running on that port. You can modify the port mapping in the .env file if needed.
## API Endpoint ##
### Get all movie characters ###
Parameters:
+ page (optional, default: 0) - The page number for pagination.
+ count (optional, default: 10) - The number of records per page.
+ sortBy (optional, default: "name") - The field to sort the results by.

Example Request:
GET *http://localhost:8080/movie_characters?page=0&count=10&sortBy=name*
Example Response:
```
[
  {
    "id": 1,
    "name": "Rick Sanchez",
    "species": "Human",
    "status": "Alive"
    // Additional character attributes...
  },
  {
    "id": 2,
    "name": "Morty Smith",
    "species": "Human",
    "status": "Alive"
  }
  // More characters...
]
```
### Get a random movie character ###
Example Request:
GET *http://localhost:8080/movie_characters/randomCharacter*
Example Response:
```
{
  "id": 3,
  "name": "Summer Smith",
  "species": "Human",
  "status": "Alive"
}
```
### Get movie characters by name part ###
Parameters:
+ namePart (required) - The partial name to search for.
Example Request:
GET *http://localhost:8080/movie_characters/by-name?namePart=rick
Example Response:
```
[
  {
    "id": 1,
    "name": "Rick Sanchez",
    "species": "Human",
    "status": "Alive"
  }
]
```
## :eyes:How it looks like
https://rick-and-morty.azurewebsites.net/

The site may take a long time to load. It's because of azure cold start.
After 30 seconds, reload the page. You should see a cartoon image. If you see this, the site is working 
and you can send your requests to the appropriate endpoints presented above.
## :muscle: ## 
That's it! You should now be able to use the danylojavadev/rick-and-morty app through the provided Docker containers.
If you need stop containers Run the following command: 
```
docker compose down
```
If you need any further assistance, feel free to reach out.
