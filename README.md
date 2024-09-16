Prueba Técnia de Java
==============

Este proyecto es una aplicación de prueba desarrollada en Java y desplegadoo en AWS. A continuación, se detallan los pasos para descargar, ejecutar y probar la aplicación.

Descargar el Proyecto
---------------------

1.  Abre una terminal y ejecuta el siguiente comando para clonar

2.  git clone https://github.com/jeanbryanrr/prueba_tecnica


Construir y Ejecutar la Aplicación
----------------------------------

1.  mvn clean install


Probar la Aplicación
--------------------

### Login

Para probar el endpoint de login, realiza una solicitud POST a
`ec2-54-226-159-158.compute-1.amazonaws.com:8001/login` con el siguiente cuerpo JSON:

`{  "email": "bryan@gmail.com", "password": "adminA1" }`

### Registro de Usuario

Para registrar un nuevo usuario, realiza una solicitud POST a
`ec2-54-226-159-158.compute-1.amazonaws.com:8001/registrarUsuario` con el siguiente cuerpo JSON:

`{"name": "Bryan","email": "bryand@gmail.com",  "password": "adminA1", "phones": [ {"number": "987654321",  "citycode": "1","countrycode": "USA"},{"number": "91248454",  "citycode": "2","countrycode": "ESPAÑA" }      ]  }`

### Listar todos los Usuarios (servicio requerido con token )

Para probar el endpoint de listarUsuarios, realiza una solicitud POST a
`ec2-54-226-159-158.compute-1.amazonaws.com:8001/listarUsuarios`



