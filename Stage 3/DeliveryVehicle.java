/**
 * Author:
 * Assignment: Project
 */
public class DeliveryVehicle {

    private String name;
    private String make;
    private String model;
    private String year;
    private String color;
    private String condition;
    private DeliveryPerson assignedDriver;

    // Constructor to create a new vehicle with default condition "Good".
    public DeliveryVehicle(String name, String make, String model, String year, String color) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.condition = "Good";
    }

    // @return name
    public String getName() {
        return name;
    }

    // Sets name
    public void setName(String name) {
        this.name = name;
    }

    // @return make
    public String getMake() {
        return make;
    }

    // Sets make
    public void setMake(String make) {
        this.make = make;
    }

    // @return model
    public String getModel() {
        return model;
    }

    // Sets model
    public void setModel(String model) {
        this.model = model;
    }

    // @return year
    public String getYear() {
        return year;
    }

    // Sets year
    public void setYear(String year) {
        this.year = year;
    }

    // @return color
    public String getColor() {
        return color;
    }

    // Sets color
    public void setColor(String color) {
        this.color = color;
    }

    // @return condition
    public String getCondition() {
        return condition;
    }

    // Sets condition
    public void setCondition(String condition) {
        if (condition != null && !condition.trim().isEmpty()) {
            this.condition = condition;
        }
    }

    // @return assigned driver
    public DeliveryPerson getAssignedDriver() {
        return assignedDriver;
    }

    // Sets assigned driver
    public void setAssignedDriver(DeliveryPerson assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    // Prints all vehicle information.
    public void displayDetails() {
        System.out.println(
                "Vehicle { " +
                "Name='" + name + '\'' +
                ", Make='" + make + '\'' +
                ", Model='" + model + '\'' +
                ", Year='" + year + '\'' +
                ", Color='" + color + '\'' +
                ", Condition='" + condition + '\'' +
                ", Driver=" + (assignedDriver == null ? "None" : assignedDriver.getName()) +
                " }"
        );
    }
}
