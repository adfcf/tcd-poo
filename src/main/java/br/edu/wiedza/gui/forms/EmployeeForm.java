/*
 * The MIT License
 *
 * Copyright 2023 adfcf.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.edu.wiedza.gui.forms;

import br.edu.wiedza.db.dao.CredentialsDao;
import br.edu.wiedza.db.dao.EmployeeDao;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import br.edu.wiedza.gui.LoginFrame;
import br.edu.wiedza.gui.Util;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author adfcf
 */
public class EmployeeForm extends javax.swing.JFrame {

    private final Optional<Employee> selectedEmployee;
    
    private final boolean readOnly;
    
    /**
     * Creates new form EmployeeForm
     * @param e if e is null, it means this form should be able to insert a new employee into the database.
     * Otherwise, it'll update the given employee if necessary.
     * @param readOnly
     */
    public EmployeeForm(Employee e, boolean readOnly) {
        
        initComponents();
        
        setLocationRelativeTo(null);
        setFocusable(true);
        
        selectedEmployee = Optional.ofNullable(e);
        selectedEmployee.ifPresent(se -> {
            if (se.getId().isEmpty()) throw new IllegalArgumentException("The given employee must have a valid database ID.");
        });
        
        this.readOnly = readOnly && e != null;
        
        setUp();
        
    }

    private void setUp() {
        
        if (readOnly) {
            
            txtName.setEditable(false);
            txtUserName.setEditable(false);
            txtCpf.setEditable(false);
            
            spinnerDay.setEnabled(false);
            spinnerMonth.setEnabled(false);
            spinnerYear.setEnabled(false);
            
            txtPrimaryPhone.setEditable(false);
            txtSecondaryPhone.setEditable(false);
            
            txtStreet.setEditable(false);
            txtDistrict.setEditable(false);
            txtCep.setEditable(false);
            txtNumber.setEditable(false);
            
            comboSector.setEnabled(false);
            txtRole.setEditable(false);
            txtSalary.setEditable(false);
            txtHours.setEditable(false);
           
        }
        
        // EDIT/VIEW EMPLOYEE
        if (selectedEmployee.isPresent()) {
            
            final var e = selectedEmployee.get();
            
            txtName.setText(e.getName());
            
            final var c = e.getCredentials().orElse(new Credentials(null, "N/A", "N/A"));
            
            txtUserName.setText(c.getUserName());
            txtPassword.setText(c.getPassword());
            
            txtCpf.setText(String.valueOf(e.getCpf().asLong()));
            
            spinnerDay.setValue(e.getDateOfBirth().getDayOfMonth());
            spinnerMonth.setValue(e.getDateOfBirth().getMonthValue());
            spinnerYear.setValue(e.getDateOfBirth().getYear());
            
            txtPrimaryPhone.setText(String.valueOf(e.getPrimaryPhoneNumber()));
            txtSecondaryPhone.setText(String.valueOf(e.getSecondaryPhoneNumber().orElse(0L)));
            
            txtStreet.setText(e.getAddress().getStreet());
            txtDistrict.setText(e.getAddress().getDistrict());
            txtCep.setText(String.valueOf(e.getAddress().getCep()));
            txtNumber.setText(e.getAddress().getNumber());
            
            comboSector.getModel().setSelectedItem(e.getSector().getName());
            txtRole.setText(e.getRole());
            txtSalary.setText(String.valueOf(e.getSalary()));
            txtHours.setText(String.valueOf(e.getWeeklyWorkHours()));
            
            
        } else { // NEW
            
            
            
        }
        
    }
    
    private void invalidFieldMessage(String s) {
        lblStatus.setForeground(LoginFrame.DEFAULT_RED);
        lblStatus.setText(s);      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField9 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtName = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        txtDistrict = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNumber = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        comboSector = new javax.swing.JComboBox<>();
        txtRole = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSecondaryPhone = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrimaryPhone = new javax.swing.JFormattedTextField();
        txtHours = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        spinnerDay = new javax.swing.JSpinner();
        spinnerMonth = new javax.swing.JSpinner();
        spinnerYear = new javax.swing.JSpinner();
        txtSalary = new javax.swing.JTextField();

        jTextField9.setText("jTextField9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escola Wiedza - Formulário de Funcionário");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.setNextFocusableComponent(txtUserName);
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setToolTipText("");
        txtUserName.setNextFocusableComponent(txtCpf);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Usuário:");
        jLabel2.setToolTipText("");

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setToolTipText("");
        txtPassword.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Senha:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("CPF:");

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCep.setNextFocusableComponent(txtNumber);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Data de Nascimento:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Logradouro:");

        txtStreet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtStreet.setNextFocusableComponent(txtDistrict);

        txtDistrict.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDistrict.setNextFocusableComponent(txtCep);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Bairro:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("CEP:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCpf.setNextFocusableComponent(spinnerDay);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Número:");

        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumber.setNextFocusableComponent(comboSector);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Setor:");

        comboSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        comboSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ensino", "Coordenação", "Secretaria", "Direção", "Outro" }));
        comboSector.setNextFocusableComponent(txtRole);

        txtRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRole.setNextFocusableComponent(txtSalary);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Cargo:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Telefone principal:");

        try {
            txtSecondaryPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSecondaryPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSecondaryPhone.setNextFocusableComponent(txtStreet);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Telefone secundário:");

        try {
            txtPrimaryPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPrimaryPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPrimaryPhone.setNextFocusableComponent(txtSecondaryPhone);

        txtHours.setColumns(5);
        txtHours.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHours.setText("40");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Horas de trabalho (semanais):");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Salário:");

        btnOk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStatus.setText(" ");

        spinnerDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        spinnerMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        spinnerYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerYear.setModel(new javax.swing.SpinnerNumberModel(2005, 1923, 2005, 1));
        spinnerYear.setNextFocusableComponent(txtStreet);

        txtSalary.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSalary.setText("1200");
        txtSalary.setNextFocusableComponent(txtHours);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCpf, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSecondaryPhone, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPrimaryPhone, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStreet)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(comboSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2)
                            .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtSalary))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrimaryPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSecondaryPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private Employee extractNewEmployee() {
        
        final var name = txtName.getText();
        
        if (!Validator.ofName(name)) {
            invalidFieldMessage("O nome informado não é válido.");
            return null;
        }
        
        final var userName = txtUserName.getText();
        final var password = txtPassword.getText();
            
        if (!Validator.ofUserName(userName)) {
            invalidFieldMessage("O nome de usuário informado não é válido.");
            return null;
        }
            
        if (Util.removeSpecialCharacters(txtCpf.getText()).isEmpty()) {
            invalidFieldMessage("O CPF informado não é válido.");
            return null;
        }
        
        Cpf cpf = new Cpf(0);
        try {
            cpf = new Cpf(Long.parseLong(Util.removeSpecialCharacters(txtCpf.getText())));
        } catch (Exception e) {
        }
            
        final var dateOfBirth = LocalDate.of(
                (Integer) spinnerYear.getValue(), 
                (Integer) spinnerMonth.getValue(), 
                (Integer) spinnerDay.getValue());
        
        if (Util.removeSpecialCharacters(txtPrimaryPhone.getText()).isEmpty()) {
            invalidFieldMessage("É necessário preencher ao menos o telefone principal.");
            return null;
        }
        final var primaryPhone = Long.parseLong(Util.removeSpecialCharacters(txtPrimaryPhone.getText()));
        
       // final var secondaryPhone = Util.removeSpecialCharacters(txtPrimaryPhone.getText()).isEmpty() ? 0L : Long.parseLong(Util.removeSpecialCharacters(txtSecondaryPhone.getText()));
            
        final var street = txtStreet.getText();
        final var district = txtDistrict.getText();
        final var cep = txtCep.getText();
        final var number = txtNumber.getText();

        if (street.isEmpty() || district.isEmpty() || cep.isEmpty() || number.isEmpty()) {
            invalidFieldMessage("Verifique o endereço informado. Os campos não podem ser vazios.");
            return null;
        }
        final var address = new Address(Integer.parseInt(Util.removeSpecialCharacters(cep)), number, street, district);
        
        final var sector = Employee.Sector.fromString(comboSector.getModel().getSelectedItem().toString());
        
        final var role = txtRole.getText();
        if (role.isEmpty()) {
            invalidFieldMessage("O cargo do funcionário deve ser informado.");
            return null;
        }

        if (!Validator.ofPositiveFloat(txtSalary.getText())) {
            invalidFieldMessage("O salário informado não é válido.");
            return null;
        }
        final var salary = Float.parseFloat(txtSalary.getText());
          
        if (!Validator.ofPositiveInteger(txtHours.getText())) {
            invalidFieldMessage("A quantidade de horas não é válida.");
            return null;
        }
        final var hours = Integer.parseInt(txtHours.getText());
        
        final var credentials = new Credentials(null, userName, password);
        
        return new Employee(
                null, 
                credentials,
                name,
                dateOfBirth,
                cpf,
                address,
                primaryPhone,
                0L,     
                sector,
                role,
                salary,
                hours
        );
       
    }
    
    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        
        if (readOnly) {
            dispose();
            return;
        }
        
        Employee e = extractNewEmployee();
        
        // Validation failed
        if (e == null) {
            return;
        }
        
        btnOk.setEnabled(false);
        
        java.awt.EventQueue.invokeLater(() -> {
            
            String message = "Dados de funcionário atualizados com sucesso.";
        
            if (selectedEmployee.isPresent()) { // if it already exists, it's an update.
                e.setId(selectedEmployee.get().getId().get());
                e.getCredentials().get().setId(selectedEmployee.get().getCredentials().get().getId().get());
            } else {
                e.getCredentials().get().setId(CredentialsDao.getInstance().createOrUpdate(e.getCredentials().get()).get());
                message = "Funcionário cadastrado com sucesso.";
            }
           
            EmployeeDao.getInstance().createOrUpdate(e);
        
            JOptionPane.showMessageDialog(this, message);
            dispose();
            
        });
        
    }//GEN-LAST:event_btnOkActionPerformed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        java.awt.EventQueue.invokeLater(() -> {
            final var text = txtName.getText().toLowerCase().trim().replace(" ", ".");
            txtUserName.setText(text);
        });
    }//GEN-LAST:event_txtNameKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> comboSector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JSpinner spinnerDay;
    private javax.swing.JSpinner spinnerMonth;
    private javax.swing.JSpinner spinnerYear;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtDistrict;
    private javax.swing.JTextField txtHours;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JFormattedTextField txtPrimaryPhone;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JFormattedTextField txtSecondaryPhone;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}

