package org.scbit.lsbi.renren.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class WorkAction extends ActionSupport {
	
	private List<File> data;
	private List<String> dataFileName;
	private List<String> dataContentType;
	private String savePath;//文件上传后保存的路径  
    private String allowTypes;

	public List<File> getData() {
		return data;
	}

	public void setData(List<File> data) {
		this.data = data;
	}

	public List<String> getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(List<String> dataFileNames) {
		this.dataFileName = dataFileNames;
	}

	public List<String> getDataContentTypes() {
		return dataContentType;
	}

	public void setDataContentType(List<String> dataContentTypes) {
		this.dataContentType = dataContentTypes;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(dataFileName==null);
		
		for(String ss : dataFileName)
			System.out.println(ss);
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] se = request.getParameterValues("se");
		for(String s : se) {
			System.out.println("Content: "+s);
		}
		request.setAttribute("se", se[0]);
		return SUCCESS;
	}

}
