package object2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Object2Window extends JFrame {
    private JTextArea textArea = new JTextArea();
    private Image icon = new ImageIcon("res/Object2WindowIcon.png").getImage();
    private double[] xList;
    private double[] yList;

    protected Object2Window(Object_2 object2, double[] xList, double[] yList) {
        this.setTitle("Object2");
        this.setBounds(600, 200, 250, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(icon);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                object2.destroyObject2Window();
            }
        });

        this.xList = xList;
        this.yList = yList;

        textArea.setEditable(false);
        textArea.setEnabled(false);
        textArea.setForeground(Color.BLACK);
        setTextArea(this.xList, this.yList);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        setVisible(true);
    }

    protected void setTextArea(double[] xList, double[] yList ){

        String result;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < xList.length; i++){

            String currentX = String.valueOf(xList[i]);
            String currentY = String.valueOf(yList[i]);
            String curIndex = String.valueOf((i + 1));

            stringBuilder.append("x").append(curIndex).append("=");
            stringBuilder.append(currentX);
            stringBuilder.append("     ");
            stringBuilder.append("y").append(curIndex).append("=");
            stringBuilder.append(currentY);
            stringBuilder.append("\n");
        }

        result = stringBuilder.toString();

        textArea.setText(result);
    }

}
