# prueba-tec-sentra
Prueba técnica para postular a Backend developer, Sentra

## Requisitos
- java 17+
- gradle

## Pasos para ejecutar el proyecto
- git clone https://github.com/xergiox/prueba-tec-sentra.git
- gradle build bootRun

El endpoint se levantará en http://localhost:8080/register

### cURL (puedes ejecutarlo por ejemplo en Postman)
curl --location 'http://localhost:8080/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hun.Rer2",
"phones": [
{
"number": "1234567",
"citycode": "1",
"contrycode": "57"
}
]
}'

### Notas
Para la solución se usó una base de datos en memoria (H2). La configuracion de esta base de datos 
está en application.properties.
Al arrancar el programa la Base de Datos se configura automaticamente y puedes ingresar a la url http://localhost:8080/h2-console (pass: password) para revisar el contenido.

Tambien se agrega OpenApi como documentacion y UI la cual puede acceder desde http://localhost:8080/swagger-ui/index.html una vez que arranque el proyecto.