package jdbc;

import org.junit.Test;
import study.enumdefine.MyJDBCType;

import java.sql.JDBCType;

public class TestBaseType {

    @Test
    public void TestJdbcType(){
        JDBCType jdbcType=JDBCType.BIGINT;
        MyJDBCType myJDBCType= MyJDBCType.BIT;
        print(jdbcType.getName());
        print(myJDBCType.getName());
    }


    private void print(Object  obj){
        System.out.println(obj);
    }
}
