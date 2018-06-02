package by.babroval.bike.dao;

import java.util.List;

import by.babroval.bike.model.Point;

public interface PointDao {

	Point loadPointById(Integer id);

	Point loadPointByNum(Integer numPoint);

	List<Point> loadAllPoints();

	void storePoint(Point point);

	void deletePointbyNumPoint(Integer numPoint);

}