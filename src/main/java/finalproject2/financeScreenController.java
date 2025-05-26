package finalproject2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class financeScreenController {

private String userID;
private String userPass;
private String userName;
private String userCourse;

public void setUserID(String userID) {
       this.userID = userID;
}

 private Stage stage;
 private Scene scene;

       /*public void backFinance(ActionEvent event) throws IOException{
              Parent root = FXMLLoader.load(getClass().getResource("financeScreen.fxml"));
              stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
              scene = new Scene(root);
              stage.setScene(scene);
              stage.show();
    
       }*/

       public void toTransaction(ActionEvent event) throws IOException{
              FXMLLoader loader = new FXMLLoader(getClass().getResource("transactionScreen.fxml"));
              Parent root = loader.load(); // this will also load TransactionController correctly
              stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
              scene = new Scene(root);
              stage.setScene(scene);
              stage.setMaximized(true);
              stage.show();  
 
       }
       
       @FXML
       public void goHome(ActionEvent event) throws IOException {
           System.out.println("pressed");
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/finalproject2/homeScreen.fxml"));
           Parent root = loader.load();

           homeScreenController controller = loader.getController();
           System.out.println("This id will be set: " + userID); 
           controller.setUserInfo(this.userID, this.userPass, this.userName, this.userCourse); 
           controller.refreshSchedule();

           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(new Scene(root));
           stage.setMaximized(true);
           stage.show();

       }

}

