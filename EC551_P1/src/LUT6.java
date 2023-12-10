import java.util.Arrays;

public class LUT6 {

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

    public static String[] mapl6ToLiteral(String lut6_term){
        String[] mappedL6term = new String[6];
        for(int i =0;i<lut6_term.length();i++){
            mappedL6term[i]= String.valueOf(lut6_term.charAt(i));
        }
        return mappedL6term;
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
        System.out.println(isall6literal);
        System.out.println(Arrays.toString(LUT6.mapl6ToLiteral(lut6term[5])));
    }


}
