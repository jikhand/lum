package com.set.dao;
import java.util.List;
import java.util.Map;

import com.set.model.City;
import com.set.model.CityDetails;

public interface CityDao {
	public CityDetails getAllCityList(int pagenumber,String searchdata);
	public void saveCityMaster(City city);
	public void deleteCity(long id);
	public City getCityMasterDto(long cityId);
	public boolean IsExist(String cityName,long stateId);
	public List<Map<String , String>> getAllCitySelect(long stateid);
}
