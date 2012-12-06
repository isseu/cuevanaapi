package CuevanaApi;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceSearcher {
	// window.location.href =
	// 'source_get?def='+sel_source.def+'&audio='+sel_source.audio+'&host='+sel_source.host+'&id=310&tipo=pelicula';

	// http://www.cuevana.tv/player/source_get?def=720&audio=2&host=bayfiles&id=572&tipo=pelicula&wup_premium=false
	// recaptcha_challenge_field=03AHJ_VuuMvMskNQoLrW_EyZn_abUeME8vNyMCiv5uWHKyFJtKicCAtCNrMA9zhotNVRKLV6xXGQ9Cg3FZ8HY8CYTb4-PFLt1GnK9v0AgyVl1Znne6dQO-wGaX8jErT0xcjjevsSVPa7wbdHdJPqMZ3EhAsZpemSW3S8R3Nn2BVUKEY-PaEmLYrpM&recaptcha_response_field=alyaju+after

	private String recaptcha_public_key = "6LeO_MwSAAAAABVHhC7ftszdwEtjTpe-gId7KshJ";
	private String recaptcha_url = String.format("http://www.google.com/recaptcha/api/challenge?k=%s&amp;error=incorrect-captcha-sol", this.recaptcha_public_key);
	private String recaptcha_response = "recaptcha_challenge_field=%s&recaptcha_response_field=%s";
	private String challenge;
	Source source;

	public SourceSearcher(Source source) {
		this.source = source;
	}

	public Captcha getCaptcha() throws Exception {
		String content = Util.getWebContent(this.recaptcha_url);
		Matcher m = Pattern.compile("challenge : '(.*?)',").matcher(content);

		if (m.find()) {
			this.challenge = m.group(1).trim();
			return new Captcha(this.challenge);
		}

		return null;

	}

	public String getLink(String solution) throws Exception {
		String content = Util
				.getWebContent(
						String.format(
								"%s/player/source_get?def=%s&audio=%s&host=%s&id=%s&tipo=%s&wup_premium=false",
								CuevanaApi.WEBPAGE_BASE,
								this.source.definicion.getValue(),
								this.source.audio, 
								this.source.host,
								this.source.contenido.getId(),
								this.source.contenido.tipo.getValue()), 
								String.format(this.recaptcha_response,
										this.challenge,
										URLEncoder.encode(solution, "UTF-8")),
						"Location");
		// Tenemos que limpiar link
		return this.CleanLink(content);
	}
	public String CleanLink(String link) throws UnsupportedEncodingException
	{
		// Esto puede deberia cambiar
		//linkto?url=http%3A%2F%2Fbayfiles.com%2Ffile%2Fuxa5%2FqHh8uQ%2FThe.Bourne.Legacy.2012.720p.BluRay.x264.DTS-HDChina.mp4%3Fcid%3D4759%26ctipo%3Dpelicula%26cdef%3D720
		link = link.substring(link.indexOf("=") + 1);
		link = link.substring(0, link.indexOf("%3Fcid"));
		return URLDecoder.decode(link, "UTF-8");
	}

}
