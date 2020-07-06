package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.BaseException;

public interface IRiderManager {
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
	public Rider reg(String riderid, String pwd,String pwd2) throws BaseException;
	/**
	 * ��½
	 * 1������û������ڻ�����������׳�һ���쳣
	 * 2�������֤�ɹ����򷵻ص�ǰ�û���Ϣ
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public Rider login(String riderid,String ridername)throws BaseException;
	/**
	 * �޸�����
	 * ���û�гɹ��޸ģ����׳��쳣
	 * @param user    ��ǰ�û�
	 * @param oldPwd  ԭ����
	 * @param newPwd  ������
	 * @param newPwd2 �ظ������������
	 */
	//public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
}
