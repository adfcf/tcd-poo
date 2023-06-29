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
package br.edu.wiedza.gui;

import br.edu.wiedza.db.Database;
import br.edu.wiedza.db.dao.OccurrenceDao;
import br.edu.wiedza.db.dao.StudentDao;
import br.edu.wiedza.entities.Occurrence;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.entities.persons.Student;
import br.edu.wiedza.gui.forms.OccurrenceForm;
import br.edu.wiedza.gui.forms.StudentForm;
import br.edu.wiedza.gui.forms.Validator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maicon
 */
public class SecretariatFrame extends javax.swing.JFrame {

    private List<Occurrence> allOcurrences;
    private List<Student> allStudents;

    private final Employee currentUser;

    /**
     * Creates new form SecretariatFrame
     */
    public SecretariatFrame(Employee currentUser) {
        initComponents();
        setLocationRelativeTo(null);

        this.currentUser = currentUser;
        this.lblName.setText(currentUser.getName());

        combReports.setModel(getReportsListModel());

        updateEntities();
    }

    private DefaultComboBoxModel<String> getReportsListModel() {

        return new DefaultComboBoxModel<String>(new String[]{"Ocorrências", "Boletim"});
    }

    private void updateEntities() {

        listStudents.setModel(new DefaultListModel<>());
        listOccurrences.setModel(new DefaultListModel<>());

        allStudents = StudentDao.getInstance().retrieveAll();
        allOcurrences = OccurrenceDao.getInstance().retrieveAll();

        final var s1 = new DefaultListModel<String>();
        for (int i = 0; i < allStudents.size(); ++i) {
            s1.add(i, allStudents.get(i).getName());
        }

        final var s2 = new DefaultListModel<String>();
        for (int i = 0; i < allOcurrences.size(); ++i) {
            s2.add(i, allOcurrences.get(i).getStudent().getName() + " - " + allOcurrences.get(i).getDate().toString());
        }

        listStudents.setModel(s1);
        listOccurrences.setModel(s2);
    }

    private Student getSelectedStudent() {

        final var name = listStudents.getSelectedValue();

        if (name == null) {
            return null;
        }

        return allStudents.stream().filter(s -> s.getName().equals(name)).findAny().get();
    }

    private Occurrence getSelectedOccurrence() {

        final var occurrenceTitle = listOccurrences.getSelectedValue();

        if (occurrenceTitle == null) {
            return null;
        }

        final var studentName = occurrenceTitle.substring(0, occurrenceTitle.indexOf('-') - 1);
        final var dateString = occurrenceTitle.substring(occurrenceTitle.indexOf('-') + 2, occurrenceTitle.length());
        final var date = LocalDate.parse(dateString);
        Occurrence occurrence = null;

        for (Occurrence o : allOcurrences) {
            if (o.getStudent().getName().equals(studentName) && o.getDate().equals(date)) {
                occurrence = o;
                break;
            }
        }

        return occurrence;
    }
    
    private void makeReports(String reportName){
        
        try(InputStream in = getClass().getResourceAsStream(reportName)){
        
            JasperPrint jasperPrint = JasperFillManager.fillReport(in, null, Database.getConnection());
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            
            JDialog dialog = new JDialog(this);
            dialog.setContentPane(jasperViewer.getContentPane());
            dialog.setSize(jasperViewer.getSize());
            dialog.setTitle(reportName);
            dialog.setModal(true);
            dialog.setVisible(true);
            
        }catch(IOException | JRException ex){
            Logger.getLogger(SecretariatFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        combReports = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblReports = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnViewStudent = new javax.swing.JButton();
        btnEditStudent = new javax.swing.JButton();
        btnNewStudent = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listStudents = new javax.swing.JList<>();
        txtSearchStudent = new javax.swing.JTextField();
        btnGenerateGradesReport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listOccurrences = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        txtSearchOccurrence = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblStudentName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnNewOccurrence = new javax.swing.JButton();
        btnViewOccurrence = new javax.swing.JButton();
        btnEditOccurrence = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();

        combReports.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        combReports.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setFocusable(false);

        lblReports.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        lblReports.setText("Relatório a ser gerado:");

        btnGenerate.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnGenerate.setText("Gerar relatório");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lblReports)
                .addContainerGap(227, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerate)
                .addGap(77, 77, 77))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblReports)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(btnGenerate)
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Documentação", jPanel4);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnViewStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnViewStudent.setText("Visualizar");
        btnViewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStudentActionPerformed(evt);
            }
        });

        btnEditStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditStudent.setText("Editar");
        btnEditStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStudentActionPerformed(evt);
            }
        });

        btnNewStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNewStudent.setText("Matricular");
        btnNewStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewStudentActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Pesquisar por nome: ");

        listStudents.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listStudents.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listStudents.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listStudentsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listStudents);

        txtSearchStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchStudentKeyReleased(evt);
            }
        });

        btnGenerateGradesReport.setText("jButton1");
        btnGenerateGradesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateGradesReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(txtSearchStudent))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNewStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerateGradesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnNewStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditStudent)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewStudent)
                        .addGap(28, 28, 28)
                        .addComponent(btnGenerateGradesReport)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alunos", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        listOccurrences.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listOccurrences.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listOccurrences.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listOccurrencesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(listOccurrences);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Matrícula do aluno:");

        txtSearchOccurrence.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblStudentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStudentName.setText("-");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nome:");

        btnNewOccurrence.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnNewOccurrence.setText("Lançar");
        btnNewOccurrence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewOccurrenceActionPerformed(evt);
            }
        });

        btnViewOccurrence.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnViewOccurrence.setText("Visualizar");
        btnViewOccurrence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewOccurrenceActionPerformed(evt);
            }
        });

        btnEditOccurrence.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnEditOccurrence.setText("Editar");
        btnEditOccurrence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditOccurrenceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStudentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchOccurrence, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNewOccurrence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewOccurrence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditOccurrence, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchOccurrence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnNewOccurrence)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewOccurrence)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditOccurrence)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ocorrências", jPanel2);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel8.setText("Bem vindo:");

        lblName.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        lblName.setText("-");

        btnExit.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(jLabel8)
                    .addComponent(lblName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        lf.start();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnEditOccurrenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditOccurrenceActionPerformed

        final var selectedOccurrence = getSelectedOccurrence();

        if (selectedOccurrence != null) {
            var form = new OccurrenceForm(selectedOccurrence, allStudents, false);
            form.setVisible(true);
            form.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateEntities();
                }
            });
        } else {
            Util.disableAll(btnViewOccurrence, btnEditOccurrence);
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma ocorrência para editá-la ou visualizá-la.");
        }
    }//GEN-LAST:event_btnEditOccurrenceActionPerformed

    private void btnViewOccurrenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOccurrenceActionPerformed
        final var selectedOccurrence = getSelectedOccurrence();

        if (selectedOccurrence != null) {
            new OccurrenceForm(selectedOccurrence, allStudents, true).setVisible(true);
        } else {
            Util.disableAll(btnViewOccurrence, btnEditOccurrence);
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma ocorrência para editá-la ou visualizá-la.");
        }
    }//GEN-LAST:event_btnViewOccurrenceActionPerformed

    private void btnNewOccurrenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewOccurrenceActionPerformed
        var form = new OccurrenceForm(null, allStudents, false);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                updateEntities();
            }
        });
    }//GEN-LAST:event_btnNewOccurrenceActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        if (!Validator.ofPositiveInteger(txtSearchOccurrence.getText())) {
            lblStudentName.setText("Digite uma matrícula válida para buscar.");
        }

        Integer enrollmentSearched = Integer.valueOf(txtSearchOccurrence.getText());

        var searchedOccurrences = new DefaultListModel<String>();

        allOcurrences.stream().forEach(o -> {
            Integer studentEnrollment = o.getStudent().getId().get();
            if (studentEnrollment.equals(enrollmentSearched)) {
                searchedOccurrences.addElement(o.getStudent().getName() + " - " + o.getDate().toString());
            }
        });

        if (searchedOccurrences.isEmpty()) {
            lblStudentName.setText("Nenhum aluno possui a matrícula digitada.");
            return;
        }

        final var listEntry = searchedOccurrences.firstElement();

        lblStudentName.setText(listEntry.substring(0, listEntry.indexOf('-')));
        listOccurrences.setModel(searchedOccurrences);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void listOccurrencesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listOccurrencesValueChanged

        Util.enableAll(btnViewOccurrence, btnEditOccurrence);
    }//GEN-LAST:event_listOccurrencesValueChanged

    private void btnGenerateGradesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateGradesReportActionPerformed

        makeReports("/Grades.jasper");
    }//GEN-LAST:event_btnGenerateGradesReportActionPerformed

    private void txtSearchStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentKeyReleased

        final var searchTerm = txtSearchStudent.getText();

        var searchedStudents = new DefaultListModel<String>();

        allStudents.stream().forEach(s -> {
            String studentName = s.getName().toLowerCase();
            if (studentName.contains(searchTerm.toLowerCase())) {
                searchedStudents.addElement(s.getName());
            }
        });

        listStudents.setModel(searchedStudents);
    }//GEN-LAST:event_txtSearchStudentKeyReleased

    private void listStudentsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listStudentsValueChanged

        Util.enableAll(btnViewStudent, btnEditStudent);
    }//GEN-LAST:event_listStudentsValueChanged

    private void btnNewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewStudentActionPerformed

        var form = new StudentForm(null, false);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                updateEntities();
            }
        });
    }//GEN-LAST:event_btnNewStudentActionPerformed

    private void btnEditStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStudentActionPerformed

        final Student selectedStudent = getSelectedStudent();

        if (selectedStudent != null) {

            var form = new StudentForm(selectedStudent, false);
            form.setVisible(true);
            form.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateEntities();
                }
            });
        } else {
            Util.disableAll(btnViewStudent, btnEditStudent);
            JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno para editá-lo ou visualizá-lo.");
        }
    }//GEN-LAST:event_btnEditStudentActionPerformed

    private void btnViewStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStudentActionPerformed

        final Student selectedStudent = getSelectedStudent();

        if (selectedStudent != null) {
            new StudentForm(selectedStudent, true).setVisible(true);
        } else {
            Util.disableAll(btnViewStudent, btnEditStudent);
            JOptionPane.showMessageDialog(this, "Por favor, selecione um aluno para editá-lo ou visualizá-lo.");
        }
    }//GEN-LAST:event_btnViewStudentActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed

        final String selectedReport = combReports.getItemAt(combReports.getSelectedIndex());

        makeReports(selectedReport);
    }//GEN-LAST:event_btnGenerateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditOccurrence;
    private javax.swing.JButton btnEditStudent;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnGenerateGradesReport;
    private javax.swing.JButton btnNewOccurrence;
    private javax.swing.JButton btnNewStudent;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewOccurrence;
    private javax.swing.JButton btnViewStudent;
    private javax.swing.JComboBox<String> combReports;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblReports;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JList<String> listOccurrences;
    private javax.swing.JList<String> listStudents;
    private javax.swing.JTextField txtSearchOccurrence;
    private javax.swing.JTextField txtSearchStudent;
    // End of variables declaration//GEN-END:variables
}
