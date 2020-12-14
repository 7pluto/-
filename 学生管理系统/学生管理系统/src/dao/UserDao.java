package dao;

import model.Student;
import model.User;

public interface UserDao {

	/**
	 * 查找
	 * @param yser
	 * @return
	 */
	User findUser(User user) throws Exception;
	
	/**
	 * 查找
	 * @param yser
	 * @return
	 */
	User findManage(User user) throws Exception;
	
	/**
	 * 添加密码
	 * @return
	 */
	int inCrateUser(User user) throws Exception;
	
	/**
	 * 修改密码
	 * @return
	 */
	int updateUser(User user) throws Exception;
	
	/**
	 * 根据账号和身份证查询
	 * @param userName
	 * @param credential
	 * @return
	 * @throws Exception
	 */
	Student findUserCredential(String number,String credential) throws Exception;
	
}
