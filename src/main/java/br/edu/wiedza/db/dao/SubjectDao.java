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
import br.edu.wiedza.entities.classroom.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maicon
 */
public class SubjectDao extends Dao<Subject> {

    private static SubjectDao instance;

    private SubjectDao() {
    }

    public static SubjectDao getInstance() {
        if (instance == null) {
            instance = new SubjectDao();
        }
        return instance;
    }

    public static final String TABLE_NAME = "subjects";

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

        rawStatement.append("name = ?, ");
        rawStatement.append("code = ?, ");
        rawStatement.append("total_class_hours = ?, ");
        rawStatement.append("content = ?, ");
        rawStatement.append("requirement_id = ? ");

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

        rawStatement.append(" (name");
        rawStatement.append(", code");
        rawStatement.append(", total_class_hours");
        rawStatement.append(", content");
        rawStatement.append(", requirement_id) ");

        rawStatement.append("VALUES (?, ?, ?, ?, ?);");

        return rawStatement.toString();
    }

    @Override
    protected String getRetrieveAllStatement() {
        return "SELECT * FROM " +TABLE_NAME+ 
                " LEFT JOIN  " +TABLE_NAME+ 
                " AS s1 ON s1.requirement_id = " 
                +TABLE_NAME+ ".id;";
    }

    @Override
    protected String getFindByIdStatement() {
        return "SELECT * FROM " +TABLE_NAME+ 
                " s1 LEFT JOIN " +TABLE_NAME+ 
                " ON s1.requirement_id = " 
                +TABLE_NAME
                + ".id WHERE s1.id = ?;";
    }

    @Override
    protected void putData(PreparedStatement s, Subject e) {

        try {

            s.setString(1, e.getName());

            s.setString(2, e.getCode());

            s.setInt(3, e.getTotalClassHours());

            s.setString(4, e.getContent());

            if (e.getRequirement() != null) {
                s.setInt(5, e.getRequirement().getId().get());
            } else {
                s.setNull(5, Types.INTEGER);
            }

            if (e.getId().isPresent()) {
                s.setInt(6, e.getId().get());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void putId(PreparedStatement s, int id) {

        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Subject getObjectFrom(ResultSet resultSet) {

        Subject s = null;
        try{
            final var id = resultSet.getInt(1);

            final var name = resultSet.getString(2);
            
            final var code = resultSet.getString(3);

            final var totalHoursWorked = resultSet.getInt(4);

            final var content = resultSet.getString(5);
            final int requirementId = resultSet.getInt(6);
            Subject requirement = null;
            if (requirementId != 0) {
               
                final var id1 = resultSet.getInt(7);

                final var name1 = resultSet.getString(8);

                final var code1 = resultSet.getString(9);

                final var totalHoursWorked1 = resultSet.getInt(10);

                final var content1 = resultSet.getString(11);

                Subject requirement1 = null;
                requirement = new Subject(name1,
                    code1,
                    totalHoursWorked1,
                    content1,
                    requirement1,
                    id1
            );
            }

            s = new Subject(name,
                    code,
                    totalHoursWorked,
                    content,
                    requirement,
                    id
            );

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return s;
    }

}
