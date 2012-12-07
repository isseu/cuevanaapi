package CuevanaApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Episode extends Content{
	
	private String show_name_url;;
	private String episode_name_url;
	
	public Episode(String url, int id) throws Exception {
		super(url, id, ContentType.EPISODE);
		// Sacamos nombre capitulo y de la serie
		Matcher m = Pattern.compile("\\/#!\\/series\\/\\d+\\/(.*?)\\/(.*?)$").matcher(url);
		m.find();
		this.show_name_url = m.group(1);
		this.episode_name_url = m.group(2);
		this.loadInfo(String.format("http://www.cuevana.tv/web/series?&%s&%s&%s", id, this.show_name_url, this.episode_name_url));
	}

	@Override
	void dataFormat(String content) {
		// TODO Auto-generated method stub
		
	}

}
