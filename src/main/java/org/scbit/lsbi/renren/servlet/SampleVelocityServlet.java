package org.scbit.lsbi.renren.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;

public class SampleVelocityServlet extends VelocityServlet {
	
	@Override
	protected Template handleRequest(HttpServletRequest request,
			HttpServletResponse response, Context ctx) throws Exception {
		String p1 = "Jakarta";
		String p2 = "Velocity";
		ArrayList<String> list = new ArrayList<String>();
		list.add(p1);
		list.add(p2);
		ctx.put("list", list);
		Template template = null;
		
		template =getTemplate("template/sample.vm", "UTF-8");
		return template;
	}
	

}
