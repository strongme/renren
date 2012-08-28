package org.scbit.lsbi.renren.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.junit.Test;
import org.scbit.lsbi.renren.pojo.City;
import org.scbit.lsbi.renren.pojo.County;
import org.scbit.lsbi.renren.pojo.Province;
import org.scbit.lsbi.renren.pojo.TbCity;
import org.scbit.lsbi.renren.pojo.TbCounty;
import org.scbit.lsbi.renren.pojo.TbProvince;
import org.scbit.lsbi.renren.pojo.TbWeather;
import org.scbit.lsbi.renren.pojo.TbWeatherId;
import org.scbit.lsbi.renren.service.PlaceService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class PlaceAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	private PlaceService placeService;
	
	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}


	private HttpServletRequest request;

	private HttpServletResponse response;
	
	public String data() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String data = null;
		PrintWriter writer = response.getWriter();
		data = isWorking();
		writer.print(data);
		writer.flush();
		return null;
	}
	
	/**
	 * 最终方案
	 * 从文件直接读取数据展示
	 * @throws IOException
	 */
	@Test
	public String isWorking() throws IOException {
		System.out.println("placeService is null ? "+(placeService == null));
		List<Province> allPro = new ArrayList<Province>();
		BufferedReader reader = org.scbit.lsbi.renren.util.TextFile.readAsReader("location.txt");
		String tmp = null;
		Province province = null;
		City city = null;
		while((tmp=reader.readLine())!=null) {
			if(isProvince(tmp.trim())) {
				if(province!=null) {
					allPro.add(province);
				}
				province = null;
				province = new Province();
				province.setName(tmp.trim());
				province.setChildren(new ArrayList<City>());
			}
			if(isCity(tmp.trim())) {
				city = null;
				city = new City();
				city.setName(tmp.trim());
				city.setChildren(new ArrayList<County>());
				if(city!=null && city.getChildren().size()>=0) {
					province.getChildren().add(city);
				}
			}
			if(isCounty(tmp.trim())) {
				County county = new County();
				county.setName(tmp.trim());
				city.getChildren().add(county);
			}
		}
		allPro.add(province);
		Gson gson = new Gson();
		return gson.toJson(allPro);
	}
	
	
	/**
	 * 检测字符串s是否是省
	 * @param s
	 * @return
	 */
	public boolean isProvince(String s) {
		Pattern pattern = Pattern.compile("(\\d){6}( {2})([\u4e00-\u9fa5]+)");
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * 检测字符串s是否是市区
	 * @param s
	 * @return
	 */
	public boolean isCity(String s) {
		Pattern pattern = Pattern.compile("(\\d){6}( {4})([\u4e00-\u9fa5]+)");
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches())
			return true;
		else
			return false;
	}
	/**
	 * 检测字符串s是否是县
	 * @param s
	 * @return
	 */
	public boolean isCounty(String s) {
		Pattern pattern = Pattern.compile("(\\d){6}( {6})([\u4e00-\u9fa5]+)");
		Matcher matcher = pattern.matcher(s);
		if(matcher.matches())
			return true;
		else
			return false;
	}


	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
