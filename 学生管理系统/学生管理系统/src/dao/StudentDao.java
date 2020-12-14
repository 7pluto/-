package dao;

import java.util.List;
import java.util.Vector;

import model.Student;

public interface StudentDao {

	/**
	 * �õ�������
	 * select count(*) from user;
	 * @return
	 */
	int getAllNum() throws Exception;
	
	/**
	 * �õ�����ѧ����Ϣ
	 * @return
	 */
	List<Student> findAll() throws Exception;
	
	/**
	 * �õ�������Ϣ
	 * @param student
	 * @return
	 */
	Student findOne(Student student) throws Exception;
	
	@SuppressWarnings("rawtypes")
	List<Vector> dataList(Student student) throws Exception;
	
	List<Student> dataListScore(Student student) throws Exception;
	
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param student
	 * @return
	 */
	int updateStudent(Student student) throws Exception;
	
	/**
	 * ����ѧ����Ϣ
	 * @param student
	 * @return
	 */
	int updateStudents(Student student) throws Exception;
	
	/**
	 * ���ѧ��
	 * @param student
	 * @return
	 */
	int increateStudent(Student student) throws Exception;
	
	/**
	 * ����ָ���꼶�õ�ѧ��
	 * @param gradeName
	 * @return
	 * @throws Exception
	 */
	List<String> findGradeNumber(String gradeName) throws Exception;
}
