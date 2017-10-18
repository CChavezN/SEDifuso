package sedifusov2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
        //____________________________________________
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
        Regla r1 = new Regla();
            r1.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r1.setAntecedente(new Premisa(notas,"MALAS"));
            r1.setAntecedente(new Premisa(examen,"MALO"));
            r1.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r1.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
           
        Regla r2 = new Regla();
            r2.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r2.setAntecedente(new Premisa(notas,"MALAS"));
            r2.setAntecedente(new Premisa(examen,"REGULAR"));
            r2.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r2.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
        
        Regla r3 = new Regla();
            r3.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r3.setAntecedente(new Premisa(notas,"MALAS"));
            r3.setAntecedente(new Premisa(examen,"BUENO"));
            r3.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r3.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r4 = new Regla();
            r4.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r4.setAntecedente(new Premisa(notas,"MALAS"));
            r4.setAntecedente(new Premisa(examen,"MALO"));
            r4.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r4.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r5 = new Regla();
            r5.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r5.setAntecedente(new Premisa(notas,"MALAS"));
            r5.setAntecedente(new Premisa(examen,"REGULAR"));
            r5.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r5.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r6 = new Regla();
            r6.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r6.setAntecedente(new Premisa(notas,"MALAS"));
            r6.setAntecedente(new Premisa(examen,"BUENO"));
            r6.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r6.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        //_____________ fin columna 1 FAM _________________________________
        Regla r7 = new Regla();
            r7.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r7.setAntecedente(new Premisa(notas,"MALAS"));
            r7.setAntecedente(new Premisa(examen,"MALO"));
            r7.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r7.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
           
        Regla r8 = new Regla();
            r8.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r8.setAntecedente(new Premisa(notas,"MALAS"));
            r8.setAntecedente(new Premisa(examen,"REGULAR"));
            r8.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r8.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
        
        Regla r9 = new Regla();
            r9.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r9.setAntecedente(new Premisa(notas,"MALAS"));
            r9.setAntecedente(new Premisa(examen,"BUENO"));
            r9.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r9.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r10 = new Regla();
            r10.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r10.setAntecedente(new Premisa(notas,"MALAS"));
            r10.setAntecedente(new Premisa(examen,"MALO"));
            r10.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r10.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r11 = new Regla();
            r11.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r11.setAntecedente(new Premisa(notas,"MALAS"));
            r11.setAntecedente(new Premisa(examen,"REGULAR"));
            r11.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r11.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r12 = new Regla();
            r12.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r12.setAntecedente(new Premisa(notas,"MALAS"));
            r12.setAntecedente(new Premisa(examen,"BUENO"));
            r12.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r12.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));            
        //_____________ fin columna 2 FAM _________________________________
        Regla r13 = new Regla();
            r13.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r13.setAntecedente(new Premisa(notas,"MALAS"));
            r13.setAntecedente(new Premisa(examen,"MALO"));
            r13.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r13.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
           
        Regla r14 = new Regla();
            r14.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r14.setAntecedente(new Premisa(notas,"MALAS"));
            r14.setAntecedente(new Premisa(examen,"REGULAR"));
            r14.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r14.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
        
        Regla r15 = new Regla();
            r15.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r15.setAntecedente(new Premisa(notas,"MALAS"));
            r15.setAntecedente(new Premisa(examen,"BUENO"));
            r15.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r15.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r16 = new Regla();
            r16.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r16.setAntecedente(new Premisa(notas,"MALAS"));
            r16.setAntecedente(new Premisa(examen,"MALO"));
            r16.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r16.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r17 = new Regla();
            r17.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r17.setAntecedente(new Premisa(notas,"MALAS"));
            r17.setAntecedente(new Premisa(examen,"REGULAR"));
            r17.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r17.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r18 = new Regla();
            r18.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r18.setAntecedente(new Premisa(notas,"MALAS"));
            r18.setAntecedente(new Premisa(examen,"BUENO"));
            r18.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r18.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            //_____________ fin columna 3 FAM _________________________________
        Regla r19 = new Regla();
            r19.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r19.setAntecedente(new Premisa(notas,"MALAS"));
            r19.setAntecedente(new Premisa(examen,"MALO"));
            r19.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r19.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
           
        Regla r20 = new Regla();
            r20.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r20.setAntecedente(new Premisa(notas,"MALAS"));
            r20.setAntecedente(new Premisa(examen,"REGULAR"));
            r20.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r20.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
        
        Regla r21 = new Regla();
            r21.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r21.setAntecedente(new Premisa(notas,"MALAS"));
            r21.setAntecedente(new Premisa(examen,"BUENO"));
            r21.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r21.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r22 = new Regla();
            r22.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r22.setAntecedente(new Premisa(notas,"MALAS"));
            r22.setAntecedente(new Premisa(examen,"MALO"));
            r22.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r22.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r23 = new Regla();
            r23.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r23.setAntecedente(new Premisa(notas,"MALAS"));
            r23.setAntecedente(new Premisa(examen,"REGULAR"));
            r23.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r23.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r24 = new Regla();
            r24.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r24.setAntecedente(new Premisa(notas,"MALAS"));
            r24.setAntecedente(new Premisa(examen,"BUENO"));
            r24.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r24.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            //_____________ fin columna 4 FAM _________________________________    
        Regla r25 = new Regla();
            r25.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r25.setAntecedente(new Premisa(notas,"MALAS"));
            r25.setAntecedente(new Premisa(examen,"MALO"));
            r25.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r25.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
           
        Regla r26 = new Regla();
            r26.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r26.setAntecedente(new Premisa(notas,"MALAS"));
            r26.setAntecedente(new Premisa(examen,"REGULAR"));
            r26.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r26.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
        
        Regla r27 = new Regla();
            r27.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r27.setAntecedente(new Premisa(notas,"MALAS"));
            r27.setAntecedente(new Premisa(examen,"BUENO"));
            r27.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r27.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r28 = new Regla();
            r28.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r28.setAntecedente(new Premisa(notas,"MALAS"));
            r28.setAntecedente(new Premisa(examen,"MALO"));
            r28.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r28.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r29 = new Regla();
            r29.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r29.setAntecedente(new Premisa(notas,"MALAS"));
            r29.setAntecedente(new Premisa(examen,"REGULAR"));
            r29.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r29.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r30 = new Regla();
            r30.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r30.setAntecedente(new Premisa(notas,"MALAS"));
            r30.setAntecedente(new Premisa(examen,"BUENO"));
            r30.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r30.setConsecuente(new Premisa(aprobacion, "APRUEBA"));  
             //_____________ fin columna 5 FAM _________________________________  
             
        Regla r31 = new Regla();
            r31.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r31.setAntecedente(new Premisa(notas,"REGULARES"));
            r31.setAntecedente(new Premisa(examen,"MALO"));
            r31.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r31.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r32 = new Regla();
            r32.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r32.setAntecedente(new Premisa(notas,"REGULARES"));
            r32.setAntecedente(new Premisa(examen,"REGULAR"));
            r32.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r32.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r33 = new Regla();
            r33.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r33.setAntecedente(new Premisa(notas,"REGULARES"));
            r33.setAntecedente(new Premisa(examen,"BUENO"));
            r33.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r33.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
            
        Regla r34 = new Regla();
            r34.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r34.setAntecedente(new Premisa(notas,"REGULARES"));
            r34.setAntecedente(new Premisa(examen,"MALO"));
            r34.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r34.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
        
        Regla r35 = new Regla();
            r35.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r35.setAntecedente(new Premisa(notas,"REGULARES"));
            r35.setAntecedente(new Premisa(examen,"REGULAR"));
            r35.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r35.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
        
            
        Regla r36 = new Regla();
            r36.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r36.setAntecedente(new Premisa(notas,"REGULARES"));
            r36.setAntecedente(new Premisa(examen,"BUENO"));
            r36.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r36.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
         //_____________ fin columna 6 FAM _________________________________  
            
        Regla r37 = new Regla();
            r37.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r37.setAntecedente(new Premisa(notas,"REGULARES"));
            r37.setAntecedente(new Premisa(examen,"MALO"));
            r37.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r37.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r38 = new Regla();
            r38.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r38.setAntecedente(new Premisa(notas,"REGULARES"));
            r38.setAntecedente(new Premisa(examen,"REGULAR"));
            r38.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r38.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r39 = new Regla();
            r39.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r39.setAntecedente(new Premisa(notas,"REGULARES"));
            r39.setAntecedente(new Premisa(examen,"BUENO"));
            r39.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r39.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
            
        Regla r40 = new Regla();
            r40.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r40.setAntecedente(new Premisa(notas,"REGULARES"));
            r40.setAntecedente(new Premisa(examen,"MALO"));
            r40.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r40.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
        
        Regla r41 = new Regla();
            r41.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r41.setAntecedente(new Premisa(notas,"REGULARES"));
            r41.setAntecedente(new Premisa(examen,"REGULAR"));
            r41.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r41.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r42 = new Regla();
            r42.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r42.setAntecedente(new Premisa(notas,"REGULARES"));
            r42.setAntecedente(new Premisa(examen,"BUENO"));
            r42.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r42.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
             //_____________ fin columna 7 FAM _________________________________  
             
        Regla r43 = new Regla();
            r43.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r43.setAntecedente(new Premisa(notas,"REGULARES"));
            r43.setAntecedente(new Premisa(examen,"MALO"));
            r43.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r43.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r44 = new Regla();
            r44.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r44.setAntecedente(new Premisa(notas,"REGULARES"));
            r44.setAntecedente(new Premisa(examen,"REGULAR"));
            r44.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r44.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r45 = new Regla();
            r45.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r45.setAntecedente(new Premisa(notas,"REGULARES"));
            r45.setAntecedente(new Premisa(examen,"BUENO"));
            r45.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r45.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
            
        Regla r46 = new Regla();
            r46.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r46.setAntecedente(new Premisa(notas,"REGULARES"));
            r46.setAntecedente(new Premisa(examen,"MALO"));
            r46.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r46.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r47 = new Regla();
            r47.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r47.setAntecedente(new Premisa(notas,"REGULARES"));
            r47.setAntecedente(new Premisa(examen,"REGULAR"));
            r47.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r47.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r48 = new Regla();
            r48.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r48.setAntecedente(new Premisa(notas,"REGULARES"));
            r48.setAntecedente(new Premisa(examen,"BUENO"));
            r48.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r48.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
                   
        //_____________ fin columna 8 FAM _________________________________  
        
        Regla r49 = new Regla();
            r49.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r49.setAntecedente(new Premisa(notas,"REGULARES"));
            r49.setAntecedente(new Premisa(examen,"MALO"));
            r49.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r49.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r50 = new Regla();
            r50.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r50.setAntecedente(new Premisa(notas,"REGULARES"));
            r50.setAntecedente(new Premisa(examen,"REGULAR"));
            r50.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r50.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
        Regla r51 = new Regla();
            r51.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r51.setAntecedente(new Premisa(notas,"REGULARES"));
            r51.setAntecedente(new Premisa(examen,"BUENO"));
            r51.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r51.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
            
        Regla r52 = new Regla();
            r52.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r52.setAntecedente(new Premisa(notas,"REGULARES"));
            r52.setAntecedente(new Premisa(examen,"MALO"));
            r52.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r52.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r53 = new Regla();
            r53.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r53.setAntecedente(new Premisa(notas,"REGULARES"));
            r53.setAntecedente(new Premisa(examen,"REGULAR"));
            r53.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r53.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r54 = new Regla();
            r54.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r54.setAntecedente(new Premisa(notas,"REGULARES"));
            r54.setAntecedente(new Premisa(examen,"BUENO"));
            r54.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r54.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        //_____________________________________________          
        Regla r55 = new Regla();
            r55.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r55.setAntecedente(new Premisa(notas,"REGULARES"));
            r55.setAntecedente(new Premisa(examen,"MALO"));
            r55.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r55.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r56 = new Regla();
            r56.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r56.setAntecedente(new Premisa(notas,"REGULARES"));
            r56.setAntecedente(new Premisa(examen,"REGULAR"));
            r56.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r56.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
        Regla r57 = new Regla();
            r57.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r57.setAntecedente(new Premisa(notas,"REGULARES"));
            r57.setAntecedente(new Premisa(examen,"BUENO"));
            r57.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r57.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
            
        Regla r58 = new Regla();
            r58.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r58.setAntecedente(new Premisa(notas,"REGULARES"));
            r58.setAntecedente(new Premisa(examen,"MALO"));
            r58.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r58.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r59 = new Regla();
            r59.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r59.setAntecedente(new Premisa(notas,"REGULARES"));
            r59.setAntecedente(new Premisa(examen,"REGULAR"));
            r59.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r59.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r60 = new Regla();
            r60.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r60.setAntecedente(new Premisa(notas,"REGULARES"));
            r60.setAntecedente(new Premisa(examen,"BUENO"));
            r60.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r60.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        //__________________________________________________________________
        
        Regla r61 = new Regla();
            r61.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r61.setAntecedente(new Premisa(notas,"BUENAS"));
            r61.setAntecedente(new Premisa(examen,"MALO"));
            r61.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r61.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r62 = new Regla();
            r62.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r62.setAntecedente(new Premisa(notas,"BUENAS"));
            r62.setAntecedente(new Premisa(examen,"REGULAR"));
            r62.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r62.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r63 = new Regla();
            r63.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r63.setAntecedente(new Premisa(notas,"BUENAS"));
            r63.setAntecedente(new Premisa(examen,"BUENO"));
            r63.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r63.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
            
        Regla r64 = new Regla();
            r64.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r64.setAntecedente(new Premisa(notas,"BUENAS"));
            r64.setAntecedente(new Premisa(examen,"MALO"));
            r64.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r64.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
        
        Regla r65 = new Regla();
            r65.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r65.setAntecedente(new Premisa(notas,"BUENAS"));
            r65.setAntecedente(new Premisa(examen,"REGULAR"));
            r65.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r65.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r66 = new Regla();
            r66.setAntecedente(new Premisa(entusiasmo,"NADA"));
            r66.setAntecedente(new Premisa(notas,"BUENAS"));
            r66.setAntecedente(new Premisa(examen,"BUENO"));
            r66.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r66.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        //___________________________________________________-
        //__________________________________________________________________
        
        Regla r67 = new Regla();
            r67.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r67.setAntecedente(new Premisa(notas,"BUENAS"));
            r67.setAntecedente(new Premisa(examen,"MALO"));
            r67.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r67.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r68 = new Regla();
            r68.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r68.setAntecedente(new Premisa(notas,"BUENAS"));
            r68.setAntecedente(new Premisa(examen,"REGULAR"));
            r68.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r68.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r69 = new Regla();
            r69.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r69.setAntecedente(new Premisa(notas,"BUENAS"));
            r69.setAntecedente(new Premisa(examen,"BUENO"));
            r69.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r69.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
            
        Regla r70 = new Regla();
            r70.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r70.setAntecedente(new Premisa(notas,"BUENAS"));
            r70.setAntecedente(new Premisa(examen,"MALO"));
            r70.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r70.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r71 = new Regla();
            r71.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r71.setAntecedente(new Premisa(notas,"BUENAS"));
            r71.setAntecedente(new Premisa(examen,"REGULAR"));
            r71.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r71.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r72 = new Regla();
            r72.setAntecedente(new Premisa(entusiasmo,"POCO"));
            r72.setAntecedente(new Premisa(notas,"BUENAS"));
            r72.setAntecedente(new Premisa(examen,"BUENO"));
            r72.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r72.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        //____________________________________________________
        
        Regla r73 = new Regla();
            r73.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r73.setAntecedente(new Premisa(notas,"BUENAS"));
            r73.setAntecedente(new Premisa(examen,"MALO"));
            r73.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r73.setConsecuente(new Premisa(aprobacion, "NOAPRUEBA"));
            
        Regla r74 = new Regla();
            r74.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r74.setAntecedente(new Premisa(notas,"BUENAS"));
            r74.setAntecedente(new Premisa(examen,"REGULAR"));
            r74.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r74.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r75 = new Regla();
            r75.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r75.setAntecedente(new Premisa(notas,"BUENAS"));
            r75.setAntecedente(new Premisa(examen,"BUENO"));
            r75.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r75.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
            
        Regla r76 = new Regla();
            r76.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r76.setAntecedente(new Premisa(notas,"BUENAS"));
            r76.setAntecedente(new Premisa(examen,"MALO"));
            r76.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r76.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r77 = new Regla();
            r77.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r77.setAntecedente(new Premisa(notas,"BUENAS"));
            r77.setAntecedente(new Premisa(examen,"REGULAR"));
            r77.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r77.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
            
        Regla r78 = new Regla();
            r78.setAntecedente(new Premisa(entusiasmo,"MEDIO"));
            r78.setAntecedente(new Premisa(notas,"BUENAS"));
            r78.setAntecedente(new Premisa(examen,"BUENO"));
            r78.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r78.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        //____________________________________________________
        
        //____________________________________________________
        
        Regla r79 = new Regla();
            r79.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r79.setAntecedente(new Premisa(notas,"BUENAS"));
            r79.setAntecedente(new Premisa(examen,"MALO"));
            r79.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r79.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r80 = new Regla();
            r80.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r80.setAntecedente(new Premisa(notas,"BUENAS"));
            r80.setAntecedente(new Premisa(examen,"REGULAR"));
            r80.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r80.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
        Regla r81 = new Regla();
            r81.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r81.setAntecedente(new Premisa(notas,"BUENAS"));
            r81.setAntecedente(new Premisa(examen,"BUENO"));
            r81.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r81.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
            
        Regla r82 = new Regla();
            r82.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r82.setAntecedente(new Premisa(notas,"BUENAS"));
            r82.setAntecedente(new Premisa(examen,"MALO"));
            r82.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r82.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
        
        Regla r83 = new Regla();
            r83.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r83.setAntecedente(new Premisa(notas,"BUENAS"));
            r83.setAntecedente(new Premisa(examen,"REGULAR"));
            r83.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r83.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        
            
        Regla r84 = new Regla();
            r84.setAntecedente(new Premisa(entusiasmo,"SUFICIENTE"));
            r84.setAntecedente(new Premisa(notas,"BUENAS"));
            r84.setAntecedente(new Premisa(examen,"BUENO"));
            r84.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r84.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        //____________________________________________________
        Regla r85 = new Regla();
            r85.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r85.setAntecedente(new Premisa(notas,"BUENAS"));
            r85.setAntecedente(new Premisa(examen,"MALO"));
            r85.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r85.setConsecuente(new Premisa(aprobacion, "CASIAPRUEBA"));
            
        Regla r86 = new Regla();
            r86.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r86.setAntecedente(new Premisa(notas,"BUENAS"));
            r86.setAntecedente(new Premisa(examen,"REGULAR"));
            r86.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r86.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
        Regla r87 = new Regla();
            r87.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r87.setAntecedente(new Premisa(notas,"BUENAS"));
            r87.setAntecedente(new Premisa(examen,"BUENO"));
            r87.setAntecedente(new Premisa(proyecto,"NOFUNCIONAL"));
            r87.setConsecuente(new Premisa(aprobacion, "APRUEBA"));
            
            
        Regla r88 = new Regla();
            r88.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r88.setAntecedente(new Premisa(notas,"BUENAS"));
            r88.setAntecedente(new Premisa(examen,"MALO"));
            r88.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r88.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        
        Regla r89 = new Regla();
            r89.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r89.setAntecedente(new Premisa(notas,"BUENAS"));
            r89.setAntecedente(new Premisa(examen,"REGULAR"));
            r89.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r89.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        
            
        Regla r90 = new Regla();
            r90.setAntecedente(new Premisa(entusiasmo,"MUCHO"));
            r90.setAntecedente(new Premisa(notas,"BUENAS"));
            r90.setAntecedente(new Premisa(examen,"BUENO"));
            r90.setAntecedente(new Premisa(proyecto,"FUNCIONAL"));
            r90.setConsecuente(new Premisa(aprobacion, "APRUEBAEXCELENTE"));
        //____________________________________________________
        
        
        //Guardamos las reglas
        reglas.add(r1);reglas.add(r2);reglas.add(r3);
        reglas.add(r4);reglas.add(r5);reglas.add(r6);
        reglas.add(r7);reglas.add(r8);reglas.add(r9);
        reglas.add(r10);reglas.add(r11);reglas.add(r12);
        reglas.add(r13);reglas.add(r14);reglas.add(r15);
        reglas.add(r16);reglas.add(r17);reglas.add(r18);
        reglas.add(r19);reglas.add(r20);reglas.add(r21);
        reglas.add(r22);reglas.add(r23);reglas.add(r24);
        reglas.add(r25);reglas.add(r26);reglas.add(r27);
        reglas.add(r28);reglas.add(r29);reglas.add(r30);
        reglas.add(r31);reglas.add(r32);reglas.add(r33);
        reglas.add(r34);reglas.add(r35);reglas.add(r36);
        reglas.add(r37);reglas.add(r38);reglas.add(r39);
        reglas.add(r40);reglas.add(r41);reglas.add(r42);
        reglas.add(r43);reglas.add(r44);reglas.add(r45);
        reglas.add(r46);reglas.add(r47);reglas.add(r48);
        reglas.add(r49);reglas.add(r50);reglas.add(r51);
        reglas.add(r52);reglas.add(r53);reglas.add(r54);
        reglas.add(r55);reglas.add(r56);reglas.add(r57);
        reglas.add(r58);reglas.add(r59);reglas.add(r60);
        reglas.add(r61);reglas.add(r62);reglas.add(r63);
        reglas.add(r64);reglas.add(r65);reglas.add(r66);
        reglas.add(r67);reglas.add(r68);reglas.add(r69);
        reglas.add(r70);reglas.add(r71);reglas.add(r72);
        reglas.add(r73);reglas.add(r74);reglas.add(r75);
        reglas.add(r76);reglas.add(r77);reglas.add(r78);
        reglas.add(r79);reglas.add(r80);reglas.add(r81);
        reglas.add(r82);reglas.add(r83);reglas.add(r84);
        reglas.add(r85);reglas.add(r86);reglas.add(r87);
        reglas.add(r88);reglas.add(r89);reglas.add(r90);
        
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
