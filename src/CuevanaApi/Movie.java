package CuevanaApi;

public class Movie extends Content{
	
	public Movie(String url, int id) throws Exception {
		super(url, id, ContentType.MOVIE);
		// Obtenemos informacion
		//http://www.cuevana.tv/web/peliculas?&310&the-lord-of-the-rings-the-fellowship-of-the-ring
		this.loadInfo(String.format("http://www.cuevana.tv/web/peliculas?&%s", id));
	}

	@Override
	void dataFormat(String content) {
		
	}

}
