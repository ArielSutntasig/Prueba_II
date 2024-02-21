package com.saew.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saew.dto.Student;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private static List<Student> students = new ArrayList<>();
    static {
        // Datos quemados
        students.add(new Student(1, "Pedro Ramirez", "pedro.ramirez@example.com", 3.7f));
        students.add(new Student(2, "Ana Torres", "ana.torres@example.com", 3.9f));
        students.add(new Student(3, "Luis Morales", "luis.morales@example.com", 3.4f));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String nombre) {
        List<Student> result = students.stream()
                .filter(student -> student.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        students.add(student); // En un caso real, aquí se debería asignar un nuevo ID único
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                Student student = students.get(i);
                student.setNombre(updatedStudent.getNombre());
                student.setCorreoElectronico(updatedStudent.getCorreoElectronico());
                student.setGPA(updatedStudent.getGPA());
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
