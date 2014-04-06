TeleVentaProject
================

Sistema de venta telefónica. Proyecto realizado para la materia Sistemas Distribuidos y Paralelos de la Universidad Nacional de San Luis

Muestra el uso de Remote Method Invocation.


Para poder compilar y luego ejecutar la aplicación se debe seguir los siguientes pasos:

- Instalar Java Devolopment Kit (versión 1.6 o mayor).
- Configurar JDK y Java DB (Derby).[Mire el tutorial](http://db.apache.org/derby/papers/DerbyTut/install_software.html).
- Colocar todos los archivos fuente en una carpeta y ejecutar el comando `javac InterfazArticuloVenta.java ArticuloVenta.java InterfazVenta.java Venta.java InterfazServer.java Server.java Vendedor.java`.
- Crear la Base de Datos e instanciar con los valores iniciales utilizando el comando `ij`. Para ello invoque a la herramienta ij desde la terminal y coloque `connect 'jdbc:derby:VentasDB;create=true';` y luego `run 'Creacion DB e instancia inicial.sql';` sin comillas. Para salir de la herramienta ij ingrese `exit;`.
- Luego abra dos lineas de comando (una hará el papel de servidor y registro rmi, y la otra hará de cliente). Primero en la terminal servidor ejecute el comando `run rmiregistry 3001` sin comillas (se tiene que abrir una nueva ventana en negro) y luego ejecute el comando `java Server`. Luego en la terminal cliente ejecute el comando `java Vendedor nombreVendedor` sin comillas y reemplazando 'nombreVendedor' por el nombre de vendedor que quiera.