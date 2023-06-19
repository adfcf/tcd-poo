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
package br.edu.wiedza.entities.persons;

import br.edu.wiedza.entities.persons.components.Address;
import br.edu.wiedza.entities.persons.components.Cpf;
import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author adfcf
 */
public class Employee extends Person {
    
    public enum Sector {
        
        SECRETARIAT("Secretaria"),
        COORDENATION("Coordenação"),
        DIRECTION("Direção"),
        TEACHING("Ensino"),
        OTHER("Outro");
        
        private final String name;
        
        private Sector(String s) {
            this.name = s;
        }
        
        public String getName() {
            return name;
        }
        
        public static Sector fromString(String s) {
            return switch (s) {
                case "Secretaria" -> Sector.SECRETARIAT;
                case "Coordenação" -> Sector.COORDENATION;
                case "Direção" -> Sector.DIRECTION;
                case "Ensino" -> Sector.TEACHING;
                default -> Sector.OTHER;
            };
        }
        
    }
    
    private Sector sector;
    private String role;
    
    private float salary;
    private int weeklyWorkHours;
    
    public Employee(
            
            // Person data
            Integer id,
            Credentials credentials,
            String name,
            LocalDate dateOfBirth,
            Cpf cpf,
            Address address,
            long primaryPhoneNumber,
            Long secondaryPhoneNumber,
            
            // Employee data
            Sector sector,
            String role,
            float salary,
            int weeklyWorkHours
            
    ) {
        super(id, credentials, name, dateOfBirth, cpf, address, primaryPhoneNumber, secondaryPhoneNumber);
        
        this.sector = requireNonNull(sector);
        this.role = requireNonNull(role);
        
        this.salary = salary;
        this.weeklyWorkHours = weeklyWorkHours;
        
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setWeeklyWorkHours(int weeklyWorkHours) {
        this.weeklyWorkHours = weeklyWorkHours;
    }
    
    public Sector getSector() {
        return sector;
    }

    public String getRole() {
        return role;
    }
    
    public boolean canTeach() {
        return sector.equals(Sector.TEACHING);
    }

    public float getSalary() {
        return salary;
    }
    
    public int getWeeklyWorkHours() {
        return weeklyWorkHours;
    }
    
}
