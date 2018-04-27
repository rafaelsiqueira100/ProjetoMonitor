import java.sql.Time;
import java.io.*;
public class Main{
	public static void main(String[] args){
		try{
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			IntervaloHorario ih1 = new IntervaloHorario
			(new Time(7,30,0), new Time(8,20,0));
			IntervaloHorario ih2 = new IntervaloHorario
			(new Time(7,30,0), new Time(22,30,0));
			IntervaloHorario ih3 = new IntervaloHorario
			(new Time(8,0,0), new Time(8,20,0));
			System.out.println(ih1.getHorarioInicio().toString());
			System.out.println(ih2.getHorarioInicio().toString());
			System.out.println(ih1.getHorarioFim().toString());
			System.out.println(ih2.getHorarioFim().toString());
			ih1.setHorarioInicio(new Time(8,0,0));
			ih2.setHorarioFim(new Time(16,20,0));
			System.out.println(ih1.getHorarioInicio().toString());
			System.out.println(ih2.getHorarioInicio().toString());
			System.out.println(ih1.getHorarioFim().toString());
			System.out.println(ih2.getHorarioFim().toString());
			if(ih3.equals(ih1)){
				System.out.println("IH3 .EQUALS(IH1) = TRUE");
			}
			else{
				System.out.println("IH3 .EQUALS(IH1) = FALSE");
			}
			if(ih1.equals(ih2)){
				System.out.println("IH2 .EQUALS(IH1) = TRUE");
			}
			else{
				System.out.println("IH2 .EQUALS(IH1) = FALSE");
			}
			System.out.println("Comparação ih1 com ih2 = "+ ih1.compareTo(ih2));
			System.out.println("Comparação ih1 com ih3 = "+ ih1.compareTo(ih3));
			System.out.println("Comparação ih2 com ih3 = "+ ih2.compareTo(ih3));

		}
		catch(Exception ex){

		}
	}
}