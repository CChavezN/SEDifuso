package sedifusov2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author carlos
 */
public class SEDifusoV2 extends JFrame{
    
    private ArrayList<Regla> reglas;//Almacena el conjunto de reglas
    private ArrayList<VariableDifusa> variablesDifusas;//Almacena las variables difusas
    private VariableDifusa conjuntoSalida;//Representa el conjunto de salida del SED
    //Variables difusas
    private VariableDifusa entusiasmo;
    private VariableDifusa notas;
    private VariableDifusa examen;
    private VariableDifusa proyecto;
    private VariableDifusa aprobacion;
    
    private float corteNA;
    private float corteCA;
    private float corteA;
    private float corteAE;
    
    Funcion aprobacionNoAprueba;
    Funcion aprobacionCasiAprueba;
    Funcion aprobacionAprueba;
    Funcion aprobacionApruebaExcelente;
    
    JDesktopPane desktop;
    JInternalFrame jIAprobacion;
    Plano graficaAprobacion;
    public SEDifusoV2() {
        super("Sistema Experto Difuso");
        initGUI();                        
        reglas = new ArrayList<>();
        variablesDifusas = new ArrayList<>();
        
        //Comienza dibujando los subconjuntos
        
        //_________ ENTUSIASMO ____________
        Funcion entusiasmoNada = new FuncionTrapecio("NADA", -999, 0, 10, 20);
        Funcion entusiasmoPoco = new FuncionTriangulo("POCO", 15, 25, 35);
        Funcion entusiasmoMedio = new FuncionTriangulo("MEDIO", 30, 40, 50);
        Funcion entusiasmoSuficiente = new FuncionTriangulo("SUFICIENTE", 50, 60, 80);
        Funcion entusiasmoMucho = new FuncionTrapecio("MUCHO", 70, 85, 100, 999);
        
        Plano graficaEntusiasmo = new Plano();
            graficaEntusiasmo.addFigura("NADA",new Integer[]{0,0,10,20});
            graficaEntusiasmo.addFigura("POCO",new Integer[]{15,25,35});
            graficaEntusiasmo.addFigura("MEDIO",new Integer[]{30,40,50});
            graficaEntusiasmo.addFigura("SUFICIENTE",new Integer[]{45,60,75});
            graficaEntusiasmo.addFigura("MUCHO",new Integer[]{70,85,100,100});
            graficaEntusiasmo.setPreferredSize(new Dimension(540, 280));
            //Cortes
            
            
        JInternalFrame jIentusiasmo = new JInternalFrame("Entusiamos", true, false,false,true);
            jIentusiasmo.add(graficaEntusiasmo);
            //jIentusiasmo.setIconifiable(true);
            jIentusiasmo.setLocation(0, 0);
            jIentusiasmo.pack();
        desktop.add(jIentusiasmo);
            jIentusiasmo.setVisible(true);

        entusiasmo = new VariableDifusa("ENTUSIASMO", new Funcion[]{
            entusiasmoNada,entusiasmoPoco,entusiasmoMedio,entusiasmoSuficiente,entusiasmoMucho
        });        
        variablesDifusas.add(entusiasmo);
        //___________________________________
        //_________ NOTAS _____________
        Funcion notasMalas = new FuncionTrapecio("MALAS", -999, 0, 20, 35);
        Funcion notasRegulares = new FuncionTriangulo("REGULARES", 30, 50, 65);
        Funcion notasBuenas = new FuncionTrapecio("BUENAS", 60, 70, 100, 999);
        
        Plano graficaNotas = new Plano();
            graficaNotas.addFigura("MALAS",new Integer[]{0,0,20,35});
            graficaNotas.addFigura("REGULARES",new Integer[]{30,50,65});
            graficaNotas.addFigura("BUENAS",new Integer[]{60,70,100,100});            
            graficaNotas.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jINotas = new JInternalFrame("Notas", true, false,false,true);
            jINotas.add(graficaNotas);
            jINotas.setLocation(540, 0);
            jINotas.pack();
        desktop.add(jINotas);
            jINotas.setVisible(true);
        
        notas = new VariableDifusa("NOTAS", new Funcion[]{notasMalas,
            notasRegulares,notasBuenas});
        variablesDifusas.add(notas);
        //___________________________________________________________________________
        //_________ EXAMEN ______________________
        Funcion examenMalo = new FuncionTrapecio("MALO", -999, 0, 25, 35);
        Funcion examenRegular = new FuncionTriangulo("REGULAR", 25, 50, 70);
        Funcion examenBueno = new FuncionTrapecio("BUENO", 65, 85, 100, 999);
        
        Plano graficaExamen = new Plano();
            graficaExamen.addFigura("MALO",new Integer[]{0,0,20,35});
            graficaExamen.addFigura("REGULAR",new Integer[]{25,50,70});
            graficaExamen.addFigura("BUENO",new Integer[]{65,85,100,100});            
            graficaExamen.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jIExamen = new JInternalFrame("Examen", true, false,false,true);
            jIExamen.add(graficaExamen);
            jIExamen.pack();
            jIExamen.setLocation(0, 320);
        desktop.add(jIExamen);
            jIExamen.setVisible(true);
        
        examen = new VariableDifusa("EXAMEN", new Funcion[]{examenMalo,
            examenRegular,examenBueno});
        variablesDifusas.add(examen);
        //__________________________________________
        //________ PROYECTO ________________________
        Funcion proyectoNoFuncional = new FuncionTrapecio("NOFUNCIONAL", -999, 0, 50, 65);
        Funcion proyectoFucional = new FuncionTrapecio("FUNCIONAL", 60, 75, 100, 999);
        
        Plano graficaProyecto = new Plano();
            graficaProyecto.addFigura("NO_FUNCIONAL",new Integer[]{0,0,50,65});            
            graficaProyecto.addFigura("FUNCIONAL",new Integer[]{60,75,100,100});
            graficaProyecto.setPreferredSize(new Dimension(540, 280));
        JInternalFrame jIProyecto = new JInternalFrame("Proyecto", true, false,false,true);
            jIProyecto.add(graficaProyecto);
            jIProyecto.pack();
            jIProyecto.setLocation(540, 320);
        desktop.add(jIProyecto);
            jIProyecto.setVisible(true);
        
        proyecto = new VariableDifusa("PROYECTO", new Funcion[]{
            proyectoNoFuncional,proyectoFucional
        });
        variablesDifusas.add(proyecto);
        //__________________________________________
        //_____________ APROBACION _________________
        aprobacionNoAprueba = new FuncionTrapecio("NOAPRUEBA", -999, 0, 30, 45);
        aprobacionCasiAprueba = new FuncionTriangulo("CASIAPRUEBA", 35, 50, 70);
        aprobacionAprueba = new FuncionTriangulo("APRUEBA", 65, 75, 80);
        aprobacionApruebaExcelente = new FuncionTrapecio("APRUEBAEXCELENTE", 75, 85, 100, 999);
        
        graficaAprobacion = new Plano();
            graficaAprobacion.addFigura("NO_APRUEBA",new Integer[]{0,0,30,45});
            graficaAprobacion.addFigura("CASI_APRUEBA",new Integer[]{35,50,70});
            graficaAprobacion.addFigura("APRUEBA",new Integer[]{65,75,80});
            graficaAprobacion.addFigura("APRUABE EXCELENTE",new Integer[]{75,85,100,100});            
            graficaAprobacion.setPreferredSize(new Dimension(540, 280));
            jIAprobacion = new JInternalFrame("Aprobacion", true, false,false,true);
            jIAprobacion.add(graficaAprobacion);
            jIAprobacion.pack();
            jIAprobacion.setLocation(300, 300);
        desktop.add(jIAprobacion);
            jIAprobacion.setVisible(false);
        
        aprobacion = new VariableDifusa("APROBACION", new Funcion[]{
            aprobacionNoAprueba,aprobacionCasiAprueba,aprobacionAprueba,aprobacionApruebaExcelente
        });
        //____________________________________________Comienza la inferencia
        generarReglas();
        difusificar();
        getPuntosDeCorte();
        desdifusificar();
        for (int i = 0; i < reglas.size(); i++) {
            String txtRegla="IF ";
            Regla r = reglas.get(i);
            ArrayList<Premisa> antecedentes = r.getAntecedentes();
            for (int j = 0; j < antecedentes.size(); j++) {     
                Premisa p = antecedentes.get(j);
                
                txtRegla+=" "+p.getVariableLinguistica()+" ES "+p.getTerminoLinguistico()+" ("+p.getPeso()+") AND ";
            }
            txtRegla+=" -> "+r.getConsecuenteName();
            System.out.println(""+txtRegla); 
            System.out.println("Pesor R"+i+": "+r.getPeso());
        }
        
        
        revalidate();
    }
    
    
    private void initGUI(){
        desktop = new JDesktopPane();
        this.getContentPane().add(desktop);
        this.setSize(1024,600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    private void difusificar(){
        int[] input = new int[variablesDifusas.size()];
        for (int i = 0; i < variablesDifusas.size(); i++) {  
            VariableDifusa v = variablesDifusas.get(i);
            input[i]=(Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor para "+v.getNombre())));
        }                
        for (int i = 0; i < reglas.size(); i++) {
            Regla r = reglas.get(i);
            System.out.println("Peso R"+(i+1)+": "+r.getPesoRegla(input));                                                         
            System.out.println("----------------");
        }        
    }
    
    
    private void generarReglas(){
         cont++;
        StringBuffer buffer=null;
        RandomAccessFile archivo=new RandomAccessFile("bh/"+ind[cont], "rw");
        long treg=obtener_treg(ind[cont]);
        archivo.seek((min-1)*treg);
        long dmax=max*treg;
        char temp;
        while(archivo.getFilePointer()<dmax)
        {
            String sub="";
            for(int i=0; i<15; i++)
            {
                temp=archivo.readChar();
                if(temp!=0)
                {
                    sub+=temp;
                }
            }
            subconjuntos[cont]=sub;
            min=archivo.readInt();
            max=archivo.readInt();
            if(min==0 && max==0)
            {
                //Debe hacer la pregunta para llenar 
                String pregunta="Si en ";
                for(int i=0; i<ind.length; i++)
                {
                    pregunta+=ind[i]+" es "+subconjuntos[i]+" ";
                }
                String respuesta=JOptionPane.showInputDialog(pregunta);
                buffer=new StringBuffer(respuesta);
                buffer.setLength(20);
                archivo.writeChars(buffer.toString());
            }
            else
            {
                llenar_matriz(min, max);
                for(int i=0; i<20; i++)
                {
                    archivo.readChar();
                }
                cont--;
            }
        }   
        
    }
    
    
    public void getPuntosDeCorte(){
        for (int i = 0; i < reglas.size(); i++) {
            Regla regla = reglas.get(i);
            switch(regla.getConsecuenteName()){
                case "NOAPRUEBA":
                    if(regla.getPeso()>corteNA)
                        corteNA = regla.getPeso();
                    break;
                case "CASIAPRUEBA":
                    if(regla.getPeso()>corteCA)
                        corteCA = regla.getPeso();
                    break;
                case "APRUEBA":
                    if(regla.getPeso()>corteA)
                        corteA = regla.getPeso();
                    break;
                case "APRUEBAEXELENTE":
                    if(regla.getPeso()>corteAE)
                        corteAE = regla.getPeso();
                    break;
            }            
        }
        System.out.println("Corte no aprueba "+corteNA);
        System.out.println("Corte casi aprueba "+corteCA);
        System.out.println("Corte aprueba "+corteA);
        System.out.println("Corte aprueba excelente "+corteAE);
        graficaAprobacion.c1=(int)((corteNA*10)*20);
        graficaAprobacion.c2=(int)((corteCA*10)*20);
        graficaAprobacion.c3=(int)((corteA*10)*20);
        graficaAprobacion.c4=(int)((corteAE*10)*20);
        jIAprobacion.setVisible(true);
    }
    
    private void desdifusificar(){        
        float vMembresia;
        float dividendo = 0;
        float divisor = 0;
        
        for (int i = 0; i < 45; i++) {
           vMembresia=aprobacionNoAprueba.getMembresiaX(i);
           if(vMembresia>corteNA){
               vMembresia=corteNA;
           }
           if(vMembresia==aprobacionCasiAprueba.getMembresiaX(i)){
               break;
           }
           divisor += (vMembresia*i);
           dividendo += vMembresia;
            
        }
        
        for (int i = 36; i < 70; i++) {
           vMembresia=aprobacionCasiAprueba.getMembresiaX(i);
           if(vMembresia>corteCA){
               vMembresia=corteCA;
           }
           if(vMembresia==aprobacionAprueba.getMembresiaX(i)){
               break;
           }
           divisor += (vMembresia*i);
           dividendo += vMembresia;
            
        }
        
        for (int i = 66; i < 80; i++) {
           vMembresia=aprobacionAprueba.getMembresiaX(i);
           if(vMembresia>corteA){
               vMembresia=corteA;
           }
           if(vMembresia==aprobacionApruebaExcelente.getMembresiaX(i)){
               break;
           }   
           divisor += (vMembresia*i);
           dividendo += vMembresia;
        }
        
        System.out.println(""+divisor+"/"+dividendo);
        JOptionPane.showMessageDialog(this, "Resultado:"+(divisor/dividendo));
        for (int i = 75; i < 100; i++) {
            vMembresia=aprobacionApruebaExcelente.getMembresiaX(i);
           if(vMembresia>corteAE){
               vMembresia=corteAE;
           }
           divisor += (vMembresia*i);
           dividendo += vMembresia;
        }
    }
    
    
    
    
    public static void main(String[] args) {
        new SEDifusoV2();
    }
    
    
    
}
