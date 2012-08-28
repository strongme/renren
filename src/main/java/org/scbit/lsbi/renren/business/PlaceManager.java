package org.scbit.lsbi.renren.business;

import java.util.List;

import javax.annotation.Resource;

import org.scbit.lsbi.common.business.BasicManager;
import org.scbit.lsbi.renren.pojo.TbCity;
import org.scbit.lsbi.renren.pojo.TbProvince;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("placeManager")
public class PlaceManager extends BasicManager {
	
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void savePlace(TbProvince tbProvince) {
		hibernateTemplate.save(tbProvince);
	}
	
	public TbProvince getProById(String id) {
		return (TbProvince) hibernateTemplate.get(TbProvince.class, id);
	}
	
	public TbCity getCiById(String id) {
		return (TbCity) hibernateTemplate.get(TbCity.class, id);
	}
	
	public List<TbProvince> allProes() {
		return hibernateTemplate.find("from TbProvince");
	}
	
	
	
	
	
	
	
	
	

	@Override
	public List getBOByIds(long[] arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
