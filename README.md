# filesystemapi
File System Spring Boot API - H2 Memory DDBB

# compile
mvn clean install
mvn clean integration-test

#run
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html

### play
http://localhost:8090/
%2F

##### Crud
curl -i -X POST "http://localhost:8090/create/Users%2Fmarcoguastalli%2Ftemp"

##### cRud
curl -i -X GET "http://localhost:8090/read/Users%2Fmarcoguastalli%2Ftemp"
curl -i -X GET "http://localhost:8090/find/Users%2Fmarcoguastalli%2Ftemp"

##### crUd
curl -i -X PUT "http://localhost:8090/update/Users%2Fmarcoguastalli%2Ftemp" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"directory\": true, \"ext\": \"string\", \"name\": \"string\", \"path\": \"string\", \"timestamp\": \"string\"}"

##### cruD
curl -i -X DELETE "http://localhost:8090/delete/Users%2Fmarcoguastalli%2Ftemp"

##### GET printPathToFile
http://localhost:8090/printPathToFile/Users%2Fmarcoguastalli%2Fopt/Users%2Fmarcoguastalli%2Ftemp%2Fopt.txt
http://localhost:8090/printPathToFile/Volumes%2FMAC100%2Fmac100%2FSoftware/Users%2Fmarcoguastalli%2Ftemp%2Fmac100.txt
