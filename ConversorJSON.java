import java.util.ArrayList;
import java.sql.Time;
public class ConversorJSON{
	public static ArrayList<IntervaloHorario> converteParaHorario(String json){
		if(json == null ||json.equals(""))
			return null;
		ArrayList<IntervaloHorario> retorno = new ArrayList();
		IntervaloHorario atual;
		char charAtual= 0;
		int indAtual = 0;
		while(charAtual != ']'){
			consumirAte('{', indAtual, json);
			atual = new IntervaloHorario();
			atual.setHorarioFim(proximoHorario(indAtual, json));
			atual.setHorarioInicio(proximoHorario(indAtual, json));
			consumirAte('}', indAtual, json);
			retorno.add(atual);
		}//acabou leitura
		return retorno;
	}
	private static void consome(char atual, int i, String base){
		atual = base.charAt(i);
		i++;
	}
	private static void consumirAte(char carEsperado, int i, String base){
		char atual = base.charAt(i);
		while(atual != carEsperado){
			consome(atual, i, base);
		}
		i ++;//consome carEsperado
	}
	private static int proximoInt(int i, String base){
		char[] paraConverter = {base.charAt(i), base.charAt(i+1)};
		int retorno = Integer.parseInt(
		new String(paraConverter)
		);
		i +=2;
		return retorno;
	}
	private static Time proximoHorario(int i, String base){
		int hora, min, seg;
		consumirAte(':', i, base);
		consumirAte('"', i, base);
		hora = proximoInt(i, base);
		consumirAte(':', i, base);
		min = proximoInt(i, base);
		consumirAte(':', i, base);
		seg = proximoInt(i, base);
		consumirAte('"', i, base);
		return new Time(hora, min, seg);
	}
}
