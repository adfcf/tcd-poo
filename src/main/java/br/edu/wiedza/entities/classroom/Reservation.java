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
import br.edu.wiedza.entities.Location;
import java.sql.Time;
import java.time.DayOfWeek;

/**
 *
 * @author 
 */
public class Reservation extends Entity{
    
    private final Location location;
    private final DayOfWeek weekDay;
    private final Time startTime;
    
    private final int numberOfClassHours;

    public Reservation(Location location, DayOfWeek weekDay, Time startTime, int numberOfClassHours, Integer id) {
        super(id);
        this.location = location;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.numberOfClassHours = numberOfClassHours;
    }

    public Location getLocation() {
        return location;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getNumberOfClassHours() {
        return numberOfClassHours;
    }
    
    
    
    
    
}
