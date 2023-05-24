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
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author adfcf
 */
public final class Assessment extends Entity {
    
    private final Class assessmentClass;
    private final String name;
    private final List<Float> studentsGrades;
    
    private final float value;
    
    public Assessment(Integer id, Class assessmentClass, String name, List<Float> studentsGrades, float value) {
        
        super(id);
        
        this.value = value;
     
        this.assessmentClass = requireNonNull(assessmentClass);
        this.studentsGrades = requireNonNull(studentsGrades);
        this.name = requireNonNull(name);
        
    }

    public float getGrade(int index) {
        return studentsGrades.get(index);
    }
    
    public List<Float> getGrades(){
        return studentsGrades;
    }
    
    public Class getAssessmentClass() {
        return assessmentClass;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }
    
}
