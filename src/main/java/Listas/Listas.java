package Listas;

import Entidades.Medicamento;

public class Listas {
    private static Listas intancia;
    public static Listas getOrCreate(){
        if (intancia == null){
            intancia = new Listas();
        }
        return intancia;
    }

    private Medicamento[] medicamentos;
    private int cantM;

    public Listas(){
        medicamentos = new Medicamento[cantM];
    }
    public void agregarMedicamento(Medicamento m){
        medicamentos[cantM] =m;
        cantM++;
    }

}
