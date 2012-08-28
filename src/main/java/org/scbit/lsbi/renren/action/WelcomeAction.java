package org.scbit.lsbi.renren.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.scbit.lsbi.renren.config.AppConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;

public class WelcomeAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("appId", AppConfig.APP_ID);
		String sessionKey = request.getParameter("xn_sig_session_key");
		String renrenUserId = request.getParameter("xn_sig_user");
		if(null !=sessionKey && null !=renrenUserId) {
			RenrenApiClient apiClient = new RenrenApiClient(sessionKey);
			JSONArray userInfo = apiClient.getUserService().getInfo(renrenUserId, "name,headurl",new SessionKey(sessionKey));
			if(null != userInfo && userInfo.size()>0) {
				JSONObject currentUser = (JSONObject) userInfo.get(0);
				if(null != currentUser) {
					String userName = (String) currentUser.get("name");
					String userHead = (String)currentUser.get("headurl");
					request.setAttribute("userName", userName);
					request.setAttribute("userHead", userHead);
				}
			}
		}
		return SUCCESS;
	}
	

}
