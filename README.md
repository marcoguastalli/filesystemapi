# filesystemapi
File System Spring Boot API

# compile
mvn clean install
mvn clean integration-test

#run
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html

# oracle
http://192.168.56.3:8080/apex/

### play
http://localhost:8090/
%2F

##### GET printPathToFile
http://localhost:8090/printPathToFile/tmp/Users%2Fmarco27%2Ftemp%2Ftmp.txt
http://localhost:8090/printPathToFile/Users%2Fmarco27%2Fopt/Users%2Fmarco27%2Ftemp%2Fopt.txt
http://localhost:8090/printPathToFile/Volumes%2FMAC200%2Fmac200/Users%2Fmarco27%2Ftemp%2Fmac200.txt
http://localhost:8090/printPathToFile/Users%2Fmarcoguastalli%2Fopt%2Fdocker/Users%2Fmarcoguastalli%2Ftemp%2Fdocker.txt
{"pathToPrint":"/Users/marcoguastalli/opt/docker","fileToPrint":"/Users/marcoguastalli/temp/docker.txt"}

##### GET findPathStructureById
http://localhost:8090/findPathStructureById/Users%2Fmarco27%2Ftemp
http://localhost:8090/findPathStructureById/Users%2Fmarcoguastalli%2Ftemp

##### GET findPathStructureByPath
http://localhost:8090/findPathStructureByPath/Users%2Fmarco27%2Ftemp
http://localhost:8090/findPathStructureByPath/Users%2Fmarcoguastalli%2Ftemp

##### GET(!) savePathStructure
http://localhost:8090/savePathStructure/Users%2Fmarco27%2Ftemp
http://localhost:8090/savePathStructure/Users%2Fmarcoguastalli%2Ftemp

##### DELETE deletePathStructure
http://localhost:8090/deletePathStructure/Users%2Fmarco27%2Ftemp
http://localhost:8090/deletePathStructure/Users%2Fmarcoguastalli%2Ftemp
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deletePathStructure/Users%2Fmarcoguastalli%2Ftemp"
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deletePathStructure/Users%2Fmarco27%2Ftemp"
