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
	private JButton btnLogin = new JButton("µÇÂ½");
	private JButton btnCancel = new JButton("ÍË³ö");
	private JButton btnRegister = new JButton("×¢²á");
	
	private JButton btnrshop = new JButton("ÉÌ¼Ò×¢²á");
	private JButton btnruser = new JButton("ÓÃ»§×¢²á");
	private JButton btnrrider = new JButton("ÆïÊÖ×¢²á");
	
	private JButton btnlshop = new JButton("ÉÌ¼ÒµÇÂ½");
	private JButton btnluser = new JButton("ÓÃ»§µÇÂ½");
	private JButton btnlrider = new JButton("ÆïÊÖµÇÂ½");
	
	private JLabel labelUser = new JLabel("ÕËºÅID£º");
	private JLabel labelPwd = new JLabel("ÃÜÂë/ÐÕÃû£º");
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
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
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
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if(e.getSource() == this.btnlshop) {
			String shopid=this.edtUserId.getText();
			String shopname=new String(this.edtPwd.getPassword());
			try {
				Business_information.currentLoginshop= PersonPlanUtil.shopManager.login(shopid, shopname);
				} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} 
		else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"×¢²á",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnrshop){
			Frmrshop dlg=new Frmrshop(this,"×¢²á",true);
			dlg.setVisible(true);
		}
	}

}
