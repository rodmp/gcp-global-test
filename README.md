# API test

### Reference Documentation

	- https://gnews.io/api/v4/top-headlines?token=680490ee7d77d05f2d84488515a298d3&lang=es
	- https://gnews.io/api/v4/search?q=oregon&token=680490ee7d77d05f2d84488515a298d3&lang=en&from=2022-07-24T01:59:57Z&to=2022-07-24T22:59:57Z
	
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
	

