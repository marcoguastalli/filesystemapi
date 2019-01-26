# filesystemapi
File System Spring Boot API

# compile
mvn clean install
mvn clean integration-test

#run
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html

### play
http://localhost:8090/files
http://localhost:8090/files/fichero

##### GET printPathToFile
http://localhost:8090/printPathToFile/tmp/Users%2Fmarco27%2Ftemp%2Ftmp.txt
http://localhost:8090/printPathToFile/Users%2Fmarco27%2Fopt/Users%2Fmarco27%2Ftemp%2Fopt.txt
http://localhost:8090/printPathToFile/Volumes%2FMAC200%2Fmac200/Users%2Fmarco27%2Ftemp%2Fmac200.txt

##### GET getPathStructure
http://localhost:8090/getPathStructure/Users%2Fmarco27%2Ftmp
http://localhost:8090/getPathStructure/Users%2Fmarco27%2Ftemp
http://localhost:8090/getPathStructure/Users%2Fmarcoguastalli%2Ftemp

##### GET storePathStructure
http://localhost:8090/storePathStructure/Users%2Fmarco27%2Ftemp

##### GET findPathStructure
http://localhost:8090/findPathStructure/Users%2Fmarco27%2Ftemp
