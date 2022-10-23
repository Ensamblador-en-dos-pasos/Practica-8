import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Isaac Ulises
 * @author Saul
 * @version 1.45
 */
public class Ensamblador {
        public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException { 
                /*
                Leer read = new Leer();
                LcTabop tab = new LcTabop();
                CodMaquina cod= new CodMaquina();
                read.leerArc("P10ASMP.txt");
                read.Lcc();
                tab.buscar();
                cod.cMaquina();
                */
                File f = new File("OBJ.txt");
                if(f.exists()){f.delete();}
                Registros_S re = new Registros_S();
                re.S0("P1.ASM");
                re.S9();
                //10010010
        }
}
