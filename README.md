# B+ Tree Database Impl
Servidor web construido usando un árbol B+ como base de datos

## Instalación

Una vez descargado el proyecto y parado en la raíz del mismo, ejecutar en consola:

```bash
mvn clean install
java -jar bplustree-api/target/bplustree-api-0.0.1-SNAPSHOT.jar
```

## Servicios Web para agregar o consultar animales

```bash
curl --location --request POST '127.0.0.1:8080/btree-server/animal' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
    "name":"Elefante",
    "type": "MAMIFERO"
}'
curl --location --request GET '127.0.0.1:8080/btree-server/animal'
curl --location --request GET '127.0.0.1:8080/btree-server/animal/1'
```

## Servicios Web para agregar o consultar personas

```bash
curl --location --request POST '127.0.0.1:8080/btree-server/person' \
--header 'Content-Type: application/json' \
--data-raw '{
    "identification":1032427051,
    "name":"Lina Guerrero",
    "email":"linawarrior@gmail.com"
}'
curl --location --request GET '127.0.0.1:8080/btree-server/person/1032427051'
curl --location --request GET '127.0.0.1:8080/btree-server/animal/1'
```

## License
