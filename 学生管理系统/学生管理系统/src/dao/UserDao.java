package dao;

import model.Student;
import model.User;

public interface UserDao {

	/**
	 * ����
	 * @param yser
	 * @return
	 */
	User findUser(User user) throws Exception;
	
	/**
	 * ����
	 * @param yser
	 * @return
	 */
	User findManage(User user) throws Exception;
	
	/**
	 * �������
	 * @return
	 */
	int inCrateUser(User user) throws Exception;
	
	/**
	 * �޸�����
	 * @return
	 */
	int updateUser(User user) throws Exception;
	
	/**
	 * �����˺ź����֤��ѯ
	 * @param userName
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	Student findUserCredential(String number,String credential) throws Exception;
	
}
