package study.service;

import study.db.dao.StudentDao;
import study.db.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDao studentDao;
    public int AddStudent(List<Student> studentList){
        try {
            int result = getStudentDao().insert(studentList);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public List<Student> getStudentByName(String name){
        return getStudentDao().queryByName(name);
    }

    public void InsertWithTransation(Student student,boolean commit){
        getStudentDao().insertUseTransation(student,commit);
    }

    private StudentDao getStudentDao(){
        if (studentDao==null){
            studentDao=new StudentDao();
            return studentDao;
        }else{
            return studentDao;
        }
    }
}
