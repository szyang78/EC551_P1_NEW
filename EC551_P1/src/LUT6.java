import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class LUT6 {
    private ArrayList<String> sram = new ArrayList<>(64);

    public LUT6() {
        for (int i = 0; i < 63; i++) {
            this.sram.add(null);
        }
    }

    public void setSram(ArrayList<String> sram) {
        this.sram = sram;
    }

    public ArrayList<String> getSram() {
        return this.sram;
    }

    enum lut6_term {
        l0("000000", 0),
        l1("000001", 1),
        l2("000010", 2),
        l3("000011", 3),
        l4("000100", 4),
        l5("000101", 5),
        l6("000110", 6),
        l7("000111", 7),
        l8("001000", 8),
        l9("001001", 9),
        l10("001010", 10),
        l11("001011", 11),
        l12("001100", 12),
        l13("001101", 13),
        l14("001110", 14),
        l15("001111", 15),
        l16("010000", 16),
        l17("010001", 17),
        l18("010010", 18),
        l19("010011", 19),
        l20("010100", 20),
        l21("010101", 21),
        l22("010110", 22),
        l23("010111", 23),
        l24("011000", 24),
        l25("011001", 25),
        l26("011010", 26),
        l27("011011", 27),
        l28("011100", 28),
        l29("011101", 29),
        l30("011110", 30),
        l31("011111", 31),
        l32("100000", 32),
        l33("100001", 33),
        l34("100010", 34),
        l35("100011", 35),
        l36("100100", 36),
        l37("100101", 37),
        l38("100110", 38),
        l39("100111", 39),
        l40("101000", 40),
        l41("101001", 41),
        l42("101010", 42),
        l43("101011", 43),
        l44("101100", 44),
        l45("101101", 45),
        l46("101110", 46),
        l47("101111", 47),
        l48("110000", 48),
        l49("110001", 49),
        l50("110010", 50),
        l51("110011", 51),
        l52("110100", 52),
        l53("110101", 53),
        l54("110110", 54),
        l55("110111", 55),
        l56("111000", 56),
        l57("111001", 57),
        l58("111010", 58),
        l59("111011", 59),
        l60("111100", 60),
        l61("111101", 61),
        l62("111110", 62),
        l63("111111", 63);

        private lut6_term(String term_binary, int index) {
            this.term_binary = term_binary;
            this.index = index;
        }

        private final String term_binary;
        private final int index;

        String getBinary() {
            return this.term_binary;
        }

        int getIndex() {
            return this.index;
        }

        static lut6_term creL6_term(int index) {
            return switch (index) {
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
                default -> null;
            };
        }
        //end of lut6 create term function
    }

    /**
     * @param lut6_term
     * @return String[]
     * This method returns a String array that is mapped by lut6 terms
     */
    public static String[] maplut6_term_toArray(String lut6_term) {
        String[] mappedL6term = new String[6];
        for (int i = 0; i < lut6_term.length(); i++) {
            mappedL6term[i] = String.valueOf(lut6_term.charAt(i));
        }
        return mappedL6term;
    }


    /*
    Following method turns literals to numbers
    if the slot is empty,then mark it as "X"
    */
    public static String[] maplut6LiteralToNumArray(String[] lut6_literal) {
        String[] mappedlut6Num = new String[6];
        for (int i = 0; i < lut6_literal.length; i++) {
            switch (lut6_literal[i]) {
                case "a", "b", "c", "d", "e", "f":
                    mappedlut6Num[i] = "1";
                    break;
                case "a'", "b'", "c'", "d'", "e'", "f'":
                    mappedlut6Num[i] = "0";
                    break;
            }
        }
        for (int i = 0; i < mappedlut6Num.length; i++) {
            if (mappedlut6Num[i] == null) {
                mappedlut6Num[i] = "X";
            }
        }
        return mappedlut6Num;
    }
    public static String[][] matchpattern(String[] term){
        ArrayList<String[]> pattern= new ArrayList<>();
        String[][] lut6_term = getlut6terms();
        int j=0;
        while(j< lut6_term.length){
        if(patternfind(term,lut6_term[j])){
            pattern.add(lut6_term[j]);
        }
        j++;
        }
        String[][] pattern_found= new String[pattern.size()][6];
        for(int i=0;i<pattern.size();i++){
            pattern_found[i]=pattern.get(i);
        }
        return pattern_found;
    }

    public static boolean patternfind(String[] term1,String[] term2){
        boolean patternfind = false;
        for(int i=0;i<term1.length;i++){
            if(!Objects.equals(term1[i],"X")){
                if(Objects.equals(term1[i],term2[i])){
                    patternfind=true;
                }
                else{
                    patternfind=false;
                }
            }
        }
        return patternfind;
    }

    public static String[] map6chartoStringarray(char[] cha){
        String[] sw = new String[6];
        for(int i=0;i<sw.length;i++){
            sw[i]= String.valueOf(cha[i]);
        }
        return sw;
    }

    public static void main(String[] args) throws IOException {
        /*

        String[] lut6term = new String[64];
        boolean isall6literal = false;
        for (int i = 0; i < lut6term.length; i++) {
            lut6term[i] = LUT6.lut6_term.creL6_term(i).getBinary();
        }
        for (int i = 0; i < lut6term.length; i++) {
            if (lut6term[i].length() != 6) {
                isall6literal = false;
            } else {
                isall6literal = true;
            }
        }
        //System.out.println(isall6literal);


        String[][] soptoSplitlut6 = Split_Command.sopToSplitlut6("(a'*b*c*d')+(b'*c'*d'*e'*f')+(a*b*e'*f)");
        System.out.println(Arrays.deepToString(Split_Command.sopToSplitlut6("(a'*b*c*d')+(b'*c'*d'*e'*f')+(a*b*e'*f)")));
        for (int i = 0; i < soptoSplitlut6.length; i++) {
            System.out.println(Arrays.toString(LUT6.maplut6LiteralToNumArray(soptoSplitlut6[i])));
            soptoSplitlut6[i]=LUT6.maplut6LiteralToNumArray(soptoSplitlut6[i]);
        }
        LUT6.simplifyLUT6_SOP(soptoSplitlut6);
        System.out.println(Arrays.deepToString(soptoSplitlut6));
*/
        if (Objects.equals(args[0], "write")) {
            String[][] lut6 = Split_Command.sopToSplitlut6(args[1]);
            System.out.println("Input function:"+args[1]);
            String[][] lut6_num = new String[lut6.length][6];
            String[][] all_lut6_term = LUT6.getlut6terms();
            for(int i=0;i<lut6_num.length;i++){
                lut6_num[i]=Literals.map6litto6num(lut6[i]);
            }
            ArrayList<String[][]> pattern = new ArrayList<>();
            for(int i=0;i<lut6_num.length;i++){
                pattern.add(matchpattern(lut6_num[i]));
            }
            ArrayList<String[]> pattern_final = new ArrayList<>();
            for(int i=0;i<pattern.size();i++){
                pattern_final.addAll(Arrays.asList(pattern.get(i)));
            }
            String[][] patternarry = new String[pattern_final.size()][6];
            for(int i=0;i<patternarry.length;i++){
                patternarry[i]=pattern_final.get(i);
            }

            String[] index = new String[64];
            for (int i=0;i<patternarry.length;i++){
                for(int k=0;k<all_lut6_term.length;k++){
                    if( Arrays.equals(patternarry[ i ], all_lut6_term[ k ]) ){
                        index[k]= String.valueOf(k);
                    }
                }
            }
            for(int i=0;i<index.length;i++){
                if(Objects.equals(index[i],null)){
                    index[i]="0";
                }
            }
            for(int i=0;i<index.length;i++){
                if(!Objects.equals(index[i],"0")){
                    index[i]="1";
                }
            }
            int numoftermtowrite=0;
            for(int i=0;i<index.length;i++){
                if(Objects.equals(index[i],"1")){
                    numoftermtowrite+=1;
                }
            }
            File bitstream = new File("bitstream" + "_lut6_write.txt");
            FileWriter fw = new FileWriter(bitstream);
            fw.write(Arrays.toString(index));
            fw.write("\n");
            fw.close();
            ArrayList<String[]> luts_wrriten = new ArrayList<>();
            for(int i=0;i<index.length;i++){
                if(Objects.equals(index[i],"1")){
                    luts_wrriten.add(map6chartoStringarray(LUT6.lut6_term.creL6_term(i).getBinary().toCharArray()));
                }
            }
            String[][] luts_array = new String[luts_wrriten.size()][6];
            for(int i=0;i<luts_array.length;i++){
                luts_array[i]=luts_wrriten.get(i);
            }



            ArrayList<ArrayList<String[]>> t = LUT6.simplifysop(luts_array);
            StringBuilder circuit_sb_simp = new StringBuilder();
            ArrayList<String> st = new ArrayList<>();
            for(int i=0;i<t.size();i++){
                for(int k=0;k<t.get(i).size();k++){
                    st.add(Literals.map6litsoptostring(Literals.map6numto6sop(t.get(i).get(k))));
                }
            }
            for(int i=0;i<st.size();i++){
                if(i<st.size()-1){
                    circuit_sb_simp.append(st.get(i));
                    circuit_sb_simp.append("+");
                }
                if(i==st.size()-1){
                    circuit_sb_simp.append(st.get(i));
                }
            }
            System.out.println("LUT inputs:" + " " + "a,b,c,d,e,f");
            System.out.println("Number of Luts written:"+"1");
            System.out.println("Functions to be mapped:" + circuit_sb_simp.toString());
            System.out.println("File written to:" + "bitstream" + "_lut6_write.txt");
        }

        if (Objects.equals(args[0], "read")) {
            File fw = new File(args[1]);
            String name = FileUtils.readFileToString(fw, StandardCharsets.UTF_8);
            String[] readline_fromname = StringUtils.split(name, "\n");
            System.out.println("Read bitstreams from written file");
            System.out.println(Arrays.toString(readline_fromname));

            String[][] lut_mapping = new String[readline_fromname.length][64];

            int[][] lut_index_mapping = new int[readline_fromname.length][64];

            for (int i = 0; i < lut_mapping.length; i++) {
                for (int k = 0; k < lut_mapping[i].length; k++) {
                    lut_mapping[i][k] = StringUtils.split(StringUtils.substringBetween(readline_fromname[i], "[", "]"), ", ")[k];
                }
            }

            for (int i = 0; i < lut_index_mapping.length; i++) {
                for (int k = 0; k < lut_index_mapping[i].length; k++) {
                    lut_index_mapping[i][k] = Integer.parseInt(lut_mapping[i][k]);
                }
            }

            ArrayList<ArrayList<Integer>> tomap = new ArrayList<>();
            for (int i = 0; i < readline_fromname.length; i++) {
                tomap.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < lut_index_mapping.length; i++) {
                for (int k = 0; k < lut_index_mapping[i].length; k++) {
                    if (lut_index_mapping[i][k] == 1) {
                        tomap.get(i).add(k);
                    }
                }
            }

            ArrayList<int[]> mappedindex = new ArrayList<>();
            for (int i = 0; i < tomap.size(); i++) {
                int[] index = new int[tomap.get(i).size()];
                for (int k = 0; k < index.length; k++) {
                    index[k] = tomap.get(i).get(k);
                }
                mappedindex.add(index);
            }

            for (int i = 0; i < tomap.size(); i++) {
                System.out.println("terms to be mapped to lut"+ ":" + tomap.get(i));
            }

            for (int i = 0; i < mappedindex.size(); i++) {
                System.out.println("Mapping bitstream to lut"+ "With Function:");
                        //+
                       // LUT6.simplifysop();
            }

            String[][] lut6_mappedterm = new String[tomap.get(0).size()][6];
            for(int i=0;i<lut6_mappedterm.length;i++){
                lut6_mappedterm[i]= LUT6.getlut6string(LUT6.lut6_term.creL6_term(tomap.get(0).get(i).intValue()).getBinary()) ;
            }

            System.out.println(writelut6term(lut6_mappedterm));
            ArrayList<ArrayList<String[]>> t = LUT6.simplifysop(lut6_mappedterm);
            StringBuilder circuit_sb_simp = new StringBuilder();
            ArrayList<String> st = new ArrayList<>();

            for(int i=0;i<t.size();i++){
                for(int k=0;k<t.get(i).size();k++){
                    st.add(Literals.map6litsoptostring(Literals.map6numto6sop(t.get(i).get(k))));
                }
            }
            for(int i=0;i<st.size();i++){
                if(i<st.size()-1){
                    circuit_sb_simp.append(st.get(i));
                    circuit_sb_simp.append("+");
                }
                if(i==st.size()-1){
                    circuit_sb_simp.append(st.get(i));
                }
            }

            System.out.println("This is simplified version:"+circuit_sb_simp.toString());
            //end of lut4 "read" operation
        }

        //System.out.println(Arrays.toString(LUT6.maplut6_term_toArray("100100")));
        //System.out.println(Arrays.toString(LUT6.getlut6string("100100")));
        //System.out.println(LUT6.map6to6sop(LUT6.getlut6string("100100")));
        //String[][] lut6 = new String[2][6];
        //lut6[0] = LUT6.getlut6string(LUT6.lut6_term.creL6_term(1).getBinary());
        //lut6[1] = LUT6.getlut6string(LUT6.lut6_term.creL6_term(0).getBinary());

    }

    public static String writelut6term(String[][] term) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < term.length; i++) {
            if (i < term.length - 1) {
                sb.append(LUT6.map6to6sop(term[i]));
                sb.append("+");
            }
            if (i == term.length - 1) {
                sb.append(LUT6.map6to6sop(term[i]));
            }
        }
        return sb.toString();
    }

    /**
     * This function receive the lut sram index and create all the term
     */
    public static String creLUT6stringbyIndex(int[] index) {
        StringBuilder sb = new StringBuilder();
        String[][] all_lut6_term = new String[index.length][6];
        for (int i = 0; i < index.length; i++) {
            all_lut6_term[i] = LUT6.getlut6string(LUT6.lut6_term.creL6_term(index[i]).getBinary());
        }
        for (int i = 0; i < all_lut6_term.length; i++) {
            sb.append(Arrays.toString(all_lut6_term[i]));
        }
        return sb.toString();
    }

    public static String map6to6sop(String[] term) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < term.length; i++) {
            if (i < term.length - 1) {
                if (!Objects.equals(term[i], "X")) {
                    sb.append(term[i]);
                    sb.append("*");
                }
            }
            if (i == term.length - 1) {
                sb.append(term[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }


    /**
     * This function maps lut6 term binary to literal
     */
    public static String[] getlut6string(String term) {
        String[] binarystring = LUT6.maplut6_term_toArray(term);
        String[] literalstring = new String[6];
        for (int i = 0; i < binarystring.length; i++) {
            switch (i) {
                case 0:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "a'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "a";
                    }
                    break;
                case 1:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "b'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "b";
                    }
                    break;
                case 2:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "c'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "c";
                    }
                    break;
                case 3:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "d'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "d";
                    }
                    break;
                case 4:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "e'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "e";
                    }
                    break;
                case 5:
                    if (Objects.equals(binarystring[i], "0")) {
                        literalstring[i] = "f'";
                    } else if (Objects.equals(binarystring[i], "1")) {
                        literalstring[i] = "f";
                    }
                    break;
            }
        }
        return literalstring;
    }

    /**
     * This method returns all the lut6 minterms in a string array
     *
     * @return an array contains all minterms
     */
    public static String[][] getlut6terms() {
        String[][] alllut6_sop_term = new String[64][6];
        for (int i = 0; i < alllut6_sop_term.length; i++) {
            String[] tmp = new String[6];
            for (int k = 0; k < tmp.length; k++) {
                tmp[k] = String.valueOf(lut6_term.creL6_term(i).getBinary().charAt(k));
            }
            alllut6_sop_term[i] = tmp;
        }
        return alllut6_sop_term;
    }

    /*
    This functions returns the index for values stored in sram according to the equation inputs
     */
    public static String[] maplut6_to_sopindex(String[][] lut6) {
        String[] map_index = new String[64];
        String[][] lut6_term = LUT6.getlut6terms();
        String[][] map_lut6_num = new String[lut6.length][6];

        ArrayList<String[]> lut6_terms = new ArrayList<>();
        for (int i = 0; i < lut6.length; i++) {
            map_lut6_num[i] = LUT6.maplut6LiteralToNumArray(lut6[i]);
        }
        for (int i = 0; i < map_lut6_num.length; i++) {
            for (int k = 0; k < map_lut6_num[i].length; k++) {
                if (Objects.equals(map_lut6_num[i][k], "X")) {

                }
            }
        }

        return map_index;
    }

    /*
     * This function returns true if 2 terms differ by only one literal
     *
     * */
    public static boolean isonlydiffbyX(String[] term1, String[] term2) {
        boolean isdiffbyX = false;
        int diffnum = 0;
        for (int i = 0; i < term1.length; i++) {
            if (!Objects.equals(term1[i], term2[i])) {
                if (Objects.equals(term1[i], "X") && !Objects.equals(term2[i], "X")) {
                    isdiffbyX = true;
                }
            }
        }
        return isdiffbyX;
    }

    public static boolean is1diff(String[] term1, String[] term2) {
        boolean is1diff;
        int diffnum = 0;
        for (int i = 0; i < term1.length; i++) {
            if (!Objects.equals(term1[i], term2[i])) {
                diffnum += 1;
            }
        }
        is1diff = (diffnum == 1);
        return is1diff;
    }

    public static boolean isnodiff(String[] term1, String[] term2) {
        boolean isnodiff = false;
        for (int i = 0; i < term1.length; i++) {
            isnodiff = Objects.equals(term1[i], term2[i]);
        }
        return isnodiff;
    }

    public static int num_of_1s(String[] term) {
        int num = 0;
        for (int i = 0; i < term.length; i++) {
            if (Objects.equals(term[i], "1")) {
                num += 1;
            }
        }
        return num;
    }

    public static String[] simplify_if1diff(String[] term1, String[] term2) {
        String[] simplified_term = new String[6];
        if (LUT6.is1diff(term1, term2)) {
            for (int i = 0; i < simplified_term.length; i++) {
                if (Objects.equals(term1[i], term2[i])) {
                    simplified_term[i] = term1[i];
                } else {
                    simplified_term[i] = "X";
                }
            }
        }
        return simplified_term;
    }

    public static String[] simplify_ifonlydiffbyX(String[] term1, String[] term2) {
        String[] simplified_term = new String[6];
        if (LUT6.isonlydiffbyX(term1, term2)) {
            for (int i = 0; i < simplified_term.length; i++) {
                if (!Objects.equals(term1[i], "X")) {
                    simplified_term[i] = term1[i];
                }
            }
        }
        return simplified_term;
    }

    public static String[][] simplifyLUT6_nonsop(String[][] lut6) {
        String[][] simplified_nonsop = new String[lut6.length][6];
        ArrayList<String[]> simplified = new ArrayList<>();
        ArrayList<String[]> nonsimplified = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < lut6.length; i++) {
            while (j < lut6.length) {
                if (LUT6.isonlydiffbyX(lut6[i], lut6[j])) {
                    simplified.add(LUT6.simplify_ifonlydiffbyX(lut6[i], lut6[j]));
                    j++;
                } else if (LUT6.is1diff(lut6[i], lut6[j])) {
                    simplified.add(LUT6.simplify_if1diff(lut6[i], lut6[j]));
                    j++;
                } else if (LUT6.isnodiff(lut6[i], lut6[j])) {
                    nonsimplified.add(lut6[i]);
                    j++;
                }
            }
        }
        return simplified_nonsop;
    }

    public static ArrayList<ArrayList<String[]>> simplifysop(String[][] sop) {
        ArrayList<String[]> group0 = new ArrayList<>();
        ArrayList<String[]> group1 = new ArrayList<>();
        ArrayList<String[]> group2 = new ArrayList<>();
        ArrayList<String[]> group3 = new ArrayList<>();
        ArrayList<String[]> group4 = new ArrayList<>();
        ArrayList<String[]> group5 = new ArrayList<>();
        ArrayList<String[]> group6 = new ArrayList<>();
        ArrayList<ArrayList<String[]>> sortsop = new ArrayList<>();
        boolean simplifydone = false;
        sortsop.add(group0);
        sortsop.add(group1);
        sortsop.add(group2);
        sortsop.add(group3);
        sortsop.add(group4);
        sortsop.add(group5);
        sortsop.add(group6);
        for (int i = 0; i < sop.length; i++) {
            switch (Literals.num_of_1s(sop[ i ])) {
                case 0:
                    group0.add(sop[ i ]);
                    break;
                case 1:
                    group1.add(sop[ i ]);
                    break;
                case 2:
                    group2.add(sop[ i ]);
                    break;
                case 3:
                    group3.add(sop[ i ]);
                    break;
                case 4:
                    group4.add(sop[ i ]);
                    break;
                case 5:
                    group5.add(sop[ i ]);
                    break;
                case 6:
                    group6.add(sop[ i ]);
                    break;
            }
        }
        /*
        for(int i=0;i<group0.size();i++){
            System.out.println("group0"+Arrays.toString(group0.get(i)));
        }
        for(int i=0;i<group1.size();i++){
            System.out.println("group1"+Arrays.toString(group1.get(i)));
        }
        for(int i=0;i<group2.size();i++){
            System.out.println("group2"+Arrays.toString(group2.get(i)));
        }
        for(int i=0;i<group3.size();i++){
            System.out.println("group3"+Arrays.toString(group3.get(i)));
        }
        for(int i=0;i<group4.size();i++){
            System.out.println("group4"+Arrays.toString(group4.get(i)));
        }
*/

        while (!simplifydone) {
            for (int i = 0; i < sortsop.size(); i++) {
                for (int k = 0; k < sortsop.get(i).size(); k++) {
                    for (int j = 0; j < sortsop.get(i + 1).size(); j++) {
                        if ( Literals.is1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)) ) {
                            switch (Literals.num_of_1s(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)))) {
                                case 0:
                                    group0.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 1:
                                    group1.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 2:
                                    group2.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 3:
                                    group3.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 4:
                                    group4.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 5:
                                    group5.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 6:
                                    group6.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                            }
                        } else if ( Literals.isonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)) ) {
                            switch (Literals.num_of_1s(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)))) {
                                case 0:
                                    group0.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 1:
                                    group1.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 2:
                                    group2.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 3:
                                    group3.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 4:
                                    group4.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 5:
                                    group5.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                                case 6:
                                    group6.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i + 1).trimToSize();
                                    break;
                            }
                        }
                    }
                }
            }


            for (int i = 0; i < 5000; i++) {
                if ( i == 4999 ) {
                    simplifydone = true;
                }
            }

        }//end of checking if anything should be done
        return sortsop;
    }

    public static String simplifiedtoString(ArrayList<ArrayList<String[]>> t){
        for(int i=0;i<t.size();i++){
            for(int j=0;j<t.get(i).size();j++){
                //System.out.println(Arrays.toString(t.get(i).get(j)));
            }
        }
        StringBuilder circuit_sb_simp = new StringBuilder();
        ArrayList<String> st = new ArrayList<>();
        for(int i=0;i<t.size();i++){
            for(int k=0;k<t.get(i).size();k++){
                st.add(Literals.map6numto6litstring(Literals.map6numto6sop(t.get(i).get(k))));
            }
        }
        for(int i=0;i<st.size();i++){
            if(i<st.size()-1){
                circuit_sb_simp.append(st.get(i));
                circuit_sb_simp.append("+");
            }
            if(i==st.size()-1){
                circuit_sb_simp.append(st.get(i));
            }
        }
        return circuit_sb_simp.toString();
    }

    public static void simplifysort(ArrayList<ArrayList<String[]>> sortsop){

        for (int i = 0; i < sortsop.size()&&!sortsop.get(i+1).isEmpty(); i++) {
            for (int k = 0; k < sortsop.get(i).size()&& !sortsop.get(i).isEmpty(); k++) {
                for (int j = 0; j < sortsop.get(i+1).size()&& !sortsop.get(i+ 1).isEmpty(); j++) {
                    if (Literals.is1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j))) {
                        switch (Literals.num_of_1s(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j)))) {
                            case 0:
                                sortsop.get(0).add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 1:
                                sortsop.get(1).add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 2:
                                sortsop.get(2).add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 3:
                                sortsop.get(3).add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 4:
                                sortsop.get(4).add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 5:
                                sortsop.get(5).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i + 1).trimToSize();
                                break;
                            case 6:
                                sortsop.get(6).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i + 1).trimToSize();
                                break;
                        }
                    }
                    else
                    if (Literals.isonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+1).get(j))) {
                        switch (Literals.num_of_1s(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)))) {
                            case 0:
                                sortsop.get(0).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 1:
                                sortsop.get(1).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 2:
                                sortsop.get(2).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 3:
                                sortsop.get(3).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 4:
                                sortsop.get(4).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i+1).trimToSize();
                                break;
                            case 5:
                                sortsop.get(5).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i + 1).trimToSize();
                                break;
                            case 6:
                                sortsop.get(6).add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));
                                sortsop.get(i).remove(sortsop.get(i).get(k));
                                sortsop.get(i + 1).remove(sortsop.get(i + 1).get(j));
                                sortsop.get(i).trimToSize();
                                sortsop.get(i + 1).trimToSize();
                                break;
                        }
                    }
                }
            }


        }


    }


}

