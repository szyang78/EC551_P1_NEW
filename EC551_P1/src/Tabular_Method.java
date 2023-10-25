import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tabular_Method {
    public static String Tabular_SOP(int[] index){
        int[] num = new int[index.length];
        String[] name = new String[index.length];
        Minterm.minterm[] mt = new Minterm.minterm[index.length];
        for (int i =0;i<mt.length;i++){
            mt[i]=(Minterm.getMinterm(index[i]));
        }
        for (int i =0;i<num.length;i++){
            num[i]=(mt[i].getBinary());
        }
        for(int i=0;i<name.length;i++){
            name[i]=(mt[i].getTermName());
        }

        ArrayList<Minterm.minterm> zero_1 = new ArrayList<>();
        ArrayList<Minterm.minterm> one_1 = new ArrayList<>();
        ArrayList<Minterm.minterm> two_1 = new ArrayList<>();
        ArrayList<Minterm.minterm> three_1 = new ArrayList<>();
        ArrayList<Minterm.minterm> four_1 = new ArrayList<>();
        ArrayList<ArrayList<Minterm.minterm>> gp1 = new ArrayList<>(5);

        gp1.set(0,zero_1);
        gp1.set(1,one_1);
        gp1.set(2,two_1);
        gp1.set(3,three_1);
        gp1.set(4,four_1);

        for(int i =0;i<mt.length;i++){
            switch(mt[i].getIndex()){
                case 0 -> zero_1.add(mt[i]);
                case 1 -> one_1.add(mt[i]);
                case 2 -> throw new RuntimeException();
                case 3 ->throw new RuntimeException();
                case 4 ->throw new RuntimeException();
                case 5 ->throw new RuntimeException();
                case 6 ->throw new RuntimeException();
                case 7 ->throw new RuntimeException();
                case 8 ->throw new RuntimeException();
                case 9 ->throw new RuntimeException();
                case 10 ->throw new RuntimeException();
                case 11 ->throw new RuntimeException();
                case 12 ->throw new RuntimeException();
                case 13 ->throw new RuntimeException();
                case 14 ->throw new RuntimeException();
                case 15 ->throw new RuntimeException();
                default ->throw new RuntimeException();
            }
        }


        return " ";
    }

    public static String Tabular_POS(int[] index){

        return " ";
    }
}
