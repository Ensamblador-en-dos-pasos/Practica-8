import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Isaac Ulises
 * @author Saul
 * @version 1.45
 */
public class Ensamblador {
        public static void main(String[] args) throws FileNotFoundException, IOException { 
                /*
                Leer read = new Leer();
                LcTabop tab = new LcTabop();
                CodMaquina cod= new CodMaquina();
                read.leerArc("P10ASMP.txt");
                read.Lcc();
                tab.buscar();
                cod.cMaquina();
                */
                String bin = "1101";
                Idx id = new Idx();
                
                Registros_S re = new Registros_S();
                re.S0("P1.ASM");
                //10010010
        }
}
