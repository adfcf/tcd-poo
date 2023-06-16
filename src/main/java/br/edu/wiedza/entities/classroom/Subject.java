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

/**
 *
 * @author 
 */
public class Subject extends Entity {

    private final String name;
    private final String code;
    private final String content;
    private final Subject requirement;    
    
    private final int totalClassHours;

    public Subject(String name, String code, int totalClassHours, String content, Subject requirement, Integer id) {
        super(id);
        this.name = name;
        this.code = code;
        this.totalClassHours = totalClassHours;
        this.content = content;
        this.requirement = requirement;
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

    public Subject getRequirement() {
        return requirement;
    }

    public int getTotalClassHours() {
        return totalClassHours;
    }
}
