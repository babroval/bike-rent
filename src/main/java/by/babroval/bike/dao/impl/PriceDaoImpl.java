package by.babroval.bike.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import by.babroval.bike.dao.PriceDao;
import by.babroval.bike.model.Price;

@Repository
public class PriceDaoImpl extends AbstractDao implements PriceDao {

	@Override
	public Price loadPriceById(Integer id) {
		Price price = (Price) getSession().load(Price.class, id);
		if (price != null) {
			Hibernate.initialize(price);
		}
		return price;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Price> loadAllPrices() {

		Criteria criteria = getSession().createCriteria(Price.class);

		return criteria.list();
	}

}