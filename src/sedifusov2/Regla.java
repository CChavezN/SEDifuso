package sedifusov2;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class Regla {

    private ArrayList<Premisa> antecedentes;//Premisa guarda la variable y el termino
    private Premisa consecuete;
    private float peso;
    public Regla() {
        antecedentes= new ArrayList<>();
    }
    
    public void setAntecedente(Premisa p){
        this.antecedentes.add(p);        
    }
    
    public void setConsecuente(Premisa consecuente){
        this.consecuete=consecuente;
    }
    
    public int getNumeroAntcedentes(){
        return antecedentes.size();
    }
    
    public ArrayList<Premisa> getAntecedentes(){
        return this.antecedentes;
    }
    
    public String getConsecuenteName(){
        return this.consecuete.getTerminoLinguistico();
    }
    
    public float getPeso(){
        return this.peso;
    }
        
    
    public float getPesoRegla(int input[]){
        this.peso=999;        
        for (int i = 0; i < antecedentes.size(); i++) {            
            Premisa antedente = antecedentes.get(i);                
                float fuzzyValue = antedente.getFuzzyValue(input[i], antedente.getTerminoLinguistico());
                if(fuzzyValue<peso)
                    peso = fuzzyValue;
        }        
        return this.peso;
    }
                
}
