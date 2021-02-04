package com.set.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.CountryDao;
import com.set.model.Country;
import com.set.model.CountryDetails;

@Service
public class CountryServiceImp implements CountryService {
    @Autowired
   private CountryDao countryDao;
    
	@Override
	public void saveCountryData(Country country) {
		countryDao.saveCountryData(country);
		
	}

	@Override
	public void editCountryData(Country country) {
		countryDao.editCountryData(country);
		
	}

	@Override
	public void deleteCountryData(long countryId) {
		countryDao.deleteCountryData(countryId);
	}

	@Override
	public Country getCountryData(long countryId) {
		return countryDao.getCountryData(countryId);
	}

	@Override
	public CountryDetails getAllCountry(int pagenumber,String searchdata) {
		return countryDao.getAllCountry(pagenumber, searchdata);
	}

	@Override
	public List<Country> getAllCountrySelect() {
		return countryDao.getAllCountrySelect();
	}

	@Override
	public boolean IsExist(String countryName) {
		return countryDao.IsExist(countryName);
	}

}