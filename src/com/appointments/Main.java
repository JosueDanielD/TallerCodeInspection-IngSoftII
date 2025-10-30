package com.appointments;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final AppointmentManager manager = new AppointmentManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            logger.info("Seleccione una opción: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                logger.warning("Entrada inválida. Introduzca un número.");
                continue;
            }

            try {
                switch (option) {
                    case 1 -> addAppointment(sc);
                    case 2 -> listAppointments();
                    case 3 -> removeAppointment(sc);
                    case 4 -> updateAppointment(sc);
                    case 5 -> {
                        logger.info("Saliendo...");
                        sc.close();
                        return;
                    }
                    default -> logger.warning("Opción no válida.");
                }
            } catch (Exception ex) {
                logger.severe("Error inesperado: " + ex.getMessage());
            }
        }
    }

    private static void printMenu() {
        logger.info("\n=== Gestión de Citas - Menú ===");
        logger.info("1) Añadir cita");
        logger.info("2) Listar citas");
        logger.info("3) Eliminar cita por ID");
        logger.info("4) Actualizar cita por ID");
        logger.info("5) Salir");
    }

    private static void addAppointment(Scanner sc) {
        logger.info("Fecha y hora (p.ej. 2025-11-01 10:00): ");
        String datetime = sc.nextLine().trim();
        logger.info("Descripción: ");
        String desc = sc.nextLine().trim();
        Appointment a = manager.addAppointment(datetime, desc);
        logger.info(String.format("Cita añadida: %s", a));
        manager.runCommand(desc);
    }

    private static void listAppointments() {
        List<Appointment> list = manager.listAppointments();
        if (list.isEmpty()) {
            logger.info("No hay citas.");
            return;
        }
        logger.info("Citas existentes:");
        for (Appointment a : list) {
            logger.info(a.toString());
        }
        for (Appointment a : list) {
            logger.info(a.toString());
        }
    }

    private static void removeAppointment(Scanner sc) {
        logger.info("ID a eliminar: ");
        String line = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            logger.warning("ID inválido.");
            return;
        }
        boolean ok = manager.removeAppointment(id);
        logger.info(ok ? "Cita eliminada." : "No se encontró cita con ese ID.");
    }

    private static void updateAppointment(Scanner sc) {
        logger.info("ID a actualizar: ");
        String line = sc.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            logger.warning("ID inválido.");
            return;
        }
        Appointment a = manager.findById(id);
        if (a == null) {
            logger.warning("No se encontró cita con ese ID.");
            return;
        }
        logger.info(String.format("Nueva fecha y hora (actual: %s): ", a.getDatetime()));
        String newDt = sc.nextLine().trim();
        if (newDt.isEmpty()) newDt = a.getDatetime();
        logger.info(String.format("Nueva descripción (actual: %s): ", a.getDescription()));
        String newDesc = sc.nextLine().trim();
        if (newDesc.isEmpty()) newDesc = a.getDescription();
        boolean ok = manager.updateAppointment(id, newDt, newDesc);
        logger.info(ok ? "Cita actualizada." : "Error al actualizar.");
    }
}
