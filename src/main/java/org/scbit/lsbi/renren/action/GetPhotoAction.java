package org.scbit.lsbi.renren.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;

public class GetPhotoAction extends ActionSupport {
	
	public String execute()throws Exception {
		System.out.println("HELLO?");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
			String sessionKey = (String) session.getAttribute("sessionKey");
			SessionKey key = new SessionKey(sessionKey);
			String renrenUserId = (String) session.getAttribute("xn_sig_user");
			if (null != sessionKey && null != renrenUserId) {
				System.out.println("sessionKey不是空的");
				RenrenApiClient apiClient = new com.renren.api.client.RenrenApiClient(
						sessionKey);
				JSONArray userInfo = apiClient.getUserService().getInfo(
						renrenUserId, "name,headurl", key);
				System.out.println(userInfo);
				if (null != userInfo && userInfo.size() > 0) {
					JSONObject currentUser = (JSONObject) userInfo.get(0);
					if (null != currentUser) {
						String userName = (String) currentUser.get("name");
						String userHead = (String) currentUser.get("headurl");
						request.setAttribute("userName", userName);
						request.setAttribute("userHead", userHead);
					}
				}
				JSONObject user = (JSONObject) userInfo.get(0);
				String uid = user.get("uid").toString();
				JSONArray photos = apiClient.getPhotoService().getAlbums(
						Long.valueOf(uid), 1, 10, "", key);
				PrintWriter writer = response.getWriter();
				System.out.println("Photos : "+photos.toJSONString());
				writer.print(photos.toJSONString());
				writer.flush();
				
			}
			return null;
	}

}
