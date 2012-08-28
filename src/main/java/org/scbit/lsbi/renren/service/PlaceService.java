package org.scbit.lsbi.renren.service;

import java.util.List;

import javax.annotation.Resource;

import org.scbit.lsbi.renren.business.PlaceManager;
import org.scbit.lsbi.renren.pojo.Province;
import org.scbit.lsbi.renren.pojo.TbCity;
import org.scbit.lsbi.renren.pojo.TbProvince;
import org.springframework.stereotype.Component;

@Component("placeService")
public class PlaceService {
	
	private PlaceManager placeManager;

	public PlaceManager getPlaceManager() {
		return placeManager;
	}

	@Resource(name="placeManager")
	public void setPlaceManager(PlaceManager placeManager) {
		this.placeManager = placeManager;
	}
	
	public void savePlace(TbProvince tbProvince) {
		placeManager.savePlace(tbProvince);
	}
	
	public TbProvince getProById(String id) {
		return (TbProvince) placeManager.getProById(id);
	}
	
	public TbCity getCiById(String id) {
		return (TbCity) placeManager.getCiById(id);
	}
	
	public List<TbProvince> allProes() {
		return placeManager.allProes();
	}

}
