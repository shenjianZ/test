import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    public static void main(String[] args) throws SQLException {
        // JDBC连接的URL, 不同数据库有不同的格式:
        String URL = "jdbc:mysql://localhost:3306/dbdemo?serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            /****************************************/

            String sql = "SELECT\r\n" +
                    "student.id,\r\n" +
                    "student.`name`,\r\n" +
                    "student.age\r\n" +
                    "FROM\r\n" +
                    "student";
            try (Statement stat = conn.createStatement()) {
                try (ResultSet rs = stat.executeQuery(sql)) {
                    /*********************************************/
                    //此处读取的结果的代码
                    while(rs.next())
                    {
                        int id = rs.getInt(1); // 注意：索引从1开始
                        String name = rs.getString(2);
                        int age = rs.getInt(3);
                        System.out.println(id+" "+name+" "+age);
                    }
/*********************************************/

                }
            }

            /*******************************************/
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败："+e.getMessage());
        }
    }
}
//ciugaiiyu
//pull代码
