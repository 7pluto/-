package dao;

import java.util.List;

import model.Classed;

public interface ClassedDao {

	int getNum() throws Exception;
	
	List<Classed> findAll() throws Exception;
}
