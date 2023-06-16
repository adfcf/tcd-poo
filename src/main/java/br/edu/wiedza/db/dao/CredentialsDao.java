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

import br.edu.wiedza.entities.persons.Credentials;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author 
 */
public final class CredentialsDao extends Dao<Credentials> {

    private static CredentialsDao instance;
    private CredentialsDao() {}
    
    public static CredentialsDao getInstance() {
        if (instance == null) {
            instance = new CredentialsDao();
        }
        return instance;
    }
    
    public static final String TABLE_NAME = "credentials";
    
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

        rawStatement.append("user = ?, ");
        rawStatement.append("password = ? ");

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

        rawStatement.append(" (user");
        rawStatement.append(", password)");
        
        rawStatement.append("VALUES (?, ?);");

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
    protected void putData(PreparedStatement s, Credentials e) {
        
        try {
            
            s.setString(1, e.getUserName());
            
            s.setString(2, e.getPassword());
            
            if(e.getId().isPresent()){
                s.setInt(3, e.getId().get());
            }
            
        }catch(SQLException ex){
            Logger.getLogger(CredentialsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void putId(PreparedStatement s, int id) {
        
        try {
            s.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(CredentialsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Credentials getObjectFrom(ResultSet resultSet) {
        
        Credentials c = null;
        try {
            
            var id = resultSet.getInt(1);
            
            var user = resultSet.getString(2);
            
            var password = resultSet.getString(3);
            
            c = new Credentials(id, user, password);
            
        } catch(SQLException ex){
            Logger.getLogger(CredentialsDao.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        return c;
    }
    
}
