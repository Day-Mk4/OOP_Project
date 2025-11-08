/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
public class Main {
    public static void main(String[] args) {
        Initializer init = new Initializer();
        SubMenuManager app = new SubMenuManager(init.list1, init.list2, init.list3, init.list4, init.list5, init.list6);
        app.loginMenu();
    }
}