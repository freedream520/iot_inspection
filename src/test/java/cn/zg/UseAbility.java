package cn.zg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ElectricInspection.class })
@WebAppConfiguration
public class UseAbility {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Test
	public void setRequireId() {
		String baseSql = "  from CZ_INSPECTION_PLAN ip" +  
				"  		right JOIN cz_inspection_scheme isc on ip.SCHEME_ID = isc.SCHEME_ID" + 
				"  		right JOIN CZ_INSEPECTION_S_P_R spr on isc.SCHEME_ID = spr.SCHEME_ID" + 
				"  		right JOIN CZ_INSPECTION_POINT ipo on spr.POINT_ID = ipo.POINT_ID" + 
				"  		right JOIN CZ_INSEPCTION_C_P_R cpr on ipo.POINT_ID = cpr.POINT_ID" + 
				"  		right JOIN CZ_INSPECTION_CONTENT ico on cpr.CONTENT_ID = ico.CONTENT_ID" + 
				"  		right JOIN CZ_INSPECTION_REQUIRE ire on ico.CONTENT_ID = ire.CONTENT_ID" + 
				"  where isc.SCHEME_ID in('EBC953D7215E420CBDAEC6D4C7683D4B','EDE99F171FC74FEFB03BA7DED5564397','5503CA3E1873477EA19521BE8CBB277F','48210E9C803746DEB60D8A3C8091EA3B','F05BD9EA7F7F4A5F8CB9DC15A638D7EF','C2FDEA1145D14D83BC8834B233BEF123','8C9247B5BCC44F39AF6C88A67EFFBEB8','B3F54F97D3BA4072A66AC29B7F941F44','5F2A59E02B4348738E18A82BDA6ECE1D','4C8EF1CE602D4974B21CF4D5699A6528') and  ip.plan_id is not null order by plan_id,point_name";
		//获取整个电子巡检所有信息
		String sql = " select ip.plan_id,ip.plan_name,isc.SCHEME_ID,isc.SCHEME_NAMe,ipo.POINT_ID,ipo.POINT_NAME," + 
				"             ico.CONTENT_ID,ico.CONTENT_NAME,ire.REQUIRE_ID,ire.REQUIRE_CONTEXT,"+ 
				" 			  ire.EQU_POSITION_NUM,ire.REQUIRE_UNIT,"+ 
				"             (case when ire.REQUIRE_RES_DESC is not null then REQUIRE_RES_DESC " +
				"	when ire.REQUIRE_MIX  is not null and ire.REQUIRE_MAX is null and ire.REQUIRE_SYMBOL like '%lt%' then '<'||ire.REQUIRE_MIX" + 
				"	when ire.REQUIRE_MIX  is not null and ire.REQUIRE_MAX is null and ire.REQUIRE_SYMBOL like '%le%' then '≤'||ire.REQUIRE_MIX" + 
				"	when ire.REQUIRE_MIX  is null and ire.REQUIRE_MAX is not null and ire.REQUIRE_SYMBOL like '%gt%' then '>'||ire.REQUIRE_MAX" + 
				"	when ire.REQUIRE_MIX  is null and ire.REQUIRE_MAX is not null and ire.REQUIRE_SYMBOL like '%ge%' then '≥'||ire.REQUIRE_MAX" + 
				"	when ire.REQUIRE_MIX  is not null and ire.REQUIRE_MAX is not null then ire.REQUIRE_MIX||'~'||ire.REQUIRE_MAX  END) as DATARANGE";
		List<Map<String, Object>> oldConfig = jdbcTemplate.queryForList( sql + baseSql );
		System.out.println(sql + baseSql);
		System.out.println("oldConfig数量："+  oldConfig.size() );
		
		//循环，一对一生成配置表信息，计算ID-
		ArrayList<String> sqls = new ArrayList<String>();
		for( int i = 0; i < oldConfig.size(); i++ ) {
			System.out.println(i);
			Map<String, Object> tempMap = oldConfig.get(i);
			//生成id
			String id = UUID.randomUUID().toString();
			//生成INSPECTIONNAME
			String planid = tempMap.get("SCHEME_ID") == null? "": tempMap.get("SCHEME_ID").toString();
			//生成巡检单元CHECKPOSITON
			String pointname = tempMap.get("POINT_NAME") == null? "": tempMap.get("POINT_NAME").toString();
			//生成合并单元格FIRSTCOLSPAN
			int firstColspan = 0;
			if( pointname != null ){
				for( int j = 0; j < oldConfig.size(); j++ ) {
					Map<String, Object> tempMap1 = oldConfig.get(j);
					Object tempPlanIdObj = tempMap1.get("SCHEME_ID");
					if( tempPlanIdObj == null ) {
						continue;
					}
					String tempPlanId = tempPlanIdObj.toString();
					if( tempPlanId!= null && tempPlanId.equals(planid)  && 
							tempMap1.get("POINT_NAME") != null && tempMap1.get("POINT_NAME").toString().equals(pointname) ) {
						firstColspan ++;
					}
				}
			}else {
				firstColspan = 1;
			}
			System.out.println( "pointname合并单元格：" + firstColspan );			
			
			//生成项目名称PROJECTNAME1
			String projectname1 = tempMap.get("CONTENT_NAME").toString();			
			//生成合并单元格SECONDCOLSPAN1
			int secondColspan1 = 0;
			if( pointname != null ) {
				for( int j = 0; j < oldConfig.size(); j++ ) {
					Map<String, Object> tempMap1 = oldConfig.get(j);
					if( tempMap1.get("CONTENT_NAME") != null && tempMap1.get("CONTENT_NAME").toString().equals(projectname1) ) {
						secondColspan1 ++;
					}
				}
			}else {
				secondColspan1 = 1;
			}
			System.out.println( "projectname1合并单元格：" + secondColspan1 );	
			
			//生成合并单元格PROJECTNAME2
			String projectname2 = tempMap.get("REQUIRE_CONTEXT") == null? " ": tempMap.get("REQUIRE_CONTEXT").toString();					
			//生成合并单元格SECONDCOLSPAN2
			int secondColspan2 = 1;			
			
			//生成位号POSITIONNUM
			String positionnum = tempMap.get("EQU_POSITION_NUM") == null? " ": tempMap.get("EQU_POSITION_NUM").toString();	
			//生成单位UNIT
			String unit = tempMap.get("REQUIRE_UNIT") == null? " ": tempMap.get("REQUIRE_UNIT").toString();	
			//生成合并单元格FOURTHCOLSPAN
			int fourColspan = 1;
			
			//生成值范围DATARANGE
			String dataRange = tempMap.get("DATARANGE") == null? " ": tempMap.get("DATARANGE").toString();	
			//生成合并单元格FIVTHCOLSPAN
			int fiveColspan = 1;
			//生成要求id
			String requireid = tempMap.get("REQUIRE_ID") == null? " ": tempMap.get("REQUIRE_ID").toString();	
			
			String tempSql = " insert into CZ_INSPECTION_HEADCONFIG "
					+ " values ( '"
					+ id + "','" + planid + "','" + pointname + "','" + firstColspan + "','" + projectname1 
					+ "','" + secondColspan1 + "','" + projectname2 + "','" + secondColspan2 + "','" + positionnum
					+ "','" + unit + "','" + fourColspan + "','" + dataRange + "','" + fiveColspan + "','" + requireid
					+ "' ) ";
			System.out.println( "插入数据Sql：" + tempSql );	
			sqls.add(tempSql);
		}
		System.out.println("Sqls：" + sqls);
		
		//删除原配置表信息，重新insert
		String dsql = " delete from CZ_INSPECTION_HEADCONFIG";		
		jdbcTemplate.execute(dsql);
		for( int i = 0; i < sqls.size(); i++ ) {
			try {
				jdbcTemplate.execute(sqls.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
