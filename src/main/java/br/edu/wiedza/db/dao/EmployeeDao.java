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
package br.edu.wiedza.db.dao;

import br.edu.wiedza.db.Database;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maicon
 */
public class EmployeeDao extends Dao<Employee> {
    
    private static EmployeeDao instance;
    private EmployeeDao() {}
    
    public static EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }
        return instance;
    }

    public static final String TABLE_NAME = "employees";

    @Override
    protected String getUpdateStatement() {

        // Command format:
        // UPDATE `table_name` 
        // SET
        // field_1 = value_1, field_2 = value_2, ..., field_n = value_n
        // WHERE id = id_value;
        
        var rawStatement = new StringBuilder();

        rawStatement.append("UPDATE ");
        rawStatement.append(TABLE_NAME);
        rawStatement.append(" SET ");

        rawStatement.append("credentials_id = ?, ");
        rawStatement.append("name = ?, ");
        rawStatement.append("date_of_birth = ?, ");
        rawStatement.append("cpf = ?, ");
        rawStatement.append("sector = ?, ");
        rawStatement.append("role = ?, ");
        rawStatement.append("salary = ?, ");
        rawStatement.append("weekly_work_hours = ?, ");
        rawStatement.append("street = ?, ");
        rawStatement.append("district = ?, ");
        rawStatement.append("number = ?, ");
        rawStatement.append("cep = ?, ");
        rawStatement.append("primary_phone_number = ?, ");
        rawStatement.append("secondary_phone_number = ? ");

        rawStatement.append("WHERE id = ?;");

        return rawStatement.toString();
    }

    @Override
    protected String getCreateStatement() {

        // Command format:
        // INSERT INTO `table_name` 
        // (field_1, field_2, ..., field_n) 
        // VALUES 
        // (value_1, value_2, ..., value_n);
        
        var rawStatement = new StringBuilder();

        rawStatement.append("INSERT INTO ");
        rawStatement.append(TABLE_NAME);

        rawStatement.append(" (credentials_id");
        rawStatement.append(", name");
        rawStatement.append(", date_of_birth");
        rawStatement.append(", cpf");
        rawStatement.append(", sector");
        rawStatement.append(", role");
        rawStatement.append(", salary");
        rawStatement.append(", weekly_work_hours");
        rawStatement.append(", street");
        rawStatement.append(", district");
        rawStatement.append(", number");
        rawStatement.append(", cep");
        rawStatement.append(", primary_phone_number");
        rawStatement.append(", secondary_phone_number) ");

        rawStatement.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        return rawStatement.toString();
    }

    private String getFindByCredentialsStatement() {
        return "SELECT " + TABLE_NAME + ".*, " + CredentialsDao.TABLE_NAME + ".* " + " FROM " 
             +   TABLE_NAME 
             + " INNER JOIN " + CredentialsDao.TABLE_NAME
             + " ON " + TABLE_NAME + ".credentials_id = " + CredentialsDao.TABLE_NAME + ".id"
             + " WHERE user = ? AND password = ?"
             + "ORDER BY " + TABLE_NAME + ".name;";
    }
    
    @Override
    protected String getRetrieveAllStatement() {
        return "SELECT " + TABLE_NAME + ".*, " + CredentialsDao.TABLE_NAME + ".* " + " FROM "  
             +   TABLE_NAME 
             + " INNER JOIN " + CredentialsDao.TABLE_NAME
             + " ON " + TABLE_NAME + ".credentials_id = " + CredentialsDao.TABLE_NAME + ".id "
             + "ORDER BY " + TABLE_NAME + ".name;";
    }

    @Override
    protected String getFindByIdStatement() {
        return "SELECT " + TABLE_NAME + ".*, " + CredentialsDao.TABLE_NAME + ".* " + " FROM "  
             +   TABLE_NAME 
             + " INNER JOIN " + CredentialsDao.TABLE_NAME
             + " ON " + TABLE_NAME + ".credentials_id = " + CredentialsDao.TABLE_NAME + ".id"
             + " WHERE user = ? "
             + "ORDER BY " + TABLE_NAME + ".name;";
    }

    @Override
    protected void putData(PreparedStatement s, Employee e) {
        try {
            
            s.setInt(1, e.getCredentials().get().getId().get());
            
            s.setString(2, e.getName());
            
            s.setObject(3, e.getDateOfBirth().toString(), JDBCType.DATE);
            
            s.setLong(4, e.getCpf().asLong());
            
            s.setString(5, e.getSector().toString());
            
            s.setString(6, e.getRole());
            
            s.setFloat(7, e.getSalary());
            
            s.setInt(8, e.getWeeklyWorkHours());
            
            s.setString(9, e.getAddress().getStreet());
            s.setString(10, e.getAddress().getDistrict());
            s.setString(11, e.getAddress().getNumber());
            
            s.setInt(12, e.getAddress().getCep());
            
            s.setLong(13, e.getPrimaryPhoneNumber());
            s.setLong(14, e.getSecondaryPhoneNumber().get());
            
            if (e.getId().isPresent()) {
                s.setInt(15, e.getId().get());
            }
        }catch(SQLException ex){
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void putId(PreparedStatement s, int id) {
        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Employee getObjectFrom(ResultSet resultSet) {
        
        Employee e = null;
        
        try {
            
            final int id = resultSet.getInt(1);
             
            final var name = resultSet.getString(3);
            
            final var dateOfBirth = resultSet.getDate(4).toLocalDate();
            
            final var cpf = new Cpf(resultSet.getLong(5));
            
            final var sector = Employee.Sector.valueOf(resultSet.getString(6));
            
            final var role = resultSet.getString(7);
            
            final var salary = resultSet.getFloat(8);
            
            final var weeklyWorkHours = resultSet.getInt(9);
            
            final var street = resultSet.getString(10);
            
            final var district = resultSet.getString(11);
            
            final var number = resultSet.getString(12);
            
            final var cep = resultSet.getInt(13);
            
            final var primaryPhoneNumber = resultSet.getLong(14);
            
            final var secondaryPhoneNumber = resultSet.getLong(15);
            
            final var credentialsId = resultSet.getInt(16);
            final var userName = resultSet.getString(17);
            final var password = resultSet.getString(18);
            
            final var credentials = new Credentials(credentialsId, userName, password);
            
            final var address = new Address(cep, number, street, district);
            e = new Employee(
                    id,
                    credentials,
                    name,
                    dateOfBirth,
                    cpf,
                    address,
                    primaryPhoneNumber,
                    secondaryPhoneNumber,
                    sector,
                    role,
                    salary,
                    weeklyWorkHours
            );
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return e;
    }
    
    private void putCredentials(PreparedStatement ps, Credentials c) {
        try {
            ps.setString(1, c.getUserName());
            ps.setString(2, c.getPassword());
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Optional<Employee> authenticate(Credentials c) {
        
        final var db = Database.getConnection();
        
        Optional<Employee> entity = Optional.empty();
        
        try (var statement = db.prepareStatement(getFindByCredentialsStatement(),
                Statement.RETURN_GENERATED_KEYS)) {
            
            putCredentials(statement, c);
            
            System.out.println("SQL: " + statement.toString());
            
            var currentRow = statement.executeQuery();
            if (currentRow.next()) {
                entity = Optional.of(getObjectFrom(currentRow));
            }
            
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        
        return entity;
        
    }

}
