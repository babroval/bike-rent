package by.babroval.bike.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.babroval.bike.dao.BikeDao;
import by.babroval.bike.model.Bike;

@Repository
public class BikeDaoImpl extends AbstractDao implements BikeDao {

	@Override
	public Bike loadBikeById(Integer id) {
		Bike bike = (Bike) getSession().load(Bike.class, id);
		if (bike != null) {
			Hibernate.initialize(bike);
		}
		return bike;
	}

	@Override
	public Bike loadBikeByVin(String vin) {

		Query query = getSession().createQuery("From Bike WHERE vin='" + vin + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}
		return (Bike) results.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bike> loadAllBikes() {

		Criteria criteria = getSession().createCriteria(Bike.class);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bike> loadBikesByPointId(Integer id) {

		Query query = getSession().createQuery("From Bike WHERE point_id ='" + id + "'");

		@SuppressWarnings("rawtypes")
		List results = query.list();

		if (results.isEmpty()) {
			return null;
		}
		return results;
	}

	@Override
	public void storeBike(Bike bike) {
		getSession().save(bike);
	}

	@Override
	public void deleteBikeByVin(String vin) {

		Bike bike = loadBikeByVin(vin);

		deleteBike(bike);
	}

	public void deleteBike(Bike bike) {
		getSession().delete(bike);
	}

}