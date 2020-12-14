package dao;

import java.util.List;
import java.util.Vector;

import model.Student;

public interface StudentDao {

	/**
	 * 得到总人数
	 * select count(*) from user;
	 * @return
	 */
	int getAllNum() throws Exception;
	
	/**
	 * 得到所有学生信息
	 * @return
	 */
	List<Student> findAll() throws Exception;
	
	/**
	 * 得到个人信息
	 * @param student
	 * @return
	 */
	Student findOne(Student student) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List<Vector> dataList(Student student) throws Exception;
	
	List<Student> dataListScore(Student student) throws Exception;
	
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	int updateStudent(Student student) throws Exception;
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	int updateStudents(Student student) throws Exception;
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	int increateStudent(Student student) throws Exception;
	
	/**
	 * 根据指定年级得到学号
	 * @param gradeName
	 * @return
	 * @throws Exception
	 */
	List<String> findGradeNumber(String gradeName) throws Exception;
}
