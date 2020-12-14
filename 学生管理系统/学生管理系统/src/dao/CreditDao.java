package dao;

import java.util.List;

import model.Credit;

public interface CreditDao {

	/**
	 * 得到年级所有学生学分
	 * @return
	 * @throws Exception
	 */
	List<Credit> findGradeAll(String geadeName) throws Exception;
}
