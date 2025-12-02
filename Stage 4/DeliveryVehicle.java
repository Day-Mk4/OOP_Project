/**
 * Author: Lazo McCarroll
 * Assignment: Project
 */
package swiftbytes;

public class DeliveryVehicle {

    // Instance variables
    private String name;
    private String make;
    private String model;
    private String year;
    private String color;
    private String condition;
    private String assignedDriver;

    /**
     * This constructor initializes the instance variables.
     * @param name
     * @param make
     * @param model
     * @param year
     * @param color
     * @param condition
     */
    public DeliveryVehicle(String name, String make, String model, String year, String color, String condition, String assignedDriver) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.condition = condition;
        this.assignedDriver = assignedDriver;
    }

    /**
     * This method returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the make.
     * @return
     */
    public String getMake() {
        return make;
    }

    /**
     * This method sets the make.
     * @param make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * This method returns the model.
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     * This method sets the model.
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method returns the year.
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * This method sets the year.
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * This method returns the color.
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     * This method sets the color.
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method returns the condition.
     * @return
     */
    public String getCondition() {
        return condition;
    }

    /**
     * This method sets the condition.
     * @param condition
     */
    public void setCondition(String condition) {
        if (condition != null && !condition.trim().isEmpty()) {
            this.condition = condition;
        }
    }

    /**
     * This method returns the assigned driver.
     * @return
     */
    public String getAssignedDriver() {
        return assignedDriver;
    }

    /**
     * This method sets the assigned driver.
     * @param assignedDriver
     */
    public void setAssignedDriver(String assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    /**
     * This method displays the vehicle details.
     */
    public void displayDetails() {
        System.out.println(
                "Vehicle { " +
                "Name: '" + name + '\'' +
                ", Make: '" + make + '\'' +
                ", Model: '" + model + '\'' +
                ", Year: '" + year + '\'' +
                ", Color: '" + color + '\'' +
                ", Condition: '" + condition + '\'' +
                ", Driver: " + (assignedDriver == null ? "None" : assignedDriver) +
                " }"
        );
    }
}
