package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.util.BaseException;

public interface IshopManager {
	/**
	 * 注册：
	 * 要求用户名不能重复，不能为空
	 * 两次输入的密码必须一致，密码不能为空
	 * 如果注册失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public Business_information reg(String shopid, String pwd,String pwd2) throws BaseException;
	
	
	/**
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public Business_information login(String shopid,String shopname)throws BaseException;
	
	/**
	 * 添加计划
	 * 要求新增的计划的排序号为当前用户现有最大排序号+1
	 * 注意：当前登陆用户可通过 BeanUser.currentLoginUser获取
	 * @param name  计划名称
	 * @throws BaseException
	 */
	public List<Commodity_categories> loadAll()throws BaseException;
	
	
	/**
	 * 修改密码
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	//public void changePwd(Business_information user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
}
