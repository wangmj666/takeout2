package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

public class ExampleStepManager implements IStepManager {

	@Override
	public void add(BeanPlan plan, String name, String planstartdate,
			String planfinishdate) throws BaseException {
		
		if(name==null||"".equals(name))throw new BusinessException("计划名称未提供");
		Connection conn=null;
		
	try {
		conn=DBUtil.getConnection();
		int rc=1;
		String sql="select step_id from tbl_step where plan_id=? and step_name=?";
		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1,plan.getPlan_id() );
		pst.setString(2,name);
		java.sql.ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			rs.close();
			pst.close();
			throw new BusinessException("该用户已有同名步骤");
		}
		rs.close();
		pst.close();
		
		
		sql="SELECT max(step_order) from tbl_step where plan_id=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,plan.getPlan_id());
		rs=pst.executeQuery();
		if(rs.next()) {
			rc=rs.getInt(1)+1;
		}
		rs.close();
		pst.close();
		
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
		java.util.Date d=null;
		try {
			d = sdf.parse(planstartdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//注意这里转换的是java.util.Date mysql，数据库也提供了一个自身的date模式，千万不能混淆
		
		java.util.Date d1=null;
		try {
			d1 = sdf.parse(planfinishdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql="insert into tbl_step(plan_id,step_order,step_name,plan_begin_time,plan_end_time,real_begin_time,real_end_time) values(?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,plan.getPlan_id());
		pst.setInt(2,rc);
		pst.setString(3,name);
		pst.setTimestamp(4,new java.sql.Timestamp(d.getTime()));//这里是将until.date的时间转换为 sql.date类型，这一步是必须的
		pst.setTimestamp(5,new java.sql.Timestamp(d.getTime()));
		pst.setDate(6,null);
		pst.setDate(7,null);
		pst.execute();
		pst.close();
		
		
		
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
	public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {
		List<BeanStep> result=new ArrayList<BeanStep>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from tbl_step where plan_id=? order by step_order";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlan_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanStep p=new BeanStep();
				p.setPlan_begin_time(rs.getTimestamp(5));
				p.setPlan_end_time(rs.getTimestamp(6));
				p.setPlan_id(rs.getInt(2));
				p.setReal_begin_time(rs.getTimestamp(7));
				p.setReal_end_time(rs.getTimestamp(8));
				p.setStep_id(rs.getInt(1));
				p.setStep_name(rs.getString(4));
				p.setStep_order(rs.getInt(3));
				result.add(p);
			}
		}catch(SQLException ex) {
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
	public void deleteStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		int step_id=step.getStep_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select step_order,user_id,tbl_plan.plan_id from tbl_plan,tbl_step where tbl_plan.plan_id =tbl_step.plan_id and step_id="+step_id;
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			int step_ord=0;
			int plan_id=0;
			String plan_user_id=null;
			if(rs.next()) {
				step_ord=rs.getInt(1);
				plan_user_id=rs.getString(2);
				plan_id=rs.getInt(3);
			}else {
				rs.close();
				st.close();
				throw new BusinessException("步骤不存在");
			}
				rs.close();	
			if(!BeanUser.currentLoginUser.getUser_id().equals(plan_user_id)) {
				st.close();
				throw new BusinessException("不能删除别人步骤");
			}
			sql="delete from tbl_step where step_id="+step_id;
			st.execute(sql);
			st.close();
			
			sql="update tbl_step set step_order=step_order-1 where plan_id=? and step_order>"+step_ord;
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
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
	

	@Override
	public void startStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
				int sid=step.getStep_id();
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					int pid=0;
					String sql="select plan_id,real_begin_time from tbl_step where step_id="+sid;
					java.sql.Statement st=conn.createStatement();
					java.sql.ResultSet rs=st.executeQuery(sql);
					if(rs.next()) {
						pid=rs.getInt(1);
						if(rs.getTimestamp(2)!=null) {
							rs.close();
							st.close();
							throw new BusinessException("步骤已经开始");
						}
					}else {
						rs.close();
						st.close();
						throw new BusinessException("步骤不存在");
					}
					sql="update tbl_step set real_begin_time=now() where step_id="+sid;
					st.execute(sql);
					sql="update tbl_plan set start_step_count=start_step_count+1 where plan_id="+pid;
					st.execute(sql);
				}catch(SQLException e) {
					e.printStackTrace();
					throw new DbException(e);
				}finally {
					if(conn!=null) {
						try {
							conn.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}}

	@Override
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
				int sid=step.getStep_id();
				Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					int pid=0;
					String sql="select plan_id,real_begin_time from tbl_step where step_id="+sid;
					java.sql.Statement st=conn.createStatement();
					java.sql.ResultSet rs=st.executeQuery(sql);
					if(rs.next()) {
						pid=rs.getInt(1);
						if(rs.getTimestamp(2)==null) {
							rs.close();
							st.close();
							throw new BusinessException("步骤未开始");
						}
					}else {
						rs.close();
						st.close();
						throw new BusinessException("步骤不存在");
					}
					sql="update tbl_step set real_end_time=now() where step_id="+sid;
					st.execute(sql);
					sql="update tbl_plan set finished_step_count=finished_step_count+1 where plan_id="+pid;
					st.execute(sql);
				}catch(SQLException e) {
					e.printStackTrace();
					throw new DbException(e);
				}finally {
					if(conn!=null) {
						try {
							conn.close();
						}catch(SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
	
	
	/*String sql="select step_order,plan_id where step_id="+step_id;
	java.sql.Statement st=conn.createStatement();
	java.sql.ResultSet rs=st.executeQuery(sql);
	int step_ord=step.getStep_order();
	int plan_id=0;
	
	if(rs.next()&&rs.getInt(1)>1) {
		step_ord=rs.getInt(1);
		plan_id=rs.getInt(2);
	}else {
		rs.close();
		st.close();
		throw new BusinessException("该步骤已是第一个");
	}
		rs.close();	
	*/
	@Override
	public void moveUp(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		int step_id=step.getStep_id();
		int step_ord=step.getStep_order();
		int plan_id=step.getPlan_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			
			if(step_ord>1) {
			String sql="update tbl_step set step_order=step_order-1 where plan_id=? and step_order=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
			pst.setInt(2, step_ord);
			pst.execute();
			
			step_ord=step_ord-1;
			sql="update tbl_step set step_order=step_order+1 where plan_id=? and step_order=? and step_id!=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
			pst.setInt(2, step_ord);
			pst.setInt(3, step_id);
			pst.execute();
			pst.close();
			}
			else {
				throw new BusinessException("该步骤已是第一个");
			}
	
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

	@Override
	public void moveDown(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		int step_id=step.getStep_id();
		int step_ord=step.getStep_order();
		int plan_id=step.getPlan_id();
		int step_max=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select max(step_order) from tbl_step where plan_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				step_max=rs.getInt(1);
			}
					
			if(step_ord<step_max) {
			sql="update tbl_step set step_order=step_order+1 where plan_id=? and step_order=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
			pst.setInt(2, step_ord);
			pst.execute();
			
			step_ord=step_ord+1;
			sql="update tbl_step set step_order=step_order-1 where plan_id=? and step_order=? and step_id!=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, plan_id);
			pst.setInt(2, step_ord);
			pst.setInt(3, step_id);
			pst.execute();
			pst.close();
			}
			else {
				throw new BusinessException("该步骤已是最后一个");
			}
	
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
