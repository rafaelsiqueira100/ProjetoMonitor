package teste1;

import java.util.ArrayList;
import java.sql.Time;
public class ConversorJSON{
    private static String base;
    private static int i;
    private static char atual;
    public static ArrayList<IntervaloHorario> converteParaHorario(String json){
		if(json == null ||json.equals(""))
			return null;
		ArrayList<IntervaloHorario> retorno = new ArrayList();
		IntervaloHorario atual;
		ConversorJSON.atual= 0;
		ConversorJSON.i = 0;
		ConversorJSON.base = json;
		boolean continuar = true;
		while(continuar){
			consumirAte('{');
			atual = new IntervaloHorario();
			atual.setHorarioFim(proximoHorario());
			atual.setHorarioInicio(proximoHorario());
			consumirAte('}');
			retorno.add(atual);
			continuar = consumirAte(',',']');
		}//acabou leitura
		return retorno;
	}
	private static void consome(){
		
		i++;
		atual = base.charAt(i);
	}
	private static void consumirAte(char carEsperado){
		atual = base.charAt(i);
		while(atual != carEsperado){
			consome();
		}
		i ++;//consome carEsperado
	}
	private static boolean consumirAte(char carEsperado1, char carEsperado2){
		atual = base.charAt(i);
		while(atual != carEsperado1 && atual != carEsperado2){
			consome();
		}
		i ++;//consome carEsperado
		return atual == carEsperado1;
		
		
	}
	private static int proximoInt(){
		char[] paraConverter = {base.charAt(i), base.charAt(i+1)};
		int retorno = Integer.parseInt(
		new String(paraConverter)
		);
		i +=2;
		return retorno;
	}
	private static Time proximoHorario(){
		int hora, min, seg;
		consumirAte(':');
		consumirAte('"');
		hora = proximoInt();
		consumirAte(':');
		min = proximoInt();
		consumirAte(':');
		seg = proximoInt();
		consumirAte('"');
		long paraMilis = (hora+3)*60 + min;
		paraMilis*=60;
		paraMilis +=seg;
		paraMilis*=1000;
		return new Time(paraMilis);
	}
}
