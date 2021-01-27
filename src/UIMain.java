import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JLabel;

public class UIMain extends JFrame implements WindowListener{

	//private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField txtKeyword = null;
	private JButton btSearch = null;
	private JButton btPrev = null;
	private JButton btNext = null;
	private Thumnail tm=null;
	private JTextArea lbContent = null;
	private int page=1;
	private JLabel lbPage = null;
	private JLabel lbinfo = null;
	private String prevlink="";
	private JLabel lbState = null;
	public UIMain() {
		super();
		try {
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		    /*
		     metal - "javax.swing.plaf.metal.MetalLookAndFeel"
			motif - "com.sun.java.swing.plaf.motif.MotifLookAndFeel"
				windows - "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
		     */
		} catch (Exception exc){System.out.println(exc.toString());}
		
		initialize();
		addWindowListener(this);	//���������� ����
		

	}
	private void initialize() {
		this.setSize(500, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("Film Guide");
	}
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbState = new JLabel();
			lbState.setBounds(new Rectangle(61, 231, 299, 23));
			lbState.setText("");
			lbinfo = new JLabel();
			lbinfo.setBounds(new Rectangle(60, 210, 120, 15));
			lbinfo.setText("click");
			lbPage = new JLabel();
			lbPage.setBounds(new Rectangle(410, 230, 29, 24));
			lbPage.setText("0");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTxtKeyword(), null);
			jContentPane.add(getBtSearch(), null);
			jContentPane.add(getBtPrev(), null);
			jContentPane.add(getBtNext(), null);
			jContentPane.add(getThumnail(), null);
			jContentPane.add(getContents(),null);
			jContentPane.add(lbPage, null);
			jContentPane.add(lbinfo, null);
			jContentPane.add(lbState, null);
			
		}
		return jContentPane;
	}
	private JTextField getTxtKeyword() {
		if (txtKeyword == null) {
			txtKeyword = new JTextField();
			txtKeyword.setBounds(new Rectangle(0, 5, 430, 30));
		}
		return txtKeyword;
	}
	private JButton getBtSearch() {
		if (btSearch == null) {
			btSearch = new JButton();
			btSearch.setBounds(new Rectangle(430, 5, 53, 30));
			btSearch.setText("Go");
			btSearch.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					lbState.setText("");
					page=1;
					lbPage.setText("1");
					xml x=new xml();
					x.xmlRead(txtKeyword.getText(),page);
					if(xml.title=="nullpointer"){
						lbState.setText("�˻� ����� �����ϴ�.");
					}
					else{
						//�泻��, ���� ������ �ٽ�
						jContentPane.remove(tm);
						jContentPane.add(getThumnail(), null);
						lbContent.setText("<Details>\n"+xml.title +xml.subtitle + "  "+ xml.pubDate+"\n\nDirector: "+xml.director +"\nActors: "+xml.actor +"\n\nRate: "+ xml.rate);
					}
				}
			});
		}
		return btSearch;
	}
	private Thumnail getThumnail() {
		if (tm == null) {
			tm = new Thumnail();
			tm.setBounds(new Rectangle(60, 50, 120, 160));
			tm.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					try{
						 String[] path = {"C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE",xml.link};
						 Runtime rt = Runtime.getRuntime();
						 rt.exec(path);
					}catch(Exception ex){System.out.println("������ ���� ����\nInternet Explorer�� ����Ʈ ��ο� ��ġ�� Windows���� ���� ����.");}
				}
			});
		}
		return tm;
	}
	private JTextArea getContents(){
		if(lbContent==null){
			lbContent=new JTextArea();
			lbContent.setLineWrap(true);
			if(xml.title=="")
				lbContent.setText("\n\tFilmGuide\n\t���� �׸��� Ŭ���ϸ�\n\t���� ��ũ�� �����ϴ�.\n\n\t�������б� ��ǻ�Ͱ���\n\t  ������,�̽���");
			else
				lbContent.setText("<Details>\n"+xml.title +xml.subtitle + "  "+ xml.pubDate+"\n\nDirector: "+xml.director +"\nActors: "+xml.actor +"\n\nRate: "+ xml.rate);
			lbContent.setBounds(new Rectangle(185, 35, 220, 220));
			lbContent.setEditable(false);
			lbContent.setBackground(jContentPane.getBackground());
		}
		return lbContent;
	}
	private JButton getBtPrev() {
		if (btPrev == null) {
			btPrev = new JButton();
			btPrev.setBounds(new Rectangle(0, 35, 50, 220));
			btPrev.setText("<");
			btPrev.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//���ʹ�ư
					lbState.setText("");
					if(page<=1){
						System.out.println("ù��° ������ �Դϴ�.");
						lbState.setText("ù��° ������");
						lbPage.setText(Integer.toString(page));
						return;
					}
					page--;
					lbPage.setText(Integer.toString(page));
					xml x=new xml();
					x.xmlRead(txtKeyword.getText(),page);
					//�泻��, ���� ������ �ٽ�
					jContentPane.remove(tm);
					jContentPane.add(getThumnail(), null);
					lbContent.setText("<Details>\n"+xml.title +xml.subtitle + "  "+ xml.pubDate+"\n\nDirector: "+xml.director +"\nActors: "+xml.actor +"\n\nRate: "+ xml.rate);
				}
			});
		}
		return btPrev;
	}
	private JButton getBtNext() {
		if (btNext == null) {
			btNext = new JButton();
			btNext.setBounds(new Rectangle(430, 35, 50, 220));
			btNext.setText(">");
			btNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//������ ��ư
					prevlink=xml.link;
					page++;
					xml x=new xml();
					x.xmlRead(txtKeyword.getText(),page);
					lbState.setText("");
					if(prevlink.equals(xml.link)){
						page--;
						System.out.println("������ ������ �Դϴ�");
						lbState.setText("������ ������");
					}
					lbPage.setText(Integer.toString(page));
					jContentPane.remove(tm);
					jContentPane.add(getThumnail(), null);
					lbContent.setText("<Details>\n"+xml.title +xml.subtitle + "  "+ xml.pubDate+"\n\nDirector: "+xml.director +"\nActors: "+xml.actor +"\n\nRate: "+ xml.rate);
				}
			});
		}
		return btNext;
	}
	
//���������� ����
	public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}

}
