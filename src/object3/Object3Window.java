package object3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Object3Window extends JFrame {
    private Image icon = new ImageIcon("res/Object3WindowIcon.png").getImage();
    private final GraphPanel graphPanel;

    protected Object3Window(Object_3 object3, double[] xList, double[] yList){

        this.setTitle("Object3");
        this.setBounds(900, 200, 600, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setIconImage(icon);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                object3.destroyObject3Window();
            }
        });


        graphPanel = new GraphPanel(xList, yList);

        this.add(graphPanel);
        this.setVisible(true);
    }

    protected void updateGraphic(double[] xList, double[] yList){
        graphPanel.repaintGraphPanel(xList, yList);
    }

}

 class GraphPanel extends JPanel {

    private double[] xList;
    private double[] yList;
    private final Stroke AXIS_STROKE = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    private final Stroke GRAPHIC_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    protected GraphPanel(double[] xList, double[] yList){
        this.xList = xList;
        this.yList = yList;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.setStroke(AXIS_STROKE);

        g2.draw(new Line2D.Double(new Point2D.Double(0.0, 360.0), new Point2D.Double(600, 360.0)));
        g2.draw(new Line2D.Double(new Point2D.Double(3.0, 400.0), new Point2D.Double(3.0, 0.0)));

        g2.setColor(Color.RED);
        g2.setStroke(GRAPHIC_STROKE);

        for(int i = 0; i < xList.length; i++){

            if(i == xList.length - 1){
                break;
            } else {
                Point2D point1 = new Point2D.Double(xList[i], yList[i]);
                Point2D point2 = new Point2D.Double(xList[i + 1], yList[i + 1]);
                g2.draw(new Line2D.Double(point1, point2));
            }
        }
    }

    protected void repaintGraphPanel(double[] xList, double[] yList){
        this.xList = xList;
        this.yList = yList;
        repaint();
    }

}
