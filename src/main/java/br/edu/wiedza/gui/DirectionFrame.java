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
package br.edu.wiedza.gui;

import br.edu.wiedza.db.Database;
import br.edu.wiedza.gui.forms.LocationForm;
import br.edu.wiedza.db.dao.EmployeeDao;
import br.edu.wiedza.db.dao.LocationDao;
import br.edu.wiedza.entities.Location;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.gui.forms.EmployeeForm;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author adfcf
 */
public class DirectionFrame extends javax.swing.JFrame {

    private final Employee currentUser;
    
    private List<Location> allLocations;
    private List<Employee> allEmployees;

    private double costs;
    
    // Temporary
    // TODO calculate the real revenue somehow
    private double revenue = 100000;
    
    /**
     * Creates new form DirectionFrame
     * @param currentUser
     */
    public DirectionFrame(Employee currentUser) {
        
        initComponents();
        setLocationRelativeTo(null);

        this.currentUser = currentUser;
        this.lblName.setText(currentUser.getName());
     
        updateEntities();
        
        this.requestFocus();
        
    }
    
    private void updateEntities() {
        btnReportEmployee.setEnabled(true);
        btnReportLocations.setEnabled(true);
        listLocations.setModel(new DefaultListModel<>());
        listEmployees.setModel(new DefaultListModel<>());
        
        allLocations = LocationDao.getInstance().retrieveAll();
        allEmployees = EmployeeDao.getInstance().retrieveAll();
        
        final var s1 = new DefaultListModel<String>();
        for (int i = 0; i < allLocations.size(); ++i) {
            s1.add(i, allLocations.get(i).getName());
        }
        
        final var s2 = new DefaultListModel<String>();
        for (int i = 0; i < allEmployees.size(); ++i) {
            s2.add(i, allEmployees.get(i).getName());
        }
        
        listLocations.setModel(s1);
        listEmployees.setModel(s2);
        
        updateFinance();
        
    } 
    
    private void updateFinance() {
        
        costs = 0;
   
        if (checkLocationCosts.isSelected()) {
            costs += locationCosts();
        }
        
        if (checkTeachingCost.isSelected()) {
            costs += teachingCosts();
        }
        
        if (checkDirectionCost.isSelected()) {
            costs += directionCosts();
        }
                
        if (checkCoordenationCost.isSelected()) {
            costs += coordenationCosts();           
        }
                        
        if (checkSecretariatCost.isSelected()) {
            costs += secretariatCosts();
        }
        
        if (checkOtherCost.isSelected()) {
            costs += otherCosts();
        }
  
        revenue = 100_000;
        
        lblRevenue.setText("R$ " + revenue);
        lblTotalCosts.setText("R$ " + costs);
        
        final var netIncome = revenue - costs;
      
        lblNetIncome.setForeground(netIncome <= 0.0 ? LoginFrame.DEFAULT_RED : LoginFrame.DEFAULT_GREEN);
        lblNetIncome.setText("R$ " + netIncome);
        
    }
    
    private double secretariatCosts() {
        return allEmployees
                    .stream()
                    .filter(e -> e.getSector() == Employee.Sector.SECRETARIAT)
                    .mapToDouble(e -> e.getSalary())
                    .sum();   
    }
    
    private double coordenationCosts() {
        return allEmployees
                    .stream()
                    .filter(e -> e.getSector() == Employee.Sector.COORDENATION)
                    .mapToDouble(e -> e.getSalary())
                    .sum();   
    }

    private double locationCosts() {
        return allLocations
                    .stream()
                    .mapToDouble(l -> l.getMonthlyCost())
                    .sum(); 
    }

    private double teachingCosts() {
        return allEmployees
                    .stream()
                    .filter(e -> e.canTeach())
                    .mapToDouble(e -> e.getSalary())
                    .sum();   
    }

    private double directionCosts() {
        return allEmployees
                    .stream()
                    .filter(e -> e.getSector() == Employee.Sector.DIRECTION)
                    .mapToDouble(e -> e.getSalary())
                    .sum();   
    }

    private double otherCosts() {
        return allEmployees
                    .stream()
                    .filter(e -> e.getSector() == Employee.Sector.OTHER)
                    .mapToDouble(e -> e.getSalary())
                    .sum();   
    }    

    private Location getSelectedLocation() {
        
        final String name = listLocations.getSelectedValue();
        
        if (name == null) {
            return null;
        }
        
        return allLocations.stream().filter(l -> l.getName().equals(name)).findAny().get();
        
    }
        
    private Employee getSelectedEmployee() {
        
        final String name = listEmployees.getSelectedValue();
        
        if (name == null) {
            return null;
        }
        
        return allEmployees.stream().filter(e -> e.getName().equals(name)).findAny().get();
        
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
        tabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listLocations = new javax.swing.JList<>();
        btnNewLocation = new javax.swing.JButton();
        btnViewLocation = new javax.swing.JButton();
        btnEditLocation = new javax.swing.JButton();
        btnReportLocations = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        checkOtherCost = new javax.swing.JCheckBox();
        checkLocationCosts = new javax.swing.JCheckBox();
        checkTeachingCost = new javax.swing.JCheckBox();
        checkDirectionCost = new javax.swing.JCheckBox();
        checkCoordenationCost = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalCosts = new javax.swing.JLabel();
        lblRevenue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNetIncome = new javax.swing.JLabel();
        checkSecretariatCost = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listEmployees = new javax.swing.JList<>();
        btnNewEmployee = new javax.swing.JButton();
        btnViewEmployee = new javax.swing.JButton();
        btnEditEmployee = new javax.swing.JButton();
        btnReportEmployee = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escola Wiedza - Controle da Direção");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        tabbedPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabbedPane.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabbedPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabbedPaneKeyPressed(evt);
            }
        });

        jPanel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        listLocations.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listLocations.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listLocations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listLocations.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listLocationsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listLocations);

        btnNewLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNewLocation.setText("Cadastrar");
        btnNewLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewLocationActionPerformed(evt);
            }
        });

        btnViewLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnViewLocation.setText("Visualizar");
        btnViewLocation.setEnabled(false);
        btnViewLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewLocationActionPerformed(evt);
            }
        });

        btnEditLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditLocation.setText("Editar");
        btnEditLocation.setEnabled(false);
        btnEditLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditLocationActionPerformed(evt);
            }
        });

        btnReportLocations.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnReportLocations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconReport.png"))); // NOI18N
        btnReportLocations.setText("<html> <p>Relatório  <br>de Instalações\n </p>   <html>");
        btnReportLocations.setEnabled(false);
        btnReportLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportLocationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEditLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addComponent(btnViewLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addComponent(btnNewLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportLocations, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNewLocation)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewLocation)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditLocation)
                        .addGap(18, 18, 18)
                        .addComponent(btnReportLocations, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Instalações", jPanel2);

        checkOtherCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkOtherCost.setText("Outros funcionários");
        checkOtherCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOtherCostActionPerformed(evt);
            }
        });

        checkLocationCosts.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkLocationCosts.setText("Manutenção das instalações");
        checkLocationCosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLocationCostsActionPerformed(evt);
            }
        });

        checkTeachingCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkTeachingCost.setText("Professores");
        checkTeachingCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkTeachingCostActionPerformed(evt);
            }
        });

        checkDirectionCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkDirectionCost.setText("Diretores");
        checkDirectionCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkDirectionCostActionPerformed(evt);
            }
        });

        checkCoordenationCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkCoordenationCost.setText("Coordenadores");
        checkCoordenationCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCoordenationCostActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Gastos totais: . . . . . . . . . . . . . . . . . . . .");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Receita bruta: . . . . . . . . . . . . . . . . . . . .");

        lblTotalCosts.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotalCosts.setText("0");

        lblRevenue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRevenue.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Receita líquida: . . . . . . . . . . . . . . . . . . .");

        lblNetIncome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNetIncome.setText("0");

        checkSecretariatCost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        checkSecretariatCost.setText("Secretários");
        checkSecretariatCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSecretariatCostActionPerformed(evt);
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
                        .addGap(8, 8, 8)
                        .addComponent(lblNetIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkLocationCosts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkTeachingCost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkDirectionCost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkCoordenationCost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(checkOtherCost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(checkSecretariatCost, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalCosts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(checkLocationCosts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkTeachingCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkDirectionCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkCoordenationCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkSecretariatCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOtherCost)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalCosts)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblRevenue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblNetIncome))
                .addGap(22, 22, 22))
        );

        tabbedPane.addTab("Despesas", jPanel3);

        listEmployees.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listEmployees.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        listEmployees.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listEmployees.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listEmployeesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listEmployees);

        btnNewEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNewEmployee.setText("Cadastrar");
        btnNewEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewEmployeeActionPerformed(evt);
            }
        });

        btnViewEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnViewEmployee.setText("Visualizar");
        btnViewEmployee.setEnabled(false);
        btnViewEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewEmployeeActionPerformed(evt);
            }
        });

        btnEditEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditEmployee.setText("Editar");
        btnEditEmployee.setEnabled(false);
        btnEditEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditEmployeeActionPerformed(evt);
            }
        });

        btnReportEmployee.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnReportEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconReport.png"))); // NOI18N
        btnReportEmployee.setText("<html> <p>Relatório  <br>de Funcionários\n </p>   <html>");
        btnReportEmployee.setEnabled(false);
        btnReportEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportEmployeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnNewEmployee)
                        .addGap(18, 18, 18)
                        .addComponent(btnViewEmployee)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditEmployee)
                        .addGap(18, 18, 18)
                        .addComponent(btnReportEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Funcionários", jPanel4);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Bem vindo(a),");

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblName.setText("-");

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                    .addComponent(tabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblName)
                    .addComponent(btnExit))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewLocationActionPerformed
        final Location selectedLocation = getSelectedLocation();
        if (selectedLocation != null) {
            new LocationForm(selectedLocation, true).setVisible(true);
        } else {
            Util.disableAll(btnEditLocation, btnViewLocation);
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma instalação para editá-la ou visualizá-la.");
        }
    }//GEN-LAST:event_btnViewLocationActionPerformed

    private void btnEditLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditLocationActionPerformed
        final Location selectedLocation = getSelectedLocation();
        if (selectedLocation != null) {
            final var form = new LocationForm(selectedLocation, false);
            form.setVisible(true);
            form.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateEntities();
                }
            });
        } else {
            Util.disableAll(btnEditLocation, btnViewLocation);
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma instalação para editá-la ou visualizá-la.");
        }
    }//GEN-LAST:event_btnEditLocationActionPerformed

    private void btnNewLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewLocationActionPerformed
        final var form = new LocationForm(null, false);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                updateEntities();
            }
        });
    }//GEN-LAST:event_btnNewLocationActionPerformed

    private void btnViewEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewEmployeeActionPerformed
        final Employee selectedEmployee = getSelectedEmployee();
        if (selectedEmployee != null) {
            new EmployeeForm(selectedEmployee, true).setVisible(true);
        } else {
            Util.disableAll(btnEditEmployee, btnViewEmployee);
            JOptionPane.showMessageDialog(this, "Por favor, selecione funcionário para editar/visualizar seus dados.");
        }    
    }//GEN-LAST:event_btnViewEmployeeActionPerformed

    private void checkOtherCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOtherCostActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkOtherCostActionPerformed

    private void checkTeachingCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkTeachingCostActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkTeachingCostActionPerformed

    private void checkDirectionCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkDirectionCostActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkDirectionCostActionPerformed

    private void checkCoordenationCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkCoordenationCostActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkCoordenationCostActionPerformed

    private void checkLocationCostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLocationCostsActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkLocationCostsActionPerformed

    private void checkSecretariatCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSecretariatCostActionPerformed
        updateFinance();
    }//GEN-LAST:event_checkSecretariatCostActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_1 -> System.out.println("1");
            case java.awt.event.KeyEvent.VK_2 -> System.out.println("2");
            case java.awt.event.KeyEvent.VK_3 -> System.out.println("3");
        }
    }//GEN-LAST:event_formKeyPressed

    private void tabbedPaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabbedPaneKeyPressed
        switch (evt.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_1 -> tabbedPane.getModel().setSelectedIndex(0);
            case java.awt.event.KeyEvent.VK_2 -> tabbedPane.getModel().setSelectedIndex(1);
            case java.awt.event.KeyEvent.VK_3 -> tabbedPane.getModel().setSelectedIndex(2);
        }
    }//GEN-LAST:event_tabbedPaneKeyPressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        lf.start();
    }//GEN-LAST:event_btnExitActionPerformed

    private void listEmployeesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listEmployeesValueChanged
        Util.enableAll(btnViewEmployee, btnEditEmployee);
    }//GEN-LAST:event_listEmployeesValueChanged

    
    private void btnEditEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditEmployeeActionPerformed
        final Employee selectedEmployee = getSelectedEmployee();
        if (selectedEmployee != null) {
            final var form = new EmployeeForm(selectedEmployee, false);
            form.setVisible(true);
            form.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateEntities();
                }
            });
        } else {
            Util.disableAll(btnEditEmployee, btnViewEmployee);
            JOptionPane.showMessageDialog(this, "Por favor, selecione funcionário para editar/visualizar seus dados.");
        }
    }//GEN-LAST:event_btnEditEmployeeActionPerformed

    private void btnNewEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewEmployeeActionPerformed
        final var form = new EmployeeForm(null, false);
        form.setVisible(true);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                updateEntities();
                requestFocus();
            }
        });
    }//GEN-LAST:event_btnNewEmployeeActionPerformed
    private void makeReports(String reportName) {

        try (InputStream in = getClass().getResourceAsStream(reportName)) {

            JasperPrint jasperPrint = JasperFillManager.fillReport(in, null, Database.getConnection());

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);

            JDialog dialog = new JDialog(this);
            dialog.setContentPane(jasperViewer.getContentPane());
            dialog.setSize(jasperViewer.getSize());
            dialog.setTitle(reportName);
            dialog.setModal(true);
            dialog.setVisible(true);

        } catch (IOException | JRException ex) {
            Logger.getLogger(SecretariatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void listLocationsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listLocationsValueChanged
        Util.enableAll(btnViewLocation, btnEditLocation);
    }//GEN-LAST:event_listLocationsValueChanged

    private void btnReportLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportLocationsActionPerformed
        makeReports("/Locationreports.jasper");
    }//GEN-LAST:event_btnReportLocationsActionPerformed

    private void btnReportEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportEmployeeActionPerformed
        makeReports("/employeesReport.jasper");
    }//GEN-LAST:event_btnReportEmployeeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditEmployee;
    private javax.swing.JButton btnEditLocation;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNewEmployee;
    private javax.swing.JButton btnNewLocation;
    private javax.swing.JButton btnReportEmployee;
    private javax.swing.JButton btnReportLocations;
    private javax.swing.JButton btnViewEmployee;
    private javax.swing.JButton btnViewLocation;
    private javax.swing.JCheckBox checkCoordenationCost;
    private javax.swing.JCheckBox checkDirectionCost;
    private javax.swing.JCheckBox checkLocationCosts;
    private javax.swing.JCheckBox checkOtherCost;
    private javax.swing.JCheckBox checkSecretariatCost;
    private javax.swing.JCheckBox checkTeachingCost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNetIncome;
    private javax.swing.JLabel lblRevenue;
    private javax.swing.JLabel lblTotalCosts;
    private javax.swing.JList<String> listEmployees;
    private javax.swing.JList<String> listLocations;
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
