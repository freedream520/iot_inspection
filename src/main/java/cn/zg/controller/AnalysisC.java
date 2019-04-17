package cn.zg.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zg.entity.dataExchange.ResultJson;
import cn.zg.service.impl.AnalysisSI;
import cn.zg.service.inter.AnalysisS;
import cn.zg.service.inter.PurificationSchemeInter;

/**
 * @ClassName: AnalysisC
 * @Description: 分析化验控制层
 * @author zhugang
 * @date 2019年4月11日
 */
@RestController
@RequestMapping("/analysis")
public class AnalysisC {
	private static Logger logger = LoggerFactory.getLogger( AnalysisC.class );
	
	@Autowired
	private AnalysisS as;
	
	/**   
	 * @Title: getPointTypeC   
	 * @Description:  获取分析方案的巡检单元种类  
	 * @return: ResultJson<List<String>>        
	 */  
	@GetMapping("/points")
	public ResultJson<List<String>> getPointTypeC( @RequestParam("name") String name ){
		logger.debug( "--C------------获取分析方案的巡检单元种类 --------------");
		logger.debug( name );
		List<String> pointTypes = as.getPointType( name );
		logger.debug( "--C------------pointTypes--------------" + pointTypes);
		if( pointTypes != null ) {
			return new ResultJson<List<String>>( 0, "查询数据成功", pointTypes );
		}else {
			return new ResultJson<List<String>>( 1, "查询数据为空", null );
		}
	}
	
	/**   
	 * @Title: getPointTypeC   
	 * @Description:  获取分析方案的巡检单元种类  
	 * @return: ResultJson<List<String>>        
	 */  
	@GetMapping("/projects")
	public ResultJson<List<String>> getProjectTypeC( 
			@RequestParam("name") String name,
			@RequestParam("pointName") String pointName){
		logger.debug( "--C------------获取分析方案的巡检单元种类 --------------");
		logger.debug( name );
		logger.debug( pointName );
		List<String> projectTypes = as.getProjectType( name.trim() , pointName.trim() );
		logger.debug( "--C------------pointTypes--------------" + projectTypes);
		if( projectTypes != null ) {
			return new ResultJson<List<String>>( 0, "查询数据成功", projectTypes );
		}else {
			return new ResultJson<List<String>>( 1, "查询数据为空", null );
		}
	}
	
	/**   
	 * @Title: getHeadConfigC   
	 * @Description: 获取分析方案的表头信息   
	 * @return: ResultJson<List<Object>>        
	 */  
	@GetMapping("/headConfig")
	public ResultJson<List<Object>> getHeadConfigC(
			@RequestParam("planName") String planName,
			@RequestParam("pointName") String pointName,
			@RequestParam("projectName") String projectName){
		logger.debug( "--C------------获取分析方案的巡检单元种类 --------------");
		logger.debug( planName );
		logger.debug( pointName );
		logger.debug( projectName );
		List<Object> headConfigS = as.getHeadConfigS( planName, pointName, projectName );
		if( headConfigS != null ) {
			return new ResultJson<List<Object>>( 0, "查询数据成功", headConfigS );
		}else {
			return new ResultJson<List<Object>>( 1, "查询数据失败", null );
		}
	}
}