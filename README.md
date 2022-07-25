# API test

### Reference Documentation

	- https://gnews.io/api/v4/top-headlines?token=680490ee7d77d05f2d84488515a298d3&lang=es
	- https://gnews.io/api/v4/search?q=oregon&token=680490ee7d77d05f2d84488515a298d3&lang=en&from=2022-07-24T01:59:57Z&to=2022-07-24T22:59:57Z
	
## Endpoints

Return top ten news filter by, content, title, description and from, to dates

	- /top/ten/articles
	
Return most relevant news by country code.	

	- /most/relevant


## Instalation

### Requirements:
	
	jdk 8
	
### compilation

	- mvn compile
	
	
### deploy

	- mvn spring-boot: run	

### properties profile

	- dev
	
## Postman

	- postman folder contains some examples and collection	

