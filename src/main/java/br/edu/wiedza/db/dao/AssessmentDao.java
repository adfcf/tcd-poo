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

import br.edu.wiedza.entities.classroom.Class;
import br.edu.wiedza.entities.classroom.Assessment;
import br.edu.wiedza.entities.classroom.Offering;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maicon
 */
public class AssessmentDao extends Dao<Assessment> {

    private static AssessmentDao instance;
    private AssessmentDao() {}
    
    public static AssessmentDao getInstance() {
        if (instance == null) {
            instance = new AssessmentDao();
        }
        return instance;
    }

    public static final String TABLE_NAME = "assessments";

    
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

        rawStatement.append("class_id = ?, ");
        rawStatement.append("name = ?, ");
        rawStatement.append("value = ?, ");
        rawStatement.append("grades = ? ");
    
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

        rawStatement.append(" (class_id");
        rawStatement.append(", name");
        rawStatement.append(", value");
        rawStatement.append(", grades) ");

        rawStatement.append("VALUES (?, ?, ?, ?);");

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
    protected void putData(PreparedStatement s, Assessment e) {
        
        try {
            s.setInt(1, e.getAssessmentClass().getId().get());
        
            s.setString(2, e.getName());
        
            s.setFloat(3, e.getValue());
            
            s.setString(4, toSemicolonSeparatedValues(e.getGrades()));
           
            if(e.getId().isPresent()){
                s.setInt(5, e.getId().get());
            }
        }catch(SQLException ex){
            Logger.getLogger(AssessmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void putId(PreparedStatement s, int id) {
        
        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(AssessmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Assessment getObjectFrom(ResultSet resultSet) {
        
        Assessment a = null;
        
        try{
            
            final var id = resultSet.getInt(1);
            
            //TO DO: Use ClassDao to get the Class object
            final var classId = resultSet.getInt(2);
            
            final var name = resultSet.getString(3);
            
            final var value = resultSet.getFloat(4);
            
            final var grades = new ArrayList<Float>();
            fromSemicolonSeparatedFloats(resultSet.getString(5))
                    .forEach(ac -> grades.add(ac));
            
            a = new Assessment(id, 
                    new Class(classId, new Offering(1), "name", LocalDate.now(), LocalTime.now(),2),
                    name,
                    grades,
                    value);
            
        }catch (SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }
}
