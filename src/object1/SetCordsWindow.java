package object1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetCordsWindow extends JDialog {


    public SetCordsWindow(Object_1 parentFrame){
        super(parentFrame, "Впишіть значення", true);
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
        JButton acceptButton = new JButton("Відправити");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем значения из текстовых полей
                String pointsText = pointsField.getText();
                String xminText = xMinField.getText();
                String xmaxText = xMaxField.getText();
                String yminText = yMinField.getText();
                String ymaxText = yMaxField.getText();

                // Преобразуем текст в необходимые типы данных
                int points = Integer.parseInt(pointsText);
                double xmin = Double.parseDouble(xminText);
                double xmax = Double.parseDouble(xmaxText);
                double ymin = Double.parseDouble(yminText);
                double ymax = Double.parseDouble(ymaxText);

                // Далее вы можете использовать полученные значения
                System.out.println("Points: " + points);
                System.out.println("Xmin: " + xmin);
                System.out.println("Xmax: " + xmax);
                System.out.println("Ymin: " + ymin);
                System.out.println("Ymax: " + ymax);

                dispose();
            }
        });

        acceptButton.setFocusPainted(false);
        panel.add(acceptButton, gbc);
        return panel;
    }



}
