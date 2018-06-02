package by.babroval.bike.dao;

import java.util.List;

import by.babroval.bike.model.Price;

public interface PriceDao {

	Price loadPriceById(Integer id);

	List<Price> loadAllPrices();

}
