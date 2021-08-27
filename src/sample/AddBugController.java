package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddBugController {

    @FXML
    private TextField projectField;
    @FXML
    private TextField versionField;
    @FXML
    private TextField companyField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private RadioButton statusRadioButton;
    @FXML
    private Label dateEnteredField;

    DdHelper db = new DdHelper();

    public  void newBug() {
        String statusData;
        String project = projectField.getText();
        String version = versionField.getText();
        String company = companyField.getText();
        String description = descriptionField.getText();

        if(statusRadioButton.isSelected()){
            statusData = "yes";
        } else {
            statusData = "no";
        }
        db.addNewBug(project,version,company,statusData,description);
    }

    public void updateDateEnteredOnAddBug(String date){
        dateEnteredField.setText(date);
    }

}
