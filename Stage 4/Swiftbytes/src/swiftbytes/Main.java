/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

public class Main {
    public static void main(String[] args) {
        
        final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginGUI.class.getName());
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            Initializer init = new Initializer();
            init.initForGUI();
            new LoginGUI(init).setVisible(true);
        });
    }
}
