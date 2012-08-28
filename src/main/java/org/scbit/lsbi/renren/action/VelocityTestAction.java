package org.scbit.lsbi.renren.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class VelocityTestAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("name", "Walter");
		return SUCCESS;
	}

}
