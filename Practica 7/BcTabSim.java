import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BcTabSim {
    Conversor conv = new Conversor();
    String valor, contloc, et, valenc;
    boolean ban = true;

    public String BuscarEt(String oper) throws FileNotFoundException{
        Scanner sc = new Scanner(new FileReader("TABSIM.txt"));
        
        try {
            
            while (sc.hasNext()){
                contloc = sc.next();
                et = sc.next();
                valor = sc.next();
            

                if(oper.equals(et)){
                    valenc = valor;
                }

            }

        }finally {
            if (sc != null) {
                sc.close();
            } // Fin del if
        }

        return valenc;
    }
    public String asciiFCC(String palabra) {     
        String palfinal ="";
        palabra = palabra.replaceAll("&", " ");
        byte[] bytes = palabra.getBytes(StandardCharsets.US_ASCII);
        //convertirlo a hexa
        for (int i = 0; i < bytes.length; i++) {
            palfinal = palfinal + conv.dectohex(bytes[i]) + " ";
        }
        return palfinal.toUpperCase();
    }
}
