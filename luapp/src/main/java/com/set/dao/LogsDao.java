package com.set.dao;

import java.util.List;
import java.util.Map;
import com.set.model.TblLogs;

public interface LogsDao {
	public List<Map<String , String>> list(int pagenumber,String searchdata);
	void save(TblLogs tblLogs);
	public int totalLogs();
}
