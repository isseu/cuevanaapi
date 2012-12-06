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
		// 38
		Espanol("Espa\u00f1ol"), Ingles("Ingl\u00e9s"), Portuges("Portugu\u00e9s"), Aleman("Alem\u00e1n"),
		Frances("Franc\u00e9s"), Coreano("Coreano"), Italiano("Italiano"), Tailandes("Tailand\u00e9s"),
		Ruso("Ruso"), Mongol("Mongol"), Polaco("Polaco"), Esloveno("Esloveno"), Sueco("Sueco"),
		Griego("Griego"), Cantones("Canton\u00e9s"), Japones("Japon\u00e9s"), Danes("Dan\u00e9s"),
		Nederlandes("Neerland\u00e9s"), Hebreo("Hebreo"), Serbio("Serbio"), Arabes("\u00c1rabe"),
		Hindi("Hindi"), Noruego("Noruego"), Turco("Turco"), Mandarin("Mandar\u00edn"), Nepales("Nepal\u00e9s"),
		Rumano("Rumano"), Iraned("Iran\u00ed"), Eston("Est\u00f3n"), Bosnio("Bosnio"), Checo("Checo"),
		Croata("Croata"), Fines("Fin\u00e9s"), Hungaro("H\u00fanagro"), Persa("Persa"), Indonesio("Indonesio");
		
		private String value;
		private int id;
		private static int id_cont = 0;
		
		private Lenguaje(String value)
		{
			this.value = value;
			Lenguaje.id_cont++;
			this.id = 1;
		}
		
		protected static Lenguaje getLenguaje(int i)
		{
			if( i > Lenguaje.values().length || i < 0)
			{
				return Lenguaje.Espanol;
			}
			return Lenguaje.values()[i];
		}
		
		public int getId()
		{
			return this.id;
		}
		
		public String getValue() {
			return value;
		}
		
	}
	
	Definition definicion;
	public String host;
	Lenguaje audio;
	Content contenido;
	
	public Source(Definition definicion, String host, int audio, Content contenido) {
		this.definicion = definicion;
		this.host = host;
		this.audio = Lenguaje.getLenguaje(audio);
		this.contenido = contenido;
	}
	
	public String getSubUrl()
	{
		// Siempre el mismo (?)
		return String.format("%s/files/sub/%s_ES.srt", CuevanaApi.WEBPAGE_CONTENT_BASE, this.contenido.getId());
	}

}
