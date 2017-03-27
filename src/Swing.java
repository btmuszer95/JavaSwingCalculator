import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Currency;

public class Swing extends JFrame {


    JTextField number1TextField;
    JTextField number2TextField;
    JCheckBox currencyCheckBox;
    JRadioButton addButton;
    JRadioButton subtractButton;
    JRadioButton multiplyButton;
    JRadioButton divideButton;
    JButton calculateButton;
    String message = "";
    JTextField sliderValue;
    JSlider slider;
    Integer amountOfCalculations;
    double numberNo1;
    double numberNo2;
    double[] values;
    ButtonGroup group;
    double result;


    public Swing() throws HeadlessException {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        this.setSize(400, 400);
        int xPos = dim.width / 2 - this.getWidth() / 2;
        int yPos = dim.height / 2 - this.getHeight() / 2;
        this.setLocation(xPos, yPos);

        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setResizable(false);
        calculateButton = new JButton("Calculate");
        calculateButton.setBorderPainted(true);
        panel.add(calculateButton);

        JLabel label1 = new JLabel("number 1");
        panel.add(label1);

        number1TextField = new JTextField();
        number1TextField.setPreferredSize(new Dimension(70, 24));
        panel.add(number1TextField);

        JLabel label2 = new JLabel("number 2");
        panel.add(label2);

        number2TextField = new JTextField();
        number2TextField.setPreferredSize(new Dimension(70, 24));
        panel.add(number2TextField);

        JPanel typPanel = new JPanel();
        typPanel.setLayout(new FlowLayout());
        panel.add(typPanel);

        currencyCheckBox = new JCheckBox("Currency");
        currencyCheckBox.setBorderPainted(true);
        typPanel.add(currencyCheckBox);


        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new FlowLayout());
        radioButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "operation type", 1, 1));


        addButton = new JRadioButton("add");
        subtractButton = new JRadioButton("subtract");
        multiplyButton = new JRadioButton("multiply");
        divideButton = new JRadioButton("divide");

        addButton.setSelected(true);

        group = new ButtonGroup();
        group.add(addButton);
        group.add(subtractButton);
        group.add(multiplyButton);
        group.add(divideButton);

        radioButtonPanel.add(addButton);
        radioButtonPanel.add(subtractButton);
        radioButtonPanel.add(multiplyButton);
        radioButtonPanel.add(divideButton);
        panel.add(radioButtonPanel);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout());


        JLabel iloscRazy = new JLabel("calculation's amount");
        sliderPanel.add(iloscRazy);

        slider = new JSlider(0, 20, 1);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setValueIsAdjusting(true);
        sliderPanel.add(slider);
        panel.add(sliderPanel);


        JPanel sliderValuePanel = new JPanel();
        sliderValuePanel.setLayout(new FlowLayout());


        JLabel sliderValueLabel = new JLabel("Slider current value : ");
        sliderValuePanel.add(sliderValueLabel);

        sliderValue = new JTextField();
        sliderValue.setPreferredSize(new Dimension(50, 24));
        sliderValue.setEditable(false);
        sliderValuePanel.add(sliderValue);

        panel.add(sliderValuePanel);

        ListenForMouse listenForMouse = new ListenForMouse();
        calculateButton.addMouseListener(listenForMouse);


        ListenForSlide listenForSlide = new ListenForSlide();
        slider.addChangeListener(listenForSlide);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private class ListenForMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) throws java.lang.ArithmeticException, java.lang.NumberFormatException, java.lang.NullPointerException {
            if (e.getSource() == calculateButton) {
                sliderExceptionSolve();
                if (addButton.isSelected()) {
                    getValuesAndnumberFormatExceptionSolve();
                    if (amountOfCalculations == 0) {
                        JOptionPane.showMessageDialog(Swing.this, message = "You can not perform calculation 0 times", "Result Window", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if(numberFormatExceptionSolveBoolean()) {
                            calculateAdd();
                            showResult();
                        }
                    }
                }
            }

            if (e.getSource() == calculateButton) {
                sliderExceptionSolve();
                if (multiplyButton.isSelected()) {
                    getValuesAndnumberFormatExceptionSolve();
                    if (amountOfCalculations == 0) {
                        JOptionPane.showMessageDialog(Swing.this, message = "You can not perform calculation 0 times", "Result Window", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if(numberFormatExceptionSolveBoolean()) {
                            calculateMultiply();
                            showResult();
                        }
                    }
                }
            }

            if (e.getSource() == calculateButton) {
                sliderExceptionSolve();
                if (subtractButton.isSelected()) {
                    getValuesAndnumberFormatExceptionSolve();
                    if (amountOfCalculations == 0) {
                        JOptionPane.showMessageDialog(Swing.this, message = "You can not perform calculation 0 times", "Result Window", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if(numberFormatExceptionSolveBoolean()) {
                            calculateSubtract();
                            showResult();
                        }
                    }
                }
            }

            if (e.getSource() == calculateButton) {
                sliderExceptionSolve();
                if (divideButton.isSelected()) {
                    getValuesAndnumberFormatExceptionSolve();
                    if (amountOfCalculations == 0) {
                        JOptionPane.showMessageDialog(Swing.this, message = "You can not perform calculation 0 times", "Result Window", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if(numberFormatExceptionSolveBoolean()) {
                            calculateDivide();
                            showResult();
                        }
                    }
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class ListenForSlide implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == slider) {
                if (slider.getValueIsAdjusting()) {
                    int sliderValueText = slider.getValue();
                    sliderValue.setText(Integer.toString(sliderValueText));
                }

            }
        }
    }

    private void sliderExceptionSolve() {
        try {
            amountOfCalculations = Integer.parseInt(sliderValue.getText());
        } catch (java.lang.NumberFormatException exc) {
            JOptionPane.showMessageDialog(Swing.this, message = "Please choose calculation's amount", "Result Window", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void getValuesAndnumberFormatExceptionSolve() {
        try {
            numberNo1 = Double.valueOf(number1TextField.getText());
            numberNo2 = Double.valueOf(number2TextField.getText());
            values = new double[2];
            values[0] = numberNo1;
            values[1] = numberNo2;
        } catch (java.lang.NumberFormatException | NullPointerException exc) {
            JOptionPane.showMessageDialog(Swing.this , message = "Please fill in the number fields" , "Result Window" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean numberFormatExceptionSolveBoolean() {
        try {
            numberNo1 = Double.valueOf(number1TextField.getText());
            numberNo2 = Double.valueOf(number2TextField.getText());
            return true;
        } catch (java.lang.NumberFormatException exc) {
            return false;
        }
    }


    private void showResult() {
        if (result == Double.POSITIVE_INFINITY) {
            JOptionPane.showMessageDialog(Swing.this, "Dividing by zero is impossible", "Wynik", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (currencyCheckBox.isSelected()) {
                JOptionPane.showMessageDialog(Swing.this, message = "Result : " + result + " " + Currency.getInstance(getLocale()), "Result Window", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(Swing.this, message = "Result : " + result, "Result Window", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }


    private double calculateAdd() {
        int iterations = slider.getValue();
        for (int i = 0; i < iterations; i++) {
            result = values[0] + values[1];
            values[0] = result;
        }
        return result;


    }

    private double calculateMultiply() {
        int iterations = slider.getValue();
        for (int i = 0; i < iterations; i++) {
            result = values[0] * values[1];
            values[0] = result;
        }
        return result;


    }

    private double calculateSubtract() {
        int iterations = slider.getValue();
        for (int i = 0; i < iterations; i++) {
            result = values[0] - values[1];
            values[0] = result;


        }
        return result;
    }

    private double calculateDivide() throws ArithmeticException {
        int iterations = slider.getValue();
        for (int i = 0; i < iterations; i++) {
            result = values[0] / values[1];
            values[0] = result;
        }

        return result;
    }
}