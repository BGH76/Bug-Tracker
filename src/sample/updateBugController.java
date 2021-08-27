package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class updateBugController implements Initializable {

    @FXML
    private Label bugId, dateEnteredLabel, projectLabel, companyLabel, versionLabel, lastUpdatedLabel;
    @FXML
    private TextArea descriptionField;
    @FXML
    private RadioButton fixedRadioButton;
    String i;
    String stat;

    //TODO define i and j better to show Label -> String -> int for database selection of id

    public void display(String id, String dateEntered, String prooject, String company, String version, String lastUpdate, String status, String description){
        bugId.setText(id);
        dateEnteredLabel.setText(dateEntered);
        projectLabel.setText(prooject);
        companyLabel.setText(company);
        versionLabel.setText(version);
        lastUpdatedLabel.setText(lastUpdate);
        this.stat = status;
        descriptionField.setText(description);
        i = id;
        System.out.println(stat);
        if(stat.equals("y")){
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
            temp = "y";
        } else {
            temp = "n";
        }
        String descript = descriptionField.getText();
        db.updateBugStatusAndDescription(j, temp, descript);
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
//    TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
//    int row = pos.getRow();
//
//    // Item here is the table view type:
//    Item item = table.getItems().get(row);
//
//    TableColumn col = pos.getTableColumn();
//
//    // this gives the value in the selected cell:
//    String data = (String) col.getCellObservableValue(item).getValue();