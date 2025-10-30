Aplicación de consola en Java para gestión simple de citas.

Archivos:
- `src/Appointment.java` - clase modelo para una cita.
- `src/AppointmentManager.java` - gestión en memoria (añadir, listar, eliminar, actualizar).
- `src/Main.java` - interfaz de consola (menú).

Compilar:

    javac src/*.java -d out

Ejecutar:

    java -cp out Main

Uso:
- Opción 1: añadir cita (fecha/hora y descripción)
- Opción 2: listar citas
- Opción 3: eliminar por ID
- Opción 4: actualizar por ID
- Opción 5: salir

Nota: La aplicación es muy básica y guarda las citas sólo en memoria mientras el programa esté corriendo.
