package finalproject2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class homeScreenController {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Label dashUserCourse;

    @FXML
    private Label dashUserID;

    @FXML
    private Label dashUserName;
    
    @FXML
    private TableView<schedule> dashSched;
    
    @FXML 
    private TableColumn<schedule, String> dashSchedClass;
    
    @FXML 
    private TableColumn<schedule, String> dashSchedTime;
    
    @FXML
    private TabPane mainTabs;
    
    private String userID;

    @FXML
    public void initialize() {
        dashSchedClass.setCellValueFactory(new PropertyValueFactory<>("className"));
        dashSchedTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

        mainTabs.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldTab, newTab) -> {
                if (newTab != null) {
                    String tabDay = newTab.getId();
                    dashSched.setItems(getScheduleData(tabDay));
                    
                    System.out.println("Setting schedule for day: " + tabDay);
                    System.out.println("Items count: " + getScheduleData(tabDay).size());

                }
            }
        );
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public void setUserInfo(String userID, String userPass, String userName, String userCourse) {
    	this.userID = userID;
    	dashUserName.setText(userName);
    	dashUserID.setText(userID + "@my.xu.edu.ph");
    	dashUserCourse.setText(userCourse);
    	
    	dashSched.setItems(getScheduleData("Monday"));
    }
    
    public ObservableList<schedule> getScheduleData(String tabDay) {
        ObservableList<schedule> list = FXCollections.observableArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis2", "root", "");
            String sql = "SELECT c.className, c.classTime FROM schedule s JOIN classes c ON s.classID = c.classID WHERE s.userID =? AND c.classDay =?"; //c is for classes, s is for schedule
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("USER ID:" + userID);
            stmt.setString(1, userID);
            stmt.setString(2, tabDay);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(new schedule(
                    rs.getString("className"),
                    rs.getString("classTime")
                ));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void refreshSchedule() {
        dashSched.setItems(getScheduleData("Monday"));
    }

    @FXML
    public void goEnroll(ActionEvent event) throws IOException {
        System.out.println("pressed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/finalproject2/enrollment.fxml"));
        Parent root = loader.load();
        enrollController controller = loader.getController();
        System.out.println("Setting this ID: " + userID);
        controller.setUserID(this.userID);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();

    }

    @FXML
    public void goFinance(ActionEvent event) throws IOException {
        System.out.println("pressed finance");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/finalproject2/financeScreen.fxml"));
        Parent root = loader.load();
        financeScreenController controller = loader.getController();
        System.out.println("Setting this ID for finance:" + userID);
        controller.setUserID(this.userID);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();
    }

}


