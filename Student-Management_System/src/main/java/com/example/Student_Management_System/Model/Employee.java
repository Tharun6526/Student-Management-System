    package com.example.Student_Management_System.Model;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Pattern;
    import lombok.Data;

    import java.util.ArrayList;
    import java.util.List;

    @Data
    @Entity
    public class Employee {
        @Id
        private Long Eid;
        @NotBlank(message = "Name must be Mentioned")
        private String Ename;
        @Email(message = "Incorrect Email Format")
        private String Eemail;
        @Pattern(regexp = "\\d{10}",message = "Phone Number is not Valid")
        private String phone;
        @NotBlank(message = "Address should not be empty")
        private  String address;


        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;

        @OneToMany(mappedBy ="employee")
        private List<Course> course = new ArrayList<>();
    }
