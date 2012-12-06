package CuevanaApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import CuevanaApi.Source.Definition;

public abstract class Content {
	private String title;
	private String url;
	private String gender;
	private String producer;
	private String director;
	private String plot;
	private String lenguaje;
	private int year;
	private int id;
	private int duration; // en mins
	private String[] subs;
	private ArrayList<String> cast = new ArrayList<String>();
	private ArrayList<Source> sources = new ArrayList<Source>();
	private String image;
		
	public enum ContentType
	{
		MOVIE("pelicula"),
		EPISODE("serie");
		private String value;
		
		private ContentType(String value)
		{
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	ContentType tipo = null;
	
	public Content(String url, int id, ContentType tipo) throws Exception {
		this.url = url;
		this.id = id;
		this.tipo = tipo;
	}
	
	protected void loadInfo(String url) throws Exception
	{
		String content = Util.getWebContent(url);
		Matcher m = Pattern.compile("<div class\\=\"tit\">(.*?)<\\/div>").matcher(content);
		if( m.find() )
		{
			this.title = m.group(1).trim();
		}
		System.out.println(this.title);
		m = Pattern.compile("\\<div\\>\\<b\\>Género\\:\\<\\/b\\>(.*?)\\<\\/div\\>").matcher(content);
		if( m.find() )
		{
			this.gender = m.group(1).trim();
		}
		System.out.println(this.gender);
		m = Pattern.compile("\\<div\\>\\<b\\>Idioma\\:\\<\\/b\\>(.*?)\\<\\/div\\>").matcher(content);
		if( m.find() )
		{
			this.lenguaje = m.group(1).trim();
		}
		System.out.println(this.lenguaje);
		m = Pattern.compile("\\<div\\>\\<b\\>Duración\\:\\<\\/b\\> (\\d+) min<\\/div\\>").matcher(content);
		if( m.find() )
		{
			this.duration = Integer.valueOf(m.group(1));
		}
		System.out.println(this.duration);
		m = Pattern.compile("<div><b>Reparto:<\\/b>(.*?)<\\/div>").matcher(content);
		m.find();
		m = Pattern.compile("<a href='\\#\\!\\/.*?'>(.+?)<\\/a>").matcher(m.group());
		while( m.find())
		{
			this.cast.add(m.group(1).trim());
		}
		System.out.println(Arrays.toString(cast.toArray()));
		m = Pattern.compile("<div><b>Director:<\\/b> <a href='.*?'>(.+?)<\\/a><\\/div>").matcher(content);
		if( m.find() )
		{
			this.director = m.group(1).trim();
		}
		System.out.println(this.director);
		m = Pattern.compile("<h2>Sinopsis</h2>(.*?)<div class\\=\"sep\">").matcher(content);
		if( m.find() )
		{
			this.plot = m.group(1).trim();
		}
		System.out.println(this.plot);
		//
		m = Pattern.compile("<div class=\"serie\">(\\d+)</div>").matcher(content);
		if( m.find() )
		{
			this.year = Integer.valueOf(m.group(1));
		}
		System.out.println(this.year);
		//
		m = Pattern.compile("<div class=\"img\"><img src=\"(.*?)\" \\/><\\/div>").matcher(content);
		if( m.find() )
		{
			this.image = m.group(1);
		}
		System.out.println(this.image);
		this.dataFormat(content);
	}
	
	public String getSub()
	{
		return String.format("http://sc.cuevana.tv/files/sub/%s_ES.srt", this.id);
	}
	
	public void loadSources() throws Exception
	{
		String url = null;
		switch(this.tipo)
		{
		case MOVIE:
			url = String.format("%s/player/sources?id=%s&tipo=pelicula", CuevanaApi.WEBPAGE_BASE, this.id);
			break;
		case EPISODE:
			url = String.format("%s/player/sources?id=%s&tipo=serie", CuevanaApi.WEBPAGE_BASE, this.id);
			break;
		default:
			break;
		}
		String content = Util.getWebContent(url);
		//var sources = {"2":{"360":["bayfiles","filebox","180upload"],"720":["bayfiles","cramit"]}}, sel_source = 0;
		Matcher m = Pattern.compile("var sources \\= (.*?)\\, sel_source").matcher(content);
		if(m.find())
		{
			content = m.group(1);
			
			JSONParser parser = new JSONParser();
			JSONObject audio = (JSONObject) parser.parse(content);

			for (Iterator iterator = audio.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				JSONObject def = (JSONObject) entry.getValue();
				for (Iterator iterator2 = def.entrySet().iterator(); iterator2.hasNext();) {
					Map.Entry entry2 = (Map.Entry) iterator2.next();
					JSONArray host = (JSONArray) entry2.getValue();
					for (int i = 0; i < host.size(); i++) {
						this.sources.add(new Source(
								Definition.getDef(Integer.valueOf(entry2.getKey().toString())),
								host.get(i).toString(), 
								Integer.valueOf(entry.getKey().toString()), 
								this));
					}
				}

			}
		}
		System.out.println(content);
	}
	
	abstract void dataFormat(String content);

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String[] getSubs() {
		return subs;
	}

	public void setSubs(String[] subs) {
		this.subs = subs;
	}

	public ArrayList<String> getCast() {
		return cast;
	}

	public void setCast(ArrayList<String> cast) {
		this.cast = cast;
	}

	public ArrayList<Source> getSources() throws Exception {
		this.loadSources();
		return this.sources;
	}

}
