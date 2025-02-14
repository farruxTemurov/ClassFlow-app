package com.zumba.service;

import com.zumba.bean.Students;
import com.zumba.dao.StudentsDAO;

public class StudentsService {
    private StudentsDAO studentsDAO;

    public StudentsService() {
        this.studentsDAO = new StudentsDAO();
    }

    public boolean addStudent(Students student) {
        return studentsDAO.addStudent(student);
    }

    public Students getStudent(String name, String email) {
        return studentsDAO.getStudent(name, email);
    }
}
