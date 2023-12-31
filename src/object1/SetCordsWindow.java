package object1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetCordsWindow extends JDialog {

    private Object_1 parentFrame;
    public SetCordsWindow(Object_1 parentFrame){
        super(parentFrame, "Впишіть значення", true);
        this.parentFrame = parentFrame;
        createDialog();
        this.setVisible(true);
    }

    private void createDialog(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        this.setBounds(dimension.width / 2 - 215, dimension.height / 2 - 150, 430, 300);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(getAndInitPanel());
    }

    private JPanel getAndInitPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Number Of Points:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField pointsField = new JTextField(28);
        panel.add(pointsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Xmin:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField xMinField = new JTextField(28);
        panel.add(xMinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Xmax:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField xMaxField = new JTextField(28);
        panel.add(xMaxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Ymin:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField yMinField = new JTextField(28);
        panel.add(yMinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Ymax:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JTextField yMaxField = new JTextField(28);
        panel.add(yMaxField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton acceptButton = initAcceptButton(pointsField, xMinField, xMaxField, yMinField, yMaxField);
        panel.add(acceptButton, gbc);

        return panel;
    }

    private JButton initAcceptButton(JTextField pointsField, JTextField xMinField,
                                     JTextField xMaxField, JTextField yMinField, JTextField yMaxField){
        JButton acceptButton = new JButton("Відправити");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pointsText = pointsField.getText();
                String xminText = xMinField.getText();
                String xmaxText = xMaxField.getText();
                String yminText = yMinField.getText();
                String ymaxText = yMaxField.getText();

                try {
                    int points = Integer.parseInt(pointsText);
                    double xmin = Double.parseDouble(xminText);
                    double xmax = Double.parseDouble(xmaxText);
                    double ymin = Double.parseDouble(yminText);
                    double ymax = Double.parseDouble(ymaxText);

                    if(isCorrectData(points, xmin, xmax, ymin, ymax)){
                        parentFrame.setDatasToUpdateObject_2(points, xmin, xmax, ymin, ymax);
                    }

                    dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Будь ласка введіть всі значення",
                            "Неправильно введені значення", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        acceptButton.setFocusPainted(false);
        return acceptButton;
    }


    private boolean isCorrectData(int points, double xmin, double xmax, double ymin, double ymax){
        if(points <= 1){
            JOptionPane.showMessageDialog(null, "Кількість точок має бути більше за 1",
                    "Неправильно введені значення", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (xmin > xmax || ymin > ymax){
            JOptionPane.showMessageDialog(null, "Значення Xmin і Ymin мають бути менші за Xmax і Ymax",
                    "Неправильно введені значення", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
