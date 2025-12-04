/**
 * Author: Amadeo Pena
 * Assignment: Project
 */

package swiftbytes;

import java.util.*;
import javax.swing.*;
import java.awt.*;


public class CustomerGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerGUI.class.getName());

    
    private final Initializer initializer;
    private final Customer customer;

    private DefaultListModel<Restaurant> restaurantListModel;
    private java.util.List<String[]> items = new ArrayList<>();

    /**
     * Creates new form CustomerGUI
     */
    public CustomerGUI(Initializer initializer, Customer customer) {
        this.initializer = initializer;
        this.customer = customer;

        initComponents();

        // dynamic header
        jLabel1.setText("Customer - " + customer.getName());

        // initialize restaurant model
        restaurantListModel = new DefaultListModel<>();
        listRestaurants.setModel(restaurantListModel);
        listRestaurants.setCellRenderer(new RestaurantRenderer());

        loadRestaurants();
    }

    
    private void loadRestaurants() {
        restaurantListModel.clear();
        for (Restaurant r : initializer.getRestaurants()) {
            restaurantListModel.addElement(r);
        }
    }
    
    private Restaurant getSelectedRestaurant() {
        Restaurant r = listRestaurants.getSelectedValue();
        if (r == null) {
            JOptionPane.showMessageDialog(this, "Please select a restaurant on the left.");
        }
        return r;
    }
    
    private void showMenuForSelected() {
        Restaurant r = getSelectedRestaurant();
        if (r == null) return;
        if (r.getMenu() == null) {
            txtArea.setText("Restaurant has no menu.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Menu for ").append(r.getName()).append("\n\n");

        sb.append("Singles:\n");
        for (Map.Entry<String, Double> e : r.getMenu().getSingles().entrySet()) {
            sb.append("  ").append(e.getKey()).append(" .... $").append(e.getValue()).append("\n");
        }

        sb.append("\nCombos:\n");
        for (Map.Entry<String, Double> e : r.getMenu().getCombos().entrySet()) {
            sb.append("  ").append(e.getKey()).append(" .... $").append(e.getValue()).append("\n");
        }

        txtArea.setText(sb.toString());
    }
    
    
    private void placeOrderFlow() {
        Restaurant selected = getSelectedRestaurant();
        if (selected == null) return;

        items = new ArrayList<>();

        if (selected.getMenu() == null) {
            JOptionPane.showMessageDialog(this, "This restaurant has no menu.");
            return;
        }

        while (true) {
            String itemName = JOptionPane.showInputDialog(this,
                    "Enter item name (exactly as menu) or 'done' to finish:");

            if (itemName == null) return;
            if ("done".equalsIgnoreCase(itemName.trim())) break;

            Double price = selected.getMenu().getSingles().get(itemName);
            if (price == null) price = selected.getMenu().getCombos().get(itemName);

            if (price == null) {
                JOptionPane.showMessageDialog(this, "Unknown item: " + itemName);
                continue;
            }

            String qtyStr = JOptionPane.showInputDialog(this, "Quantity:", "1");
            if (qtyStr == null) return;

            int qty;
            try {
                qty = Integer.parseInt(qtyStr.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.");
                continue;
            }

            items.add(new String[]{itemName, price.toString(), String.valueOf(qty)});
        }

        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items selected.");
            return;
        }

        // Coupon
        Set<DiscountCoupon> usedCoupons = new HashSet<>();
        String code = JOptionPane.showInputDialog(this, "Coupon code (optional):");

        if (code != null && !code.trim().isEmpty()) {
            boolean found = false;
            for (DiscountCoupon c : initializer.getCoupons()) {
                if (c.getCode().equalsIgnoreCase(code.trim()) && c.isValid()) {
                    usedCoupons.add(c);
                    c.invalidate();
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Coupon invalid or already used.");
            }
        }

        if (initializer.getDrivers().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No drivers available.");
            return;
        }

        DeliveryPerson dp = initializer.getDrivers().iterator().next();

        String[][] arr = items.toArray(new String[0][0]);

        String orderID = "ORD-" + (initializer.getOrders().size() + 1);

        Order order = new Order(
                orderID,
                arr,
                selected.getName(),
                customer.getName(),
                dp.getID(),
                usedCoupons
        );


        selected.addOrder(order);
        initializer.getOrders().add(order);
        initializer.getAdmin().recordOrder(order);
        dp.assignOrder(order);
        customer.recordOrder(order);

        StringBuilder sb = new StringBuilder();
        sb.append("Order created!\n\n");
        sb.append("Order ID: ").append(order.getOrderID()).append("\n");
        sb.append("Restaurant: ").append(selected.getName()).append("\n");
        sb.append("Driver: ").append(dp.getName()).append("\n\n");
        sb.append("Items:\n");

        for (String[] row : order.getOrderItems()) {
            sb.append("  ").append(row[0]).append(" x").append(row[2])
              .append(" @ $").append(row[1]).append("\n");
        }

        sb.append("\nTotal: $").append(order.getPrice());

        txtArea.setText(sb.toString());
    }
    
    private void payLastOrder() {
        java.util.List<Order> history = customer.getOrderHistory();
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders to pay.");
            return;
        }

        Order last = history.get(history.size() - 1);

        if (last.getPayment() != null && last.getPayment().getPaidStatus()) {
            JOptionPane.showMessageDialog(this, "Last order already paid.");
            return;
        }

        String payID = "PAY-" + (initializer.getPayments().size() + 1);

        Payment pay = new Payment(
                payID,
                last.getPrice(),
                last.getDeliveryPerson(),  
                last.getOrderID(),       
                last.getCustomer(),         
                false                       
        );
        
        last.setPayment(pay);
        initializer.getPayments().add(pay);
        initializer.getAdmin().recordPayment(pay);

        PaymentManager pm = new PaymentManager();
        String method = JOptionPane.showInputDialog(this, "Payment method (card/cash):", "card");
        if (method == null) return;

        String details = JOptionPane.showInputDialog(this, "Payment details:", "");
        if (details == null) return;

        pm.process(pay, method, details);

        txtArea.setText("Payment processed.\nAmount: $" + pay.getPrice());
    }
    
    
    private void showOrderHistory() {
        txtArea.setText("");

        java.util.List<Order> history = customer.getOrderHistory();

        if (history.isEmpty()) {
            txtArea.setText("No past orders.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order History for ").append(customer.getName()).append("\n\n");

        for (Order o : history) {
            sb.append("Order ").append(o.getOrderID())
                    .append(" at ").append(o.getRestaurant())
                    .append(" - Total: $").append(o.getPrice())
                    .append("\n");
        }

        txtArea.setText(sb.toString());
    }

    private static class RestaurantRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Restaurant r) {
                lbl.setText(r.getName() + " (" + r.getRating() + ")");
            }

            return lbl;
        }
    }






    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRestaurants = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnViewMenu = new javax.swing.JButton();
        btnPlaceOrder = new javax.swing.JButton();
        btnPayLast = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(251, 153, 51));

        jLabel1.setBackground(new java.awt.Color(255, 153, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Customer");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel1)
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 0));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(listRestaurants);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtArea.setLineWrap(true);
        txtArea.setRows(5);
        txtArea.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtArea);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(45, 45, 45));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 309));

        btnViewMenu.setBackground(new java.awt.Color(255, 153, 51));
        btnViewMenu.setText("View Menu");
        btnViewMenu.setFocusPainted(false);
        btnViewMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewMenuActionPerformed(evt);
            }
        });

        btnPlaceOrder.setBackground(new java.awt.Color(255, 153, 51));
        btnPlaceOrder.setText("Place Order");
        btnPlaceOrder.setFocusPainted(false);
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });

        btnPayLast.setBackground(new java.awt.Color(255, 153, 51));
        btnPayLast.setText("Pay Last Order");
        btnPayLast.setFocusPainted(false);
        btnPayLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayLastActionPerformed(evt);
            }
        });

        btnHistory.setBackground(new java.awt.Color(255, 153, 51));
        btnHistory.setText("Order History");
        btnHistory.setFocusPainted(false);
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 153, 51));
        btnLogout.setText("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPayLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPlaceOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnViewMenu)
                .addGap(0, 0, 0)
                .addComponent(btnPlaceOrder)
                .addGap(0, 0, 0)
                .addComponent(btnPayLast)
                .addGap(0, 0, 0)
                .addComponent(btnHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewMenuActionPerformed
        // TODO add your handling code here:
        showMenuForSelected();
    }//GEN-LAST:event_btnViewMenuActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        // TODO add your handling code here:
        placeOrderFlow();
    }//GEN-LAST:event_btnPlaceOrderActionPerformed

    private void btnPayLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayLastActionPerformed
        // TODO add your handling code here:
        payLastOrder();
    }//GEN-LAST:event_btnPayLastActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // TODO add your handling code here:
        showOrderHistory();
    }//GEN-LAST:event_btnHistoryActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        LoginGUI loginGUI = new LoginGUI(initializer);
        loginGUI.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPayLast;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnViewMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Restaurant> listRestaurants;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
