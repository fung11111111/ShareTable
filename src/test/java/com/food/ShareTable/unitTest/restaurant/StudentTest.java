package com.food.ShareTable.unitTest.restaurant;

import com.food.ShareTable.student.Student;
import com.food.ShareTable.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;

public class StudentTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testInsert() {
        Student s = new Student();
        s.setFirstName("Tom");
        s.setLastName("Halland");
        s.setEmail("hello@gamil.com");
        s.setGender("Male");
        s.setCreateDate(LocalDateTime.now());
        s.setLastUpdateDate(LocalDateTime.now());
        //studentRepository.cre(s);

    }
}
