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
package br.edu.wiedza.entities;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author adfcf
 */
public final class Student extends Person {
    
    private final Optional<Course>[] completedCourses;
    
    private final boolean active;
    
    public Student(
            
            // Person data
            Integer id,
            Credentials credentials,
            String name,
            LocalDate dateOfBirth,
            Cpf cpf,
            Address address,
            PhoneNumber primaryPhoneNumber,
            PhoneNumber secondaryPhoneNumber,
            
            // Student data
            Optional<Course>[] completedCourses,
            boolean active
            
    ) {
        super(id, credentials, name, dateOfBirth, cpf, address, primaryPhoneNumber, secondaryPhoneNumber);
        
        this.active = active;
        this.completedCourses = requireNonNull(completedCourses);
        
        if (completedCourses.length != Course.NUMBER_OF_SCHOOL_YEARS) {
            throw new IllegalArgumentException("'completedCourses' should be an array of length = " + Course.NUMBER_OF_SCHOOL_YEARS);
        }
        
    }
    
    public boolean isActive() {
        return active;
    }
    
    public Optional<Course> getCourse(int grade) {
        return completedCourses[grade];
    }
    
}
