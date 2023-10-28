public class Gate_not {
    public static int[] invert_array(int[] i1){
        int[] outcome = new int[4];
        for(int i =0;i<outcome.length;i++){
            outcome[i]= (~i1[i]);
        }
        return outcome;
    }

    public static String invert_term(String term){
        return term+"'";
    }

    public static int invert_1_digit(int i){
        return ~i;
    }

    public static String invert_literal(String i){
        return i+"'";
    }
}
