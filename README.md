
# --------------Creacion de usuarios--------------------

# Este proyecto tiene 4 End-point/RestController, 
# creacion de usuario, consulta de usuarios, actualizar usuario y eliminar usuario.
# 
# -------------Desarrollodo con ---------------------
# Spring boot 2.7.8
# Java 11
# Jpa
# BD - H2
# Maven

# -------------Como funciona ---------------------
# Clone el proyecto a traves del siguiente enlace: 
# 
# abra el proyecto con sus editor favorito, con el mismo editor actualice dependencias
# o por terminal, en la ubicacion de su proyecto ejecute : mvn dependency:resolve
# una vez descargadas las dependencias ya puede compilar su proyecto y probar.


# IMPORTANTE
# No existe la necesidad de ejecutar algun sql para creación de bd, ni tablas, etc. 
# ya que jpa lo hace automaticamente.
# ---
# El eliminado se realiza de forma logia cambiando el campo isactive y no de manera permanente en BD.
# El proyecto apunta al 8081, puede cambiarlo en el archivo  application.properties
# Es necesario que su configuración del IDE apunte a su java 11
# En la siguiente liga(https://app.swaggerhub.com/apis-docs/falcarazing2/ApiRestUsuarios/1.0) se puede consultar el Swagger
# o Al compilar el proyecto se puede acceder a dicho swagger http://localhost:8081/swagger-ui.html

