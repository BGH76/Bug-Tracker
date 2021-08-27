package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddBugController {

    @FXML
    private TextField projectField;
    @FXML
    private TextField versionField;
    @FXML
    private TextField companyField;
//    @FXML
//    private TextField statusField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private RadioButton statusRadioButton;
    @FXML
    private Label dateEnteredField;

    DdHelper db = new DdHelper();
    // TODO removed Bug and added void. Delete return statement when program is done.
    public  void newBug() {
        String statusData;
//        FXMLLoader fxmlLoader = new FXMLLoader();
        String project = projectField.getText();
        String version = versionField.getText();
        String company = companyField.getText();
//        String status = statusField.getText();
        String description = descriptionField.getText();

        if(statusRadioButton.isSelected()){
            statusData = "yes";
        } else {
            statusData = "no";
        }
        db.addNewBug(project,version,company,statusData,description);

//        return null;
    }
    public void updateDateEnteredOnAddBug(String date){
        dateEnteredField.setText(date);
    }

}
