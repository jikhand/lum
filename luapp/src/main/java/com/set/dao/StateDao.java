package com.set.dao;

import java.util.List;
import com.set.model.State;
import com.set.model.StateDetails;

public interface StateDao {
	public StateDetails getAllState(int pagenumber,String searchdata);
	public State getStateByStateId(Long stateId);
	public void save(State state);
	public void deleteState(long id);
	public List<State> getAllStateSelect(long countryId);
	public boolean IsExist(String stateName,long countryId);
}