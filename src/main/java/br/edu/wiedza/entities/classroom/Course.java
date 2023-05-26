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
package br.edu.wiedza.entities.classroom;

import br.edu.wiedza.entities.Entity;
import br.edu.wiedza.entities.persons.Employee;

/**
 *
 * A course represents a completed school year of a student.
 * @author adfcf
 */
public class Course extends Entity {
    private final Subject subject;
    
    private final Employee teacher;
    
    private final int year;
    
    private final Boolean completed;
    


    public Course(Integer id,Subject subject, Employee teacher, int year, Boolean completed) {
        super(id);
        this.subject = subject;
        this.teacher = teacher;
        this.year = year;
        this.completed = completed;
    }

    public Subject getSubject() {
        return subject;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public int getYear() {
        return year;
    }

    public Boolean getCompleted() {
        return completed;
    }

}
