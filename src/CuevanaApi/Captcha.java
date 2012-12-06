package CuevanaApi;

public class Captcha {
	
	private String url;
	private String recaptcha_base_url = "http://www.google.com/recaptcha/api/image?c=%s";
	
	public Captcha(String challenge) {
		this.url = String.format(this.recaptcha_base_url, challenge);
	}

	public String getUrl() {
		return url;
	}

	public void getImage()
	{
		
	}
}
