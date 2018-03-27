import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by user on 27.03.18.
 */
public class ImportSCV {

    public static void main(String[] args) throws SQLException {
        DBase db = new DBase();
        Connection conn = db.connect("jdbc:mysql://localhost:3306/Orders?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Rfhfylfitkm12r");
        db.importData(conn, "/home/user/IdeaProjects/myHibernate/src/main/resources/Mydb.csv");
        conn.close();
        conn = db.connect("jdbc:mysql://localhost:3306/Orders?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Rfhfylfitkm12r");
        db.readData(conn);
        conn.close();
   }
}
