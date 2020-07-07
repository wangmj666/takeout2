package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.model.Business_information;
import cn.edu.zucc.personplan.util.BaseException;

public interface IshopManager {
	/**
	 * ע�᣺
	 * Ҫ���û��������ظ�������Ϊ��
	 * ����������������һ�£����벻��Ϊ��
	 * ���ע��ʧ�ܣ����׳��쳣
	 * @param userid
	 * @param pwd  ����
	 * @param pwd2 �ظ����������
	 * @return
	 * @throws BaseException
	 */
	public Business_information reg(String shopid, String pwd,String pwd2) throws BaseException;
	
	
	/**
	 * ��½
	 * 1������û������ڻ�����������׳�һ���쳣
	 * 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public Business_information login(String shopid,String shopname)throws BaseException;
	
	/**
	 * ��Ӽƻ�
	 * Ҫ�������ļƻ��������Ϊ��ǰ�û�������������+1
	 * ע�⣺��ǰ��½�û���ͨ�� BeanUser.currentLoginUser��ȡ
	 * @param name  �ƻ�����
	 * @throws BaseException
	 */
	public List<Commodity_categories> loadAll()throws BaseException;
	
	
	/**
	 * �޸�����
	 * ���û�гɹ��޸ģ����׳��쳣
	 * @param user    ��ǰ�û�
	 * @param oldPwd  ԭ����
	 * @param newPwd  ������
	 * @param newPwd2 �ظ������������
	 */
	//public void changePwd(Business_information user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
}
