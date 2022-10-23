package com.food.ShareTable.student;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Integer id;
    @Column(name = "first_name", length = 50, nullable = false,columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name", length = 50, nullable = false,columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email", length = 50, nullable = false,columnDefinition = "TEXT")
    private String email;
    @Column(name = "gender", length = 50,nullable = false, columnDefinition = "TEXT")
    private String gender;
    @Column(name = "create_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createDate;
    @Column(name = "last_update_date", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime lastUpdateDate;

    public Student(String firstName, String lastName, String email, String gender, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
