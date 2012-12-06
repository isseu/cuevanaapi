import java.util.ArrayList;
import java.util.Scanner;

import CuevanaApi.Captcha;
import CuevanaApi.CuevanaApi;
import CuevanaApi.Source;
import CuevanaApi.SourceSearcher;


public class Prueba {

	public static void main(String[] args) throws Exception {
		System.out.println("[+] Test Cuevana API: ");
		Scanner input = new Scanner(System.in);
		
		CuevanaApi api = new CuevanaApi();
		ArrayList<Source> sources = api.get_movie("http://www.cuevana.tv/#!/peliculas/4759/the-bourne-legacy").getSources();
		
		for (int i = 0; i < sources.size(); i++) {
			System.out.println(sources.get(i).host);
		}
		
		System.out.println("[+] Vamos a intentar obtener un source");
		SourceSearcher ss = new SourceSearcher(sources.get(1));
		Captcha cacha = ss.getCaptcha();
		System.out.println("[+] Captcha image url: " + cacha.getUrl());
		System.out.print("[?] Solucion?: ");
		System.out.println("Link: " + ss.getLink(input.nextLine().trim()));
		
	}

}
