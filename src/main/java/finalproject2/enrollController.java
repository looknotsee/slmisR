package finalproject2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class enrollController implements Initializable {

private String userID;

public void setUserID(String userID) {
    this.userID = userID;
}

@FXML
private ListView<Course> courseListView;
private ObservableList<Course> getCourses() {
    ObservableList<Course> list = FXCollections.observableArrayList();
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis2", "root", "");
        String sql = "SELECT * FROM classes";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add (new Course(
                rs.getString("classID"),
                rs.getString("className"),
                rs.getString("classTime"),
                rs.getString("classDay")
            ));
        }

        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
    
} 

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Course> courses = getCourses();
        courseListView.setItems(courses);
        System.out.println("Attempting to insert for userID: " + userID);
        courseListView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Course selected = courseListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis2", "root", "");
                        String sql = "INSERT INTO schedule (userID, classID) Values (?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, userID);
                        stmt.setString(2, selected.getClassID());
                        stmt.executeUpdate();
                        courseListView.setItems(getCourses());
                        conn.close();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Enrollment");
                        alert.setHeaderText(null);
                        alert.setContentText("Class added to your schedule!");
                        alert.showAndWait();

                    } catch (SQLIntegrityConstraintViolationException dup) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Duplicate Entry");
                        alert.setHeaderText(null);
                        alert.setContentText("This class is already in your schedule.");
                        alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    

    @FXML
    public void goHome(ActionEvent event) throws IOException {
        System.out.println("pressed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/finalproject2/homeScreen.fxml"));
        Parent root = loader.load();

        homeScreenController controller = loader.getController();
        System.out.println("This id will be set: " + userID); 
        controller.setUserID(this.userID); 
        controller.refreshSchedule();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.show();

    }

}
