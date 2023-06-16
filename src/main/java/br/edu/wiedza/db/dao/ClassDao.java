/*
 * The MIT License
 *
 * Copyright 2023 Ivanderlei Filho &lt;imsf@aluno.ifnmg.edu.br&gt;.
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
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ivanderlei Filho &lt;imsf@aluno.ifnmg.edu.br&gt;
 */
public class ClassDao extends Dao<Class>{
    private static ClassDao instance;
    public static final String TABLE_NAME = "classes";
    private ClassDao(){}
    
    public static ClassDao getinstance(){
        if(instance== null){
            instance = new ClassDao();
        }
        return instance;
    }
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

        rawStatement.append("course_id = ?, ");
        rawStatement.append("content  = ?, ");
        rawStatement.append("date = ?, ");
        rawStatement.append("start_time = ?, ");
        rawStatement.append("number_of_class_hours = ?, ");

        // Very important clause to select the correct entity.
        rawStatement.append("WHERE id = ?;");

        return rawStatement.toString();
    }

    @Override
    protected String getCreateStatement() {
        // Command format:
        // UPDATE `table_name` 
        // SET
        // field_1 = value_1, field_2 = value_2, ..., field_n = value_n
        // WHERE id = id_value;
        // (value_1, value_2, ..., value_n);
        var rawStatement = new StringBuilder();

        rawStatement.append("INSERT INTO ");
        rawStatement.append(TABLE_NAME);

        rawStatement.append("(course_id");
        rawStatement.append(", content");
        rawStatement.append(", date");
        rawStatement.append(", start_time");
        rawStatement.append(", number_of_class_hours)");

        // Very important clause to select the correct entity.
        rawStatement.append("VALUES (?, ?, ?, ?, ?);");

        return rawStatement.toString();
    }

    @Override
    protected String getRetrieveAllStatement() {
        return "SELECT * FROM " + TABLE_NAME + ";";
    }

    @Override
    protected String getFindByIdStatement() {
        return "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
    }

    @Override
    protected void putData(PreparedStatement s, Class e) {
        
        try {
            s.setInt(1, e.getCourse().getId().get());
            s.setString(2,e.getContent());
            s.setObject(3, e.getDate().toString(),JDBCType.DATE);
            s.setObject(4, e.getStartTime(),JDBCType.TIME);
            s.setInt(5, e.getNumberOfClassHours());
            if(e.getId().isPresent()){
                s.setInt(6, e.getId().get());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    protected void putId(PreparedStatement s, int id) {
         try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(ClassDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Class getObjectFrom(ResultSet resultSet) {
           Class c = null;
           try{
               final var id =resultSet.getInt(1);
               
               final var course = CourseDao.getInstance().findById(resultSet.getInt(2)).get();
               
               final var content = resultSet.getString(3);
               
               final var date = resultSet.getDate(4).toLocalDate();
               
               final var time = resultSet.getTime(5).toLocalTime();
               
               final var classHours = resultSet.getInt(6);
               
               c = new Class(id,course, content, date, time, classHours);
               
           }catch (SQLException ex) {
            Logger.getLogger(ClassDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         return c;
    }
    
}
