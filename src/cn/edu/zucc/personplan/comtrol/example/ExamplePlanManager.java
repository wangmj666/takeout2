package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.sql.*;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

public class ExamplePlanManager implements IPlanManager {

	@Override
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		
		if(name==null||"".equals(name))throw new BusinessException("计划名称未提供");
		
		Connection conn=null;
		BeanPlan plan=new BeanPlan();
		plan.setPlan_name(name);
		plan.setUser_id(BeanUser.currentLoginUser.getUser_id());
		
	try {
		conn=DBUtil.getConnection();
		int rc=1;
		String sql="select plan_id from tbl_plan where user_id=? and plan_name=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1,BeanUser.currentLoginUser.getUser_id() );
		pst.setString(2,name);
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			rs.close();
			pst.close();
			throw new BusinessException("该用户已有同名计划");
		}
		rs.close();
		pst.close();
		
		sql="SELECT max(plan_order) from tbl_plan where user_id=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1,BeanUser.currentLoginUser.getUser_id());
		rs=pst.executeQuery();
		if(rs.next()) {
			rc=rs.getInt(1)+1;
		}
		plan.setPlan_order(rc);
		rs.close();
		pst.close();
		
		sql="insert into tbl_plan(user_id,plan_order,plan_name,create_time,step_count,start_step_count,finished_step_count) values(?,?,?,now(),?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setString(1,BeanUser.currentLoginUser.getUser_id());
		pst.setInt(2,rc);
		pst.setString(3,name);
		pst.setInt(4,0);
		pst.setInt(5,0);
		pst.setInt(6,0);
		pst.execute();
		pst.close();
		
		plan.setCreate_time(new Date());
		sql="select max(plan_id) from tbl_plan where user_id=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, BeanUser.currentLoginUser.getUser_id());
		rs=pst.executeQuery();
		if(rs.next()) {
			int pid=rs.getInt(1);
			plan.setPlan_id(pid);
		}
		else {}
		rs.close();
		pst.close();
		return plan;
	}
		catch(SQLException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
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
	public List<BeanPlan> loadAll() throws BaseException {//当前登录的user_id 
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tbl_plan where user_id=? order by plan_order";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanPlan p=new BeanPlan();
				p.setCreate_time(rs.getTimestamp(5));
				p.setFinished_step_count(rs.getInt(8));
				p.setPlan_id(rs.getInt(1));
				p.setPlan_name(rs.getString(4));
				p.setPlan_order(rs.getInt(3));
				p.setStart_step_count(rs.getInt(7));
				p.setStep_count(rs.getInt(6));
				p.setUser_id(rs.getString(2));
				result.add(p);
			}
		}
		catch(SQLException ex) {
			throw new DbException(ex);
		}
		finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		int plan_id=plan.getPlan_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from tbl_step where plan_id="+plan_id;
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					rs.close();
					st.close();
					throw new BusinessException("该计划步骤已存在，不能删除");
					
				}
			}
			rs.close();
			//pst=1 st=n
			
			sql="select plan_order,user_id from tbl_plan where plan_id ="+plan_id;
			rs=st.executeQuery(sql);
			int plan_ord=0;
			String plan_user_id=null;
			if(rs.next()) {
				plan_ord=rs.getInt(1);
				plan_user_id=rs.getString(2);
			}else {
				rs.close();
				st.close();
				throw new BusinessException("计划不存在");
			}
				rs.close();	
			if(!BeanUser.currentLoginUser.getUser_id().equals(plan_user_id)) {
				st.close();
				throw new BusinessException("不能删除别人计划");
			}
			sql="delete from tbl_plan where plan_id="+plan_id;
			st.execute(sql);
			st.close();
			sql="update tbl_plan set plan_order=plan_order-1 where user_id=? and plan_order>"+plan_ord;
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, plan_user_id);
			pst.execute();
	
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new DbException(ex);
			
			
		}finally {
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
