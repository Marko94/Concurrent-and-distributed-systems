package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{

	private int m,n;

	public GUI() {
		initComponents();
	}

	private void initComponents(){
		setSize(200,200);
		JLabel sirina=new JLabel("Sirina tabele:");
		JTextField sirinaT=new JTextField();

		JLabel visina=new JLabel("Visina tabele:");
		JTextField visinaT=new JTextField();

		JButton ok=new JButton("OK");

		setTitle("Connect Five");
		JPanel podaci=new JPanel();
		JPanel drugi=new JPanel();

		podaci.setLayout(new GridLayout(3, 3));
		drugi.setLayout(new BorderLayout());

		podaci.add(sirina);
		podaci.add(sirinaT);
		podaci.add(visina);
		podaci.add(visinaT);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(""+sirinaT.getText());
				System.out.println(""+visinaT.getText());
				m=Integer.parseInt(visinaT.getText());
				n=Integer.parseInt(sirinaT.getText());
				Tabela tabla=new Tabela(n, m);
				if(tabla.isVisible()==false){
					tabla.setVisible(true);
				}
				//podaci.setLayout(new GridLayout(n, m));

				//drugi.add(initTable());
				//add(drugi);


			}
		});
		podaci.add(ok);

		add(podaci);
	}

	/*private JPanel initTable(){
		JPanel tabela=new JPanel();
		tabela.setLayout(new GridLayout(n, m));
		JLabel label=new JLabel("X");
		tabela.add(label);
		return tabela;
	}*/
}