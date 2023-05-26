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

import br.edu.wiedza.db.Database;
import br.edu.wiedza.entities.Entity;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author adfcf
 * @param <E> entity from the database.
 */
public abstract class Dao<E extends Entity> implements IDao<E> {
    
    protected abstract String getUpdateStatement();
    protected abstract String getCreateStatement();
    protected abstract String getRetrieveAllStatement();
    protected abstract String getFindByIdStatement(int id);
    
    protected abstract void putData(PreparedStatement s, E e);
    protected abstract void putId(PreparedStatement s, int id);
    
    protected abstract E getObjectFrom(ResultSet resultSet);
    
    private void update(E e) {
        
        final var db = Database.getConnection();
        
        try (var statement = db.prepareStatement(getUpdateStatement(), Statement.RETURN_GENERATED_KEYS)) {
            
            putData(statement, e);
            System.out.println("SQL: " + statement.toString());
            
            final int count = statement.executeUpdate();
            System.out.println("Affected rows: " + count);
            
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        
    }
    
    private Optional<Integer> create(E e) {
        
        final var db = Database.getConnection();
        
        try (var statement = db.prepareStatement(getCreateStatement(), Statement.RETURN_GENERATED_KEYS)) {
            
            putData(statement, e);
            System.out.println("SQL: " + statement.toString());
            
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            
            if (keys.first()) {
                System.out.println("OK");
                e.setId(keys.getInt(1));
            } else {
                System.out.println("No rows affected");
            }
            
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        
        return e.getId();
        
    }
    
    @Override
    public final Optional<Integer> createOrUpdate(E e) {
        
        if (e.getId().isPresent()) {
            update(e);
            return e.getId();
        }
        
        return create(e);
        
    }
    
    @Override
    public final List<E> retrieveAll() {
        
        final var entities = new ArrayList<E>();
        final var db = Database.getConnection();
        
        try (var statement = db.prepareStatement(getRetrieveAllStatement(), Statement.RETURN_GENERATED_KEYS)) {
            
            System.out.println("SQL: " + statement.toString());
            
            var currentRow = statement.executeQuery();
            while (currentRow.next()) {
                entities.add(getObjectFrom(currentRow));
            }
            
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        
        return entities;
        
    }
    
    @Override
    public final Optional<E> findById(int id) {
        
        final var db = Database.getConnection();
        
        Optional<E> entity = Optional.empty();
        
        try (var statement = db.prepareStatement(getFindByIdStatement(id), Statement.RETURN_GENERATED_KEYS)) {
            
            putId(statement, id);
            
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
