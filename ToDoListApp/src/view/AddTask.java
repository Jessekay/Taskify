/*
 * Task management UI for the to_do_list_app_db.
 * Handles adding, updating, and deleting tasks, and category selection.
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Category;
import model.Tasks;
import model.User;
import utils.Session;
import java.awt.event.ActionEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.CategoryInterface;
import service.TasksInterface;

/**
 * AddTask JFrame for managing user tasks.
 */
public class AddTask extends javax.swing.JFrame {
    private List<Category> userCategories = new ArrayList<>();
    private User loggedInUser;

    /**
     * Constructor with logged-in user.
     * @param user The currently logged-in user.
     */
    
    
    public AddTask() {
    }

    public AddTask(User user) {
        this.loggedInUser = user;
        if (user == null || Session.CURRENT_USER_ID <= 0) {
            JOptionPane.showMessageDialog(this, "Invalid user session. Please log in.");
            new Signin().setVisible(true);
            dispose();
            return;
        }
        initComponents();
        loadCategoryDropDown();
        try {
            loadTasksFromSession();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to load tasks: " + e.getMessage());
            e.printStackTrace();
            dispose();
            return;
        }
        userId.setText(String.valueOf(Session.CURRENT_USER_ID));
        userId.setEditable(false);
    }

    /**
     * Default constructor (not supported).
     */
    

    /**
     * Loads categories into the dropdown for the current user.
     */
    private void loadCategoryDropDown() {
        categoryDropDown.removeAllItems();
    try {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
        CategoryInterface categoryService = (CategoryInterface) registry.lookup("category");

        Category categoryFilter = new Category();
        categoryFilter.setUser_id(Session.CURRENT_USER_ID);
        userCategories = categoryService.retrieveAll(categoryFilter);

        for (Category category : userCategories) {
            categoryDropDown.addItem(category.getName());
        }
        categoryDropDown.addItem("Add category");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Failed to load categories: " + e.getMessage());
        e.printStackTrace();
        // Allow the form to remain open
        categoryDropDown.addItem("No categories available");
    }
    }

    /**
     * Loads tasks for the current user into the table.
     */
    private void loadTasksFromSession() {
        try {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
        TasksInterface tasksService = (TasksInterface) registry.lookup("tasks");

        Tasks taskFilter = new Tasks();
        taskFilter.setUser_id(Session.CURRENT_USER_ID);
        List<Tasks> tasks = tasksService.retrieveAll(taskFilter);

        DefaultTableModel model = (DefaultTableModel) tasktable.getModel();
        model.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Tasks task : tasks) {
            model.addRow(new Object[]{
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDue_date() != null ? dateFormat.format(task.getDue_date()) : "",
                task.getPriority(),
                task.getUser_id(),
                task.getCategory_id()
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Failed to load tasks: " + e.getMessage());
        e.printStackTrace();
        // Allow the form to remain open
    }
    }

    /**
     * Initializes the form components.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        taskTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        description = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        taskDueDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        priority = new javax.swing.JTextField();
        addTaskBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        userId = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tasktable = new javax.swing.JTable();
        updateTask = new javax.swing.JButton();
        removeTask = new javax.swing.JButton();
        categoryDropDown = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(12, 91, 160));
        kGradientPanel1.setkStartColor(new java.awt.Color(204, 0, 51));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(1888, 1094));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("ADD TASK");

        taskTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel2.setText("Title");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setText("Description");

        description.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel4.setText("Due Date (yyyy-mm-dd)");

        taskDueDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel6.setText("Priority (high/low/medium)");

        priority.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));

        addTaskBtn.setBackground(new java.awt.Color(12, 91, 160));
        addTaskBtn.setFont(new java.awt.Font("Segoe UI", 1, 14));
        addTaskBtn.setForeground(new java.awt.Color(255, 255, 255));
        addTaskBtn.setText("Add task");
        addTaskBtn.addActionListener(evt -> addTaskBtnActionPerformed(evt));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel7.setText("User ID");

        userId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 204)));

        tasktable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Title", "Description", "Due Date", "Priority", "User ID", "Category ID"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tasktable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tasktableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tasktable);
        tasktable.getColumnModel().getColumn(0).setMinWidth(0);
        tasktable.getColumnModel().getColumn(0).setMaxWidth(0);
        tasktable.getColumnModel().getColumn(0).setWidth(0);

        updateTask.setBackground(new java.awt.Color(12, 91, 160));
        updateTask.setFont(new java.awt.Font("Segoe UI", 1, 14));
        updateTask.setForeground(new java.awt.Color(255, 255, 255));
        updateTask.setText("Edit task");
        updateTask.addActionListener(evt -> updateTaskActionPerformed(evt));

        removeTask.setBackground(new java.awt.Color(12, 91, 160));
        removeTask.setFont(new java.awt.Font("Segoe UI", 1, 14));
        removeTask.setForeground(new java.awt.Color(255, 255, 255));
        removeTask.setText("Remove task");
        removeTask.addActionListener(evt -> removeTaskActionPerformed(evt));

        categoryDropDown.setBackground(new java.awt.Color(255, 255, 255));
        categoryDropDown.setForeground(new java.awt.Color(0, 102, 204));
        categoryDropDown.setModel(new javax.swing.DefaultComboBoxModel<>());
        categoryDropDown.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 102, 204)));
        categoryDropDown.addActionListener(evt -> {
            try {
                categoryDropDownActionPerformed(evt);
            } catch (Exception ex) {
                Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(taskTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(description)
                    .addComponent(taskDueDate)
                    .addComponent(priority)
                    .addComponent(jLabel7)
                    .addComponent(userId)
                    .addComponent(categoryDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(284, 284, 284))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(addTaskBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateTask, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeTask, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addGap(90, 90, 90)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(taskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(taskDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTaskBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateTask, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeTask, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/logout.png")));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Taskify.");

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setFont(new java.awt.Font("Segoe UI", 1, 14));
        home.setForeground(new java.awt.Color(51, 74, 157));
        home.setText("HOME");
        home.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        home.setBorderPainted(false);
        home.addActionListener(evt -> homeActionPerformed(evt));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(1257, 1257, 1257)
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void addTaskBtnActionPerformed(ActionEvent evt) {
        if (taskTitle.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Task title is required.");
            return;
        }
        if (description.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Description is required.");
            return;
        }
        if (taskDueDate.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Due date is required.");
            return;
        }
        if (!isValidDate(taskDueDate.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Invalid due date format! Use YYYY-MM-DD.");
            return;
        }
        String priorityValue = priority.getText().trim().toLowerCase();
        if (!priorityValue.equals("high") && !priorityValue.equals("medium") && !priorityValue.equals("low")) {
            JOptionPane.showMessageDialog(this, "Priority must be 'high', 'medium', or 'low'.");
            return;
        }

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
            TasksInterface tasksService = (TasksInterface) registry.lookup("tasks");

            Tasks taskObj = new Tasks();
            taskObj.setTitle(taskTitle.getText().trim());
            taskObj.setName(taskTitle.getText().trim()); // Set name same as title
            taskObj.setDescription(description.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            taskObj.setDue_date(dateFormat.parse(taskDueDate.getText().trim()));
            taskObj.setPriority(priorityValue);
            taskObj.setUser_id(Session.CURRENT_USER_ID);
            taskObj.setIs_completed(false); // Default to not completed

            String selectedCategory = (String) categoryDropDown.getSelectedItem();
            int categoryId = -1;
            for (Category category : userCategories) {
                if (category.getName().equals(selectedCategory)) {
                    categoryId = category.getId();
                    break;
                }
            }
            if (categoryId == -1) {
                JOptionPane.showMessageDialog(this, "Selected category not found.");
                return;
            }
            taskObj.setCategory_id(categoryId);

            String result = tasksService.registerTasks(taskObj);
            JOptionPane.showMessageDialog(this, result);
            loadTasksFromSession();
            taskTitle.setText("");
            description.setText("");
            taskDueDate.setText("");
            priority.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidDate(String date) {
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!date.matches(dateRegex)) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void tasktableMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tasktable.getModel();
        int selectedRowIndex = tasktable.getSelectedRow();

        taskTitle.setText(model.getValueAt(selectedRowIndex, 1).toString());
        description.setText(model.getValueAt(selectedRowIndex, 2).toString());
        taskDueDate.setText(model.getValueAt(selectedRowIndex, 3).toString());
        priority.setText(model.getValueAt(selectedRowIndex, 4).toString());
        userId.setText(model.getValueAt(selectedRowIndex, 5).toString());

        int categoryId = Integer.parseInt(model.getValueAt(selectedRowIndex, 6).toString());
        for (Category category : userCategories) {
            if (category.getId() == categoryId) {
                categoryDropDown.setSelectedItem(category.getName());
                break;
            }
        }
    }

    private void updateTaskActionPerformed(ActionEvent evt) {
        int selectedRow = tasktable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a task to update.");
            return;
        }

        if (taskTitle.getText().trim().isEmpty() || description.getText().trim().isEmpty() ||
            taskDueDate.getText().trim().isEmpty() || !isValidDate(taskDueDate.getText().trim()) ||
            priority.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled with valid data.");
            return;
        }
        String priorityValue = priority.getText().trim().toLowerCase();
        if (!priorityValue.equals("high") && !priorityValue.equals("medium") && !priorityValue.equals("low")) {
            JOptionPane.showMessageDialog(this, "Priority must be 'high', 'medium', or 'low'.");
            return;
        }

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
            TasksInterface tasksService = (TasksInterface) registry.lookup("tasks");

            DefaultTableModel model = (DefaultTableModel) tasktable.getModel();
            Tasks task = new Tasks();
            task.setId(Integer.parseInt(model.getValueAt(selectedRow, 0).toString()));
            task.setTitle(taskTitle.getText().trim());
            task.setName(taskTitle.getText().trim()); // Set name same as title
            task.setDescription(description.getText().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            task.setDue_date(dateFormat.parse(taskDueDate.getText().trim()));
            task.setPriority(priorityValue);
            task.setUser_id(Integer.parseInt(userId.getText().trim()));
            task.setIs_completed(false); // Maintain current completion status or fetch from DB

            String selectedCategory = (String) categoryDropDown.getSelectedItem();
            int categoryId = -1;
            for (Category category : userCategories) {
                if (category.getName().equals(selectedCategory)) {
                    categoryId = category.getId();
                    break;
                }
            }
            if (categoryId == -1) {
                JOptionPane.showMessageDialog(this, "Selected category not found.");
                return;
            }
            task.setCategory_id(categoryId);

            String result = tasksService.updateTasks(task);
            JOptionPane.showMessageDialog(this, result);
            loadTasksFromSession();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void removeTaskActionPerformed(ActionEvent evt) {
        int selectedRow = tasktable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a task to delete.");
            return;
        }

        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
            TasksInterface tasksService = (TasksInterface) registry.lookup("tasks");

            DefaultTableModel model = (DefaultTableModel) tasktable.getModel();
            Tasks task = new Tasks();
            task.setId(Integer.parseInt(model.getValueAt(selectedRow, 0).toString()));
            task.setUser_id(Session.CURRENT_USER_ID);

            String result = tasksService.deleteTasks(task);
            JOptionPane.showMessageDialog(this, result);
            loadTasksFromSession();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting task: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {
        Session.CURRENT_USER = null;
        Session.CURRENT_USER_ID = 0;
        new Signin().setVisible(true);
        this.dispose();
    }

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {
        Dashboard home = new Dashboard();
        home.setVisible(true);
        this.dispose();
    }

    private void categoryDropDownActionPerformed(ActionEvent evt) {
        if (categoryDropDown.getSelectedItem() != null && categoryDropDown.getSelectedItem().equals("Add category")) {
            String name = JOptionPane.showInputDialog(this, "Enter new category name:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Category name cannot be empty.");
                loadCategoryDropDown();
                return;
            }

            String description = JOptionPane.showInputDialog(this, "Enter category description (optional):");
            if (description == null) {
                description = "";
            }

            try {
                Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1001);
                CategoryInterface categoryService = (CategoryInterface) registry.lookup("category");

                Category category = new Category();
                category.setName(name.trim());
                category.setDescription(description.trim());
                category.setUser_id(Session.CURRENT_USER_ID);

                String result = categoryService.registerCategory(category);
                JOptionPane.showMessageDialog(this, result);
                loadCategoryDropDown();
                categoryDropDown.setSelectedItem(name.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error adding category: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
 
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTask().setVisible(true);
            }
        });
    }
 

    // Variables declaration
    private javax.swing.JButton addTaskBtn;
    private javax.swing.JComboBox<String> categoryDropDown;
    private javax.swing.JTextField description;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JTextField priority;
    private javax.swing.JButton removeTask;
    private javax.swing.JTextField taskDueDate;
    private javax.swing.JTextField taskTitle;
    private javax.swing.JTable tasktable;
    private javax.swing.JButton updateTask;
    private javax.swing.JTextField userId;
}