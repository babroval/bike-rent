package by.babroval.bike.service;

import java.util.List;

import by.babroval.bike.model.Point;

public interface PointService {

	Point getPointById(Integer id);

	Point getPointByNum(Integer numPoint);

	List<Point> getAllPoints();

	void storePoint(Point point);

	void updatePoint(Point point);

	void deletePointByNumPoint(Integer numPoint);

	boolean isPointNumberUnique(Integer id, Integer numPoint);

	boolean isPointFull(Point point);

}