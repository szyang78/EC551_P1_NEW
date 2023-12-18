import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class LUT4 {

    private String outputs;
    public static final String[] inputs = {"a", "b", "c", "d"};

    private ArrayList<String> sram = new ArrayList<>(16);

    public LUT4() {
        for (int i = 0; i < 16; i++) {
            this.sram.add(null);
        }

    }

    public String[] getInputs() {
        return this.inputs;
    }

    public ArrayList<String> getSram() {
        return this.sram;
    }

    public void setSram(int[] index) {
        for (int i = 0; i < index.length; i++) {
            this.getSram().set(index[i], "1");
        }

        for (int i = 0; i < this.sram.size(); i++) {
            if (this.sram.get(i) == null) {
                this.sram.set(i, "0");
            }
        }
    }

    public static String[][] findmatchpattern(String[] term){

        ArrayList<String[]> pattern_found= new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        for(int i=0;i<term.length;i++){
            if(!Objects.equals(term[i], "X")){
                index.add(i);
            }
        }
        for(int k=0;k<15;k++){
            int l =0;
            boolean all_true_pattern=false;
                for(int t=0;t<index.size();t++){
                    if (Objects.equals(term[index.get(t)], String.valueOf(Minterm.getMinterm(k).getBinary().charAt(index.get(t))))) {
                        all_true_pattern = true;
                    }
                }

            if(all_true_pattern){
                char[] char1 = Minterm.getMinterm(k).getBinary().toCharArray();
                String[] ol = new String[4];
               for(int i=0;i<ol.length;i++){
                   ol[i]= String.valueOf(char1[i]);
               }
                pattern_found.add(ol);
            }
        }

        String[][] pattern = new String[pattern_found.size()][4];
        for(int i=0;i<pattern.length;i++){
            pattern[i]=pattern_found.get(i);
        }
        return pattern;
    }

    public void setSram(String[] index) {
        for (int i = 0; i < index.length; i++) {
            this.getSram().set(Integer.parseInt(index[i]), "1");
        }
        for (int i = 0; i < this.sram.size(); i++) {
            if (this.sram.get(i) == null) {
                this.sram.set(i, "0");
            }
        }
    }

    public static String[][] getlut4terms(){
        String[][] alllut4_sop_term = new String[16][4];
        for (int i = 0; i < alllut4_sop_term.length; i++) {
            String[] tmp = new String[4];
            for (int k = 0; k < tmp.length; k++) {
                tmp[k] = String.valueOf(Minterm.minterm.creMinterm(i).getBinary().charAt(k));
            }
            alllut4_sop_term[i] = tmp;
        }
        return alllut4_sop_term;
    }
    public static String[][] matchpattern(String[] term){
        ArrayList<String[]> pattern= new ArrayList<>();
        String[][] lut4_term = getlut4terms();
        int j=0;
        while(j< lut4_term.length){
            if(patternfind(term,lut4_term[j])){
                pattern.add(lut4_term[j]);
            }
            j++;
        }
        String[][] pattern_found= new String[pattern.size()][4];
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

    public String getLUTS4value(int index) {
        return this.sram.get(index);
    }

    public static String[] map4litto4num(String[] lit){
        String[] num= new String[4];
        for(int i=0;i<lit.length;i++){
            switch (lit[i]){
                case "a","b","c","d":num[i]="1";break;
                case "a'","b'","c'","d'":num[i]="0";break;
                case "X":num[i]="X";break;
                case null:num[i]="X";break;
                default:
                    throw new IllegalStateException("Unexpected value: " + lit[ i ]);
            }
        }
        for(int i=0;i<num.length;i++){
            if(Objects.equals(num[i],null)){
                num[i]="X";
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        if(Objects.equals(args[0], "LUT4")){
            System.out.println(LUT4.circuitRepresent(args));
        }
        /*Following function read args, if it's "write"
        * Then use args[2] and following as [minterm numbers,minterm numbers,....]
        * to specify how many functions you want to implement
        * Then use args[1] to specify the number of luts you want to write to
        * It will map to the following LUTs
        * */
        if (Objects.equals(args[0], "write")) {
            /*
            int argslength_forindex = 2;

            String[] args_index = new String[args.length - 2];

            for (int i = 0; i < args_index.length; i++) {
                args_index[i] = args[argslength_forindex];
                argslength_forindex++;
            }

            for (int i = 0; i < args_index.length; i++) {
                args_index[i] = StringUtils.substringBetween(args_index[i], "[", "]");
            }


            ArrayList<String[]> lutindex_fromstring = new ArrayList<>();
            for (int i = 0; i < args_index.length; i++) {
                lutindex_fromstring.add(StringUtils.split(args_index[i], ","));
            }

            int[] lutnum = new int[argslength_forindex];
            for (int i = 0; i < lutnum.length; i++) {
                lutnum[i] = i;
            }

            ArrayList<LUT4> luts_write = new ArrayList<>();

            for (int i = 0; i < lutindex_fromstring.size(); i++) {
                luts_write.add(new LUT4());
                luts_write.get(i).setSram(lutindex_fromstring.get(i));
            }

             */

            String[][] lut4 = Split_Command.sopToSplitSOP4(args[1]);
            System.out.println("Input function:"+args[1]);


            String[][] lut4_num = new String[lut4.length][4];
            String[][] all_lut4_term = LUT4.getlut4terms();
            for(int i=0;i<lut4_num.length;i++){
                lut4_num[i]=LUT4.map4litto4num(lut4[i]);
            }


            ArrayList<String[][]> pattern = new ArrayList<>();
            for(int i=0;i<lut4_num.length;i++){
                pattern.add(matchpattern(lut4_num[i]));
            }
            ArrayList<String[]> pattern_final = new ArrayList<>();
            for(int i=0;i<pattern.size();i++){
                pattern_final.addAll(Arrays.asList(pattern.get(i)));
            }
            String[][] patternarry = new String[pattern_final.size()][4];
            for(int i=0;i<patternarry.length;i++){
                patternarry[i]=pattern_final.get(i);
            }

            String[] index = new String[16];
            for (int i=0;i<patternarry.length;i++){
                for(int k=0;k<all_lut4_term.length;k++){
                    if( Arrays.equals(patternarry[ i ], all_lut4_term[ k ]) ){
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
            File bitstream = new File("bitstream" + "_lut4_write.txt");
            FileWriter fw = new FileWriter(bitstream);
            fw.write(Arrays.toString(index));
            fw.write("\n");
            fw.close();
            int[] index_int = new int[index.length];
            for(int i=0;i<index.length;i++){
                index_int[i]=Integer.parseInt(index[i]);
            }

            ArrayList<ArrayList<String[]>> t = LUT4.simplifysop(Literals.mapCircuitToSOPNum(args[1]));
            StringBuilder circuit_sb_simp = new StringBuilder();
            ArrayList<String> st = new ArrayList<>();
            for(int i=0;i<t.size();i++){
                for(int k=0;k<t.get(i).size();k++){
                    st.add(Literals.map4litsoptostring(Literals.map4numto4sop(t.get(i).get(k))));
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
            System.out.println("Simplified version:"+circuit_sb_simp.toString());
//Literals.mapsop4tonum(Split_Command.sopToSplitSOP4(Canonical_SOP.SOP_generation_byIndex(index_int)))))
            System.out.println("LUT inputs:" + " " + "a,b,c,d");
            System.out.println("Number of Luts written:"+1);
            System.out.println("File written to:" + "bitstream"+"_lut4_write.txt");
        }


        if (Objects.equals(args[0], "read")) {
            File fw = new File(args[1]);
            String name = FileUtils.readFileToString(fw, StandardCharsets.UTF_8);
            String[] readline_fromname = StringUtils.split(name, "\n");
            System.out.println("Read bitstreams from written file");
            System.out.println(Arrays.toString(readline_fromname));

            String[][] lut_mapping = new String[readline_fromname.length][16];

            int[][] lut_index_mapping = new int[readline_fromname.length][16];

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


            for (int i = 0, j = 1; i < tomap.size(); i++, ++j) {
                System.out.println("minterms to be mapped to lut" + j + ":" + tomap.get(i));
            }

            for (int i = 0, j = 1; i < mappedindex.size(); i++, ++j) {
                System.out.println("Mapping bitstream to lut" + j
                        + "With Function:"
                        +Main.simplifiedtoString(Main.simplifysop(Literals.mapsop4tonum(Split_Command.sopToSplitSOP4(Canonical_SOP.SOP_generation_byIndex(mappedindex.get(i)))))));
            }

            //end of lut4 "read" operation
        }

        //end of LUT4 main function
    }

    /*
    * Following function is used to print out any circuit that we want
    * constraint: 2 level circuit :SOP form
    * */
    public static String circuitRepresent(String[] args) {
        String circuit = "";
        if (Objects.equals(args[1], "CIRCUIT")) {
            Scanner scanner = new Scanner(Arrays.toString(args));
            ArrayList<String> andgate_level = new ArrayList<>();
            StringBuilder circuit_sb = new StringBuilder();


            for (int i = 2; i < args.length; i++) {
                if (StringUtils.contains(args[i], "AND")) {
                    String[] split_input = StringUtils.split(args[i], ",");
                    if (split_input.length == 3) {
                        andgate_level.add(Gate_and.AND_2_byTerm(split_input[1], split_input[2]));
                    }
                    if (split_input.length == 4) {
                        andgate_level.add(Gate_and.AND_3_byTerm(split_input[1], split_input[2], split_input[3]));
                    }
                    if (split_input.length == 5) {
                        andgate_level.add(Gate_and.AND_4_byTerm(split_input[1], split_input[2], split_input[3], split_input[4]));
                    }

                }

                if (StringUtils.contains(args[i], "OR")) {
                    for (int k = 0; k < andgate_level.size(); k++) {
                        circuit_sb.append(andgate_level.get(k));
                        if (k < andgate_level.size() - 1) {
                            circuit_sb.append("+");
                        }
                    }
                }

            }
            circuit=circuit_sb.toString();
        }
        return circuit;
        //end of circuit presentation function in lut4
    }

    public static ArrayList<ArrayList<String[]>> simplifysop(String[][] sop) {
        ArrayList<String[]> group0 = new ArrayList<>();
        ArrayList<String[]> group1 = new ArrayList<>();
        ArrayList<String[]> group2 = new ArrayList<>();
        ArrayList<String[]> group3 = new ArrayList<>();
        ArrayList<String[]> group4 = new ArrayList<>();
        ArrayList<ArrayList<String[]>> sortsop = new ArrayList<>();
        boolean simplifydone = false;
        sortsop.add(group0);
        sortsop.add(group1);
        sortsop.add(group2);
        sortsop.add(group3);
        sortsop.add(group4);
        for (int i = 0; i < sop.length; i++) {
            switch (Literals.num_of_1s(sop[i])) {
                case 0:
                    group0.add(sop[i]);
                    break;
                case 1:
                    group1.add(sop[i]);
                    break;
                case 2:
                    group2.add(sop[i]);
                    break;
                case 3:
                    group3.add(sop[i]);
                    break;
                case 4:
                    group4.add(sop[i]);
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
            /*
            for(int i=0;i<group0.size();i++){
                if(Literals.ifisnull(group0.get(i))){
                    group0.remove(group0.get(i));
                }
            }
            for(int i=0;i<group1.size();i++){
                if(Literals.ifisnull(group1.get(i))){
                    group1.remove(group1.get(i));
                }
            }
            for(int i=0;i<group2.size();i++){
                if(Literals.ifisnull(group2.get(i))){
                    group2.remove(group2.get(i));
                }
            }
            for(int i=0;i<group3.size();i++){
                if(Literals.ifisnull(group3.get(i))){
                    group3.remove(group3.get(i));
                }
            }
            for(int i=0;i<group4.size();i++){
                if(Literals.ifisnull(group4.get(i))){
                    group4.remove(group4.get(i));
                }
            }

             */

            //old version
            /*
            for (int i = 0; i < sortsop.size()&&!sortsop.get(i+1).isEmpty(); i++) {
                for (int k = 0; k < sortsop.get(i).size()&& !sortsop.get(i).isEmpty(); k++) {
                    for (int j = 0; j < sortsop.get(i+1).size()&& !sortsop.get(i+ 1).isEmpty(); j++) {
                        if (Literals.is1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j))) {
                            switch (Literals.num_of_1s(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j)))) {
                                case 0:
                                    group0.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 1:
                                    group1.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 2:
                                    group2.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 3:
                                    group3.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 4:
                                    group4.add(Literals.simplify_if1diff(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                            }
                        }
                        else
                        if (Literals.isonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+1).get(j))) {
                            switch (Literals.num_of_1s(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)))) {
                                case 0:
                                    group0.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 1:
                                    group1.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 2:
                                    group2.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 3:
                                    group3.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                                case 4:
                                    group4.add(Literals.simplify_ifonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i+ 1).get(j)));
                                    sortsop.get(i).remove(sortsop.get(i).get(k));
                                    sortsop.get(i+ 1).remove(sortsop.get(i+ 1).get(j));
                                    sortsop.get(i).trimToSize();
                                    sortsop.get(i+1).trimToSize();
                                    break;
                            }
                        }
                    }
                }
            }//end of simplifying function

             */
            /*
                for (int i = 0; i < sortsop.size(); i++) {
                    for (int k = 0; k < sortsop.get(i).size(); k++) {
                        for (int j = 0; j < sortsop.get(i + 1).size(); j++) {
                            simplifydone= (!Literals.is1diff(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)) && !Literals.isonlydiffbyX(sortsop.get(i).get(k), sortsop.get(i + 1).get(j)));

                        }
                    }
                }

             */

            for(int i=0;i<5000;i++){
                LUT4.simplifysort(sortsop);
                if(i==4999){
                    simplifydone = true;
                }
            }


        }//end of checking if anything should be done

        /*
        for(int i=0;i<sortsop.size();i++){
            for(int j=0;j<sortsop.get(i).size();j++){
                for(int k=0;k<sortsop.get(i).get(j).length;k++){
                    if(sortsop.get(i).get(j)[k]==null){
                        sortsop.get(i).get(j)[k]="X";
                    }
                }
            }
        }
        */
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
        return sortsop;
    }//end of simply function

    public static void simplifysort(ArrayList<ArrayList<String[]>> sortsop){
//&&!sortsop.get(i+1).isEmpty()
        //&& !sortsop.get(i).isEmpty()
        //&& !sortsop.get(i+ 1).isEmpty()
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
                        }
                    }
                }
            }
        }
    }
    //end of LUT4 class
}


