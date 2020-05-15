package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";
    private boolean isFraction = false;
    private boolean inNumber = false;

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
        inNumber = true;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           if(result == (long) result) {
               display.setText(String.format("%d",(long) result));
           } else display.setText(String.format("%.3g", result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
            isFraction = false;
            inNumber = false;
        }
    }

    @FXML
    public void clearDisplay(ActionEvent event) {
        String acPressed = ((Button) event.getSource()).getText();
        System.out.println(acPressed);
        startNumber = true;
        isFraction = false;
        inNumber = false;
        operator = "";
        display.setText("0");
    }

    @FXML
    public void processDot(ActionEvent event) {
        String dotPressed = ((Button) event.getSource()).getText();
        System.out.println(dotPressed);
        if(isFraction || !inNumber) {
            return;
        }
        display.setText(display.getText() + ".");
        isFraction = true;
    }

    @FXML
    public void plusMinus(ActionEvent event) {
        String pmPressed = ((Button) event.getSource()).getText();
        System.out.println(pmPressed);
        if(display.getText().charAt(0) == '-') {
            display.setText(display.getText().substring(1));
        } else if(display.getText().equals("0")) {
            return;
        } else display.setText("-" + display.getText());
    }

}
