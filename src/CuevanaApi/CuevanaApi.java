package CuevanaApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CuevanaApi.Content.ContentType;

public class CuevanaApi {
	
	final static String WEBPAGE_BASE = "http://www.cuevana.tv";
	
	//http://www.cuevana.tv/web/peliculas?&310&the-lord-of-the-rings-the-fellowship-of-the-ring
	// otra wea antes
	//view-source:http://www.cuevana.tv/player/sources?id=310&tipo=pelicula
	
	//view-source:http://www.cuevana.tv/player/sources?id=2394&tipo=serie
	//http://www.cuevana.tv/web/series?&2394&90210&theres-no-place-like-homecoming
	
	 //http://sc.cuevana.tv/files/sub/4759_ES.srt
	
	public CuevanaApi() {

	}
	
	public ContentType get_type(String url) throws NotValidURL
	{
		if(url.matches("^http:\\/\\/(www\\.)?cuevana\\.(co|tv|me|com)\\/#!\\/peliculas\\/\\d+\\/.*$"))
		{
			return ContentType.MOVIE;
		}else if(url.matches("^http:\\/\\/(www\\.)?cuevana\\.(co|tv|me|com)\\/#!\\/series\\/\\d+\\/.*\\/.*$"))
		{
			return ContentType.EPISODE;
		}
		throw new NotValidURL("URL Invalida");
	}
	
	public Movie get_movie(String url) throws Exception
	{
		if(!url.matches("^http:\\/\\/(www\\.)?cuevana\\.(co|tv|me|com)\\/#!\\/peliculas\\/\\d+\\/.*$"))
		{
			throw new NotValidURL("URL Invalida para una pelicula");
		}
		Pattern pattern = Pattern.compile("\\d+");
		Matcher m_id =  pattern.matcher(url);
		m_id.find();
		int id = Integer.valueOf(m_id.group(0));
		return new Movie(url, id);
	}
}
