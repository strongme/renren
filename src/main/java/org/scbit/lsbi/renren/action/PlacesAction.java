package org.scbit.lsbi.renren.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.scbit.lsbi.renren.pojo.City;
import org.scbit.lsbi.renren.pojo.County;
import org.scbit.lsbi.renren.pojo.Province;
import org.scbit.lsbi.renren.pojo.TbCity;
import org.scbit.lsbi.renren.pojo.TbCounty;
import org.scbit.lsbi.renren.pojo.TbProvince;
import org.scbit.lsbi.renren.service.PlaceService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class PlacesAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
	private PlaceService placeService;

	public PlaceService getPlaceService() {
		return placeService;
	}

	public void setPlaceService(PlaceService placeService) {
		this.placeService = placeService;
	}

	private HttpServletRequest request;

	private HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	public String initData() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String data = null;
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		if(id == null) {
			System.out.println("数据初始化");
			List<Province> allProes = new  ArrayList<Province>();
			List<TbProvince> all = placeService.allProes();
			for(int i=0;i<all.size();i++) {
				TbProvince tbProvince = all.get(i);
				Province province = new Province(tbProvince.getId(),tbProvince.getName());
				province.setParent(true);
				allProes.add(province);
			}
			Gson gson = new Gson();
			data = gson.toJson(allProes);
			writer.print(data);
			writer.flush();
			return null;
		}else {
			System.out.println(id);
			if(isProID(id)) {
				System.out.println("省");
				TbProvince tbProvince = placeService.getProById(id);
				List<City> cities = new ArrayList<City>();
				Set<TbCity> ces = tbProvince.getTbCities();
				Iterator<TbCity> iterator = ces.iterator();
				while(iterator.hasNext()) {
					TbCity city = iterator.next();
					City city2 = new City(city.getId(), city.getName());
					city2.setParent(true);
					cities.add(city2);
				}
				Gson gson = new Gson();
				data = gson.toJson(cities);
				writer.print(data);
				writer.flush();
				return null;
			}
			if(isCiID(id)) {
				System.out.println("市");
				TbCity tbCity = placeService.getCiById(id);
				List<County> counties = new ArrayList<County>();
				Set<TbCounty> coes = tbCity.getTbCounties();
				Iterator<TbCounty> iterator = coes.iterator();
				while(iterator.hasNext()) {
					TbCounty county  = iterator.next();
					County county2 = new County(county.getId(), county.getName());
					county2.setParent(false);
					counties.add(county2);
				}
				Gson gson = new Gson();
				data  = gson.toJson(counties);
				writer.print(data);
				writer.flush();
				return null;
			}
			if(isCoID(id)) {
				System.out.println("县");
				return null;
			}
		}
		return null;
	}
	
	public boolean isProID(String id) {
		Pattern pattern = Pattern.compile("[0-9]{2}0000");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches())
			return true;
		else
			return false;
	}
	
	public boolean isCiID(String id) {
		Pattern pattern = Pattern.compile("[0-9]{4}00");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches())
			return true;
		else
			return false;
	}
	public boolean isCoID(String id) {
		Pattern pattern = Pattern.compile("[0-9]{6}");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches())
			return true;
		else
			return false;
	}

}
