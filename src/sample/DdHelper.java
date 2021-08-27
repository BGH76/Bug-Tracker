package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DdHelper {
    //TODO change to private
    public static final String DB_NAME = "bugTrackerDB.db";
    public static final String dbconnection = "jdbc:sqlite:/Users/brianhouts/IdeaProjects/BugTracker/bugTrackerDB.db";

    public static final String TABLE_INFO = "info_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DATE_ENTERED = "date_entered";
    public static final String COLUMN_PROJECT = "project";
    public static final String COLUMN_VERSION = "version";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_LAST_UPDATE = "last_update";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DESCRIPTION = "description";

    private LocalDate myDate = LocalDate.now();

//    public static final String TABLE_DESCRIPTION = "description_table";
//    public static final String COLUMN_DES_PROJECT = "description_project";
//    public static final String COLUMN_REMEDY = "remedy";

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

//        String createDescriptionTable = "CREATE TABLE IF NOT EXISTS " + TABLE_DESCRIPTION +
//                " (" + COLUMN_DES_PROJECT + " INTEGER, " +
//                COLUMN_DESCRIPTION + " TEXT, " +
//                COLUMN_REMEDY + " TEXT)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(createInfoTable);
//            statement.execute(createDescriptionTable);
            statement.close();
            conn.close();

        }catch (SQLException e) {
            System.out.println("Something went wrong in create tables method of DbHelper" + e.getMessage());
        }
    }
    /*
        Used to add a new bug. Contents form the dialog box controller will create a bug using the bug class.
        That bug will be returned to the calling class which is the main controller. The main controller will
        use the DdHelper class and call this method passing information which is applied to the sql strings.
     */
    public void addNewBug (String projectx, String versionx, String companyx, String statusx, String descriptionx){
//        LocalDate myDate = LocalDate.now();

        String newBug = "INSERT INTO " +  TABLE_INFO + "(" + COLUMN_DATE_ENTERED + ", " + COLUMN_PROJECT + ", " + COLUMN_VERSION + ", " + COLUMN_COMPANY + ", " + COLUMN_LAST_UPDATE + ", " + COLUMN_STATUS + ", " + COLUMN_DESCRIPTION + ") " +
                " VALUES ('" + myDate + "', '" + projectx + "', '" + versionx + "', '" + companyx + "', '" + myDate + "', '" + statusx + "', '" + descriptionx + "')";

//        String newBugDescription = "INSERT INTO " + TABLE_DESCRIPTION + "(" + COLUMN_DES_PROJECT + ", " + COLUMN_DESCRIPTION + ") VALUES('" + projectx + "', '" + descriptionx +"')";

        try{
            conn = DriverManager.getConnection(dbconnection);
            if(open()){
                Statement statement = conn.createStatement();
                statement.execute(newBug);
//                statement.execute(newBugDescription);
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
//        String getDescriptionData = "SELECT * FROM " + TABLE_DESCRIPTION;
        try {
            conn = DriverManager.getConnection(dbconnection);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(getAllData);
//            ResultSet rsD = statement.executeQuery(getDescriptionData);

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

//        String upString = "UPDATE " + TABLE_INFO + " SET " + COLUMN_STATUS + " ='" + status + "'," + COLUMN_DESCRIPTION + " ='" +  descript + "' WHERE " + COLUMN_ID + "=" + idNum;
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
