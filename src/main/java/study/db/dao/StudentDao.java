package study.db.dao;

import study.db.entity.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDao {

    public List<Student> queryByName(String name){
        try {
            Connection connection = DbUtils.getConnection();
            String sql = "select * from student_t where name like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            List<Student> result= getStudentFromRs(rs);
            if (rs!=null){
                rs.close();
            }
            preparedStatement.close();
            connection.close();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

//    public int insertOne(Student student) throws SQLException {
//        Connection connection = DbUtils.getConnection();
//        String sql="insert into student_t (age,name) values(?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1,student.getAge());
//        preparedStatement.setString(2,student.getName());
//        return preparedStatement.executeUpdate();
//    }
    public int insert(List<Student> studentList) throws SQLException {
        Connection connection = DbUtils.getConnection();
        String sql="insert into student_t (age,name) values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(Student student :studentList){
            preparedStatement.setInt(1,student.getAge());
            preparedStatement.setString(2,student.getName());
            preparedStatement.addBatch();
        }
        int result = preparedStatement.executeBatch().length;
        if (preparedStatement!=null){
            preparedStatement.close();
        }
        connection.close();
        return result;
    }

    private List<Student> getStudentFromRs(ResultSet rs){
        List<Student> list=new LinkedList<>();
        while (true){
            try {
                if (!rs.next()) break;
                Student st=new Student();
                list.add(st);
                rs.getLong("id");
                st.setId(rs.getLong("id"));
                st.setAge(rs.getInt("age"));
                st.setName(rs.getString("name"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public int insertUseTransation(Student student,boolean commit) {
        try {
            Connection connection = DbUtils.getConnection();
            connection.setAutoCommit(false);
            String sql="insert into student_t (age,name) values(?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getAge());
            preparedStatement.setString(2,student.getName());
            int result = preparedStatement.executeUpdate();
            if (commit){
                connection.commit();
            }else{
                connection.rollback();
            }
            connection.close();
            return result;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }



}
