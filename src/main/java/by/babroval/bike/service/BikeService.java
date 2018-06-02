package by.babroval.bike.service;

import java.util.List;

import by.babroval.bike.model.Bike;

public interface BikeService {

	Bike getBikeByVin(String vin);

	List<Bike> getAllBikes();

	List<Bike> getBikesByPoint(Integer numPoint);

	void storeBike(Bike bike);

	void updateBike(Bike bike);

	void deleteBikeByVine(String vin);

	boolean isVinUnique(Integer id, String vin);

}