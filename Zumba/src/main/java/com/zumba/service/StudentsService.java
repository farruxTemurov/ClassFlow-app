package com.zumba.service;

import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;

import java.util.List;

public class StudentsService {
    private StudentsDAO studentsDAO;

    public StudentsService() {
        this.studentsDAO = new StudentsDAO();
    }

    // ✅ Register a student (ensuring Name & Email are provided)
    public boolean registerStudent(Students student) {
        if (student.getName() == null || student.getName().trim().isEmpty() ||
            student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return false; // Name and Email are required
        }
        return studentsDAO.addStudent(student);
    }

    // ✅ Search for a student by Name and Email
    public Students searchStudent(String name, String email) {
        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            return null; // Validation check
        }
        return studentsDAO.searchStudent(name, email);
    }

    // ✅ Get all students (now includes batch details)
    public List<Students> getAllStudents() {
        return studentsDAO.getAllStudents();
    }

    // ✅ Assign a batch to an existing student
    public boolean assignBatch(int studentId, int batchId) {
        if (studentId <= 0 || batchId <= 0) {
            return false; // Invalid IDs
        }
        return studentsDAO.updateStudentBatch(studentId, batchId);
    }
    
    
}
