package by.babroval.bike.dao;

import java.util.List;

import by.babroval.bike.model.Bike;

public interface BikeDao {

	Bike loadBikeById(Integer id);

	Bike loadBikeByVin(String vin);

	List<Bike> loadAllBikes();

	List<Bike> loadBikesByPointId(Integer id);

	void storeBike(Bike entity);

	void deleteBikeByVin(String vin);

}