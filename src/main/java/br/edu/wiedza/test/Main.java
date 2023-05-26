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
package br.edu.wiedza.test;

import br.edu.wiedza.db.Database;
import br.edu.wiedza.db.dao.CourseDao;
import br.edu.wiedza.db.dao.EmployeeDao;
import br.edu.wiedza.db.dao.StudentDao;
import br.edu.wiedza.db.dao.SubjectDao;
import br.edu.wiedza.entities.classroom.Course;
import br.edu.wiedza.entities.classroom.Subject;
import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import br.edu.wiedza.entities.persons.Credentials;
import br.edu.wiedza.entities.persons.Employee;
import br.edu.wiedza.entities.persons.Student;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author adfcf
 */
public final class Main {
    
    public static void main(String[] args) throws SQLException {
        Employee teacher = EmployeeDao.getInstance().findById(1).get();
        Subject subject = SubjectDao.getInstance().findById(1).get();
        
        Course c = new Course(null,subject,teacher,9999,true);
        CourseDao.getInstance().createOrUpdate(c);
   
    }
    
}
