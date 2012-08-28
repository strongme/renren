package org.scbit.lsbi.renren.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.scbit.lsbi.renren.pojo.TbWeather;
import org.scbit.lsbi.renren.pojo.TbWeatherId;
import org.scbit.lsbi.renren.service.WeatherService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class WeatherAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	private String locationName;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private WeatherService weatherService;

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String data() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String data = null;
		PrintWriter writer = response.getWriter();
		data = getWeatherData(locationName);
		System.out.println(data);
		if (data.equals("error")) {
			TbWeather weather = new TbWeather();
			TbWeatherId id = new TbWeatherId();
			id.setWoe("0");
			weather.setId(id);
			Gson gson = new Gson();
			data = gson.toJson(weather);
		}
		if (data.equals("City not found")) {
			TbWeather weather = new TbWeather();
			TbWeatherId id = new TbWeatherId();
			id.setWoe("1");
			weather.setId(id);
			Gson gson = new Gson();
			data = gson.toJson(weather);
		}
		writer.print(data);
		writer.flush();
		return null;
	}

	private String getWeatherData(String location) {
		if (isChiense(location)) {
			try {
				location = URLEncoder.encode(location, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("构建文档出错");
				return "error";
			}
		}
		TbWeather weather = new TbWeather();
		Document docForWoeid = null;
		;
		try {
			docForWoeid = Jsoup.connect(
					"http://sigizmund.info/woeidinfo/?woeid=" + location).get();
		} catch (IOException e) {
			System.out.println("构建文档出错");
			return "error";
		}
		Element loc = docForWoeid.select("h3").first();
		System.out.println(loc.text());
		String patternStr = "WOEID: [0-9]*";
		String allText = docForWoeid.body().text();
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(allText);
		String woeid = null;
		if (matcher.find()) {
			woeid = matcher.group().substring(7);
			System.out.println("WOEID:" + woeid);
		}
		TbWeatherId id = new TbWeatherId();
		id.setWoe(woeid);
		Date now = new Date();
		id.setDate((now.getYear()+1900)+"-"+(now.getMonth()+1)+"-"+(now.getDate()));
		if(weatherService.isExist(woeid)) {
			weather = weatherService.getById(woeid);
		}else {
			Document docForWeather = null;
			try {
				docForWeather = Jsoup.connect(
						"http://weather.yahooapis.com/forecastrss?w=" + woeid
						+ "&u=c").get();
			} catch (IOException e) {
				System.out.println("构建文档出错");
				return "error";
			}
			if (docForWeather.select("item>title").get(0).text()
					.equals("City not found")) {
				System.out
				.println(docForWeather.select("item>title").get(0).text());
				return "City not found";
			}
			weather.setCityName(docForWeather.getElementsByTag("yweather:location")
					.attr("city"));
			weather.setCityCountry(docForWeather.getElementsByTag(
					"yweather:location").attr("country"));
			System.out.println("Date:"
					+ docForWeather.getElementsByTag("pubdate").text());
			weather.setTmpLow(docForWeather.getElementsByTag("yweather:forecast")
					.attr("low"));
			weather.setTmpHigh(docForWeather.getElementsByTag("yweather:forecast")
					.attr("high"));
			weather.setWindSpeed(docForWeather.getElementsByTag("yweather:wind")
					.attr("speed"));
			weather.setHumidity(docForWeather.getElementsByTag(
					"yweather:atmosphere").attr("humidity"));
			weather.setVisiblity(docForWeather.getElementsByTag(
					"yweather:atmosphere").attr("visibility"));
			weather.setPressure(docForWeather.getElementsByTag(
					"yweather:atmosphere").attr("pressure"));
			weather.setSunrise(docForWeather.getElementsByTag("yweather:astronomy")
					.attr("sunrise"));
			weather.setId(id);
			weather.setLatitude(docForWeather.getElementsByTag("geo:lat").text());
			weather.setLongitude(docForWeather.getElementsByTag("geo:long").text());
			String urlForImg = null;
			Document docTmp = Jsoup.parse(docForWeather.getElementsByTag(
					"description").text());
			weather.setImgUrl(docTmp.getElementsByTag("img").attr("src"));
			if(weather.isWhole()) {
				System.out.println("数据库中不存在，存入");
				weatherService.save(weather);
			}
		}
		Gson gson = new Gson();
		String out = gson.toJson(weather);
		return out;
	}

	/**
	 * 判断是否为中文
	 */
	private boolean isChiense(String str) {
		if (str.getBytes().length == str.length()) {
			return false;
		}
		return true;
	}
	

}
