package CuevanaApi;

public class Episode extends Content{

	public Episode(String url, int id) throws Exception {
		super(url, id, ContentType.EPISODE);
	}

	@Override
	void dataFormat(String content) {
		// TODO Auto-generated method stub
		
	}

}
