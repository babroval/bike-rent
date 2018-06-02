package by.babroval.bike.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.babroval.bike.dao.PointDao;
import by.babroval.bike.model.Point;

@Repository
public class PointDaoImpl extends AbstractDao implements PointDao {

	@Override
	public Point loadPointById(Integer id) {
		Point point = (Point) getSession().load(Point.class, id);
		if (point != null) {
			Hibernate.initialize(point);
		}
		return point;
	}

	@Override
	public Point loadPointByNum(Integer numPoint) {

		Query query = getSession().createQuery("From Point WHERE num_point='" + numPoint + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}

		return (Point) results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Point> loadAllPoints() {

		Criteria criteria = getSession().createCriteria(Point.class);

		return criteria.list();
	}

	@Override
	public void storePoint(Point point) {
		getSession().save(point);
	}

	@Override
	public void deletePointbyNumPoint(Integer numPoint) {

		Point point = loadPointByNum(numPoint);

		deletePoint(point);
	}

	public void deletePoint(Point point) {
		getSession().delete(point);
	}

}