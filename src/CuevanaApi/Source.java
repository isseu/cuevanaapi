package CuevanaApi;

public class Source {
	
	enum Definition
	{
		_360(360), _480(480), _720(720), _1080(1080);
		private int value;
		
		private Definition(int value)
		{
			this.value = value;
		}
		
		public static Definition getDef(int value)
		{
			switch(value)
			{
			case 360:
				return Definition._360;
			case 480:
				return Definition._480;
			case 720:
				return Definition._720;
			case 1080:
				return Definition._1080;
			default:
				return Definition._360;
			}
				
		}

		public int getValue() {
			return value;
		}
	}
	
	enum Lenguaje
	{
		//var labeli = {"1":"Espa\u00f1ol","2":"Ingl\u00e9s","3":"Portugu\u00e9s","4":"Alem\u00e1n","5":"Franc\u00e9s","6":"Coreano","7":"Italiano","8":"Tailand\u00e9s","9":"Ruso","10":"Mongol","11":"Polaco","12":"Esloveno","13":"Sueco","14":"Griego","15":"Canton\u00e9s","16":"Japon\u00e9s","17":"Dan\u00e9s","18":"Neerland\u00e9s","19":"Hebreo","20":"Serbio","21":"\u00c1rabe","22":"Hindi","23":"Noruego","24":"Turco","26":"Mandar\u00edn","27":"Nepal\u00e9s","28":"Rumano","29":"Iran\u00ed","30":"Est\u00f3n","31":"Bosnio","32":"Checo","33":"Croata","34":"Fin\u00e9s","35":"H\u00fanagro","36":"Persa","38":"Indonesio"};		
	}
	
	Definition definicion;
	public String host;
	int audio;
	Content contenido;
	
	public Source(Definition definicion, String host, int audio, Content contenido) {
		this.definicion = definicion;
		this.host = host;
		this.audio = audio;
		this.contenido = contenido;
	}

}
