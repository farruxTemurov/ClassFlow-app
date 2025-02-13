package com.zumba.bean;

public class Students {
    private int studentId;
    private String name;
    private String telephone;
    private String email;
    private int batchId;
    private String batchName; // Added for displaying batch info

    public Students() {}

    public Students(int studentId, String name, String telephone, String email, int batchId, String batchName) {
        this.studentId = studentId;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.batchId = batchId;
        this.batchName = batchName;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getBatchId() { return batchId; }
    public void setBatchId(int batchId) { this.batchId = batchId; }
    public String getBatchName() { return batchName; }
    public void setBatchName(String batchName) { this.batchName = batchName; }

    @Override
    public String toString() {
        return "Students [studentId=" + studentId + ", name=" + name + ", telephone=" + telephone + ", email=" + email
                + ", batchId=" + batchId + ", batchName=" + batchName + "]";
    }
}
