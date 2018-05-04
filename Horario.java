package teste1;

import java.sql.Time;
public class Horario implements Comparable<Horario>{

	        private int codMonitor;
	        private int diaSemana;
	        private IntervaloHorario intervalo;
	        public int getDiaSemana()  { return diaSemana; }
	        public void setDiaSemana(int value) {
	                if (value > 1 && value <= 7)
	                    diaSemana = value;
	                else
	                    throw new IllegalArgumentException("Horário: Dia da semana inválido!");
	        }
	        public int getCodMonitor() { return codMonitor; }
	        public void setCodMonitor(int value) {
	                if (value > 0)
	                    this.codMonitor = value;
	                else
	                    throw new IllegalArgumentException("Horário: Código inválido!");
	        }

	        public IntervaloHorario getIntervalo() { return intervalo; }
	        public void setIntervalo (IntervaloHorario value)
	            {
	                if (value == null)
	                    throw new IllegalArgumentException("Horário: Intervalo inválido!");
	                this.intervalo = value;//intervalo horário é uma classe feita só de ponteiros estáticos
	        }

	        public Horario(int codMonitor, Time horarioInicio, Time horarioFim, int diaSemana)
	        {
	            this.setCodMonitor(codMonitor);

	            this.setIntervalo(new IntervaloHorario(horarioInicio, horarioFim));

	            this.setDiaSemana(diaSemana);

	        }
	        public Horario()
	        {
	            this.codMonitor = 0;
	            this.diaSemana = 0;
	            this.intervalo = new IntervaloHorario();
	        }
	        public int compareTo(Horario aComparar)
	        {
	            int comparacao = this.diaSemana - aComparar.getDiaSemana();
	            if(comparacao!=0)
	                return comparacao;
	            comparacao = this.intervalo.compareTo(aComparar.getIntervalo());
	            if (comparacao != 0)
	                return comparacao;
	            return this.codMonitor - aComparar.getCodMonitor();
	        }
	        //equals
	        public boolean equals(Object outro)
	        {
	            if (outro == this)
	                return true;
	            if (outro == null)
	                return false;
	            if (outro.getClass() != this.getClass())
	                return false;
	            Horario horarioComparar = (Horario)outro;
	            if (horarioComparar.compareTo(this) != 0)
	                return false;
	            return true;
	        }
	        //toString
			public String toString(){
				return this.intervalo.toString();
			}


	        public int hashCode()
	        {
	            int hashCode = 1569575288;
	            hashCode = hashCode * -1521134295 + codMonitor;
	            hashCode = hashCode * -1521134295 + diaSemana;
	            hashCode = hashCode * -1521134295 + intervalo.hashCode();
	            return hashCode;
	        }
    }
