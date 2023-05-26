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
import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author adfcf
 */
public final class Class extends Entity {
    
    private final Course course;
    private final String content;
    private final LocalDate date;
    private final LocalTime startTime;
    
    private final int numberOfClassHours;
    
    public Class(Integer id, Course course, String content, LocalDate date, LocalTime startTime, int numberOfClassHours) {
        
        super(id);
        
        this.course = requireNonNull(course);
        this.content = requireNonNull(content);
        this.date = requireNonNull(date);
        this.startTime = requireNonNull(startTime);
        
        this.numberOfClassHours = numberOfClassHours;
        
    }  

    public Course getCourse() {
        return course;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getNumberOfClassHours() {
        return numberOfClassHours;
    }
    
}
