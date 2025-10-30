import java.util.Objects;

public class Appointment {
    private int id;
    private String datetime; // formato libre, p.ej. "2025-11-01 10:00"
    private String description;

    public Appointment(int id, String datetime, String description) {
        this.id = id;
        this.datetime = datetime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s", id, datetime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
