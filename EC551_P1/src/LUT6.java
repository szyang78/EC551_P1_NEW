import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LUT6 {
        private final String[] lut6_value;

        public LUT6(String[] lut6_value){
            this.lut6_value=lut6_value;
        }

        public String[] getLut6Value(){
            return this.lut6_value;
        }

    enum lut6_term{
        l0("000000","0"),
        l1("000001","1"),
        l2("000010","2"),
        l3("000011","3"),
        l4("000100","4"),
        l5("000101","5"),
        l6("000110","6"),
        l7("000111","7"),
        l8("001000","8"),
        l9("001001","9"),
        l10("001010","10"),
        l11("001011","11"),
        l12("001100","12"),
        l13("001101","13"),
        l14("001110","14"),
        l15("001111","15"),
        l16("010000","16"),
        l17("010001","17"),
        l18("010010","18"),
        l19("010011","19"),
        l20("010100","20"),
        l21("010101","21"),
        l22("010110","22"),
        l23("010111","23"),
        l24("011000","24"),
        l25("011001","25"),
        l26("011010","26"),
        l27("011011","27"),
        l28("011100","28"),
        l29("011101","29"),
        l30("011110","30"),
        l31("011111","31"),
        l32("100000","32"),
        l33("100001","33"),
        l34("100010","34"),
        l35("100011","35"),
        l36("100100","36"),
        l37("100101","37"),
        l38("100110","38"),
        l39("100111","39"),
        l40("101000","40"),
        l41("101001","41"),
        l42("101010","42"),
        l43("101011","43"),
        l44("101100","44"),
        l45("101101","45"),
        l46("101110","46"),
        l47("101111","47"),
        l48("110000","48"),
        l49("110001","49"),
        l50("110010","50"),
        l51("110011","51"),
        l52("110100","52"),
        l53("110101","53"),
        l54("110110","54"),
        l55("110111","55"),
        l56("111000","56"),
        l57("111001","57"),
        l58("111010","58"),
        l59("111011","59"),
        l60("111100","60"),
        l61("111101","61"),
        l62("111110","62"),
        l63("111111","63");

        private lut6_term(String term_binary,String index){
            this.term_binary=term_binary;
            this.index=index;
        }

        private final String term_binary;
        private final String index;

        String getBinary(){
            return this.term_binary;
        }

        String getIndex(){
            return this.index;
        }

        static lut6_term creL6_term(int index){
            return switch(index){
                case 0 -> l0;
                case 1 -> l1;
                case 2 -> l2;
                case 3 -> l3;
                case 4 -> l4;
                case 5 -> l5;
                case 6 -> l6;
                case 7 -> l7;
                case 8 -> l8;
                case 9 -> l9;
                case 10 -> l10;
                case 11 -> l11;
                case 12 -> l12;
                case 13 -> l13;
                case 14 -> l14;
                case 15 -> l15;
                case 16 -> l16;
                case 17 -> l17;
                case 18 -> l18;
                case 19 -> l19;
                case 20 -> l20;
                case 21 -> l21;
                case 22 -> l22;
                case 23 -> l23;
                case 24 -> l24;
                case 25 -> l25;
                case 26 -> l26;
                case 27 -> l27;
                case 28 -> l28;
                case 29 -> l29;
                case 30 -> l30;
                case 31 -> l31;
                case 32 -> l32;
                case 33 -> l33;
                case 34 -> l34;
                case 35 -> l35;
                case 36 -> l36;
                case 37 -> l37;
                case 38 -> l38;
                case 39 -> l39;
                case 40 -> l40;
                case 41 -> l41;
                case 42 -> l42;
                case 43 -> l43;
                case 44 -> l44;
                case 45 -> l45;
                case 46 -> l46;
                case 47 -> l47;
                case 48 -> l48;
                case 49 -> l49;
                case 50 -> l50;
                case 51 -> l51;
                case 52 -> l52;
                case 53 -> l53;
                case 54 -> l54;
                case 55 -> l55;
                case 56 -> l56;
                case 57 -> l57;
                case 58 -> l58;
                case 59 -> l59;
                case 60 -> l60;
                case 61 -> l61;
                case 62 -> l62;
                case 63 -> l63;
                default->null;
            };
        }
        //end of lut6 create term function
    }

    /**
     *
     * @param lut6_term
     * @return String[]
     * This method returns a String array that is mapped by lut6 terms
     */
    public static String[] maplut6ToArray(String lut6_term){
        String[] mappedL6term = new String[6];
        for(int i =0;i<lut6_term.length();i++){
            mappedL6term[i]= String.valueOf(lut6_term.charAt(i));
        }
        return mappedL6term;
    }

    /*
    Following method turns literals to numbers
    if the slot is empty,then mark it as "X"
    */

    public static String[] maplut6LiteralToNumArray(String[] lut6_literal){
        String[] mappedlut6Num = new String[6];
        for(int i =0;i<lut6_literal.length;i++){
            switch(lut6_literal[i]){
                case "a", "b","c","d","e","f":  mappedlut6Num[i]="1";break;
                case "a'","b'","c'","d'","e'","f'": mappedlut6Num[i]="0";break;
            }
        }
        for(int i=0;i<mappedlut6Num.length;i++){
            if(mappedlut6Num[i]==null){
                mappedlut6Num[i]="X";
            }
        }
        return mappedlut6Num;
    }

    public static void main(String[] args){
        String[] lut6term= new String[64];
        boolean isall6literal=false;
        for (int i=0;i<lut6term.length;i++){
            lut6term[i]=LUT6.lut6_term.creL6_term(i).getBinary();
        }
        for(int i=0;i<lut6term.length;i++){
            if(lut6term[i].length()!=6){
                isall6literal=false;
            }
            else{
                isall6literal=true;
            }
        }
        //System.out.println(isall6literal);


        String[][] soptoSplitlut6 = Split_Command.sopToSplitlut6("(a'*b*c*d')+(b'*c'*d'*e'*f')+(a*b*e'*f)");
        System.out.println(Arrays.deepToString(Split_Command.sopToSplitlut6("(a'*b*c*d')+(b'*c'*d'*e'*f')+(a*b*e'*f)")));
        for(int i=0;i<soptoSplitlut6.length;i++){
            System.out.println(Arrays.toString(LUT6.maplut6LiteralToNumArray(soptoSplitlut6[i])));
        }
    }

    /*
    * This function returns true if 2 terms differ by only one literal
    *
    * */
    public static boolean isonlydiffbyX(String[] term1,String[] term2){
        boolean isdiffbyX = false;
        int diffnum=0;
        for(int i=0;i<term1.length;i++){
        if(!Objects.equals(term1[i], term2[i])){
            if(Objects.equals(term1[i], "X")&& !Objects.equals(term2[i], "X")){
                isdiffbyX=true;
            }
        }
        }
        return isdiffbyX;
    }

    public static boolean is1diff(String[] term1,String[] term2){
        boolean is1diff;
        int diffnum=0;
        for(int i=0;i<term1.length;i++){
            if(!Objects.equals(term1[i], term2[i])){
                diffnum+=1;
            }
        }
        is1diff= (diffnum == 1);
        return is1diff;
    }
    public static boolean isnodiff(String[] term1,String[] term2){
        boolean isnodiff=false;
        for(int i=0;i<term1.length;i++){
            isnodiff= Objects.equals(term1[i], term2[i]);
        }
        return isnodiff;
    }

    public static int num_of_1s(String[] term){
        int num=0;
        for(int i=0;i<term.length;i++){
            if(Objects.equals(term[i], "1")){
                num+=1;
            }
        }
        return num;
    }
    public static String[] simplify_if1diff(String[] term1,String[] term2){
        String[] simplified_term = new String[6];
        if(LUT6.is1diff(term1,term2)){
            for(int i=0;i<simplified_term.length;i++){
                if(Objects.equals(term1[i], term2[i])){
                    simplified_term[i]=term1[i];
                }
                else{
                    simplified_term[i]="X";
                }
            }
        }
        return simplified_term;
    }

    public static String[] simplify_ifonlydiffbyX(String[] term1,String[] term2){
        String[] simplified_term = new String[6];
        if(LUT6.isonlydiffbyX(term1,term2)){
        for(int i=0;i<simplified_term.length;i++){
            if(!Objects.equals(term1[i], "X")){
                simplified_term[i]=term1[i];
            }
        }
        }
        return simplified_term;
    }

    public static String[][] simplifyLUT6_nonsop(String[][] lut6){
        String[][] simplified_nonsop = new String[lut6.length][6];
        ArrayList<String[]> simplified = new ArrayList<>();
        ArrayList<String[]> nonsimplified = new ArrayList<>();
        int j=0;
        for(int i=0;i<lut6.length;i++){
            while(j<lut6.length){
                if(LUT6.isonlydiffbyX(lut6[i],lut6[j])){
                    simplified.add(LUT6.simplify_ifonlydiffbyX(lut6[i],lut6[j]));
                    j++;
                }
                else
                    if(LUT6.is1diff(lut6[i],lut6[j])){
                        simplified.add(LUT6.simplify_if1diff(lut6[i],lut6[j]));
                        j++;
                    }
                    else
                        if(LUT6.isnodiff(lut6[i],lut6[j])){
                            nonsimplified.add(lut6[i]);
                            j++;
                        }
            }
        }
        return simplified_nonsop;
    }

    public static void simplifyLUT6_SOP(String[][] lut6){
        ArrayList<String[]> group0 = new ArrayList<>();
        ArrayList<String[]> group1 = new ArrayList<>();
        ArrayList<String[]> group2 = new ArrayList<>();
        ArrayList<String[]> group3 = new ArrayList<>();
        ArrayList<String[]> group4 = new ArrayList<>();
        ArrayList<String[]> group5 = new ArrayList<>();
        ArrayList<String[]> group6 = new ArrayList<>();
        ArrayList<ArrayList<String[]>> unsorted = new ArrayList<>();
        unsorted.add(group0);
        unsorted.add(group1);
        unsorted.add(group2);
        unsorted.add(group3);
        unsorted.add(group4);
        unsorted.add(group5);
        unsorted.add(group6);
        ArrayList<ArrayList<String[]>> sorted = new ArrayList<>();
        boolean simplifydone=false;
        for(int i=0;i<lut6.length;i++){
            int numof1 = 0;
            for(int k=0;k<lut6[i].length;k++){
                if(Objects.equals(lut6[i][k], "1")){
                numof1=numof1+1;
                }
                switch(numof1){
                    case 0: group0.add(lut6[i]);break;
                    case 1: group1.add(lut6[i]);break;
                    case 2: group2.add(lut6[i]);break;
                    case 3: group3.add(lut6[i]);break;
                    case 4: group4.add(lut6[i]);break;
                    case 5: group5.add(lut6[i]);break;
                    case 6: group6.add(lut6[i]);break;
                }
            }
        }//end of adding all elements in lut6 equations to groups that contain number of 1s

        while(!simplifydone){
            int nextgroup=0;
            boolean if_all_compared=false;
        for(int i=0;i<unsorted.size();i++){  //loop through all unsorted
            while(!if_all_compared){
                for(int k=0;k<unsorted.get(i).size();k++){  //unsorted first group
                    for(int j=0;j<unsorted.get(nextgroup).size();j++){//unsorted next group
                    if(LUT6.is1diff(unsorted.get(i).get(k),unsorted.get(nextgroup).get(j))) {
                        LUT6.simplify_if1diff(unsorted.get(i).get(k),unsorted.get(nextgroup).get(j));
                        nextgroup += 1;
                    }
                    if(LUT6.isonlydiffbyX(unsorted.get(i).get(k),unsorted.get(nextgroup).get(j))){
                        LUT6.simplify_ifonlydiffbyX(unsorted.get(i).get(k),unsorted.get(nextgroup).get(j));
                        nextgroup+=1;
                    }
                    }
                }

            }
        }

        }

    }


}
