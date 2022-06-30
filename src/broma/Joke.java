package broma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Joke {

	public static void main(String[] args) {
		
		JFrame marco=new JFrame();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int ancho=Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto=Toolkit.getDefaultToolkit().getScreenSize().height;
		marco.setSize(ancho, alto);
		marco.setResizable(false);
		
		marco.add(new panel(ancho, alto));
		marco.setVisible(true);

	}

}

class panel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton bt_si, bt_no;
	private JLabel tlt;
	private int w, h;
	
	public panel(int ancho, int alto) {
		
		setLayout(null);
		
		setBackground(Color.WHITE);
		
		w=ancho;
		h=alto;
		
		tlt=new JLabel("¿Quieres salir conmigo?");
		
		tlt.setFont(new Font("Verdana", Font.BOLD, 42));
		
		tlt.setBounds((ancho/2)-(600/2), 40, 600, 50);
		
		add(tlt);
		
		bt_si=new JButton("SI");
		
		bt_si.setFont(new Font("Arial", Font.BOLD, 16));
		
		bt_si.setBounds(ancho/4, (alto/2)-(50/2), 60, 50);
		
		add(bt_si);
		
		bt_si.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {

				bt_si.setForeground(Color.YELLOW.darker());
				bt_si.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			public void mouseExited(MouseEvent e) {
				bt_si.setForeground(Color.BLACK);
			}
			
			public void mousePressed(MouseEvent e) {

				bt_si.setForeground(Color.BLACK);
				love();

			}
			
			
		});
		
		bt_no=new JButton("NO");
		
		bt_no.setFont(new Font("Arial", Font.BOLD, 16));
		
		bt_no.setBounds(ancho-ancho/4, (alto/2)-(50/2), 60, 50);
		
		add(bt_no);
		
		bt_no.addMouseListener(new MouseAdapter() {
			
			private int opt=-1;
			
			public void mouseEntered(MouseEvent e) {
				
				opt=(int) (Math.random() * 10);
				
				if(opt>=0&&opt<3) {
					bt_no.setText("SÍ");
					bt_si.setText("NO");
					bt_no.setForeground(Color.YELLOW.darker());
					bt_no.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else if(opt>=3&&opt<6) {
					bt_no.setOpaque(false);
					bt_no.setFocusPainted(false);
					bt_no.setBorderPainted(false); 
					bt_no.setContentAreaFilled(false);
					bt_no.setText("");		
				}else {
					int x=ancho;
					int y=alto;
					
					while(x>ancho-60) {
						x=(int) (Math.random()*1000);
					}
					
					while(y>alto-500) {
						y=(int) (Math.random()*1000);
					}
					
					bt_no.setLocation(x, y);
				}
				
			}
			
			public void mouseExited(MouseEvent e) {
				
				bt_si.setText("SÍ");
				bt_no.setText("NO");
				bt_no.setForeground(Color.BLACK);

				bt_no.setOpaque(true);
				bt_no.setFocusPainted(true);
				bt_no.setBorderPainted(true); 
				bt_no.setContentAreaFilled(true);
				bt_no.setText("NO");
				
				bt_no.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
			
			public void mousePressed(MouseEvent e) {

				bt_si.setForeground(Color.BLACK);
				if(bt_no.getText().equals("SI")) {
					love();
				}else if(bt_no.getText().equals("NO")) {
					JOptionPane.showConfirmDialog(null, "Hmmm... no deberias poder darle que no \n¿Como pudiste?", "Uy uy uy, nono eh", JOptionPane.ERROR_MESSAGE);
				}

			}
			
			
		});
		
	}
	
	public void love (){
		
		remove(bt_no);
		remove(bt_si);
		remove(tlt);
		
		class love_panel extends JPanel{
			
			private static final long serialVersionUID = 1L;

			public love_panel(){
				setLayout(null);
				setBackground(new Color(253, 252, 252));
			}
			
			protected void paintComponent(Graphics g) {
				
			    super.paintComponent(g);
			    
		        Graphics2D g2d = (Graphics2D) g;
			    
			    Image img = new ImageIcon(Joke.class.getResource("/broma/love.gif")).getImage();
			    g.drawImage(img, 50, 50, w-100, h-100, this);
			    
		        g2d.setColor(Color.RED);
		        g2d.setFont(new Font("Verdana",Font.PLAIN,44));
		        g2d.drawString("Ps era obvio bby, buena elección ;) <3", 50, 50);
		        
			    updateUI();
			}
			
		}
		
		setLayout(new BorderLayout());
		
		add(new love_panel());

	    updateUI();

	}
	
}