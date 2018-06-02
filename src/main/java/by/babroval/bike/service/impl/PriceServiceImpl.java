package by.babroval.bike.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.babroval.bike.dao.PriceDao;
import by.babroval.bike.model.Price;
import by.babroval.bike.service.PriceService;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceDao priceDao;

	@Override
	public List<Price> getAllPrices() {
		return priceDao.loadAllPrices();
	}

}
