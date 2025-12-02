/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

public class User {

    public String username;
    public String password;
    public String name;
    public String email;
    public String phone;
    public String address;

    // Constructor for new user
    public User(String username, String password, String name,
                String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

     // @returm username
    public String getUsername() {
        return username;
    }

    // Sets new username
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

     // @returm password
    public String getPassword() {
        return password;
    }

    // Sets password
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

     // @returm name
    public String getName() {
        return name;
    }

    // Sets name
    public void setName(String name) {
        this.name = name;
    }

     // @returm email
    public String getEmail() {
        return email;
    }

    // Sets email
    public void setEmail(String email) {
        this.email = email;
    }

     // @returm phone
    public String getPhone() {
        return phone;
    }

    // Sets phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

     // @returm address
    public String getAddress() {
        return address;
    }

    // Sets address
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Prints all user details.
     */
    public void displayDetails() {
        System.out.println(
            "User {" +
            "name='" + name + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            '}'
        );
    }
}

