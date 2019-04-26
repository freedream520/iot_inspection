package cn.zg.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 * 负荷巡检数据访问层
 * @author hezhenyu
 *
 */
@Repository
@Transactional
public class LoadRecordDao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * 查询负荷记录方案集合
	 * @param scheme_id
	 * @return
	 */
	public List<Map<String,Object>> findPlans() {
		String sql = "SELECT SCHEME_ID,SCHEME_NAME FROM CZ_INSPECTION_SCHEME WHERE SCHEME_ID IN (SELECT PLAN_ID FROM IOT_INSPECTION_VALUE_LR GROUP BY PLAN_ID)";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据scheme_id查询表名
	 * @param scheme_id
	 * @return
	 */
	public String findTableName(String scheme_id) {
		String sql = "SELECT SCHEME_NAME FROM CZ_INSPECTION_SCHEME WHERE SCHEME_ID='"+scheme_id+"'";
		return jdbcTemplate.queryForObject(sql, String.class);
	}
	/**
	 * 根据scheme_id查询巡检时间顺序
	 */
	public List<Map<String,Object>> findInspectionTime(String scheme_id) {
		String sql = "SELECT REQUIRE_RECORDTIME FROM IOT_INSPECTION_LOADRECORDORDER  WHERE SCHEME_ID='"+scheme_id+"' ORDER BY REQUIRE_ORDER";
		return jdbcTemplate.queryForList(sql);
	}

	/** 
	 * 巡检数据
	 */
	public List<Map<String,Object>> findInspectionData(String plan_id,String date) {
		String sql = "SELECT  A.PLAN_ID,A.RECORD_TIME,A.POSITION_NAME,A.RECORD_TIME_NUM,A.VALUE,B.REQUIRE_CONTEXT,B.REQUIRE_MIX,B.REQUIRE_MAX,B.REQUIRE_UNIT FROM IOT_INSPECTION_VALUE_LR A,CZ_INSPECTION_REQUIRE B WHERE PLAN_ID='"+plan_id+"' AND TO_CHAR(RECORD_TIME,'YYYY-MM-DD')='"+date+"' and A.REQUIRE_ID=B.REQUIRE_ID ORDER BY POSITION_NAME";
		return jdbcTemplate.queryForList(sql);
	}
	
	
	/**
	 * 根据方案ID和时间查询对应有多少个巡检位置和数量
	 */
	public List<String> findInspectionNameByPlanId(String plan_id,String date) {
		
		String sql = "SELECT  POSITION_NAME  FROM IOT_INSPECTION_VALUE_LR WHERE PLAN_ID='"+plan_id+"' and to_char(RECORD_TIME,'yyyy-MM-dd')='"+date+"' GROUP BY POSITION_NAME";
		return jdbcTemplate.queryForList(sql, String.class);
	}
	
	
}
