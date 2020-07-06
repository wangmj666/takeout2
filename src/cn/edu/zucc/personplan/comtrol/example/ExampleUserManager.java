package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.*;
public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userid, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		
		
		if(userid==null||"".equals(userid))throw new BusinessException("用户名不能为空");
		if(!pwd.equals(pwd2))throw new BusinessException("两次输入密码不一致");
		if(pwd==null||"".equals(pwd))throw new BusinessException("密码不能为空");
		Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from tbl_user where user_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			
			rs.close();
			pst.close();
			
			sql="insert into tbl_user(user_id,user_pwd,register_time) values(?,?,now())";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			
			pst.execute();
			rs.close();
			pst.close();
			BeanUser user =new BeanUser();
			user.setRegister_time(new Date());
			user.setUser_id(userid);
			return user;
			
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
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null||"".equals(userid))throw new BusinessException("用户名不能为空");
		if(pwd==null||"".equals(pwd))throw new BusinessException("密码不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tbl_user where user_id=? and user_pwd=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);	
			pst.setString(2,pwd);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在或密码错误");
			
			rs.close();
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
		BeanUser user =new BeanUser();
		user.setUser_id(userid);
		user.setUser_pwd(pwd);
		return user;
	}


	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(user==null)throw new BusinessException("用户名不为空");
		if(!newPwd.equals(newPwd2))throw new BusinessException("修改中两次输入密码不一致");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from tbl_user where user_id=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(rs.getString(1)))throw new BusinessException("登陆密码错误");
			rs.close();
			pst.close();
			
			sql="select register_time from tbl_user where user_id=? and user_pwd=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			pst.setString(2,user.getUser_pwd());
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆密码错误");
			
			rs.close();
			pst.close();
			
			sql="update tbl_user set user_pwd=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,newPwd);
			pst.setString(2,user.getUser_id());
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
