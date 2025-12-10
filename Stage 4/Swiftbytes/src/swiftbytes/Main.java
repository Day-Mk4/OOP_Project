/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

public class Main {
    public static void main(String[] args) {
        Initializer init = new Initializer();
        LoginGUI app = new LoginGUI(init);
        app.Run();
    }
}
