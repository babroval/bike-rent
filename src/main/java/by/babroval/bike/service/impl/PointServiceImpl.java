package by.babroval.bike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.babroval.bike.dao.PointDao;
import by.babroval.bike.model.Point;
import by.babroval.bike.service.PointService;

@Service
@Transactional
public class PointServiceImpl implements PointService {

	@Autowired
	private PointDao pointDao;

	@Override
	public Point getPointById(Integer id) {
		return pointDao.loadPointById(id);
	}

	@Override
	public Point getPointByNum(Integer numPoint) {
		return pointDao.loadPointByNum(numPoint);
	}

	@Override
	public List<Point> getAllPoints() {
		return pointDao.loadAllPoints();
	}

	@Override
	public void storePoint(Point point) {
		pointDao.storePoint(point);
	}

	@Override
	public void updatePoint(Point point) {

		Point entity = getPointById(point.getId());

		if (entity != null) {

			entity.setNumPoint(point.getNumPoint());
			entity.setSlots(point.getSlots());
			entity.setFreeBikes(point.getFreeBikes());
			entity.setLongitude(point.getLongitude());
			entity.setLatitude(point.getLatitude());
			entity.setAddressMark(point.getAddressMark());
			entity.setActiveStatus(point.getActiveStatus());
			entity.setDescription(point.getDescription());
		}
	}

	@Override
	public void deletePointByNumPoint(Integer numPoint) {
		pointDao.deletePointbyNumPoint(numPoint);
	}

	@Override
	public boolean isPointNumberUnique(Integer id, Integer numPoint) {
		Point point = getPointByNum(numPoint);
		return (point == null || ((id != null) && (point.getId() == id)));
	}

	public boolean isPointFull(Point point) {

		if ((point.getSlots() - point.getFreeBikes()) < 1) {
			return true;
		} else {
			return false;
		}
	}

}
