package dao;

import java.util.List;

import model.Courses;

public interface CoursesDao {

	/**
	 * 查询学校课程总名称
	 * @return
	 * @throws Exception
	 */
	List<Courses> findAll() throws Exception;
}
