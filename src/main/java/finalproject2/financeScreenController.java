package finalproject2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class financeScreenController {

private String userID;

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

}

