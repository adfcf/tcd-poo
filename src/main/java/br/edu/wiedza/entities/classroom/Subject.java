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

/**
 *
 * @author 
 */
public class Subject extends Entity {

    private final String name;
    private final String code;
    private final String content;
    private final List<Subject> requirements;    
    
    private final int totalClassHours;

    public Subject(String name, String code, int totalClassHours, String content, List<Subject> requirements, Integer id) {
        super(id);
        this.name = name;
        this.code = code;
        this.totalClassHours = totalClassHours;
        this.content = content;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public List<Subject> getRequirements() {
        return requirements;
    }

    public int getTotalClassHours() {
        return totalClassHours;
    }
    
    public void removeRequirement(int id){
        for (int i = 0; i < requirements.size(); ++i) {
            if (requirements.get(i).getId().get() == id) {
                requirements.remove(requirements.get(i));
            }
        }
    }
}
