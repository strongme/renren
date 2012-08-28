package org.scbit.lsbi.renren.pojo;

public class County {
	private String id; 
	
	public County() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}

	public County(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		
		
		return "County [name=" + name + "]";
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	private boolean isParent;

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
	

}
