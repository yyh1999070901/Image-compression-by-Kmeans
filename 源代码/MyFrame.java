import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Integer.valueOf;

public class MyFrame extends JFrame {
    static ArrayList<Point> points = new ArrayList<>();
    static ArrayList<Point> center = new ArrayList<>();
    static JButton button = new JButton("开始聚类");
    static JTextField textField = new JTextField(10);
    static JLabel label = new JLabel("输入聚类数量");
    static int k;//聚类数量由文本框输入获得

    public MyFrame(String str)
    {
        super(str);
        //在内容面板添加控件
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(label);
        contentPane.add(textField);
        contentPane.add(button);
        //为按钮添加监听器
        MyButtonListener listener = new MyButtonListener();//为按钮添加监听器
        button.addActionListener(listener);
        //为面板添加监听器
        this.addMouseListener(new Monitor());
    }

    static public Point[] toPoints()//将屏幕上的点转换为kmean可以处理数据类型
    {
        Point[] points1 = new Point[points.size()];
        for(int i=0;i<points.size();i++)
        {
            Point p=points.get(i);
            points1[i]=new Point();
            points1[i]=p;
        }
        return points1;
    }

    private class MyButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //返回聚类中心，加入到arraylist中,画出arratlist中的点，簇心用其他颜色表示
            k=valueOf(textField.getText());
            Kmean hangKmean = new Kmean();
            int cluster[][]=hangKmean.kmean(toPoints(),k);//cluster表示属于第i类簇的点有哪些

            Point[] oldCenter = new Point[hangKmean.old_center.length];
            for(int i=0;i<oldCenter.length;i++)
            {
                center.add((Point) hangKmean.old_center[i]);
            }

            paint(getGraphics());
        }
    }


    class Monitor extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            addPoint(new Point(e.getX(),e.getY(),-1));
            repaint();
        }
    }

    public void addPoint(Point p)
    {
        points.add(p);
    }

    public void paint(Graphics g)
    {
        System.out.println("paint");
        Iterator i = points.iterator();
        while(i.hasNext()) {
            Point p = (Point)i.next();
            System.out.print(p.x+" "+p.y+'\n');
            g.setColor(Color.pink);
            g.fillOval(p.x,p.y,5,5);
        }
        i=center.iterator();
        while(i.hasNext()){
            Point p = (Point)i.next();
            System.out.print(p.x+" "+p.y+'\n');
            g.setColor(Color.black);
            g.fillOval(p.x,p.y,5,5);
        }
        label.requestFocus();
        textField.requestFocus();
        button.requestFocus();
    }

}
