package finalproject2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class financeScreenController implements Initializable {

    // Dashboard Info
    @FXML private Label dashUserID;
    @FXML private Label dashUserName;
    @FXML private Label dashUserCourse;
    @FXML private Button dashEnrButton;
    @FXML private Button dashFinButton;


    // Payable Info
    @FXML private Label financePayLabel;
    @FXML private ListView<String> financePayDisplay;

    // Payment Tabs
    @FXML private TabPane financePayTab;
    @FXML private Tab creditTab;
    @FXML private Tab bankTab;
    @FXML private Tab ewalletTab;
    @FXML private Tab ftfTab;

    // --- E-Wallet Fields ---
    @FXML private ChoiceBox<String> ewPaymentType;
    @FXML private TextField ewAccountNumber;

    // --- Bank Transfer Fields ---
    @FXML private ChoiceBox<String> bankChoiceDropdown;
    @FXML private TextField bankAccountName;
    @FXML private TextField bankAccountNumber;

    // --- Credit Card Fields ---
    @FXML private TextField ccNumberField;
    @FXML private ChoiceBox<String> ccTypeDropdown;
    @FXML private TextField ccExpiryDate;
    @FXML private TextField ccCVV;

    // --- Internal state ---
    private double balance = 50000.00;
	private String userID;
	private String userPass;
	private String userName;
	private String userCourse;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (ccTypeDropdown != null) {
            ccTypeDropdown.getItems().addAll("Visa", "MasterCard", "Amex");
        }
        if (bankChoiceDropdown != null) {
            bankChoiceDropdown.getItems().addAll("BPI", "BDO", "Metrobank", "Landbank");
        }
        if (ewPaymentType != null) {
            ewPaymentType.getItems().addAll("GCash", "PayMaya", "ShopeePay");
        }
        updateBalanceDisplay();
        loadFeeBreakdown();
    }

    private void updateBalanceDisplay() {
        if (financePayLabel != null) {
            financePayLabel.setText("Php " + String.format("%,.2f", balance));
        }
    }

    private void loadFeeBreakdown() {
        if (financePayDisplay != null) {
            financePayDisplay.getItems().clear();
            financePayDisplay.getItems().addAll(
                "Courses: Php 20,000.00",
                "Classes: Php 10,000.00",
                "Clubs: Php 5,000.00",
                "Miscellaneous Fees: Php 15,000.00"
            );
        }
    }

    // Add your @FXML methods for button actions here as needed
    
    @FXML
    private void confirmCreditCardPayment(ActionEvent event) {
        // Logic to process credit card details here
        System.out.println("Credit Card payment confirmed.");
    }

    @FXML
    private void confirmBankTransfer(ActionEvent event) {
        // Logic to process bank transfer details here
        System.out.println("Bank Transfer confirmed.");
    }

    @FXML
    private void confirmEwalletPayment(ActionEvent event) {
        // Logic to process e-wallet details here
        System.out.println("E-Wallet payment confirmed.");
    }

    @FXML
    private void confirmFTFPayment(ActionEvent event) {
        // Logic to handle face-to-face confirmation here
        System.out.println("Face-to-Face payment confirmed.");
    }

    @FXML
    private void processFinalPayment(ActionEvent event) {
        try {
            double inputAmount = Double.parseDouble(ccNumberField.getText());
            if (inputAmount > 0 && inputAmount <= balance) {
                balance -= inputAmount;
                if (ewAccountNumber != null) ewAccountNumber.clear();
	             if (bankAccountName != null) bankAccountName.clear();
	             if (bankAccountNumber != null) bankAccountNumber.clear();
	             if (bankChoiceDropdown != null) bankChoiceDropdown.setValue(null);
	             if (ccNumberField != null) ccNumberField.clear();
	             if (ccExpiryDate != null) ccExpiryDate.clear();
	             if (ccCVV != null) ccCVV.clear();
	             if (ccTypeDropdown != null) ccTypeDropdown.setValue(null);
                updateBalanceDisplay();
                System.out.println("Payment of Php " + inputAmount + " processed.");
            } else {
                System.out.println("Invalid amount entered.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    @FXML
    public void goEnroll(ActionEvent event) throws IOException {
        System.out.println("pressed");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/finalproject2/enrollment.fxml"));
        Parent root = loader.load();
        enrollController controller = loader.getController();
        System.out.println("Setting this ID: " + userID + userPass + userName + userCourse);
        controller.setUserInfo(this.userID, this.userPass, this.userName, this.userCourse);

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
    
    public void setUserID(String userID) {
        this.userID = userID;
 }
}