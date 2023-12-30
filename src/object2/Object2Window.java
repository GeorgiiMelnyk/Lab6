package object2;

import javax.swing.*;

public class Object2Window extends JFrame {
    public Object2Window() {
        this.setTitle("Object2");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel("Отримані дані: ");
        setVisible(true);
        // Здесь вы можете добавить код для взаимодействия с Object1
    }
}
