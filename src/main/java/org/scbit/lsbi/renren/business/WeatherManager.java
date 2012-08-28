package org.scbit.lsbi.renren.business;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.scbit.lsbi.common.business.BasicManager;
import org.scbit.lsbi.renren.pojo.TbWeather;
import org.scbit.lsbi.renren.pojo.TbWeatherId;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("weatherManager")
public class WeatherManager extends BasicManager {
	
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List getBOByIds(long[] arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(TbWeather weather) {
		hibernateTemplate.save(weather);
	}
	
	public boolean isExist(String woeid) {
		TbWeather weather = getById(woeid);
		if(weather==null) {
			return false;
		}else {
			return true;
		}
	}
	
	public TbWeather getById(String woeid) {
		TbWeatherId id = new TbWeatherId();
		id.setWoe(woeid);
		Date now = new Date();
		id.setDate((now.getYear()+1900)+"-"+(now.getMonth()+1)+"-"+(now.getDate()));
		TbWeather weather = (TbWeather) hibernateTemplate.get(TbWeather.class, id);
		return weather;
	}
	

}
