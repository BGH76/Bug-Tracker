package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.time.LocalDate;

public class DdHelper {

    private static final String DB_NAME = "bugTrackerDB.db";
    private static final String dbconnection = "jdbc:sqlite:/Users/brianhouts/IdeaProjects/BugTracker/bugTrackerDB.db";

    private static final String TABLE_INFO = "info_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE_ENTERED = "date_entered";
    private static final String COLUMN_PROJECT = "project";
    private static final String COLUMN_VERSION = "version";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_LAST_UPDATE = "last_update";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_DESCRIPTION = "description";

    private LocalDate myDate = LocalDate.now();
    private Connection conn;

    public boolean open(){
        try {
            conn = DriverManager.getConnection(dbconnection);
            return true;
        }catch(SQLException e) {
            System.out.println("Could not connect: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try{
            if(conn != null) {
                conn.close();
            }
        }catch (SQLException e) {
            System.out.println("Connection could not close: " + e.getMessage());
        }
    }
    public void createTables () {
        String createInfoTable = "CREATE TABLE IF NOT EXISTS " + TABLE_INFO  +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE_ENTERED + " TEXT, " +
                COLUMN_PROJECT + " TEXT, " +
                COLUMN_VERSION + " TEXT, " +
                COLUMN_COMPANY + " TEXT, " +
                COLUMN_LAST_UPDATE + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT )";

        try {
            Statement statement = conn.createStatement();
            statement.execute(createInfoTable);
            statement.close();
            conn.close();
        }catch (SQLException e) {
            System.out.println("Something went wrong in create tables method of DbHelper" + e.getMessage());
        }
    }

    public void addNewBug (String projectx, String versionx, String companyx, String statusx, String descriptionx){

        String newBug = "INSERT INTO " +  TABLE_INFO + "(" + COLUMN_DATE_ENTERED + ", " + COLUMN_PROJECT + ", " + COLUMN_VERSION + ", " + COLUMN_COMPANY + ", " + COLUMN_LAST_UPDATE + ", " + COLUMN_STATUS + ", " + COLUMN_DESCRIPTION + ") " +
                " VALUES ('" + myDate + "', '" + projectx + "', '" + versionx + "', '" + companyx + "', '" + myDate + "', '" + statusx + "', '" + descriptionx + "')";

        try{
            conn = DriverManager.getConnection(dbconnection);
            if(open()){
                Statement statement = conn.createStatement();
                statement.execute(newBug);
                statement.close();
                conn.close();
            }
        }catch(SQLException e) {
            System.out.println("addNewBug method not working: " + e.getMessage());
        }
    }

    public ObservableList<Bug> getAllData() throws SQLException {
        ObservableList<Bug> list = FXCollections.observableArrayList();
        String getAllData = "SELECT * FROM " + TABLE_INFO;
        try {
            conn = DriverManager.getConnection(dbconnection);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(getAllData);
            while (rs.next()){
                list.add(new Bug(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<Bug> getFixedData() throws SQLException {
        ObservableList<Bug> list = FXCollections.observableArrayList();
        String fixedData = "SELECT * FROM " + TABLE_INFO + " WHERE " + COLUMN_STATUS +"= 'yes'";
        try {
            conn = DriverManager.getConnection(dbconnection);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(fixedData);
            while (rs.next()){
                list.add(new Bug(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ObservableList<Bug> getUnfixedData() throws SQLException {
        ObservableList<Bug> list = FXCollections.observableArrayList();
        String unfixedData = "SELECT * FROM " + TABLE_INFO + " WHERE " + COLUMN_STATUS +"= 'no'";
        try {
            conn = DriverManager.getConnection(dbconnection);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(unfixedData);
            while (rs.next()){
                list.add(new Bug(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void updateBugStatusAndDescription(int idNum, String status, String descript){

        String upString = "UPDATE " + TABLE_INFO + " SET " + COLUMN_LAST_UPDATE + " ='" + myDate + "'," + COLUMN_STATUS + " ='" + status + "'," + COLUMN_DESCRIPTION + " ='" +  descript + "' WHERE " + COLUMN_ID + "=" + idNum;
        try {
            conn = DriverManager.getConnection(dbconnection);
            Statement statement = conn.createStatement();
            statement.executeUpdate(upString);
            statement.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
