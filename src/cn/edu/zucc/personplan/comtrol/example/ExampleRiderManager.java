package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.itf.IRiderManager;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.itf.IshopManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.model.Rider;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.*;
public class ExampleRiderManager implements IRiderManager {

	public Rider reg(String riderid, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		
		
		if(riderid==null||"".equals(riderid))throw new BusinessException("ID����Ϊ��");
		//if(!pwd.equals(pwd2))throw new BusinessException("���������̵����ֲ�һ��");
		if(pwd==null||"".equals(pwd))throw new BusinessException("rider name����Ϊ��");
		Connection conn=null;
		try {
			
			conn=DBUtil.getConnection();
			String sql="select * from rider where rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,riderid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("��½rider ID�˺��Ѿ�����");
			
			rs.close();
			pst.close();
			
			sql="insert into rider(rider_id,rider_name,joining_date,rider_level) values(?,?,now(),?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, riderid);
			pst.setString(2, pwd);
			pst.setString(3, pwd2);
			pst.execute();
			rs.close();
			pst.close();
			
			Rider rd=new Rider();
			rd.setRider_id(riderid);
			rd.setRider_name(pwd);
			rd.setRider_level(pwd2);
			return rd;
			
			
			
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

	
	public Rider login(String riderid, String ridername) throws BaseException {
		// TODO Auto-generated method stub
		if(riderid==null||"".equals(riderid))throw new BusinessException("rider ID����Ϊ��");
		if(ridername==null||"".equals(ridername))throw new BusinessException("rider name����Ϊ��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from rider where shop_id=? and shop_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,riderid);	
			pst.setString(2,ridername);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("rider ID�����ڻ�rider name����");
			
			rs.close();
			pst.close();
			
			Rider rd=new Rider();
			rd.setRider_id(riderid);
			rd.setRider_name(ridername);
			System.out.println("�˶����");
			return rd;
			
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
		if(user==null)throw new BusinessException("�û�����Ϊ��");
		if(!newPwd.equals(newPwd2))throw new BusinessException("�޸��������������벻һ��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd from tbl_user where user_id=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų�����");
			if(!oldPwd.equals(rs.getString(1)))throw new BusinessException("��½�������");
			rs.close();
			pst.close();
			
			sql="select register_time from tbl_user where user_id=? and user_pwd=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUser_id());
			pst.setString(2,user.getUser_pwd());
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�������");
			
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
