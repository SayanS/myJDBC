package dbutils;

import java.sql.*;

public class DBase {
    public DBase()
    {
    }

    public Connection connect(String db_connect_str,String db_userid, String db_password)
    {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_str,db_userid, db_password);
        }
        catch(Exception e){
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }

    public void importData(Connection conn, String fileName){
        Statement stmt;
        String query;
        try{
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            query = "LOAD DATA INFILE '"+fileName+"' " +
                    "INTO TABLE OrderHeader  " +
                    "FIELDS TERMINATED BY ',' " +
                    "LINES TERMINATED BY '\n'"+
                    "IGNORE 1 LINES"+
                    "(orderID,customerName,customerAddress)"+
                     "SET orderID = NULL";

            stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
            stmt = null;
        }
    }

    public void readData(Connection conn){
        String sql = "SELECT orderID, customerName, customerAddress FROM OrderHeader";
        try {
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            while(resultSet.next()){
                int orderID  = resultSet.getInt("orderID");
                String customerName = resultSet.getString("customerName");
                String customerAddress = resultSet.getString("customerAddress");
                System.out.print(orderID);
                System.out.print("  " + customerName);
                System.out.print("  " + customerAddress);
                System.out.println();
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
