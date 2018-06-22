package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dates.DBOperation;
import dates.MyDBConnection;

public class Window implements MouseListener {

	public JFrame frame = new JFrame("��¼����");
	private JLabel label1=new JLabel("��Ϸid:");
	private JTextField txt1=new JTextField();
	private JLabel label2=new JLabel("��   ��:");
	private JTextField txt2=new JTextField();
	private JButton btn1=new JButton("��½");
	private JButton btn2=new JButton("ע��");
	private JButton btn3=new JButton("�˳�");
	private String text1;
	private String text2;
	private int distinguish;
	String DBDriver="com.mysql.jdbc.Driver";
	String DBURL="jdbc:mysql://localhost:3306/info";
	String DBUser="root";
	String DBPass="473721601";
	MyDBConnection myDB=new MyDBConnection(DBDriver,DBURL,DBUser,DBPass);
	public DBOperation myOpr=new DBOperation(myDB);
	public Window(){
	
	}
	
	public void show(){
		frame.setLayout(null);
		frame.setSize(470,300);
		frame.setLocation(400, 200);
		
		Font font=new Font("�����п�",Font.BOLD,20);
		label1.setFont(font);
		label1.setForeground(Color.gray);
		label2.setFont(font);
		
		label2.setForeground(Color.gray);
		txt1.setOpaque(false);
		txt2.setOpaque(false);
		
		btn1.setContentAreaFilled(false);
		btn1.setFont(font);
	    btn1.setForeground(Color.gray);
	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setContentAreaFilled(false);
		btn2.setFont(font);
		btn2.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setForeground(Color.gray);
	    btn3.setContentAreaFilled(false);
		btn3.setFont(font);
		btn3.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn3.setForeground(Color.gray);
		JPanel bj = new JPanel() {
			protected void paintComponent(Graphics g) {
				Image bg;
				try {
					bg = ImageIO.read(new File("src/image/��ʼ����.PNG"));
					g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	
	    label1.setBounds(100,50,100,100);
		txt1.setBounds(180,90, 150, 20);
		label2.setBounds(100,80,100,100);
		txt2.setBounds(180,120, 150, 20);
		btn1.setBounds(100,200,80,20);
		btn2.setBounds(190,200,80,20);
		btn3.setBounds(280,200,80,20);
		frame.setContentPane(bj);
		frame.setLayout(null);
		frame.add(label1);
		frame.add(txt1);
		frame.add(label2);
		frame.add(txt2);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(btn3);
		btn1.addMouseListener(this);
		btn2.addMouseListener(this);
		btn3.addMouseListener(this);
		frame.setVisible(true);	
	}
	
	public void mouseClicked(MouseEvent arg0) {
	   text1=txt1.getText();
	   text2=txt2.getText();
	   if(distinguish==1){
	   if(myOpr.selectName(text1)){
		   if(myOpr.selectPassword(text2)){
			   JOptionPane.showMessageDialog(null, "��½�ɹ�","��ʾ",2);
				txt1.setText("");
				txt2.setText("");
				distinguish=4;
				frame.setVisible(false);
		   }else{
				JOptionPane.showMessageDialog(null, "�������","��ʾ",2);
				txt2.setText("");
				myOpr.setNumber1();
				myOpr.setNumber2();
		   }
	   }else{
		   JOptionPane.showMessageDialog(null, "��id�����ڣ���ע��","��ʾ",2);
			txt1.setText("");
			txt2.setText("");
	   }
	   }
	   if(distinguish==2){
		   
		   String logi=(String) JOptionPane.showInputDialog(null,"���������id��\n","ע��",JOptionPane.PLAIN_MESSAGE,null,null,"��������"); 
		   String pas=(String) JOptionPane.showInputDialog(null,"������������룺\n","ע��",JOptionPane.PLAIN_MESSAGE,null,null,"��������");
			myOpr.insertData(logi,pas,0);
			JOptionPane.showMessageDialog(null, "ע��ɹ�","��ʾ",2);
	   }
	   if(distinguish==3){
		   int n = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�?", "��Ϸ����",JOptionPane.YES_NO_OPTION);
		   myDB.closeMyConnection();
			if(n==JOptionPane.YES_OPTION){
			System.exit(1);
			}
	   }
	   
	}

	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btn1) {
			distinguish=1;
			btn1.setForeground(Color.red);
			btn1.setBorder(BorderFactory.createLoweredBevelBorder());
			btn2.setForeground(Color.gray);
			btn2.setBorder(BorderFactory.createRaisedBevelBorder());
			btn3.setForeground(Color.gray);
			btn3.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		if (arg0.getSource() == btn2) {
			distinguish=2;
			btn1.setForeground(Color.gray);
			btn1.setBorder(BorderFactory.createRaisedBevelBorder());
			btn2.setForeground(Color.red);
			btn2.setBorder(BorderFactory.createLoweredBevelBorder());
			btn3.setForeground(Color.gray);
			btn3.setBorder(BorderFactory.createRaisedBevelBorder());
		}
		if (arg0.getSource() == btn3) {
			distinguish=3;
			btn1.setForeground(Color.gray);
			btn1.setBorder(BorderFactory.createRaisedBevelBorder());
			btn2.setForeground(Color.gray);
			btn2.setBorder(BorderFactory.createRaisedBevelBorder());
			btn3.setForeground(Color.red);
			btn3.setBorder(BorderFactory.createLoweredBevelBorder());
			}

	}
	public void mouseExited(MouseEvent arg0) {
		distinguish=0;
		label1.setForeground(Color.gray);
		label2.setForeground(Color.gray);
		txt1.setOpaque(false);
		txt2.setOpaque(false);
		btn1.setContentAreaFilled(false);
	    btn1.setForeground(Color.gray);
	    btn1.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setContentAreaFilled(false);
		btn2.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn2.setForeground(Color.gray);
	    btn3.setContentAreaFilled(false);
		btn3.setBorder(BorderFactory.createRaisedBevelBorder());
	    btn3.setForeground(Color.gray);
	}
	
	public void mousePressed(MouseEvent arg0) {
			
	}
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
	public String getText1(){
		return text1;
	}
	public String getText2(){
		return text2;
	}
	public int getDistinguish(){
		return distinguish;
	}
	
}

