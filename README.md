# filesystemapi
File System Spring Boot API

# compile
mvn clean install
mvn clean integration-test

#run
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html

# play
http://localhost:8090/files
http://localhost:8090/files/fichero

# GET
http://localhost:8090/printPathToFile/tmp/Users%2Fmarcoguastalli%2Ftemp%2Ftemp.txt

#bash
curl -X GET --header "Accept: */*" "http://localhost:8090/printPathToFile/tmp/Users%2Fmarcoguastalli%2Ftemp%2Ftemp.txt"
