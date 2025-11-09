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
    private String assignedDriverID;


    public DeliveryVehicle(String newName, String newMake, String newModel, String newYear, String newColor, String newCondition, String newAssignedDriverID) {
        name = newName;
        make = newMake;
        model = newModel;
        year = newYear;
        color = newColor;
        condition = newCondition;
        assignedDriverID = newAssignedDriverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        if (condition != null && !condition.trim().isEmpty()) {
            this.condition = condition;
        }
    }

    public String getAssignedDriver() {
        return assignedDriverID;
    }

    public void setAssignedDriver(String newID) {
        assignedDriverID = newID;
    }

    public void displayDetails() {
        System.out.println(
            "Vehicle { " +
            "Name='" + name + '\'' +
            ", Make='" + make + '\'' +
            ", Model='" + model + '\'' +
            ", Year='" + year + '\'' +
            ", Color='" + color + '\'' +
            ", Condition='" + condition + '\'' +
            ", Driver=" + (assignedDriverID == null ? "None" : assignedDriverID) +
            " }"
        );
    }
}
