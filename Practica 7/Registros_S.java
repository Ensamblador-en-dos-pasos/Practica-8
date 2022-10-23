import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Registros_S {
    BcTabSim bt = new BcTabSim();
    Conversor conv = new Conversor();
    Idx coms = new Idx();
    String data, cm, ck, cc, addr, chk, bin;
    int suma, cont, aux, aux1;
    
    /**
     * Proceso para generar el codigo
     * del registro s0
     * @param nomArch
     * @throws IOException
     */
    public void S0(String nomArch) throws IOException {
        /*declaracion de variables y concatenacion de hexadecimales*/
        data = bt.asciiFCC(nomArch);
        data = data + "0A";
        data = "43 3A 20 " + data;
        aux = (data.length()/3 + 4);
        suma = aux;
        cc = conv.dectohex(aux);
        cc = conv.ceros9bits(cc);
        /* Sumar los valores en decimal*/
        String arr[] = data.split(" ");
        for (int i = 0; i < arr.length; i++) {
            aux1 = conv.hextodec(arr[i]);
            suma = suma + aux1;
        }
        System.out.println(suma);
        /*Tomar los valores menos significativos del hexadecimal*/
        chk = conv.dectohex(suma);
        arr = chk.split("");
        chk = arr[arr.length-2] + arr[arr.length-1];
        /* Aplicar complemento a 1 */
        cont = conv.hextodec(chk);
        bin = Integer.toBinaryString(cont);
        bin = coms.C1_8bits(bin);
        aux1 = conv.bintodec(bin);
        ck = conv.dectohex(aux1);

        WriteObj("S0", cc.toUpperCase(), "0000", data, ck.toUpperCase());
    }

    /**
     * Proceso para generar el codigo 
     * del registro(s) s1
     * @throws IOException
     */
    public void S1() throws IOException{

        WriteObj("S1", cc, addr, data, ck);
    }

    /**
     * Proceso para generar el codigo
     * del registro s9
     * @throws IOException
     */
    public void S9() throws IOException{
        WriteObj("S9", "03", "0000", "", "FC");
    }

    /**
     * @param s
     * @param cc
     * @param addr
     * @param data
     * @param ck
     * @throws IOException
     */
    public void WriteObj(String s, String cc, String addr, String data, String ck) throws IOException{
        PrintWriter out = null;
        try {// abre el fichero            
            out = new PrintWriter(new FileWriter("OBJ.txt"), true);
            out.write(s + " "+cc + " "+addr + " "+data + " "+ ck);
            out.println();            
        } finally {
            if (out != null) {
                out.close();
            } // Fin del if
        } // Fin de try

    }// Fin de método
}
