import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Registros_S {
    BcTabSim bt = new BcTabSim();
    Conversor conv = new Conversor();
    Idx coms = new Idx();
    String data, cm, ck, cc, addr, chk, bin;
    int suma, cont, contS1, aux, aux1, tamaño, pos;
    String datauni = "", dataaux = "";

    /**
     * Proceso para generar el codigo
     * del registro s0
     * 
     * @param nomArch
     * @throws IOException
     * @throws InterruptedException
     */
    public void S0(String nomArch) throws IOException, InterruptedException {
        /* declaracion de variables y concatenacion de hexadecimales */
        data = bt.asciiS0(nomArch);
        data = data + "0A";
        data = "43 3A 20 " + data;
        aux = (data.length() / 3 + 4);
        suma = aux;
        cc = conv.dectohex(aux);
        cc = conv.ceros9bits(cc);
        /* Sumar los valores en decimal */
        String arr[] = data.split(" ");
        for (int i = 0; i < arr.length; i++) {
            aux1 = conv.hextodec(arr[i]);
            suma = suma + aux1;
        }
        /* Tomar los valores menos significativos del hexadecimal */
        chk = conv.dectohex(suma);
        arr = chk.split("");
        chk = arr[arr.length - 2] + arr[arr.length - 1];
        /* Aplicar complemento a 1 */
        cont = conv.hextodec(chk);
        bin = Integer.toBinaryString(cont);
        bin = coms.C1_8bits(bin);
        aux1 = conv.bintodec(bin);
        ck = conv.dectohex(aux1);
        // Escribir en el documento
        WriteObj("S0", cc.toUpperCase(), "0000", data, ck.toUpperCase());
    }

    /**
     * Proceso para generar el codigo 
     * del registro(s) s1
     * @throws IOException
     * @throws InterruptedException
     */
    public void data(String data, String dir_inic, String addr) throws IOException{
        datauni = datauni + data;
        if(addr.equals("Memoria") || addr.equals("END")){
            s1(datauni, dir_inic);
            datauni = "";
        }else if (datauni.length() == 32) {
            s1(datauni, dir_inic);
            datauni = "";
        }else if (datauni.length() > 32) {
            String arr[] = datauni.split("");
            datauni="";
            for (int i = 0; i < arr.length; i= i + 2) {
                if(i < 32){
                    datauni = datauni + arr[i] + arr[i+1];
                }else if(i >= 32){
                    dataaux = dataaux + arr[i] + arr[i+1]; 
                }
            }
            s1(datauni, dir_inic);
            datauni="";
            datauni = dataaux;
            dataaux = "";
        } 
    }

    /**
     * Proceso para generar el codigo
     * del registro(s) s1
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void s1(String data, String dir_inic) throws IOException {
        contS1++;
        String arr1[] = data.split("");
        data = "";

        for (int i = 0; i < arr1.length; i = i + 2) {
            data = data + arr1[i] + arr1[i + 1] + " ";
        }

        aux = (data.length() / 3 + 3);
        aux1 = (data.length() / 3);
        if (contS1 > 1) {
            tamaño = conv.hextodec(dir_inic);
            tamaño = tamaño + aux1;
            dir_inic = conv.dectohex(tamaño);
            dir_inic = conv.ceros(dir_inic);
        } else if (contS1 == 1) {
            tamaño = conv.hextodec(dir_inic);
        }

        suma = aux;
        suma = suma + tamaño;
        cc = conv.dectohex(aux);
        cc = conv.ceros9bits(cc);

        /* Sumar los valores en decimal */
        String arr[] = data.split(" ");
        for (int i = 0; i < arr.length; i++) {
            aux1 = conv.hextodec(arr[i]);
            suma = suma + aux1;
        }

        /* Tomar los valores menos significativos del hexadecimal */
        chk = conv.dectohex(suma);
        arr = chk.split("");
        chk = arr[arr.length - 2] + arr[arr.length - 1];
        /* Aplicar complemento a 1 */
        cont = conv.hextodec(chk);
        bin = Integer.toBinaryString(cont);
        bin = coms.C1_8bits(bin);
        aux1 = conv.bintodec(bin);
        ck = conv.dectohex(aux1);
        ck = conv.ceros9bits(ck);

        WriteObj("S1", cc.toUpperCase(), dir_inic.toUpperCase(), data, ck.toUpperCase());
    }

    /**
     * Proceso para generar el codigo
     * del registro s9
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public void S9() throws IOException, InterruptedException {
        WriteObj("S9", "03", "0000", "", "FC");
    }

    /**
     * @param s
     * @param cc
     * @param addr
     * @param data
     * @param ck
     * @throws IOException
     * @throws InterruptedException
     */
    public void WriteObj(String s, String cc, String addr, String data, String ck) throws IOException {
        File obj = new File("OBJ.txt");
        FileWriter arc = new FileWriter(obj.getAbsolutePath(), true);
        PrintWriter out = null;

        try {// abre el fichero
            out = new PrintWriter(arc);
            out.write(s + " " + cc + " " + addr + " " + data + " " + ck);
            out.println();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } // Fin de try

    }// Fin de método
}
