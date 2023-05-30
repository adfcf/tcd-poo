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
import br.edu.wiedza.entities.classroom.Attendance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanderlei Filho &lt;imsf@aluno.ifnmg.edu.br&gt;
 */
public class AttendanceDao extends Dao<Attendance>{
    private static AttendanceDao instance;
    private AttendanceDao(){}
    
    public static AttendanceDao getInstance(){
        if(instance == null){
            instance = new AttendanceDao();
        }
        return instance;
    }
    
    public static final String TABLE_NAME = "attendances";
    @Override
    protected String getUpdateStatement() {
        // Command format:
        // UPDATE 'table_name'
        // SET
        // field_1 = value_1, field_2 = value_2, ..., field_n = value_n
        // WHERE id = id_value;
        
        var rawStatement = new StringBuilder();
        
        rawStatement.append("UPDATE ");
        rawStatement.append(TABLE_NAME );
        rawStatement.append(" SET ");
        rawStatement.append("class_id = ?, ");
        rawStatement.append("student_id = ? ");
        
        rawStatement.append("WHERE id = ?;");
        
        return rawStatement.toString();
    }

    @Override
    protected String getCreateStatement() {
        
        var rawStatement = new StringBuilder();
        
        rawStatement.append("INSERT INTO ");
        rawStatement.append(TABLE_NAME );

        rawStatement.append(" (class_id");
        rawStatement.append(", student_id) ");
        
        rawStatement.append("VALUES (?,?);");
        
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
    protected void putData(PreparedStatement s, Attendance e) {
        try{
            s.setInt(1, e.getAttendenceClass().getId().get());
            s.setInt(2,e.getStudent().getId().get());
            
            if(e.getId().isPresent()){
                s.setInt(3, e.getId().get());
            }   
        }catch(SQLException ex){
            Logger.getLogger(AttendanceDao.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    @Override
    protected void putId(PreparedStatement s, int id) {
       try{
           s.setInt(1, id);
       }catch(SQLException ex){
            Logger.getLogger(AttendanceDao.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    protected Attendance getObjectFrom(ResultSet resultSet) {
        Attendance a = null;
        try{
            final var id = resultSet.getInt(1);
            final var attendanceClass = ClassDao.getinstance().findById(resultSet.getInt(2)).get();
            final var student = StudentDao.getInstance().findById(resultSet.getInt(3)).get();
            
            a = new Attendance(id,student,attendanceClass);
        }   catch (SQLException ex) {
            Logger.getLogger(AttendanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }
}
