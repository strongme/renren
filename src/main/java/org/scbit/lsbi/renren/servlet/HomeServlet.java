package org.scbit.lsbi.renren.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.scbit.lsbi.renren.config.AppConfig;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionKey = request.getParameter("xn_sig_session_key");
		SessionKey key = new SessionKey(sessionKey);
		HttpSession session = request.getSession();
		String renrenUserId = request.getParameter("xn_sig_user");
		if(null !=sessionKey && null !=renrenUserId) {
			System.out.println("Session Key:"+sessionKey);
			session.setAttribute("sessionKey", sessionKey);
			session.setAttribute("xn_sig_user", renrenUserId);
			RenrenApiClient apiClient = new com.renren.api.client.RenrenApiClient(sessionKey);
			JSONArray userInfo = apiClient.getUserService().getInfo(renrenUserId, "name,headurl",key);
			System.out.println(userInfo);
			if(null != userInfo && userInfo.size()>0) {
				JSONObject currentUser = (JSONObject) userInfo.get(0);
				if(null != currentUser) {
					String userName = (String) currentUser.get("name");
					String userHead = (String)currentUser.get("headurl");
					request.setAttribute("userName", userName);
					request.setAttribute("userHead", userHead);
				}
			}
			JSONArray friendList = apiClient.getFriendsService().getFriends(1, 5,key);
			request.setAttribute("friendList", friendList);
			JSONObject user = (JSONObject) userInfo.get(0);
			String uid =  user.get("uid").toString();
			JSONArray photos = apiClient.getPhotoService().getAlbums(Long.valueOf(uid),1,10,"",key);
			request.setAttribute("photos", photos);
			JSONArray feeds = apiClient.getFeedService().getFeed("10", Long.valueOf(uid), 1, 30, key);
			request.setAttribute("feeds", feeds);
			
			request.setAttribute("blogs", apiClient.getBlogService().getBlogs(Long.valueOf(uid), 1, 3, true, key));
		}
		request.setAttribute("appId", AppConfig.APP_ID);
		RequestDispatcher welcomeDispatcher = request.getRequestDispatcher("/page/renren/home.jsp");
		welcomeDispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
