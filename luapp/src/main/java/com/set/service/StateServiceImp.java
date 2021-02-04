package com.set.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.StateDao;
import com.set.model.State;
import com.set.model.StateDetails;
@Service
public class StateServiceImp implements StateService {

	@Autowired
	private StateDao stateDao;
	/**  
	 * (Method description)
	 * @param @pagenumber,@Search String
	 * @return StateDetails
	 * etc
	 */
	@Override
	public StateDetails getAllState(int pagenumber,String searchdata) {
		System.out.println("get all service");
		return stateDao.getAllState(pagenumber,searchdata);
	}
	/**  
	 * @description: Get State by StateId
	 * @param @Stateid
	 * @return State object
	 * etc
	 */
	@Override
	public State getStateByStateId(Long stateId) {
		// TODO Auto-generated method stub
		return stateDao.getStateByStateId(stateId);
	}
	/**  
	 * description: Save state data
	 * @param @state
	 * @return void
	 * etc
	 */
	@Override
	public void save(State state) {
		stateDao.save(state);
		
	}
	/**  
	 * @description delete state based on id
	 * @param @id
	 * @return void
	 * etc
	 */
	@Override
	public void deleteState(long id) {
		stateDao.deleteState(id);
	}
	/**  
	 * description: List of state data
	 * @param N/A
	 * @return List of State
	 * etc
	 */
	@Override
	public List<State> getAllStateSelect(long countryId) {
		return stateDao.getAllStateSelect(countryId);
	}
	/**  
	 * (Method description)
	 * @param @stateName,@countryId
	 * @return boolean
	 * etc
	 */
	@Override
	public boolean IsExist(String stateName, long countryId) {
		return stateDao.IsExist(stateName, countryId);
	}
}