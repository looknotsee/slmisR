package finalproject2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class utilityDB {
	
	public static void changeScene(ActionEvent event, String fxml, String title, String userID, String userPass, String userName, String userCourse) {
		Parent root = null;
		
		if(userID != null && userPass != null) {
			try {
				FXMLLoader loader = new FXMLLoader(utilityDB.class.getResource(fxml));
				root = loader.load();
				homeScreenController controller = loader.getController();
				controller.setUserInfo(userID, userPass, userName, userCourse);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		else {
			try {
				root = FXMLLoader.load(utilityDB.class.getResource("/finalproject2/homeScreen.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	}
}
