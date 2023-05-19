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
public abstract class Person extends Entity {
    
    // Idenfication
    private final String name;
    private final LocalDate dateOfBirth;
    private final Cpf cpf;
    
    // Current physical address
    private final Address address;
    
    // Contact
    // Not every employee has two phone numbers.
    private final PhoneNumber primaryPhoneNumber;
    private final Optional<PhoneNumber> secondaryPhoneNumber;
    
    // Credentials
    // Not every employee has credentials.
    private final Optional<Credentials> credentials;

    public Person(
            Integer id,
            Credentials credentials,
            String name,
            LocalDate dateOfBirth,
            Cpf cpf,
            Address address,
            PhoneNumber primaryPhoneNumber,
            PhoneNumber secondaryPhoneNumber
    ) {
        
        super(id);
        
        this.name = requireNonNull(name);
        this.dateOfBirth = requireNonNull(dateOfBirth);
        this.cpf = requireNonNull(cpf);
        this.address = requireNonNull(address);
        this.primaryPhoneNumber = requireNonNull(primaryPhoneNumber);
        
        this.credentials = Optional.ofNullable(credentials);
        this.secondaryPhoneNumber = Optional.ofNullable(secondaryPhoneNumber);
        
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public Optional<PhoneNumber> getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }
    
    public Optional<Credentials> getCredentials() {
        return credentials;
    }
    
}
