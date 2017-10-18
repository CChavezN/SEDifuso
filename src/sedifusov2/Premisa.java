package sedifusov2;
/**
 *
 * @author carlos
 */
public class Premisa {
    VariableDifusa variable;
    private String termino;
    private float valorFuzzy;
    public Premisa(VariableDifusa variable,String termino){
        this.variable=variable;
        this.termino = termino;
    }
    
    public String getVariableLinguistica(){
        return this.variable.getNombre();        
    }
    
    public String getTerminoLinguistico(){
        return this.termino;
    }
    
    
    public float getFuzzyValue(float x0,String termino){
        valorFuzzy = variable.defusificar(x0,termino);
        return valorFuzzy;
    }
    
    public float getPeso(){
        return this.valorFuzzy;
    }
    
}
