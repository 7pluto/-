package dao;

import java.util.List;

import model.Courses;

public interface CoursesDao {

	/**
	 * ��ѯѧУ�γ�������
	 * @return
	 * @throws Exception
	 */
	List<Courses> findAll() throws Exception;
}
