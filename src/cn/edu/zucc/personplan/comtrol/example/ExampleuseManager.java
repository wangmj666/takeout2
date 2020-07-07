package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;

import cn.edu.zucc.personplan.itf.IRiderManager;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.itf.IshopManager;
import cn.edu.zucc.personplan.itf.IuseManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.model.Rider;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.*;
public class ExampleuseManager implements IuseManager {

	public User reg(String uname,String utel,String usex, String umail,String ucity,String vip,String pwd,String pwd2 ) throws BaseException{
		// TODO Auto-generated method stub
		
		
		if(uname==null||"".equals(uname))throw new BusinessException("姓名不能为空");
		if(utel==null||"".equals(utel))throw new BusinessException("电话不能为空");
		if(umail==null||"".equals(umail))throw new BusinessException("邮箱不能为空");
		if(ucity==null||"".equals(ucity))throw new BusinessException("城市不能为空");
		if(usex==null||"".equals(usex))throw new BusinessException("性别不能为空");
		if(vip==null||"".equals(vip))throw new BusinessException("vip不能为空");
		if(pwd==null||"".equals(pwd))throw new BusinessException("密码不能为空");
		if(!pwd.equals(pwd2))throw new BusinessException("两次输入密码不一致");
		
		Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from user where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,uname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆用户已经存在");
			rs.close();
			pst.close();
			System.out.println("cha1");
			
			
			sql="insert into user(user_name,user_sex,user_pwd,user_tel,user_mail,user_city,user_regdate,vip) values(?,?,?,?,?,?,now(),?)";
			System.out.println("cha2");
			pst=conn.prepareStatement(sql);System.out.println("cha3");
			pst.setString(1, uname);
			pst.setString(2, usex);
			pst.setString(3, pwd);
			pst.setString(4, utel);
			pst.setString(5, umail);
			pst.setString(6, ucity);
			pst.setString(7,vip);
			//pst.setString(8, x);
			pst.execute();
			rs.close();
			pst.close();
			
			User  uu=new User();
			uu.setUser_city(ucity);
			
			uu.setUser_mail(umail);
			uu.setUser_name(uname);
			uu.setUser_pwd(pwd);
			uu.setUser_regdate(new Date());
			uu.setUser_sex(usex);
			uu.setUser_tel(utel);
			uu.setVip(vip);
			//uu.setVip_lastdate(vip_lastdate);
			return uu;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

	
	public User login(String uname,String pwd)throws BaseException{
	// TODO Auto-generated method stub
		if(uname==null||"".equals(uname))throw new BusinessException("用户名不能为空");
		if(pwd==null||"".equals(pwd))throw new BusinessException("密码不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user where user_name=? and user_pwd=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,uname);	
			pst.setString(2,pwd);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在或密码错误");
			
			rs.close();
			pst.close();
			User  uu=new User();
			uu.setUser_name(uname);
			uu.setUser_pwd(pwd);
			return uu;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}


	@Override
	public void changePwd(User user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(user==null)throw new BusinessException("用户名不为空");
		if(!newPwd.equals(newPwd2))throw new BusinessException("修改中两次输入密码不一致");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from user where user_name=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1)))throw new BusinessException("登陆密码错误");
			rs.close();
			pst.close();
			
			sql="select * from user where user_id=? and user_pwd=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_name());
			pst.setString(2,user.getUser_pwd());
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆密码错误");
			
			rs.close();
			pst.close();
			
			sql="update user set user_pwd=? where user_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,newPwd);
			pst.setString(2,user.getUser_name());
			pst.execute();
			pst.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
