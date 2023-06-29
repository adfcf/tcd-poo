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

import br.edu.wiedza.entities.Occurrence;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Student;
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
public class OccurrenceDao extends Dao<Occurrence>{
    
    private static OccurrenceDao instance;
    private OccurrenceDao() {}
    
    public static OccurrenceDao getInstance() {
        if (instance == null) {
            instance = new OccurrenceDao();
        }
        return instance;
    }
    
    public static final String TABLE_NAME = "occurrences";

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

        rawStatement.append("student_id = ?, ");
        rawStatement.append("date = ?, ");
        rawStatement.append("description = ? ");    

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

        rawStatement.append(" (student_id");
        rawStatement.append(", date");
        rawStatement.append(", description)");
        
        rawStatement.append("VALUES (?, ?, ?);");

        return rawStatement.toString();
    }

    @Override
    protected String getRetrieveAllStatement() {
        return "SELECT * FROM "
                + TABLE_NAME
                + " INNER JOIN " + StudentDao.TABLE_NAME
                + " ON " + TABLE_NAME + ".student_id = " + StudentDao.TABLE_NAME + ".id"
                + " INNER JOIN " + CredentialsDao.TABLE_NAME
                + " ON " + StudentDao.TABLE_NAME + ".credentials_id = " + CredentialsDao.TABLE_NAME + ".id;";
    }

    @Override
    protected String getFindByIdStatement() {
        return "SELECT * FROM "
                + TABLE_NAME
                + " INNER JOIN " + StudentDao.TABLE_NAME
                + " ON " + TABLE_NAME + ".student_id = " + StudentDao.TABLE_NAME + ".id"
                + " INNER JOIN " + CredentialsDao.TABLE_NAME
                + " ON " + StudentDao.TABLE_NAME + ".credentials_id = " + CredentialsDao.TABLE_NAME + ".id"
                + " WHERE id = ?;";
    }

    @Override
    protected void putData(PreparedStatement s, Occurrence e) {
        
        try {
            
            s.setInt(1, e.getStudent().getId().get());
            
            s.setObject(2, e.getDate(), JDBCType.DATE);
            
            s.setString(3, e.getDescription());
            
            if(e.getId().isPresent()){
                s.setInt(4, e.getId().get());
            }
        } catch (SQLException ex) {
            Logger.getLogger(OccurrenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void putId(PreparedStatement s, int id) {
        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(OccurrenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Occurrence getObjectFrom(ResultSet resultSet) {
        
        Occurrence o = null;
        try {
            
            var id = resultSet.getInt(1);
                        
            var date = resultSet.getDate(3).toLocalDate();
            
            var description = resultSet.getString(4);
            
            final int studentId = resultSet.getInt(5);
                        
            final var name = resultSet.getString(7);
            final var dateOfBirth = resultSet.getDate(8).toLocalDate();
            final var cpf = new Cpf(resultSet.getLong(9));
            final var address = new Address(
                    resultSet.getInt(13), 
                    resultSet.getString(12),
                    resultSet.getString(11), 
                    resultSet.getString(10));
            
            final long primaryPhoneNumber = resultSet.getLong(14);
            final long secondaryPhoneNumber = resultSet.getLong(15);
            final boolean active = resultSet.getBoolean(16);
            
            final var credentialsId = resultSet.getInt(17);
            final var userName = resultSet.getString(18);
            final var password = resultSet.getString(19); 
            
            final var credentials = new Credentials(credentialsId, userName, password);
            
            final var student = new Student(studentId, credentials, name, dateOfBirth, cpf, address, primaryPhoneNumber, secondaryPhoneNumber, active);
            
            o = new Occurrence(student, date, description, id);
        } catch (SQLException ex) {
            Logger.getLogger(OccurrenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return o;
    }
    
}