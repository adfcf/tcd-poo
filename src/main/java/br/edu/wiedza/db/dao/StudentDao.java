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

import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.classroom.Course;
import br.edu.wiedza.entities.persons.components.Cpf;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Student;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a Data Access Object (DAO) for 'Student' type objects.
 * There is only one instance in each program (singleton).
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

    private static StudentDao instance;
    private StudentDao() {}
    
    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }
    
    public static final String TABLE_NAME = "students";

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
        
        rawStatement.append("completed_courses_id = ?, ");
        rawStatement.append("credentials_id = ?, ");
        rawStatement.append("name = ?, ");
        rawStatement.append("date_of_birth = ?, ");
        rawStatement.append("cpf = ?, ");
        rawStatement.append("street = ?, ");
        rawStatement.append("district = ?, ");
        rawStatement.append("number = ?, ");
        rawStatement.append("cep = ?, ");
        rawStatement.append("primary_phone_number = ?, ");
        rawStatement.append("secondary_phone_number = ?, ");
        rawStatement.append("active = ? ");

        // Very important clause to select the correct entity.
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
            
        rawStatement.append(" (completed_courses_id");
        rawStatement.append(", credentials_id");
        rawStatement.append(", name");
        rawStatement.append(", date_of_birth");
        rawStatement.append(", cpf");
        rawStatement.append(", street");
        rawStatement.append(", district");
        rawStatement.append(", number");
        rawStatement.append(", cep");
        rawStatement.append(", primary_phone_number");
        rawStatement.append(", secondary_phone_number");
        rawStatement.append(", active) ");

        rawStatement.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        
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

    @Override
    protected void putData(PreparedStatement s, Student e) {
        try {
            
            // Putting completed courses (a string with indices separated by semicolons)
            s.setString(1, toSemicolonSeparatedValues(e.getCompletedCourses()));
            
            // Putting credentials ID. They're guaranteed to be present in student objects.
            s.setInt(2, e.getCredentials().get().getId().get());
             
            // Putting name.
            s.setString(3, e.getName());
            
            // Putting date of birth.
            s.setObject(4, e.getDateOfBirth().toString(), JDBCType.DATE);
            
            // Putting CPF.
            s.setLong(5, e.getCpf().asLong());
            
            // Putting address.
            s.setString(6, e.getAddress().getStreet());
            s.setString(7, e.getAddress().getDistrict());
            s.setString(8, e.getAddress().getNumber());
            s.setInt(   9, e.getAddress().getCep());
            
            // Putting phone numbers.
            s.setLong(10, e.getPrimaryPhoneNumber());
            s.setLong(11, e.getSecondaryPhoneNumber().orElse(Long.valueOf(0)));
            
            // Putting active boolean.
            s.setBoolean(12, e.isActive());
            
            // If ID is present, then it's an update.
            if (e.getId().isPresent()) {
                s.setInt(13, e.getId().get());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Student getObjectFrom(ResultSet resultSet) {
        Student s = null;
        try {
            
            final int id = resultSet.getInt(1);

            /* CourseDao isn't implemented yet.
            final ArrayList<Course> completedCourses = new ArrayList<>();
            fromSemicolonSeparatedIntegers(resultSet.getString(2))
                    .stream()
                    .map(t -> CourseDao.getInstance().findById(t).get())
                    .forEach(c -> completedCourses.add(c));
            */
            // Temporary command
            final var completedCourses = new ArrayList<Course>();
            
            // CredentialsDao isn't implemented yet.
            // final var credentials = CredentialsDao.getInstance().findById(resultSet.getInt(3)).get();
            // Temporary command
            final var credentials = new Credentials(0, "user", "password");
            
            final var name = resultSet.getString(4);
            final var dateOfBirth = dateFromString(resultSet.getString(5));
            final var cpf = new Cpf(resultSet.getLong(6));
            final var address = new Address(
                    resultSet.getInt(10), 
                    resultSet.getString(7),
                    resultSet.getString(8), 
                    resultSet.getString(9));
            
            final long primaryPhoneNumber = resultSet.getLong(11);
            final long secondaryPhoneNumber = resultSet.getLong(12);
            final boolean active = resultSet.getBoolean(13);
           
            s = new Student(
                    id,
                    credentials,
                    name,
                    dateOfBirth,
                    cpf,
                    address,
                    primaryPhoneNumber,
                    secondaryPhoneNumber, 
                    completedCourses,
                    active
            );
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
}


