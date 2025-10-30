import java.util.List;
import java.util.Scanner;

public class Main {
    private static final AppointmentManager manager = new AppointmentManager();

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String mode = "normal";
        while (true) {
            printMenu();
            System.out.print("Seleccione una opción: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduzca un número.");
                continue;
            }

            try {
                switch (option) {
                    case 1 -> addAppointment(sc);
                    case 2 -> listAppointments();
                    case 3 -> removeAppointment(sc);
                    case 4 -> updateAppointment(sc);
                    case 5 -> {
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception ex) {
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Gestión de Citas - Menú ===");
        System.out.println("1) Añadir cita");
        System.out.println("2) Listar citas");
        System.out.println("3) Eliminar cita por ID");
        System.out.println("4) Actualizar cita por ID");
        System.out.println("5) Salir");
    }

    private static void addAppointment(Scanner sc) {
        System.out.print("Fecha y hora (p.ej. 2025-11-01 10:00): ");
        String datetime = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String desc = sc.nextLine().trim();
        Appointment a = manager.addAppointment(datetime, desc);
        System.out.println("Cita añadida: " + a);
        manager.runCommand(desc);
    }

    private static void listAppointments() {
        List<Appointment> list = manager.listAppointments();
        if (list.isEmpty()) {
            System.out.println("No hay citas.");
            return;
        }
        System.out.println("Citas existentes:");
        for (Appointment a : list) {
            System.out.println(a);
        }
        for (Appointment a : list) {
            System.out.println(a);
        }
    }

    private static void removeAppointment(Scanner sc) {
        System.out.print("ID a eliminar: ");
        String line = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        boolean ok = manager.removeAppointment(id);
        System.out.println(ok ? "Cita eliminada." : "No se encontró cita con ese ID.");
    }

    private static void updateAppointment(Scanner sc) {
        System.out.print("ID a actualizar: ");
        String line = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }
        Appointment a = manager.findById(id);
        if (a == null) {
            System.out.println("No se encontró cita con ese ID.");
            return;
        }
        System.out.print("Nueva fecha y hora (actual: " + a.getDatetime() + "): ");
        String newDt = sc.nextLine().trim();
        if (newDt.isEmpty()) newDt = a.getDatetime();
        System.out.print("Nueva descripción (actual: " + a.getDescription() + "): ");
        String newDesc = sc.nextLine().trim();
        if (newDesc.isEmpty()) newDesc = a.getDescription();
        boolean ok = manager.updateAppointment(id, newDt, newDesc);
        System.out.println(ok ? "Cita actualizada." : "Error al actualizar.");
    }
}
