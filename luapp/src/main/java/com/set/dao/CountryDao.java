package com.set.dao;

import java.util.List;
import com.set.model.Country;
import com.set.model.CountryDetails;

public interface CountryDao {
	public void saveCountryData(Country country);
	public void editCountryData(Country country);
	public void deleteCountryData(long countryId);
	public Country getCountryData(long countryId);
	public CountryDetails getAllCountry(int pagenumber,String searchdata);
	public List<Country> getAllCountrySelect();
	public boolean IsExist(String countryName);
}