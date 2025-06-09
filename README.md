# tpfinalDB

## Antes de ejecutar :
En el archivo src/dao/DBConnection.java se encuentra la configuracion para conectarse a la base de datos por default
si usted tiene una configuracion personalizada por favor cambiarla para poder conectarse correctamente.
```java
    private static final String URL = "jdbc:mysql://localhost:3306/Proyecto";
    //Cambiar puerto en caso de no usar el "3306" por tu puerto 
    private static final String USER = "root";
    //Cambiar usuario "root" si usas uno diferente
    private static final String PASSWORD = "root";
    //Cambiar contraseña "root" si usas una diferente
```
## EJECUCION : 

### Opción 1: Compilar y Ejecutar Manualmente
```bash
# Crear carpeta de salida
mkdir -p out

# Compilar
find src -name "*.java" | xargs javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out

# Ejecutar
java -cp "out:lib/mysql-connector-j-8.0.33.jar" Main
```

### Opción 2: Crear JAR Ejecutable
```bash
# Compilar
mkdir -p out
find src -name "*.java" | xargs javac -cp "lib/mysql-connector-j-8.0.33.jar" -d out

# Crear JAR con punto de entrada
jar cvfe tpfinalDB.jar Main -C out .

# Ejecutar JAR
java -cp "tpfinalDB.jar:lib/mysql-connector-j-8.0.33.jar" Main
```