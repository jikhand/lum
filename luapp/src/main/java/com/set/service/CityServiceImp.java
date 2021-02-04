package com.set.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.CityDao;
import com.set.model.City;
import com.set.model.CityDetails;
@Service
public class CityServiceImp implements CityService {
   
	@Autowired
	private CityDao cityDao;
	
	@Override
	public CityDetails getAllCityList(int pagenumber,String searchdata) {
		return cityDao.getAllCityList(pagenumber,searchdata);
	}

	@Override
	public void saveCityMaster(City city) {
		cityDao.saveCityMaster(city);
	}

	@Override
	public City getCityMasterDto(long cityId) {
		
		return cityDao.getCityMasterDto(cityId);
	}

	@Override
	public void deleteCity(long id) {
		cityDao.deleteCity(id);
	}

	@Override
	public boolean IsExist(String cityName, long stateId) {
		return cityDao.IsExist(cityName, stateId);
	}

	@Override
	public List<Map<String , String>> getAllCitySelect(long stateid) {
		return cityDao.getAllCitySelect(stateid);
	}
}
