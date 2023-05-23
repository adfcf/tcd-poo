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

import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maicon
 */
public class EmployeeDao extends Dao<Employee> {

    public static final String TABLE_NAME = "employees";

    @Override
    protected String getUpdateStatement() {

        // Command format:
        // UPDATE `table_name` 
        // SET
        // field_1 = value_1, field_2 = value_2, ..., field_n = value_n
        // WHERE id = id_value;
        var rawStatement = new StringBuilder();

        rawStatement.append("UPDATE");
        rawStatement.append(TABLE_NAME);
        rawStatement.append("SET");

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

        rawStatement.append("UPDATE");
        rawStatement.append(TABLE_NAME);
        rawStatement.append("SET");

        rawStatement.append("(credentials_id");
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
        rawStatement.append("secondary_phone_number)");

        rawStatement.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        return rawStatement.toString();
    }

    @Override
    protected String getRetrieveAllStatement() {
        return "SELECT * FROM " + TABLE_NAME + ";";
    }

    @Override
    protected String getFindByIdStatement(int id) {
        return "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
    }

    @Override
    protected void putData(PreparedStatement s, Employee e) {
        try{
            
            s.setInt(1, e.getCredentials().get().getId().get());
            
            s.setString(2, e.getName());
            
            s.setObject(3, e.getDateOfBirth().toString(), JDBCType.DATE);
            
            s.setLong(4, e.getCpf().asLong());
            
            s.setString(5, e.getSector().toString());
            
            s.setString(6, e.getRole());
            
            s.setFloat(7, e.getSalary());
            
            s.setInt(8, e.getWeeklyWorkHours());
            
            s.setString(10, e.getAddress().getStreet());
            s.setString(11, e.getAddress().getDistrict());
            s.setString(12, e.getAddress().getNumber());
            
            s.setInt(13, e.getAddress().getCep());
            
            s.setLong(14, e.getPrimaryPhoneNumber());
            //s.setLong(15, e.getSecondaryPhoneNumber());
            
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
        
        try{
            
            final int id = resultSet.getInt(1);
            
            //temporary code. Need to implement CredentialsDao
            final var credentialsId = new Credentials(0, "username", "password");
            
            final var name = resultSet.getString(3);
            
            final var dateOfBirth = dateFromString(resultSet.getString(4));
            
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
            
            e = new Employee(
                    id,
                    credentialsId,
                    name,
                    dateOfBirth,
                    cpf,
                    new Address(cep, number, street, district),
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

}
