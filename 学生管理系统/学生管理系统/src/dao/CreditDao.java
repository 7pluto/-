package dao;

import java.util.List;

import model.Credit;

public interface CreditDao {

	/**
	 * �õ��꼶����ѧ��ѧ��
	 * @return
	 * @throws Exception
	 */
	List<Credit> findGradeAll(String geadeName) throws Exception;
}
