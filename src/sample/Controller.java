package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    TextField textfield;
    @FXML
    Button enter;

    String name = null;
    String number = null;

    Logs log;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textfield.setText("Number with 10 digit");
        log = new Logs();
    }

    @FXML
    public void getName() throws IOException , JSONException {
        if(Character.isDigit(textfield.getText().charAt(0)) & textfield.getText().length() == 10 ) {
            number = textfield.getText();
            JSONObject json = NumberbookApi.readJsonFromUrl("http://146.148.112.105/caller/index.php/UserManagement/search_number?number=" + number.substring(1) + "&country_code=SA");
            String test[] = json.toString().split(",");
            int i = 0;

            while (i < test.length) {
                if (test[i].contains("name"))
                    name = test[i].replaceAll("\"", " ");
                i++;
            }
            textfield.setText("");
            JOptionPane.showMessageDialog(null, "Name : "+name.substring(name.indexOf(':')+1));
            log.write("Name :"+name.substring(name.indexOf(':')+1)+" number : "+number);
        }
        else
            JOptionPane.showMessageDialog(null, "ENTER A NUMBER \nWith 10 Digit" );
    }

    @FXML
    public void onMouseClicked(){
        if(textfield.getText().equals("Number with 10 digit"))
            textfield.setText("");
    }

    @FXML
    public void EnterKey(KeyEvent e) throws IOException,JSONException {

        if(e.getCode().equals(KeyCode.ENTER))
            getName();
    }


}
