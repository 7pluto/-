package dao;

import java.util.List;

import model.Score;
import model.ScoreCount;
import model.Student;

public interface ScoreDao {

	/**
	 * �õ��γ�����
	 * @return
	 */
	int getAllNum() throws Exception;
	
	/**
	 * �õ����˿γ�����
	 * @param score
	 * @return
	 */
	
	int getNum(Score score) throws Exception;
	/**
	 * ��ѯ����
	 * @return
	 */
	List<Score> findAll() throws Exception;
	
	/**
	 * �õ����˵�����
	 * @return
	 */
	List<Score> findAllOne(Score score) throws Exception;
	
	/**
	 * ���ѧ���ɼ�
	 * @param score
	 * @return
	 */
	int inCreateScore(Score score,int add) throws Exception;
	
	/**
	 * �õ��ɼ��������ر�ʽ
	 * @param student ѧ����Ϣ
	 * @param courseNum �γ�����
	 * @return
	 * @throws Exception
	 */
	Object[][] getManagerFindStudentScore(List<Student> student,int courseNum) throws Exception;
	
	/**
	 * �޸ĳɼ�
	 * @param score
	 * @param port
	 * @return
	 * @throws Exception
	 */
	int UpdateScore(Score score,int port) throws Exception;
	
	
	/**
	 * �õ�һ���꼶�����гɼ�
	 * @param gradeName �꼶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	List<Score> findAllGrade(String gradeName,int courseNum) throws Exception;
	 
	
	/**
	 * �õ�һ��������гɼ�
	 * @param className �༶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	List<Score> findAllClass(String className,int courseNum) throws Exception;
	
	/**
	 * �õ��꼶����߷�
	 * @param gradeName �꼶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getGradeScoreMax(String gradeName,int courseNum) throws Exception;
	
	/**
	 * �õ��꼶����ͷ�
	 * @param gradeName �꼶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getGradeScoreMin(String gradeName,int courseNum) throws Exception;
	
	/**
	 * �õ��꼶��ƽ����
	 * @param gradeName �꼶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	double getGradeScoreAvg(String gradeName,int courseNum) throws Exception;
	
	/**
	 * �õ�һ�������߷�
	 * @param gradeName �༶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getClassScoreMax(String className,int courseNum) throws Exception;
	
	/**
	 * �õ�һ�������ͷ�
	 * @param gradeName �༶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getClassScoreMin(String className,int courseNum) throws Exception;
	
	/**
	 * �õ�һ�����ƽ����
	 * @param gradeName �༶����
	 * @param courseNum ��Ŀ��id
	 * @return
	 * @throws Exception
	 */
	double getClassScoreAvg(String className,int courseNum) throws Exception;
}
