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
package br.edu.wiedza.db.dao;

import br.edu.wiedza.entities.Address;
import br.edu.wiedza.entities.Cpf;
import br.edu.wiedza.entities.Credentials;
import br.edu.wiedza.entities.PhoneNumber;
import br.edu.wiedza.entities.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a Data Access Object (DAO) for 'Student' type objects.
 * @author adfcf
 */

/**
 * CREATE TABLE `students` (
 * 
 *   `id`                       INT NOT NULL AUTO_INCREMENT,
 *   `completed_courses_id`     VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 *   `credentials_id`           INT NOT NULL,
 *   `name`                     VARCHAR(300) NOT NULL,
 *   `date_of_birth`            DATE NOT NULL,
 *   `cpf`                      BIGINT NOT NULL,
 *   `street`                   VARCHAR(100) NOT NULL,
 *   `district`                 VARCHAR(100) NOT NULL,
 *   `number`                   VARCHAR(10) NOT NULL,
 *   `cep`                      INT NOT NULL,
 *   `primary_phone_number`     BIGINT NOT NULL,
 *   `secondary_phone_number`   BIGINT DEFAULT NULL,
 *   `active`                   TINYINT(1) NOT NULL DEFAULT '1'
 * 
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */

public final class StudentDao extends Dao<Student> {

    public static final String[] TABLE_FIELDS = {
        "completed_courses_id",
        "credentials_id",
        "name",
        "date_of_birth",
        "cpf",
        "street",
        "district",
        "number",
        "cep",
        "primary_phone_number",
        "secondary_phone_number",
        "active",    
    };
    
    public static final String TABLE_NAME = "students";
    
    @Override
    protected String getUpdateStatement() {
        
        var rawStatement = new StringBuilder();
        
        rawStatement.append("UPDATE ");
        rawStatement.append(TABLE_NAME);
        rawStatement.append(" SET ");
        
        for (var field : TABLE_FIELDS) {
            rawStatement.append(field);
            rawStatement.append(" = ? ");
        }
        
        // Very important clause to select the correct entity.
        rawStatement.append("WHERE id = ?;");
        
        return rawStatement.toString();
    }

    @Override
    protected String getCreateStatement() {
          
        var rawStatement = new StringBuilder();
        
        rawStatement.append("INSERT INTO ");
        rawStatement.append(TABLE_NAME);
        rawStatement.append(" (");
        rawStatement.append(TABLE_FIELDS[0]);

        for (var field : TABLE_FIELDS) {
            
            if (field.equals(TABLE_FIELDS[0])) 
                continue;
            
            rawStatement.append(", ");
            rawStatement.append(field);
            
        }
        
        rawStatement.append(") VALUES (?");
        
        for (int i = 1; i < TABLE_FIELDS.length; ++i) {
            rawStatement.append(", ?");
        }
        rawStatement.append(");");
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
    protected void putId(PreparedStatement s, int id) {
        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /*
 *   `completed_courses_id`     VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 *   `credentials_id`           INT NOT NULL,
 *   `name`                     VARCHAR(300) NOT NULL,
 *   `date_of_birth`            DATE NOT NULL,
 *   `cpf`                      BIGINT NOT NULL,
 *   `street`                   VARCHAR(100) NOT NULL,
 *   `district`                 VARCHAR(100) NOT NULL,
 *   `number`                   VARCHAR(10) NOT NULL,
 *   `cep`                      INT NOT NULL,
 *   `primary_phone_number`     BIGINT NOT NULL,
 *   `secondary_phone_number`   BIGINT DEFAULT NULL,
 *   `active`                   TINYINT(1) NOT NULL DEFAULT '1'
  */  
    @Override
    protected void putData(PreparedStatement s, Student e) {
        try {
            
            // completed_courses_id (TODO).
            s.setString(1, "");
            
            // Putting credentials ID.
            if (e.getCredentials().isPresent()) {
                s.setInt(2, e.getCredentials().get().getId().get());
            } else {
                s.setInt(2, 1);
            }
             
            // Putting name.
            s.setString(3, e.getName());
            
            // Putting date of birth.
            s.setString(4, e.getDateOfBirth().toString());
            
            // Putting CPF.
            s.setLong(5, e.getCpf().asLong());
            
            // Putting address.
            s.setString(6, e.getAddress().getStreet());
            s.setString(7, e.getAddress().getDistrict());
            s.setString(8, e.getAddress().getNumber());
            s.setInt(   9, e.getAddress().getCep());
            
            // Putting phone numbers.
            s.setLong(10, e.getPrimaryPhoneNumber().asLong());
            s.setLong(11, e.getSecondaryPhoneNumber().isPresent() ? 
                    e.getSecondaryPhoneNumber().get().asLong() : 0);
            
            // Putting active boolean.
            s.setBoolean(12, e.isActive());
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Student getObjectFrom(ResultSet resultSet) {
        Student s = null;
        try {
            
                /*
 *   `completed_courses_id`     VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 *   `credentials_id`           INT NOT NULL,
 *   `name`                     VARCHAR(300) NOT NULL,
 *   `date_of_birth`            DATE NOT NULL,
 *   `cpf`                      BIGINT NOT NULL,
 *   `street`                   VARCHAR(100) NOT NULL,
 *   `district`                 VARCHAR(100) NOT NULL,
 *   `number`                   VARCHAR(10) NOT NULL,
 *   `cep`                      INT NOT NULL,
 *   `primary_phone_number`     BIGINT NOT NULL,
 *   `secondary_phone_number`   BIGINT DEFAULT NULL,
 *   `active`                   TINYINT(1) NOT NULL DEFAULT '1'
  */ 
            
            int id = resultSet.getInt(1);
            String completed_courses_id = resultSet.getString(2);
            int credentials_id = resultSet.getInt(3);
            String name = resultSet.getString(4);
            String dateOfBirth = resultSet.getString(5);
            long cpf = resultSet.getLong(6);
            String street = resultSet.getString(7);
            String district = resultSet.getString(8);
            String number = resultSet.getString(9);
            int cep = resultSet.getInt(10);
            long primary_phone_number = resultSet.getLong(11);
            long secundary_phone_number = resultSet.getLong(12);
            boolean active = resultSet.getBoolean(13);
           
            s = new Student(
                    id,
                    new Credentials(0, "", ""),
                    name,
                    LocalDate.now(),
                    new Cpf(),
                    new Address(0, "", "", ""),
                    new PhoneNumber((byte)0, 0),
                    new PhoneNumber((byte)0, 0),
                    
                    null,
                    true
            
            );
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
}


