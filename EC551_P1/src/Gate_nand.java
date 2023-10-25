public class Gate_nand {
    public static int[] xor_2_array(int[] i1,int[] i2){
        int[] outcome = new int[4];
        for(int i =0;i<outcome.length;i++){
            if(i1[i]==0&&i2[i]==0){
                outcome[i]=0;
            }
            else
                if ( (i1[i]==0&&i2[i]==1) || (i1[i]==1&&i2[i]==0) ) {
                    outcome[i]=1;
                }
                else
                    if(i1[i]==1&&i2[i]==1){
                        outcome[i]=0;
                    }

        }
        return outcome;
    }

    public static int xor_2_digits(int i1,int i2){
        return (~i1&i2)|(i1&~i2);
    }

    public static String xor_2_digits_SOP(String i1,String i2){

    return "("+i1+"'"+"i2"+")"+" + "+"("+i1+i2+"'"+")";
    }

    public static String xor_2_digits_POS(String i1,String i2){
        return "("+i1+"'"+i2+"'"+")"+" * "+"("+i1+i2+")";
    }
}
