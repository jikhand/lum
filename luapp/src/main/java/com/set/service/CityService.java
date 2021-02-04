package com.set.service;

import java.util.List;
import java.util.Map;

import com.set.model.City;
import com.set.model.CityDetails;

public interface CityService {
	public CityDetails getAllCityList(int pagenumber,String searchdata);
	public void saveCityMaster(City city);
	public City getCityMasterDto(long cityId);
	public void deleteCity(long id);
	public boolean IsExist(String cityName,long stateId);
	public List<Map<String , String>> getAllCitySelect(long stateid);
}
