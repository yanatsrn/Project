import java.sql.*;
import java.util.List;

public class Course {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/exampledb?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection connection;

    public Course() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error while registering JDBC driver");
        }
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);

    }

    public void create(Teacher teacher) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "insert into employees value (null, ?, ?, ?, ?);"
        );
        statement.setInt(1, teacher.getid());
        statement.setString(2, teacher.getname());
        statement.setString(3, teacher.getsurname());
        statement.setInt(4, teacher.getage());
        statement.setInt(5, teacher.getexperience());
        statement.setInt(6, teacher.getspecialty_id());
        statement.setInt(7, teacher.gete_id());
        statement.setInt(8, teacher.getsalary());

        statement.executeUpdate();
    }

    public void getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select t.id, t.name, t.surname, t.age, t.experience" +
                        " t.specialty_ id, t.e_id " +
                        "from teachers t join specialities s on (t.specialty_id = s.id_s);");

        List<Teacher> result = new ArrayList<>();

        while (resultSet.next()) {

            Teacher teacher = new Teacher();
            teacher.setId(resultSet.getInt("eid"));
            teacher.setname(resultSet.getString("name"));
            teacher.setsurname(resultSet.getString("surname"));
            teacher.setage(resultSet.getInt("age"));
            teacher.setexperience(resultSet.getInt("experience"));
            teacher.setspecialty_id(resultSet.getInt("specialty_id"));
            teacher.sete_id(resultSet.getInt("e_id"));
            teacher.setsalary(resultSet.getInt("salary"));


        }
    }
}
