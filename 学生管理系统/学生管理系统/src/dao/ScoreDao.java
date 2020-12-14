package dao;

import java.util.List;

import model.Score;
import model.ScoreCount;
import model.Student;

public interface ScoreDao {

	/**
	 * 得到课程总数
	 * @return
	 */
	int getAllNum() throws Exception;
	
	/**
	 * 得到个人课程总数
	 * @param score
	 * @return
	 */
	
	int getNum(Score score) throws Exception;
	/**
	 * 查询所有
	 * @return
	 */
	List<Score> findAll() throws Exception;
	
	/**
	 * 得到个人的所有
	 * @return
	 */
	List<Score> findAllOne(Score score) throws Exception;
	
	/**
	 * 添加学生成绩
	 * @param score
	 * @return
	 */
	int inCreateScore(Score score,int add) throws Exception;
	
	/**
	 * 得到成绩，按照特别方式
	 * @param student 学生信息
	 * @param courseNum 课程总数
	 * @return
	 * @throws Exception
	 */
	Object[][] getManagerFindStudentScore(List<Student> student,int courseNum) throws Exception;
	
	/**
	 * 修改成绩
	 * @param score
	 * @param port
	 * @return
	 * @throws Exception
	 */
	int UpdateScore(Score score,int port) throws Exception;
	
	
	/**
	 * 得到一个年级的所有成绩
	 * @param gradeName 年级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	List<Score> findAllGrade(String gradeName,int courseNum) throws Exception;
	 
	
	/**
	 * 得到一个班的所有成绩
	 * @param className 班级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	List<Score> findAllClass(String className,int courseNum) throws Exception;
	
	/**
	 * 得到年级的最高分
	 * @param gradeName 年级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getGradeScoreMax(String gradeName,int courseNum) throws Exception;
	
	/**
	 * 得到年级的最低分
	 * @param gradeName 年级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getGradeScoreMin(String gradeName,int courseNum) throws Exception;
	
	/**
	 * 得到年级的平均分
	 * @param gradeName 年级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	double getGradeScoreAvg(String gradeName,int courseNum) throws Exception;
	
	/**
	 * 得到一个班的最高分
	 * @param gradeName 班级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getClassScoreMax(String className,int courseNum) throws Exception;
	
	/**
	 * 得到一个班的最低分
	 * @param gradeName 班级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	ScoreCount getClassScoreMin(String className,int courseNum) throws Exception;
	
	/**
	 * 得到一个班的平均分
	 * @param gradeName 班级名称
	 * @param courseNum 科目的id
	 * @return
	 * @throws Exception
	 */
	double getClassScoreAvg(String className,int courseNum) throws Exception;
}
