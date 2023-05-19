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
import br.edu.wiedza.db.dao.StudentDao;
import br.edu.wiedza.entities.Address;
import br.edu.wiedza.entities.Cpf;
import br.edu.wiedza.entities.Credentials;
import br.edu.wiedza.entities.PhoneNumber;
import br.edu.wiedza.entities.Student;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author adfcf
 */
public final class Main {
    
    public static void main(String[] args) throws SQLException {
        
        System.out.println("TESTE INICIA");
        
        final var dao = new StudentDao();
        
        Student s1 = new Student(
                    null,
                    new Credentials(0, "", ""),
                    "JOAQUINA INFINITA",
                    LocalDate.now(),
                    new Cpf(),
                    new Address(0, "11", "22", "33"),
                    new PhoneNumber((byte)0, 1),
                    new PhoneNumber((byte)0,2),
                    
                    null,
                    true
            
        );
        
        var index = dao.createOrUpdate(s1);
        if (index.isPresent()) {
            System.out.println("Sucesso -> " + index.get());
        } else {
            System.out.println("Nao");
        }
        
        Database.getConnection().close();
        
    }
    
}
