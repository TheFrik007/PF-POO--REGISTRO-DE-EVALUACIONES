# Proyecto Final de POO - Registro de Evaluaciones

Este proyecto es una aplicación de registro de evaluaciones desarrollada en Java. Utiliza MySQL como sistema de gestión de bases de datos, recomendándose la instalación y ejecución de la base de datos en un entorno XAMPP.

## Requisitos

- [XAMPP](https://www.apachefriends.org/index.html) (incluye Apache y MySQL)
- [Git](https://git-scm.com/downloads)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [NetBeans IDE](https://netbeans.apache.org/download/index.html) (opcional, pero recomendado para el desarrollo)

## Configuración del Entorno

### 1. Instalación de XAMPP

1. Descarga e instala [XAMPP](https://www.apachefriends.org/index.html).
2. Inicia el panel de control de XAMPP y activa los módulos de **Apache** y **MySQL**.

### 2. Configuración de la Base de Datos

1. Abre **phpMyAdmin** desde el panel de control de XAMPP.
2. Crea una nueva base de datos llamada `evaluaciones_db`.
3. Importa el archivo `database/evaluaciones_db.sql` para crear las tablas necesarias. 

### 3. Clonación del Proyecto

1. Abre PowerShell o tu terminal preferida.
2. Navega al directorio donde deseas clonar el proyecto.
3. Ejecuta el siguiente comando para clonar el repositorio:

    ```sh
    git clone https://github.com/TheFrik007/PF-POO--REGISTRO-DE-EVALUACIONES.git
    ```

4. Navega al directorio del proyecto clonado:

    ```sh
    cd PF-POO--REGISTRO-DE-EVALUACIONES
    ```

### 4. Configuración del Proyecto en NetBeans

1. Abre NetBeans IDE.
2. Selecciona `File` > `Open Project` y navega hasta la carpeta del proyecto clonado.
3. Configura el JDK si no lo has hecho ya.
4. Configura las dependencias necesarias si es necesario.

### 5. Ejecución del Proyecto

1. Asegúrate de que XAMPP esté corriendo y que la base de datos `evaluaciones_db` esté creada y configurada.
2. Corre el proyecto desde NetBeans o usando la línea de comandos.

### Nota Adicional

- Si cambiaste la configuración predeterminada de MySQL en XAMPP (como el puerto o el usuario), asegúrate de actualizar las credenciales de la base de datos en tu aplicación Java.

## Estructura del Proyecto

```plaintext
tu-proyecto/
│
├── src/
│   ├── com/
│   │   └── empresa/
│   │       └── proyecto/
│   │           ├── dao/
│   │           ├── model/
│   │           ├── service/
│   │           └── view/
│   └── ...
├── database/
│   └── evaluaciones_db.sql
├── .gitignore
├── README.md
└── ...
