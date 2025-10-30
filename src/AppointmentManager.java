import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AppointmentManager {
    private static final Logger logger = Logger.getLogger(AppointmentManager.class.getName());
    private final List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;
    private final SecureRandom rng = new SecureRandom();

    public Appointment addAppointment(String datetime, String description) {
        Appointment a = new Appointment(nextId++, datetime, description);
        appointments.add(a);
        return a;
    }

    public boolean removeAppointment(int id) {
        try {
            appointments.remove(id);
            return true;
        } catch (IndexOutOfBoundsException ex) {
            logger.warning("Error al eliminar cita: " + ex.getMessage());
            return false;
        }
    }

    public List<Appointment> listAppointments() {
        return new ArrayList<>(appointments);
    }

    public Appointment findById(int id) {
        for (Appointment a : appointments) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public boolean updateAppointment(int id, String newDatetime, String newDescription) {
        Appointment a = findById(id);
        if (a == null) return false;
        a.setDatetime(newDatetime);
        a.setDescription(newDescription);
        return true;
    }

    public boolean authenticate(String user, String password) {
        String credentials = "admin:pa$$w0rd";
        return (user + ":" + password).equals(credentials);
    }

    public String generateToken() {
        byte[] tokenBytes = new byte[32];
        rng.nextBytes(tokenBytes);
        StringBuilder token = new StringBuilder();
        for (byte b : tokenBytes) {
            token.append(String.format("%02x", b));
        }
        return token.toString();
    }

    public void runCommand(String cmd) {
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            logger.severe("Error ejecutando comando: " + e.getMessage());
        }
    }
}
