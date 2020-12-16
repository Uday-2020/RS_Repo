# RecipesService


### Project Description
```
This is a micro service which provides service methods like getAllRecipes, createRecipet, updateRecipe , deleteRecipe required for Recipe applicaiton.
```
### Installation

* Ensure that Java 11 and Maven 4.0 are installed.
* Repository can be cloned using the command :
    `git clone https://github.com/Uday-2020/RS_Repo.git`

### Technologies

* Technologies that are used are :
  `Java 11, SpringBoot, Sweggar, Mockito, Git, Embedded H2 database`


## Usage
### Running RecipesService
* Navigate to the directory into which the repository has been cloned and run this command  
`mvn clean install`
* Navigate to the target folder and run this command  
`java -jar recipesservice-0.0.1-SNAPSHOT.jar`
* Once the service is up and running. RecipesService API can be accessible on port 8080,  e.g.`http://localhost:8080/api/v1/recipe/`. Credentials are required to access RecipesService API and same will be provided over email. 
* API Documentation can be accessible on same port 8080 using this URL.   `http://localhost:8080/swagger-ui.html`
* H2 database admin console url is `http://localhost:8080/h2-console/`. Credentials can be modified in `src/main/resources/application.properties` file.

### Running Postman Requests
* Make sure postman is installed and running.
* Navigate to postman folder
* Import the `Recipes.postman_collection` into postman. You will be able to see all requests including UnAuthorized scenarios. 
* Now run the requests to test respective operation.

### References

* RecipesService API URLs -  
	 * GET : `http://localhost:8080/api/v1/recipe/`  
	 * 	GET : `http://localhost:8080/api/v1/recipe/{id}`  
	 * 	POST : `http://localhost:8080/api/v1/recipe/`  
	 * 	PUT : `http://localhost:8080/api/v1/recipe/`  
	 * 	DELETE : `http://localhost:8080/api/v1/recipe/{id}`  
	   
	   
	 
* API Documentation :` http://localhost:8080/swagger-ui.html`

