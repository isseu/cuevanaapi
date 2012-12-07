package CuevanaApi;

import CuevanaApi.Content.ContentType;

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
		// Se saltan algunos en la pagina
		Espanol("Espa\u00f1ol", 1), Ingles("Ingl\u00e9s", 2), Portuges("Portugu\u00e9s", 3), Aleman("Alem\u00e1n", 4),
		Frances("Franc\u00e9s", 5), Coreano("Coreano", 6), Italiano("Italiano", 7), Tailandes("Tailand\u00e9s", 8),
		Ruso("Ruso", 9), Mongol("Mongol", 10), Polaco("Polaco", 11), Esloveno("Esloveno", 12), Sueco("Sueco", 13),
		Griego("Griego", 14), Cantones("Canton\u00e9s", 15), Japones("Japon\u00e9s", 16), Danes("Dan\u00e9s", 17),
		Nederlandes("Neerland\u00e9s", 18), Hebreo("Hebreo", 19), Serbio("Serbio", 20), Arabes("\u00c1rabe", 21),
		Hindi("Hindi", 22), Noruego("Noruego", 23), Turco("Turco", 24), Mandarin("Mandar\u00edn", 26), Nepales("Nepal\u00e9s", 27),
		Rumano("Rumano", 28), Iraned("Iran\u00ed", 29), Eston("Est\u00f3n", 30), Bosnio("Bosnio", 31), Checo("Checo", 32),
		Croata("Croata", 33), Fines("Fin\u00e9s", 34), Hungaro("H\u00fanagro", 35), Persa("Persa", 36), Indonesio("Indonesio", 38);
		
		private String value;
		private int id;
		
		private Lenguaje(String value, int id)
		{
			this.value = value;
			this.id = id;
		}
		
		protected static Lenguaje getLenguaje(int i)
		{
			if( i > 38 || i <= 0)
			{
				return Lenguaje.Espanol;
			}
			for (int j = 0; j < Lenguaje.values().length; j++) {
				if(Lenguaje.values()[j].getId() == i)
				{
					return Lenguaje.values()[j];
				}
			}
			return Lenguaje.Espanol;
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
		// Siempre el mismo _ES (?)
		if(this.contenido.tipo == ContentType.MOVIE)
		{
			return String.format("%s/files/sub/%s_ES.srt", CuevanaApi.WEBPAGE_CONTENT_BASE, this.contenido.getId());
		}else
		{
			return String.format("%s/files/s/sub/%s_ES.srt", CuevanaApi.WEBPAGE_CONTENT_BASE, this.contenido.getId());
		}
	}
	
	public String toString()
	{
		return String.format("[ %-10s %4sp %8s ]", this.host, this.definicion.getValue(), this.audio.getValue());
	}

}
