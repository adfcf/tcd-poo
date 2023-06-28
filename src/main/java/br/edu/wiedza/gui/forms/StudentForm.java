/*
 * The MIT License
 *
 * Copyright 2023 maicon.
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
import br.edu.wiedza.db.dao.StudentDao;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Student;
import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import br.edu.wiedza.gui.LoginFrame;
import br.edu.wiedza.gui.Util;
import java.time.LocalDate;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author maicon
 */
public class StudentForm extends javax.swing.JFrame {

    private final Optional<Student> selectedStudent;
    
    private final boolean readOnly;
    /**
     * Creates new form StudentForm
     * @param s
     * @param readOnly
     */
    public StudentForm(Student s, boolean readOnly) {
        initComponents();
        
        setLocationRelativeTo(null);
        setFocusable(true);
        
        selectedStudent = Optional.ofNullable(s);
        selectedStudent.ifPresent(ss -> {
            if(ss.getId().isEmpty()) throw new IllegalArgumentException("The given student must have a valid database ID");
        });
        
        this.readOnly = readOnly && s != null;
        
        setUp();
    }
    
    private void setUp(){
        
        if(readOnly){
            
            txtName.setEditable(false);
            txtUserName.setEditable(false);
            txtPassword.setEditable(false);
            
            txtStreet1.setEditable(false);
            txtDistrict1.setEditable(false);
            txtNumber1.setEditable(false);
            txtCep.setEditable(false);
            
            txtCpf.setEditable(false);
            
            spinnerDay.setEnabled(false);
            spinnerMonth.setEnabled(false);
            spinnerYear.setEnabled(false);
            
            txtPrimaryPhone.setEditable(false);
            txtSecondaryPhone.setEditable(false);
        }
        
        if(selectedStudent.isPresent()){
            
            final var s = selectedStudent.get();
            
            txtName.setText(s.getName());
            
            final var c = s.getCredentials().orElse(new Credentials(null, "N/A", "N/A"));
            
            txtUserName.setText(s.getCredentials().get().getUserName());
            
            txtStreet1.setText(s.getAddress().getStreet());
            txtDistrict1.setText(s.getAddress().getDistrict());
            txtCep1.setText(String.valueOf(s.getAddress().getCep()));
            txtNumber1.setText(s.getAddress().getNumber());
            
            txtCpf.setText(String.valueOf(s.getCpf().asLong()));
            
            spinnerDay.setValue(Integer.valueOf(s.getDateOfBirth().getDayOfMonth()));
            spinnerMonth.setValue(Integer.valueOf(s.getDateOfBirth().getMonthValue()));
            spinnerYear.setValue(Integer.valueOf(s.getDateOfBirth().getYear()));
            
            txtPrimaryPhone.setText(String.valueOf(s.getPrimaryPhoneNumber()));
            txtSecondaryPhone.setText(String.valueOf(s.getSecondaryPhoneNumber().get().longValue()));
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

        txtDistrict = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNumber = new javax.swing.JTextField();
        txtCep = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        spinnerDay = new javax.swing.JSpinner();
        spinnerMonth = new javax.swing.JSpinner();
        spinnerYear = new javax.swing.JSpinner();
        txtCpf = new javax.swing.JFormattedTextField();
        lblName = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtSecondaryPhone = new javax.swing.JFormattedTextField();
        txtUserName = new javax.swing.JTextField();
        lblPrimaryPhone = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        txtPrimaryPhone = new javax.swing.JFormattedTextField();
        txtPassword = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblBirthDate = new javax.swing.JLabel();
        txtDistrict1 = new javax.swing.JTextField();
        lblDistrict = new javax.swing.JLabel();
        lblCep = new javax.swing.JLabel();
        lblNumber = new javax.swing.JLabel();
        txtNumber1 = new javax.swing.JTextField();
        txtCep1 = new javax.swing.JFormattedTextField();
        lblStreet = new javax.swing.JLabel();
        txtStreet1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnOk = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        txtDistrict.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Bairro:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("CEP:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Número:");

        txtNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        try {
            txtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Logradouro:");

        txtStreet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        spinnerDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        spinnerMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        spinnerYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spinnerYear.setModel(new javax.swing.SpinnerNumberModel(2005, 1923, 2005, 1));

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setText("Nome:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Telefone principal:");

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        try {
            txtSecondaryPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSecondaryPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setToolTipText("");

        lblPrimaryPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrimaryPhone.setText("Telefone secundário:");

        lblUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUserName.setText("Usuário:");
        lblUserName.setToolTipText("");

        try {
            txtPrimaryPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPrimaryPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPassword.setText("***********");
        txtPassword.setToolTipText("");
        txtPassword.setEnabled(false);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPassword.setText("Senha:");
        lblPassword.setToolTipText("");

        lblCpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCpf.setText("CPF:");

        lblBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBirthDate.setText("Data de Nascimento:");

        txtDistrict1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDistrict.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDistrict.setText("Bairro:");

        lblCep.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCep.setText("CEP:");

        lblNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumber.setText("Número:");

        txtNumber1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        try {
            txtCep1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCep1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblStreet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblStreet.setText("Logradouro:");

        txtStreet1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnOk.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        lblStatus.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblStreet)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDistrict1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCep1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblCep, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(26, 26, 26)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBirthDate)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPrimaryPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSecondaryPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrimaryPhone))))
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lblName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUserName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lblStreet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStreet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDistrict)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDistrict1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCep)
                                    .addComponent(lblNumber))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCep1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCpf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBirthDate)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPrimaryPhone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSecondaryPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrimaryPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addGap(37, 37, 37)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(jLabel1)
                    .addComponent(lblStatus))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Student extractNewStudent(){
        
        final var name = txtName.getText();
        
        if (!Validator.ofName(name)) {
            invalidFieldMessage("O nome informado não é válido.");
            return null;
        }
        
        final var userName = txtUserName.getText();
            
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
            
        final var password = Util.removeSpecialCharacters(txtCpf.getText());
        
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
            
        final var street = txtStreet1.getText();
        final var district = txtDistrict1.getText();
        final var cep = txtCep1.getText();
        final var number = txtNumber1.getText();

        if (street.isEmpty() || district.isEmpty() || cep.isEmpty() || number.isEmpty()) {
            invalidFieldMessage("Verifique o endereço informado. Os campos não podem ser vazios.");
            return null;
        }
        final var address = new Address(Integer.parseInt(Util.removeSpecialCharacters(cep)), number, street, district);
        
        final var c = new Credentials(null, userName, password);
        
        return new Student(null,
                c,
                name,
                dateOfBirth,
                cpf,
                address,
                primaryPhone,
                0L,
                true);
    }
    
    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        java.awt.EventQueue.invokeLater(() -> {
            final var text = txtName.getText().toLowerCase().trim().replace(" ", ".");
            txtUserName.setText(text);
        });
    }//GEN-LAST:event_txtNameKeyPressed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        
        if(readOnly){
            dispose();
            return;
        }
        
        Student s = extractNewStudent();
        
        if(s == null){
            return;
        }
        
        btnOk.setEnabled(false);
        
        java.awt.EventQueue.invokeLater(() -> {
            
            String message = "Dados de aluno atualizados com sucesso.";
        
            if (selectedStudent.isPresent()) { // if it already exists, it's an update.
                s.setId(selectedStudent.get().getId().get());
                s.getCredentials().get().setId(selectedStudent.get().getCredentials().get().getId().get());
            } else {
                s.getCredentials().get().setId(CredentialsDao.getInstance().createOrUpdate(s.getCredentials().get()).get());
                message = "Aluno cadastrado com sucesso.";
            }
           
            StudentDao.getInstance().createOrUpdate(s);
        
            JOptionPane.showMessageDialog(this, message);
            dispose();
        });
    }//GEN-LAST:event_btnOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblBirthDate;
    private javax.swing.JLabel lblCep;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDistrict;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPrimaryPhone;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStreet;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JSpinner spinnerDay;
    private javax.swing.JSpinner spinnerMonth;
    private javax.swing.JSpinner spinnerYear;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JFormattedTextField txtCep1;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtDistrict;
    private javax.swing.JTextField txtDistrict1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtNumber1;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JFormattedTextField txtPrimaryPhone;
    private javax.swing.JFormattedTextField txtSecondaryPhone;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtStreet1;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
