package com.zumba.service;

import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;

public class StudentsService {
    private StudentsDAO studentsDAO;

    public StudentsService() {
        this.studentsDAO = new StudentsDAO();
    }

    public boolean registerStudent(Students student) {
        if (student.getName() == null || student.getName().trim().isEmpty() ||
            student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return false; // Name and Email are required
        }
        return studentsDAO.addStudent(student);
    }

    public Students searchStudent(String name, String email) {
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return null; // Validation
        }
        return studentsDAO.searchStudent(name, email);
    }
}
