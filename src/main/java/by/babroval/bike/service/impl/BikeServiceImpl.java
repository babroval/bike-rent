package by.babroval.bike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.babroval.bike.dao.BikeDao;
import by.babroval.bike.dao.PointDao;
import by.babroval.bike.dao.PriceDao;
import by.babroval.bike.model.Bike;
import by.babroval.bike.model.Point;
import by.babroval.bike.model.Price;
import by.babroval.bike.service.BikeService;

@Service
@Transactional
public class BikeServiceImpl implements BikeService {

	@Autowired
	private BikeDao bikeDao;

	@Autowired
	private PointDao pointDao;

	@Autowired
	private PriceDao priceDao;

	@Override
	public Bike getBikeByVin(String vin) {
		return bikeDao.loadBikeByVin(vin);
	}

	@Override
	public List<Bike> getAllBikes() {
		return bikeDao.loadAllBikes();
	}

	@Override
	public List<Bike> getBikesByPoint(Integer numPoint) {

		Point point = pointDao.loadPointByNum(numPoint);

		return bikeDao.loadBikesByPointId(point.getId());
	}

	@Override
	public void storeBike(Bike bike) {

		Point point = pointDao.loadPointById(bike.getPoint().getId());
		Price price = priceDao.loadPriceById(bike.getPrice().getId());

		if (point != null && price != null) {
			point.setFreeBikes(point.getFreeBikes() + 1);
			bike.setPoint(point);
			bike.setPrice(price);
		}
		bikeDao.storeBike(bike);
	}

	@Override
	public void updateBike(Bike bike) {

		Bike entity = bikeDao.loadBikeById(bike.getId());
		Point point = pointDao.loadPointById(bike.getPoint().getId());
		Price price = priceDao.loadPriceById(bike.getPrice().getId());

		if (entity != null && point != null && price != null) {

			entity.setVin(bike.getVin());
			entity.setDescription(bike.getDescription());
			entity.setPoint(point);
			entity.setAvailableStatus(bike.getAvailableStatus());
			entity.setCondit(bike.getCondit());
			entity.setPrice(price);
		}
	}

	@Override
	public void deleteBikeByVine(String vin) {

		Bike bike = getBikeByVin(vin);
		Point point = pointDao.loadPointById(bike.getPoint().getId());
		point.setFreeBikes(point.getFreeBikes() - 1);
		bikeDao.deleteBikeByVin(vin);
	}

	@Override
	public boolean isVinUnique(Integer id, String vin) {
		Bike bike = getBikeByVin(vin);
		return (bike == null || ((id != null) && (bike.getId() == id)));
	}

}
