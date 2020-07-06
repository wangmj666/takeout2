package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel(new GridLayout(3,2));
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("��½");
	private JButton btnCancel = new JButton("�˳�");
	private JButton btnRegister = new JButton("ע��");
	
	private JButton btnrshop = new JButton("�̼�ע��");
	private JButton btnruser = new JButton("�û�ע��");
	private JButton btnrrider = new JButton("����ע��");
	
	private JButton btnlshop = new JButton("�̼ҵ�½");
	private JButton btnluser = new JButton("�û���½");
	private JButton btnlrider = new JButton("���ֵ�½");
	
	private JLabel labelUser = new JLabel("�˺�ID��");
	private JLabel labelPwd = new JLabel("����/������");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(this.btnrshop);
		toolBar.add(btnLogin);
		toolBar.add(btnlshop);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);//
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		btnlshop.addActionListener(this);
		
		this.btnrshop.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			String userid=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if(e.getSource() == this.btnlshop) {
			String shopid=this.edtUserId.getText();
			String shopname=new String(this.edtPwd.getPassword());
			try {
				Business_information.currentLoginshop= PersonPlanUtil.shopManager.login(shopid, shopname);
				} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} 
		else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"ע��",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnrshop){
			Frmrshop dlg=new Frmrshop(this,"ע��",true);
			dlg.setVisible(true);
		}
	}

}
