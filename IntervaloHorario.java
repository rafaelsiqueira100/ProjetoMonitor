import java.sql.Time;
public class IntervaloHorario implements Comparable<IntervaloHorario>{
 		private Time horarioInicio;
        private Time horarioFim;
        public Time getHorarioFim() { return horarioFim; }
        public void setHorarioFim (Time value){
                if (haMonitoria(value))
                    if (value.compareTo(this.horarioInicio) > 0)
                        this.horarioFim = value;
                    else
                        throw new IllegalArgumentException("Intervalo de Horário: horário de início é maior que o de fim!");
                else
                    throw new IllegalArgumentException("Intervalo de Horário: Horário final fora do intevalo!");
        }
        public Time getHorarioInicio(){ return horarioInicio; }
        public void setHorarioInicio(Time value){
                if (haMonitoria(value))
                {
                    if (horarioFim == null)
                        this.horarioInicio = value;
                    else
                        if (value.compareTo(this.horarioFim) < 0)
                        this.horarioInicio = value;
                    else
                        throw new IllegalArgumentException("Intervalo de Horário: horário de início é maior que o de fim!");
                }
                else
                {
                    throw new IllegalArgumentException("Intervalo de Horário: Horário inicial fora do intevalo!");
                }
        }
        public IntervaloHorario()
        {
            this.horarioInicio = null;
            this.horarioFim = null;
        }
        public IntervaloHorario(Time horarioInicio, Time horarioFim)
        {
            this.setHorarioInicio(horarioInicio);
            this.setHorarioFim(horarioFim);
        }

        public boolean equals(Object outro)
        {
            if (this == outro)
                return true;
            if (outro == null)
                return false;
            if (this.getClass() != outro.getClass())
                return false;
            IntervaloHorario aComparar = (IntervaloHorario)outro;
            if (this.compareTo(aComparar) != 0)
                return false;
            return true;
        }
        public int compareTo(IntervaloHorario aComparar) {
            int comparacao = this.horarioInicio.compareTo(aComparar.getHorarioInicio());
            if (comparacao != 0)
                return comparacao;
            comparacao = this.horarioFim.compareTo(aComparar.getHorarioFim());
            return comparacao;
        }
        public boolean haMonitoria(Time horario)
        {
            //if(horario<7:30 || horario > 22:30)
            if (horario.compareTo(new Time(7, 30, 00)) >= 0 && horario.compareTo(new Time(22, 30, 00)) <= 0)
                return true;
            return false;
        }

        public int hashCode()
        {
            int hashCode = 1868277553;
            hashCode = hashCode * -1521134295 + (horarioInicio).hashCode();
            hashCode = hashCode * -1521134295 + (horarioFim).hashCode();
            return hashCode;
        }
        public String toString(){
			return this.horarioInicio.toString() + " - "+ this.horarioFim.toString();
		}

}