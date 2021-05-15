# VocaDb-Java-Model-Generator

A stupid small project that can generate Java classes representing VocaDB Models described in [VocaDB APIs](https://vocadb.net/swagger/ui/index#/)  

Example input models: [models.txt](models.txt)  
Example outputs: [output/](output/)

## About this project

VocaDB has switched their API page to swagger [here](https://vocadb.net/swagger/index.html). 
If you need new VocaDB Java models, use [openapi-generator](https://github.com/OpenAPITools/openapi-generator).
They also has an openapi json file on their swagger site.  
Models from this repo are old but may still works. Use at your own risk

## How to use

1. make sure you have Java 11 installed
2. download the source code of this project
3. use IDE/Editor that support Lombok plugins (Intellij, Eclipse, VScode), install the lombok plugin and open the project as maven project
4. run Main with arugument "models.txt" (and of course you can use your own txt file to store models from another api entry)
5. you should able to see the generated class in output/
6. feel free to modify the source code to suit your needs

## Appreciation

Thanks for [VocaDB](https://vocadb.net/) for providing such great APIs for fetching information about Vocaloid Songs
