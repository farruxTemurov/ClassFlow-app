package com.zumba.service;

import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;
import java.util.List;

public class StudentsService {
    private StudentsDAO studentsDAO;

    public StudentsService() {
        this.studentsDAO = new StudentsDAO();
    }

    public boolean registerStudent(Students student) {
        return studentsDAO.registerStudent(student);
    }

    public Students searchStudent(String name, String email) {
        return studentsDAO.searchStudent(name, email);
    }

    public List<Students> getAllStudents() {
        return studentsDAO.getAllStudents();
    }
}
