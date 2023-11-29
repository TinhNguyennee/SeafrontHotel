/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;

import javax.swing.JComponent;

import Component.EventMenuSelected;

/**
 *
 * @author RAVEN
 */
public class ManagerMain extends javax.swing.JFrame {

	/**
	 * Creates new form Main
	 */
//    private Form_Home home;
//    private PanelRoom form1;
//    private PanelRoomType form2;
//    private PanelStaff form3;
//    private PanelInvoice form4;
//    private PanelServices form5;

	public ManagerMain() {
		initComponents();
		setBackground(new Color(0, 0, 0, 0));
//        home = new Form_Home();
//        form1 = new PanelRoom();
//        form2 = new PanelRoomType();
//        form3 = new PanelStaff();
//        form4 = new PanelInvoice();
//        form5 = new PanelServices();
		menu.initMoving(ManagerMain.this);
		menu.addEventMenuSelected(new EventMenuSelected() {
			@Override
			public void selected(int index) {
				if (index == 0) {
					setForm(new Form_Home());
				} else if (index == 1) {
					setForm(new PanelRoom());
				} else if (index == 2) {
					setForm(new PanelRoomType());
				} else if (index == 4) {
					setForm(new PanelStaff());
				} else if (index == 3) {
					setForm(new PanelInvoice());
				} else if (index == 8) {
					setForm(new PanelServices());
				} else if (index == 9) {
					setForm(new PanelStatistical());
				} else if (index == 10) {
					new ChangePassJFrame().setVisible(true);
				}
			}
		});
		// set when system open start with home form
		setForm(new Form_Home());
	}

	private void setForm(JComponent com) {
		mainPanel.removeAll();
		mainPanel.add(com);
		mainPanel.repaint();
		mainPanel.revalidate();
	}

	private void initComponents() {

		panelBorder1 = new Swing.PanelBorder();
		menu = new Component.Menu3();
		header2 = new Component.Header();
		mainPanel = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

		menu.setMinimumSize(new java.awt.Dimension(210, 550));

		header2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

		mainPanel.setOpaque(false);
		mainPanel.setLayout(new java.awt.BorderLayout());

		javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
		panelBorder1.setLayout(panelBorder1Layout);
		panelBorder1Layout.setHorizontalGroup(panelBorder1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBorder1Layout.createSequentialGroup()
						.addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
								.addGroup(panelBorder1Layout.createSequentialGroup().addGap(6, 6, 6)
										.addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()))));
		panelBorder1Layout
				.setVerticalGroup(
						panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
								.addGroup(panelBorder1Layout.createSequentialGroup()
										.addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelBorder1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelBorder1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ManagerMain.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManagerMain().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private Component.Header header2;
	private javax.swing.JPanel mainPanel;
	private Component.Menu3 menu;
	private Swing.PanelBorder panelBorder1;
	// End of variables declaration//GEN-END:variables
}
