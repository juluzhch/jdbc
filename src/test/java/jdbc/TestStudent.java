package jdbc;

import org.junit.Test;
import study.db.dao.DbUtils;
import study.db.entity.Student;
import study.service.StudentService;

import java.util.LinkedList;
import java.util.List;

public class TestStudent {
    private String namePrefix="wang";
    @Test
    public void TestAll(){
        TestInsert();
        TestQuery();
    }

    @Test
    public  void TestInsert(){
//        String namePrefix="li";
        StudentService service =new StudentService();
        int result =service.AddStudent(mockNewStudent(namePrefix));
        print("insert result " + result);
    }

    @Test
    public void TestQuery(){
//        String namePrefix="li";
        StudentService service =new StudentService();
        print(service.getStudentByName(namePrefix+"%"));
    }

    @Test
    public void TestQueryWithDuriDataSource(){
//        String namePrefix="li";
        DbUtils.connectonType=DbUtils.ConnectonType.DuriDataSource;
        StudentService service =new StudentService();
        print(service.getStudentByName(namePrefix+"%"));
    }

    @Test
    public void TestTransation(){
        Student student=new Student();
        student.setName("TEST"+"_" +2);
        student.setAge(1);
        StudentService service =new StudentService();
        service.InsertWithTransation(student,false);
    }

    private List<Student> mockNewStudent(String namePrefix){
        List<Student> studentList=new LinkedList<>();
        for(int i=1;i<5;i++){
            Student student=new Student();
            student.setName(namePrefix+"_" +i);
            student.setAge(i);
            studentList.add(student);
        }
        return studentList;
    }
    private void print(Object obj) {
        System.out.println(obj);
    }
}
