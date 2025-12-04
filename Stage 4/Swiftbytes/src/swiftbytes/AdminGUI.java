/**
 * Author: Amadeo Pena
 * Assignment: Project
 */
package swiftbytes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AdminGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminGUI.class.getName());

    private final Initializer initializer;
    private final Admin admin;
    private DefaultTableModel model;

    /**
     * Creates new form AdminGUI
     * @param initializer
     */
   
    public AdminGUI(Initializer initializer) {
        this.initializer = initializer;
        this.admin = initializer.getAdmin();

        initComponents();

        model = (DefaultTableModel) table.getModel();

        loadCustomersTable();
    
    // Navigation actions
    btnCustomers.addActionListener(e -> {
        lblTitle.setText("Admin - Customers");
        loadCustomersTable();
    });

    btnRestaurants.addActionListener(e -> {
        lblTitle.setText("Admin - Restaurants");
        loadRestaurantsTable();
    });

    btnDrivers.addActionListener(e -> {
        lblTitle.setText("Admin - Drivers");
        loadDriversTable();
    });

    btnVehicles.addActionListener(e -> {
        lblTitle.setText("Admin - Vehicles");
        loadVehiclesTable();
    });

    btnCoupons.addActionListener(e -> {
        lblTitle.setText("Admin - Coupons");
        loadCouponsTable();
    });

    btnOrders.addActionListener(e -> {
        lblTitle.setText("Admin - Orders (Report View Only)");
        loadOrdersTable();
    });

    btnPayments.addActionListener(e -> {
        lblTitle.setText("Admin - Payments (Report View Only)");
        loadPaymentsTable();
    });

    btnLogout.addActionListener(e -> {
        LoginGUI gui = new LoginGUI(initializer);
        gui.setVisible(true);
        dispose();
    });
    
    // --- CRUD BUTTON ACTIONS ---
    btnAdd.addActionListener(e -> handleAdd());
    btnUpdate.addActionListener(e -> handleUpdate());
    btnRemove.addActionListener(e -> handleRemove());

    }

    // ----------TABLE LOADING METHODS------------

    private void loadCustomersTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Address");

        for (Customer c : initializer.getCustomers()) {
            model.addRow(new Object[]{
                    c.getCustomerID(),
                    c.getUsername(),
                    c.getName(),
                    c.getEmail(),
                    c.getPhone(),
                    c.getAddress()
            });
        }
    }

    private void loadRestaurantsTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Rating");
        model.addColumn("Address");

        for (Restaurant r : initializer.getRestaurants()) {
            model.addRow(new Object[]{
                    r.getID(),
                    r.getName(),
                    r.getPhone(),
                    r.getRating(),
                    r.getAddress()
            });
        }
    }

    private void loadDriversTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Address");

        for (DeliveryPerson d : initializer.getDrivers()) {
            model.addRow(new Object[]{
                    d.getID(),
                    d.getUsername(),
                    d.getName(),
                    d.getEmail(),
                    d.getPhone(),
                    d.getAddress()
            });
        }
    }

    private void loadVehiclesTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Name");
        model.addColumn("Make");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Color");
        model.addColumn("Condition");
        model.addColumn("Driver");

        for (DeliveryVehicle v : initializer.getVehicles()) {

            String driverName = (v.getAssignedDriver() == null || v.getAssignedDriver().isBlank())
                    ? "None"
                    : v.getAssignedDriver();

            model.addRow(new Object[]{
                    v.getName(),
                    v.getMake(),
                    v.getModel(),
                    v.getYear(),
                    v.getColor(),
                    v.getCondition(),
                    driverName
            });
        }
    }


    private void loadCouponsTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Code");
        model.addColumn("Value");
        model.addColumn("Valid");

        for (DiscountCoupon c : initializer.getCoupons()) {
            model.addRow(new Object[]{
                    c.getCode(),
                    c.getDiscountAmount(),
                    c.isValid()
            });
        }
    }

    private void loadOrdersTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Order ID");
        model.addColumn("Restaurant");
        model.addColumn("Customer");
        model.addColumn("Total");

        for (Order o : initializer.getOrders()) {
            model.addRow(new Object[]{
                    o.getOrderID(),     
                    o.getRestaurant(),    
                    o.getCustomer(),   
                    o.getPrice()          
            });
        }
    }


    private void loadPaymentsTable() {
        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Payment ID");
        model.addColumn("Order ID");
        model.addColumn("Customer");
        model.addColumn("Amount");
        model.addColumn("Paid");

        for (Payment p : initializer.getPayments()) {
            model.addRow(new Object[]{
                    p.getPaymentID(),   
                    p.getOrderID(),       
                    p.getCustomer(),      
                    p.getPrice(),          
                    p.getPaidStatus()       
            });
        }
    }

    // --------- CRUD ACTIONS ----------

    private void handleAdd() {
        String title = lblTitle.getText();
        if (title.contains("Customers")) addCustomer();
        else if (title.contains("Restaurants")) addRestaurant();
        else if (title.contains("Drivers")) addDriver();
        else if (title.contains("Vehicles")) addVehicle();
        else if (title.contains("Coupons")) addCoupon();
    }

    private void handleUpdate() {
        String title = lblTitle.getText();
        if (title.contains("Customers")) updateCustomer();
        else if (title.contains("Restaurants")) updateRestaurant();
        else if (title.contains("Drivers")) updateDriver();
        else if (title.contains("Vehicles")) updateVehicle();
        else if (title.contains("Coupons")) updateCoupon();
    }

    private void handleRemove() {
        String title = lblTitle.getText();
        if (title.contains("Customers")) removeCustomer();
        else if (title.contains("Restaurants")) removeRestaurant();
        else if (title.contains("Drivers")) removeDriver();
        else if (title.contains("Vehicles")) removeVehicle();
        else if (title.contains("Coupons")) removeCoupon();
    }

    // -------- CRUD IMPLEMENTATIONS -----------

    // ----- CUSTOMERS -----

    private void addCustomer() {
        String u = JOptionPane.showInputDialog(this, "Username:");
        if (u == null || u.isBlank()) return;

        String p = JOptionPane.showInputDialog(this, "Password:");
        if (p == null) return;

        String n = JOptionPane.showInputDialog(this, "Name:");
        if (n == null) return;

        String e = JOptionPane.showInputDialog(this, "Email:");
        if (e == null) return;

        String ph = JOptionPane.showInputDialog(this, "Phone:");
        if (ph == null) return;

        String a = JOptionPane.showInputDialog(this, "Address:");
        if (a == null) return;

        String id = "CUST-" + (initializer.getCustomers().size() + 1);

        Customer c = new Customer(u, p, n, e, ph, a, id);
        initializer.getCustomers().add(c);
        admin.addCustomer(c);

        loadCustomersTable();
    }

    private void updateCustomer() {
        String id = JOptionPane.showInputDialog(this, "Customer ID to update:");
        if (id == null || id.isBlank()) return;

        Customer found = null;
        for (Customer c : initializer.getCustomers()) {
            if (c.getCustomerID().equals(id)) {
                found = c; break;
            }
        }
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Customer not found.");
            return;
        }

        String newPhone = JOptionPane.showInputDialog(this, "New phone:", found.getPhone());
        if (newPhone != null && !newPhone.isBlank()) found.setPhone(newPhone);

        String newAddress = JOptionPane.showInputDialog(this, "New address:", found.getAddress());
        if (newAddress != null && !newAddress.isBlank()) found.setAddress(newAddress);

        loadCustomersTable();
    }

    private void removeCustomer() {
        String id = JOptionPane.showInputDialog(this, "Customer ID to remove:");
        if (id == null || id.isBlank()) return;

        initializer.getCustomers().removeIf(c -> c.getCustomerID().equals(id));
        loadCustomersTable();
    }


    // ----- RESTAURANTS -----

    private void addRestaurant() {
        String id = JOptionPane.showInputDialog(this, "Restaurant ID:");
        if (id == null || id.isBlank()) return;

        String name = JOptionPane.showInputDialog(this, "Name:");
        if (name == null) return;

        String address = JOptionPane.showInputDialog(this, "Address:");
        if (address == null) return;

        String phone = JOptionPane.showInputDialog(this, "Phone:");
        if (phone == null) return;

        double rating = Double.parseDouble(
            JOptionPane.showInputDialog(this, "Rating (0-5):", "3.0")
        );

        Restaurant r = new Restaurant(id, name, address, phone, rating);
        initializer.getRestaurants().add(r);
        admin.addRestaurant(r);

        loadRestaurantsTable();
    }

    private void updateRestaurant() {
        String id = JOptionPane.showInputDialog(this, "Restaurant ID to update:");
        if (id == null || id.isBlank()) return;

        Restaurant found = null;
        for (Restaurant r : initializer.getRestaurants()) {
            if (r.getID().equals(id)) {
                found = r; break;
            }
        }
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Restaurant not found.");
            return;
        }

        String newPhone = JOptionPane.showInputDialog(this, "New phone:", found.getPhone());
        if (newPhone != null && !newPhone.isBlank()) found.setPhone(newPhone);

        String newAddr = JOptionPane.showInputDialog(this, "New address:", found.getAddress());
        if (newAddr != null && !newAddr.isBlank()) found.setAddress(newAddr);

        String ratingStr = JOptionPane.showInputDialog(this, "New rating:", found.getRating());
        if (ratingStr != null && !ratingStr.isBlank()) {
            found.setRating(Double.parseDouble(ratingStr));
        }

        loadRestaurantsTable();
    }

    private void removeRestaurant() {
        String id = JOptionPane.showInputDialog(this, "Restaurant ID to remove:");
        if (id == null || id.isBlank()) return;

        initializer.getRestaurants().removeIf(r -> r.getID().equals(id));
        loadRestaurantsTable();
    }


    // ----- DRIVERS -----

    private void addDriver() {
        String u = JOptionPane.showInputDialog(this, "Username:");
        if (u == null || u.isBlank()) return;

        String p = JOptionPane.showInputDialog(this, "Password:");
        if (p == null) return;

        String n = JOptionPane.showInputDialog(this, "Name:");
        if (n == null) return;

        String e = JOptionPane.showInputDialog(this, "Email:");
        if (e == null) return;

        String ph = JOptionPane.showInputDialog(this, "Phone:");
        if (ph == null) return;

        String a = JOptionPane.showInputDialog(this, "Address:");
        if (a == null) return;

        DeliveryPerson d = new DeliveryPerson(u, p, n, e, ph, a);
        initializer.getDrivers().add(d);
        admin.addDeliveryPerson(d);

        loadDriversTable();
    }

    private void updateDriver() {
        String id = JOptionPane.showInputDialog(this, "Driver ID to update:");
        if (id == null || id.isBlank()) return;

        DeliveryPerson found = null;
        for (DeliveryPerson d : initializer.getDrivers()) {
            if (d.getID().equals(id)) { found = d; break; }
        }
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Driver not found.");
            return;
        }

        String newPhone = JOptionPane.showInputDialog(this, "New phone:", found.getPhone());
        if (newPhone != null && !newPhone.isBlank()) found.setPhone(newPhone);

        String newAddr = JOptionPane.showInputDialog(this, "New address:", found.getAddress());
        if (newAddr != null && !newAddr.isBlank()) found.setAddress(newAddr);

        loadDriversTable();
    }

    private void removeDriver() {
        String id = JOptionPane.showInputDialog(this, "Driver ID to remove:");
        if (id == null || id.isBlank()) return;

        initializer.getDrivers().removeIf(d -> d.getID().equals(id));
        loadDriversTable();
    }


    // ----- VEHICLES -----

    private void addVehicle() {
        String name = JOptionPane.showInputDialog(this, "Vehicle name:");
        if (name == null || name.isBlank()) return;

        String make = JOptionPane.showInputDialog(this, "Make:");
        if (make == null) return;

        String model = JOptionPane.showInputDialog(this, "Model:");
        if (model == null) return;

        String year = JOptionPane.showInputDialog(this, "Year:");
        if (year == null) return;

        String color = JOptionPane.showInputDialog(this, "Color:");
        if (color == null) return;

        String condition = JOptionPane.showInputDialog(this, "Condition:");
        if (condition == null) return;

        String assignedDriver = JOptionPane.showInputDialog(this, "Assigned Driver (optional):");
        if (assignedDriver == null) assignedDriver = "";

        DeliveryVehicle v = new DeliveryVehicle(name, make, model, year, color, condition, assignedDriver);

        loadVehiclesTable();
    }

    private void updateVehicle() {
         String name = JOptionPane.showInputDialog(this, "Vehicle name to update:");
            if (name == null || name.isBlank()) return;

            DeliveryVehicle found = null;
            for (DeliveryVehicle v : initializer.getVehicles()) {
                if (v.getName().equals(name)) {
                    found = v;
                    break;
                }
            }
            if (found == null) {
                JOptionPane.showMessageDialog(this, "Vehicle not found.");
                return;
            }

            String newColor = JOptionPane.showInputDialog(this, "New color (blank = skip):", found.getColor());
            if (newColor != null && !newColor.isBlank()) found.setColor(newColor);

            String newCondition = JOptionPane.showInputDialog(this, "New condition (blank = skip):", found.getCondition());
            if (newCondition != null && !newCondition.isBlank()) found.setCondition(newCondition);

            String newDriver = JOptionPane.showInputDialog(this, "New assigned driver (blank = skip):", found.getAssignedDriver());
            if (newDriver != null && !newDriver.isBlank()) found.setAssignedDriver(newDriver);

        loadVehiclesTable();
    }

    private void removeVehicle() {
        String name = JOptionPane.showInputDialog(this, "Vehicle name to remove:");
        if (name == null || name.isBlank()) return;

        initializer.getVehicles().removeIf(v -> v.getName().equals(name));
        loadVehiclesTable();
    }


    // ----- COUPONS -----

    private void addCoupon() {
        String code = JOptionPane.showInputDialog(this, "Coupon code:");
        if (code == null || code.isBlank()) return;

        double val = Double.parseDouble(
            JOptionPane.showInputDialog(this, "Value (e.g. 0.10 or 5.00):", "0.10")
        );

        boolean valid = Boolean.parseBoolean(
            JOptionPane.showInputDialog(this, "Valid? true/false:", "true")
        );

        DiscountCoupon c = new DiscountCoupon(code, val, valid);
        initializer.getCoupons().add(c);

        loadCouponsTable();
    }

    private void updateCoupon() {
        String code = JOptionPane.showInputDialog(this, "Coupon code to update:");
        if (code == null || code.isBlank()) return;

        DiscountCoupon found = null;
        for (DiscountCoupon c : initializer.getCoupons()) {
            if (c.getCode().equals(code)) { found = c; break; }
        }
        if (found == null) {
            JOptionPane.showMessageDialog(this, "Coupon not found.");
            return;
        }

        String valStr = JOptionPane.showInputDialog(this, "New value:", found.getDiscountAmount());
        if (valStr != null && !valStr.isBlank()) found.setValue(Double.parseDouble(valStr));

        String validStr = JOptionPane.showInputDialog(this, "Set valid? true/false:", found.isValid());
        if (validStr != null && !validStr.isBlank()) found.setValid(Boolean.parseBoolean(validStr));

        loadCouponsTable();
    }

    private void removeCoupon() {
        String code = JOptionPane.showInputDialog(this, "Coupon code to remove:");
        if (code == null || code.isBlank()) return;

        initializer.getCoupons().removeIf(c -> c.getCode().equals(code));
        loadCouponsTable();
    }

    
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnCustomers = new javax.swing.JButton();
        btnRestaurants = new javax.swing.JButton();
        btnDrivers = new javax.swing.JButton();
        btnVehicles = new javax.swing.JButton();
        btnCoupons = new javax.swing.JButton();
        btnOrders = new javax.swing.JButton();
        btnPayments = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 60));
        jPanel1.setLayout(new java.awt.BorderLayout());

        lblTitle.setBackground(new java.awt.Color(255, 153, 51));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Admin - Customers");
        lblTitle.setOpaque(true);
        jPanel1.add(lblTitle, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(45, 45, 45));
        jPanel2.setPreferredSize(new java.awt.Dimension(170, 0));

        btnCustomers.setBackground(new java.awt.Color(45, 45, 45));
        btnCustomers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCustomers.setForeground(new java.awt.Color(255, 255, 255));
        btnCustomers.setText("Customers");
        btnCustomers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnCustomers.setFocusPainted(false);
        btnCustomers.setMaximumSize(new java.awt.Dimension(150, 35));
        btnCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomersActionPerformed(evt);
            }
        });

        btnRestaurants.setBackground(new java.awt.Color(45, 45, 45));
        btnRestaurants.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRestaurants.setForeground(new java.awt.Color(255, 255, 255));
        btnRestaurants.setText("Restaurants");
        btnRestaurants.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnRestaurants.setFocusPainted(false);
        btnRestaurants.setMaximumSize(new java.awt.Dimension(150, 35));

        btnDrivers.setBackground(new java.awt.Color(45, 45, 45));
        btnDrivers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDrivers.setForeground(new java.awt.Color(255, 255, 255));
        btnDrivers.setText("Drivers");
        btnDrivers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnDrivers.setFocusPainted(false);
        btnDrivers.setMaximumSize(new java.awt.Dimension(150, 35));

        btnVehicles.setBackground(new java.awt.Color(45, 45, 45));
        btnVehicles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVehicles.setForeground(new java.awt.Color(255, 255, 255));
        btnVehicles.setText("Vehicles");
        btnVehicles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnVehicles.setFocusPainted(false);
        btnVehicles.setMaximumSize(new java.awt.Dimension(150, 35));

        btnCoupons.setBackground(new java.awt.Color(45, 45, 45));
        btnCoupons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCoupons.setForeground(new java.awt.Color(255, 255, 255));
        btnCoupons.setText("Coupons");
        btnCoupons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnCoupons.setFocusPainted(false);
        btnCoupons.setMaximumSize(new java.awt.Dimension(150, 35));

        btnOrders.setBackground(new java.awt.Color(45, 45, 45));
        btnOrders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrders.setForeground(new java.awt.Color(255, 255, 255));
        btnOrders.setText("Orders");
        btnOrders.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnOrders.setFocusPainted(false);
        btnOrders.setMaximumSize(new java.awt.Dimension(150, 35));

        btnPayments.setBackground(new java.awt.Color(45, 45, 45));
        btnPayments.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPayments.setForeground(new java.awt.Color(255, 255, 255));
        btnPayments.setText("Payments");
        btnPayments.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnPayments.setFocusPainted(false);
        btnPayments.setMaximumSize(new java.awt.Dimension(150, 35));

        btnLogout.setBackground(new java.awt.Color(45, 45, 45));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnLogout.setFocusPainted(false);
        btnLogout.setMaximumSize(new java.awt.Dimension(150, 35));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDrivers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRestaurants, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(btnCustomers, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVehicles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCoupons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPayments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestaurants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDrivers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCoupons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPayments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(204, 204, 204));
        table.setRowHeight(22);
        scrollPane.setViewportView(table);

        getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(30, 30, 30));

        btnAdd.setBackground(new java.awt.Color(255, 153, 51));
        btnAdd.setText("Add");
        jPanel3.add(btnAdd);

        btnUpdate.setBackground(new java.awt.Color(255, 153, 51));
        btnUpdate.setText("Update");
        jPanel3.add(btnUpdate);

        btnRemove.setBackground(new java.awt.Color(255, 153, 51));
        btnRemove.setText("Remove");
        jPanel3.add(btnRemove);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCustomersActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCoupons;
    private javax.swing.JButton btnCustomers;
    private javax.swing.JButton btnDrivers;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnPayments;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRestaurants;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnVehicles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
