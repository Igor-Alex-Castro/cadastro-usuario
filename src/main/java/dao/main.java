package dao;

public class main {
	public static void main(String[] args) {
		
		Double cadastros = 27.0;
		
		Double porpagina = 5.0;
		
		Double pagina = cadastros/ porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		System.out.print(pagina.intValue());
	}
}
