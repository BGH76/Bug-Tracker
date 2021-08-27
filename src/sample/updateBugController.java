package sample;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class updateBugController {

    @FXML
    private Label bugId, dateEnteredLabel, projectLabel, companyLabel, versionLabel, lastUpdatedLabel;
    @FXML
    private TextArea descriptionField;
    @FXML
    private RadioButton fixedRadioButton;

    // i is used to parsed into an int for the database helper class to query for id. id comes in as a string.
    //  The display method converts id to a Label for displaying on dialog box.
    // id is also stored in i and the updateBugSandD method parses i into an int stored in j.
    // j is sent to the database helper class as an int.
    private String i;

    public void display(String id, String dateEntered, String prooject, String company, String version, String lastUpdate, String status, String description){

        bugId.setText(id);
        dateEnteredLabel.setText(dateEntered);
        projectLabel.setText(prooject);
        companyLabel.setText(company);
        versionLabel.setText(version);
        lastUpdatedLabel.setText(lastUpdate);
        descriptionField.setText(description);
        i = id;
        if(status.equals("yes")){
            fixedRadioButton.setSelected(true);
        } else {
            fixedRadioButton.setSelected(false);
        }
    }

    public void updateBugSandD(){
        DdHelper db = new DdHelper();
        String temp;
        int j = Integer.parseInt(i);
        if(fixedRadioButton.isSelected()){
            temp = "yes";
        } else {
            temp = "no";
        }
        String descript = descriptionField.getText();
        db.updateBugStatusAndDescription(j, temp, descript);
    }
}
