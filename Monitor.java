import java.util.ArrayList;
public class Monitor implements Comparable<Monitor>{
	private int codMonitor;
	private String nome;
	private ArrayList<Horario> listaHorarios;
	public int getCodMonitor() { return this.codMonitor; }
	public void setCodMonitor (int value){
		if (value > 0)
				this.codMonitor = value;
			else
				throw new IllegalArgumentException("Monitor: Código inválido!");
	}
	public String getNome(){ return this.nome;}
	public void setNome(String value){
			if (value!=null && !value.equals(""))
				this.nome = value;
			else
				throw new IllegalArgumentException("Monitor: Nome está vazio!");
		}

	public ArrayList<Horario> getArrayListaHorarios()
	{
			if(this.listaHorarios!=null)
				return new ArrayList<Horario>(this.listaHorarios);
			return null;
	}
	public void setArrayListaHorarios (ArrayList<Horario> value)
	{
			if (value == null || value.size()<= 0)
				throw new IllegalArgumentException ("Monitor: Não há nenhum horário!");
				for(Horario hora : value)
				{
				if (hora.getCodMonitor() != this.codMonitor)
					throw new IllegalArgumentException("Monitor: Horário não corresponde com monitor!");
				}
			this.listaHorarios = new ArrayList<Horario>(value);
	}

	public Monitor(int codigo, String nome)
	{
		this.setCodMonitor(codigo);
		this.setNome(nome);
	}

	public Monitor()
	{
		this.codMonitor = 0;
		this.nome = "";
		this.listaHorarios = null;
	}
	public int compareTo(Monitor aComparar)
	{
		int comparacao =  this.codMonitor-(aComparar.getCodMonitor());
		if (comparacao != 0)
			return comparacao;
		comparacao = this.nome.compareTo(aComparar.getNome());
			return comparacao;
	}
	public boolean Equals(Object outro)
	{
		if (this == outro)
			return true;
		if (outro == null)
			return false;
		if (this.getClass() != outro.getClass())
			return false;
		Monitor mon = (Monitor)outro;
		if (mon.compareTo(this) != 0)
			return false;
		return true;
	}

	public int hashCode()
	{
		int hashCode = 1782228256;
		hashCode = hashCode * -1521134295 + codMonitor;
		hashCode = hashCode * -1521134295 + nome.hashCode();
		hashCode = hashCode * -1521134295 + listaHorarios.hashCode();
		return hashCode;
	}
	public String toString(){
		return this.nome;
	}
}