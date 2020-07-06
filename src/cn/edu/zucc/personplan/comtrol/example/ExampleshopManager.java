package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.itf.IshopManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.*;
public class ExampleshopManager implements IshopManager {

	@Override
	public Business_information reg(String shopid, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		
		
		if(shopid==null||"".equals(shopid))throw new BusinessException("ID不能为空");
		//if(!pwd.equals(pwd2))throw new BusinessException("两次输入商店名字不一致");
		if(pwd==null||"".equals(pwd))throw new BusinessException("shop name不能为空");
		Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from Business_information where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆shop ID账号已经存在");
			
			rs.close();
			pst.close();
			
			sql="insert into Business_information(shop_id,shop_name,Merchants_star) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, shopid);
			pst.setString(2, pwd);
			pst.setString(3, pwd2);
			pst.execute();
			rs.close();
			pst.close();
			
			Business_information shoper=new Business_information();
			shoper.setShop_id(shopid);
			shoper.setShop_name(pwd);
			shoper.setMerchants_star(pwd2);
			System.out.println("店注册完毕");
			return shoper;
			
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
	public Business_information login(String shopid, String shopname) throws BaseException {
		// TODO Auto-generated method stub
		if(shopid==null||"".equals(shopid))throw new BusinessException("shop ID不能为空");
		if(shopname==null||"".equals(shopname))throw new BusinessException("shop name不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from Business_information where shop_id=? and shop_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shopid);	
			pst.setString(2,shopname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("shop ID不存在或shop name错误");
			
			rs.close();
			pst.close();
			
			Business_information shoper=new Business_information();
			shoper.setShop_id(shopid);
			shoper.setShop_name(shopname);
			System.out.println("核对完毕");
			return shoper;
			
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


	/*@Override
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
*/
}
