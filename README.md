# Get near by places/suggestions from the given Location API

suggestions related API's exposed

## Getting Started
This is a maven project, so you can start running the project using
```
mvn clean install spring-boot:run
```

### swagger ui
```
http://localhost:8080/swagger-ui.html
```



### Get All the suggestions near by london and limit 2( both near and limit as QueryParameters )
```
curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '20190313' 'http://localhost:8080/venue?near=London&limit=2'
```

### QueryParameter(near) is mandatory, so if you dont provide, the API is going to return the message as part of Meta object with the message
```
curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '20190313' 'http://localhost:8080/venue'
```

### If the service could not find any suggestions for the provided location,  the API is going to return the message as part of Meta object with the message
```
curl -X GET --header 'Content-Type: application/json' --header 'Accept: application/json' -d '20190313' 'http://localhost:8080/venue?near=L&limit=2'
```

### The API is handling all the possible exceptions