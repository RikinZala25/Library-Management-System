/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package JFrame;

import java.sql.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author rikin
 */
public class issueBook extends javax.swing.JFrame {

    static String t_name1 = "book_details";
    static String t_name2 = "student_details";
    static String t_name3 = "issue_book_details";

    /**
     * Creates new form issueBook
     */
    public issueBook() {
        initComponents();
    }

    // to fetch the book details from the database
    public void getBookDetails() {
        int bookId = Integer.parseInt(txt_bookId.getText());

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DBConnection.getConnection();

            String sql = "select * from " + t_name1 + " where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            } else {
                lbl_bookError.setText("Invalid Book ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // to fetch the student details from the database
    public void getStudentDetails() {
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DBConnection.getConnection();

            String sql = "select * from " + t_name2 + " where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, studentId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            } else {
                lbl_studentError.setText("Invalid Student ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//1
    static int id = 1;

    //insert issue book details to database
    public boolean issueBook() {
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId.getText());
        String bookName = lbl_bookName.getText();
        String studentName = lbl_studentName.getText();

        Date uIssueDate = date_issueDate.getDatoFecha();
        Date uDueDate = date_dueDate.getDatoFecha();

        Long l1 = uIssueDate.getTime();
        Long l2 = uDueDate.getTime();

        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DBConnection.getConnection();

            String sql = "insert into " + t_name3 + " values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, bookId);
            pst.setString(3, bookName);
            pst.setInt(4, studentId);
            pst.setString(5, studentName);
            pst.setDate(6, sIssueDate);
            pst.setDate(7, sDueDate);
            pst.setString(8, "pending");

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                isIssued = true;
                id++;
            } else {
                isIssued = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }

    // updating book count
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DBConnection.getConnection();

            String sql = "update " + t_name1 + " set quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book count updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Book count update failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //checking weather book already allocated or not
    public boolean isAlreadyIssued() {
        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DBConnection.getConnection();

            String sql = "select * from " + t_name3 + " where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlreadyIssued;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_branch = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle4 = new necesario.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel2.setText("Student Details");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 290, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 320, 5));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Branch:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Name:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Course:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Student ID:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 200, 30));

        lbl_course.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 200, 30));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 200, 30));

        lbl_studentId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 200, 30));

        lbl_studentError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 255, 0));
        lbl_studentError.setText("~");
        jPanel2.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 260, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 420, 700));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(153, 153, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 50));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText(" Book Details");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 260, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 320, 5));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 255, 0));
        lbl_bookError.setText("~");
        jPanel5.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 260, -1));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 200, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Book ID:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 200, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 200, 30));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 200, 30));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Quantity: ");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 700));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel4.setText("Student Details");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 290, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel1.setText("Issue Books");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, 200, 50));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 170, 320, 5));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_bookId.setPlaceholder("Enter Book ID Here");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("Book ID:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Issue Date:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, -1, -1));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentId.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student ID Here");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 310, -1, -1));

        date_issueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_issueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_issueDate.setFuente(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        date_issueDate.setPlaceholder("Select Issue Date");
        jPanel1.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 240, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("Student ID:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(java.awt.Color.red);
        jLabel14.setText("Due Date:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 490, -1, -1));

        date_dueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_dueDate.setColorForeground(new java.awt.Color(255, 51, 51));
        date_dueDate.setFuente(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        date_dueDate.setPlaceholder("Select Due Date");
        jPanel1.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 480, 240, -1));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 153, 153));
        rSMaterialButtonCircle4.setText("Issue Book");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 570, 120, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        homePage home = new homePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        // TODO add your handling code here:
        if (!txt_bookId.getText().equals("")) {
            getBookDetails();
        }
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        // TODO add your handling code here:
        if (!txt_studentId.getText().equals("")) {
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        // TODO add your handling code here:

        if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book Not Available");
        } else {
            if (isAlreadyIssued() == false) {
                if (issueBook() == true) {
                    JOptionPane.showMessageDialog(this, "Book Issued");
                    updateBookCount();
                } else {
                    JOptionPane.showMessageDialog(this, "Book Issue Failed");
                }
            } else {
                JOptionPane.showMessageDialog(this, "This student has already has this book.");
            }
        }

    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(issueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(issueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(issueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(issueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new issueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
