# demo

### Reference Documentation
Xml SOAP services to consume JSON Poke Api:
	
	- https://pokeapi.co/api/v2/pokemon/{pokemon}/ 

## Wsdl

	- http://localhost:8080/soap/pokeapi.wsdl
	
## Endpoints

Return all json response in XML format by pokemon name.

	- getPokeAPI
	
Return abilities in XML format by pokemon name.	

	- getAbilities

Return base experience data in XML format by pokemon name.	
	
	- getBaseExperience

Return held items data in XML format by pokemon name.	
	
	- getHeldItems
	
Return id in XML format by pokemon name.		

	- getId

Return name in XML format by pokemon name.	
	
	- getName

Return location area encounters in XML format by pokemon name.	
	
	- getLocationAreaEncounters

## Instalation

### Requirements:
	
	jdk 8
	
### compilation

Compile model Xml class and generate WSDL

	- mvn compile
	
	
### deploy

	- mvn spring-boot: run	

### properties profile

	- dev
	
## Postman

	- postman folder contains some examples and collection
	
## H2 database

	- url: http://localhost:8080/h2-console
	- user: poke password:pass
	

