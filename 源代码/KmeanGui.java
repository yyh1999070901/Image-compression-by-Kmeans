//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.border.MatteBorder;
//import java.util.*;
//
//public class KmeanGui {
//    public static void main(String[] args) {
//        MyFrame myFrame = new MyFrame();
//        myFrame.createGUI();
//    }
//}
//
//class MyFrame extends JFrame{
//    static ArrayList points = null;
//    static JButton button = new JButton("开始聚类");
//    static JTextField textField = new JTextField(10);
//    static JLabel label = new JLabel("输入聚类数量");
//
//    public void createGUI()
//    {
//        points= new ArrayList();
//
//        JFrame frame = new JFrame("demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.addMouseListener(new Monitor());
//
//        Container contentPane = frame.getContentPane();
//        contentPane.setLayout(new FlowLayout());
//
//        contentPane.add(label);
//        contentPane.add(textField);
//        contentPane.add(button);
//        MyButtonListener listener = new MyButtonListener();//为按钮添加监听器
//        button.addActionListener(listener);
//
//        this.addMouseListener(new Monitor());//单击屏幕画点
//
//        frame.setSize(400,300);
//        frame.setVisible(true);
//    }
//
////    MyFrame(String s){
////        super(s);
////        points= new ArrayList();
////        setLayout(null);
////        setBounds(350,350,400,300);
////        this.addMouseListener(new Monitor());
////
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////        //内容面板
////        Container contentPane = getContentPane();
////        contentPane.setLayout(new FlowLayout());
////
////        //在内容面板中添加控件
////        contentPane.add(label);
////        contentPane.add(textField);
////        contentPane.add(button);
////
////
////        MyButtonListener listener = new MyButtonListener();
////        button.addActionListener(listener);
////
////        this.repaint();
////        setVisible(true);
////    }
//
//    private class MyButtonListener implements  ActionListener
//    {
//        public void actionPerformed(ActionEvent e)
//        {
//            showdian();
//        }
//
//    }
//
//    public void showdian()
//    {
//        Iterator i = points.iterator();
//        while(i.hasNext()) {
//            Point p = (Point)i.next();
//            System.out.print(p.x+" "+p.y+'\n');
//        }
//    }
//
//    public void paint(Graphics g) {
//        Iterator i = points.iterator();
//        while(i.hasNext()) {
//            Point p = (Point)i.next();
//            g.setColor(Color.pink);
//            g.fillOval(p.x,p.y,10,10);
//        }
//    }
//
//    public void addPoint(Point p) {
//        points.add(p);
//    }
//}
//
//class Monitor extends MouseAdapter{
//    public void mousePressed(MouseEvent e) {
//        MyFrame mf = (MyFrame)e.getSource();
//        mf.addPoint(new Point(e.getX(),e.getY()));
//        mf.repaint();
//    }
//}