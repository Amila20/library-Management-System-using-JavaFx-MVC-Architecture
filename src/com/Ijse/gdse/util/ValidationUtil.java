package com.Ijse.gdse.util;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;


// Validation Util Class for all validation, you can change the logic as you want
public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField, Pattern> map, Button btn) {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()){
                //if the input is not matching
                addError(key,btn);
                return key;
            }
            removeError(key,btn);
        }
        return true;
    }

    private static void removeError(TextField txtField, Button btn) {
        txtField.getParent().setStyle("-fx-border-color: Green");
        btn.setDisable(false);
    }

    private static void addError(TextField txtField, Button btn) {
        if (txtField.getText().length() > 0) {
            txtField.getParent().setStyle("-fx-border-color: red");
        }
        btn.setDisable(true);
    }

}
