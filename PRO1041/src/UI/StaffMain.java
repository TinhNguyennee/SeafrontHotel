package UI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;

public class StaffMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private PanelMain panelmain;
	private PanelBooking panelbooking;
	private PanelCustomer panelcustomer;
	private PanelBill panelbill;
	private PanelService panelservice;
//	private ChangePassword changepassword;
	private PanelCustomerChild panelcustomerchild;
	private PanelPrintBill panelprintbill;
	private PanelPay panelpay;
	private JPanel panel;

	public PanelService getPanelservice() {
		return panelservice;
	}

	public void setPanelservice(PanelService panelservice) {
		this.panelservice = panelservice;
	}

	public PanelBill getPanelbill() {
		return panelbill;
	}

	public void setPanelbill(PanelBill panelbill) {
		this.panelbill = panelbill;
	}

	public PanelCustomer getPanelcustomer() {
		return panelcustomer;
	}

	public void setPanelcustomer(PanelCustomer panelcustomer) {
		this.panelcustomer = panelcustomer;
	}

	public PanelBooking getPanelbooking() {
		return panelbooking;
	}

	public void setPanelbooking(PanelBooking panelbooking) {
		this.panelbooking = panelbooking;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMain frame = new StaffMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
//			    try {
//			    	  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			    	} catch (Exception e) {
//			    	  // handle exception
//			    	}
			}
		});
	}

	public PanelMain getPanelmain() {
		return panelmain;
	}

	public void setPanelmain(PanelMain panelmain) {
		this.panelmain = panelmain;
	}

	/**
	 * Create the frame.
	 */
	public StaffMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setLocationRelativeTo(contentPane);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		setUndecorated(true);
		MainMenu mm = new MainMenu(this);
		mm.setBounds(0, 0, 235, 550);


		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(mm);
		mm.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(235, 0, 665, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelmain = new PanelMain();
		panelmain.setVisible(true);
		panel.add(panelmain);
		
//		panelbooking = new PanelBooking(this);
//		panelbooking.setVisible(false);
//		panel.add(panelbooking);
//		
//		panelcustomer = new PanelCustomer();
//		panelcustomer.setVisible(false);
//		panel.add(panelcustomer);
//		
//		panelbill = new PanelBill();
//		panelbill.setVisible(false);
//		panel.add(panelbill);
//		
//		panelservice = new PanelService();
//		panelservice.setVisible(false);
//		panel.add(panelservice);
		
//		changepassword = new ChangePassword();
//		changepassword.setVisible(false);
		
//		panelcustomerchild = new PanelCustomerChild();
//		panelcustomerchild.setVisible(false);

		

		
	


		
	}
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public PanelPay getPanelpay() {
		return panelpay;
	}

	public void setPanelpay(PanelPay panelpay) {
		this.panelpay = panelpay;
	}


	public PanelPrintBill getPanelprintbill() {
		return panelprintbill;
	}

	public void setPanelprintbill(PanelPrintBill panelprintbill) {
		this.panelprintbill = panelprintbill;
	}

	public PanelCustomerChild getPanelcustomerchild() {
		return panelcustomerchild;
	}

	public void setPanelcustomerchild(PanelCustomerChild panelcustomerchild) {
		this.panelcustomerchild = panelcustomerchild;
	}

//	public ChangePassword getChangepassword() {
//		return changepassword;
//	}
//
//	public void setChangepassword(ChangePassword changepassword) {
//		this.changepassword = changepassword;
//	}
}
