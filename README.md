# Literalura

Literalura es una aplicación en Java que permite buscar y listar libros y autores utilizando la API de Gutendex. La aplicación está construida con Spring Boot, Spring Data JPA y PostgreSQL, y proporciona un menú interactivo para el usuario.

## Funcionalidades

1. **Buscar libro por título**: Permite buscar libros en la API de Gutendex.
2. **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos local.
3. **Listar autores registrados**: Muestra todos los autores almacenados en la base de datos local.
4. **Listar autores vivos en un determinado año**: Permite buscar autores que estaban vivos en un año específico.
5. **Listar libros por idioma**: Muestra libros en un idioma seleccionado.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson
- Maven

## Requisitos

- JDK 17
- PostgreSQL
- Maven

## Instalación

1. **Clonar el repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd literalura

#Configurar la base de datos:
Asegúrate de tener PostgreSQL instalado y crea una base de datos llamada literalura.

#Configurar el archivo de propiedades:
Modifica src/main/resources/application.properties con tus credenciales de PostgreSQL:

'''bash 
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

#Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
