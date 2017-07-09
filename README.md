# moviedb

# Supported Routes

Routes related to Person objects -

GET /api/actor/{name} returns an array of Person objects who are actors and whose name matches
	 or partially matches URL parameter, case insensitive
   
GET /api/person/{name} - returns person object matching name URL parameter - case insensitive

GET /api/director/{name} - returns an array of Person objects who are directors and whose name
	 matches or partially matches URL parameter, case insensitive
   
GET /api/author/{name} -returns an array of Person objects who are authors and whose name matches
	 or partially matches URL parameter, case insensitive
 
GET /api/directors -returns an array of Person objects who are directors

GET /api/actors - returns an array of Person objects who are actors

GET /api/authors -returns an array of Person objects who are authors

POST /api/person - adds a new person - requires name must in request body and name must not already exist - case insensitive

DELETE /api/person/{id} - deletes an existing person whose ID matches parameter in URL

PUT /api/person - updates/mergse for an existing person whose ID matches "id" in json body of request


Routes related to Movie objects -

GET  /api/movie/title/{title} -returns movie object(s) with their Actors, Directors, Authors
   movies are selected where title matches or partially matches title in URL parameter, case insensitive
   
GET /api/movies - returns an array of all movie objects with their Actors, Directors, Authors

GET /api/movie/actor/{name} -returns an array of movie objects where actors name at least partially matches name in URL parameter
   case insensitive

GET  /api/movie/director/{name} -  returns an array of movie objects where directors name at least partially matches name parameter
   case insensitive

GET /api/movie/author/{name} - returns an array of movie objects where authors name at least partially matches name parameter 
   case insensitive

GET /api/movie/genre/{genre} - returns an array of movie objects whose genre matches or partially
	 matches URL parameter, case insensitive
   
GET /api/movie/year/{year}  - returns an array of movie objects whose year matches or partially matches
	 URL parameter
   
POST /api/movie - creates a new movie - at least title must be provided and no other movie with this tile must not already exist - case insensitive

PUT /api/movie - updates/merges an existing movie where id matches the id contained in the json body of the request. 
   Directors, Actors and Authors may also be added/updated with this method.

DELETE  /api/movie/{id} - deletes an existing movie whose ID matches the URL parameter


	
