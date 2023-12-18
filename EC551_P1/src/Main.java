import java.util.*;

import org.apache.commons.lang3.StringUtils;


public class Main {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        String term_form = args[0];

        String gate_form = args[1];

        String[] inter_term = Split_Command.split_SOP(args[1]);
        String[] literals_term = Split_Command.split_SOP(args[1]);



/*
        int[] i1 = new int[]{0, 1, 1, 0};
        int[] i2 = new int[]{1, 0, 1, 1};
        int[] i3 = new int[]{1, 0, 1, 0};
        int[] i4 = new int[]{1, 1, 1, 1};
        int[] i5 = new int[]{0, 0, 0, 0};
*/
        if (Objects.equals(term_form, "SOP")) {
            String[] term = Split_Command.scan_SOP(gate_form);
            String[][] term_literals = new String[term.length][4];
            for (int i = 0; i < term_literals.length; i++) {
                term_literals[i][0] = "0";
            }

        }

        if (Objects.equals(term_form, "MIN")) {
            sb.append(args[1]);
            StringBuilder sb_simplified = new StringBuilder();
            String S3 = sb.toString();

            String[] s3 = StringUtils.split(S3, ",");
            System.out.println(Arrays.toString(s3));
            int[] index = new int[s3.length];
            for (int i = 0; i < index.length; i++) {
                index[i] = Integer.parseInt(s3[i]);
            }

            System.out.println("This is the minterm representation:" + Canonical_SOP.SOP_generation_byIndex(index));
            System.out.println("This is the maxterm representation:" + Canonical_POS.POS_generation_byIndex(index));
/*
            String SOP= Canonical_SOP.SOP_generation_byIndex(index);

            String[] splited_SOP= Split_Command.split_SOP(SOP);

            for(int i =0;i<splited_SOP.length;i++){
                splited_SOP[i]=Split_Command.split_SOP_term(splited_SOP[i]);
            }

            String[][] literal_SOP=new String[splited_SOP.length][4];

            for(int i =0;i<splited_SOP.length;i++){
                literal_SOP[i]=Split_Command.split_SOP_literal(splited_SOP[i]);
            }
*/
            String[][] lit_SOP = Split_Command.sopToSplitSOP4(Canonical_SOP.SOP_generation_byIndex(index));
            String[][] lit_SOP_num = Literals.mapsop4tonum(lit_SOP);
            //ArrayList<String[]> sim_SOP = new ArrayList<>();

           // ArrayList<String[]> not_sim_SOP = new ArrayList<>(Arrays.asList(lit_SOP));
           // ArrayList<String[]> n_sim_SOP = new ArrayList<>(lit_SOP.length);
          //  n_sim_SOP.addAll(Arrays.asList(lit_SOP));
            //System.out.println("This is n_sim_SOP" + Arrays.toString(n_sim_SOP.get(0)));
           // System.out.println("If the length is equal:" + (lit_SOP.length - n_sim_SOP.size()));
           // System.out.println("This is not simplified term:" + Arrays.toString(not_sim_SOP.get(0)));

            //System.out.println("Not simplified term" + not_sim_SOP.size());
           // System.out.println("simplified term" + sim_SOP.size());
            /*
            ArrayList<ArrayList<String[]>> t = Main.simplifysop(lit_SOP_num);
            for(int i=0;i<t.size();i++){
                for(int j=0;j<t.get(i).size();j++){
                    System.out.println("Thisis group"+t.indexOf(t.get(i))+"This is the index of item"+ t.get(i).indexOf(t.get(i).get(j)) +Arrays.toString(t.get(i).get(j)));
                }
            }

             */
           // System.out.println(not_sim_tostring.length);
           // System.out.println(sim_tostring.length);
            /*
            for(int i =0;i<sim_SOP.size();i++){
                System.out.println("This is what is in array list:"+Arrays.toString(sim_SOP.get(i)));
            }

             */

            /*
            String[][] lit_saved_sop = new String[sim_SOP.size()][4];
            for(int i =0;i<lit_saved_sop.length;i++){
                lit_saved_sop[i]=sim_SOP.get(i);
            }

            StringBuilder simped_sop_string = new StringBuilder();
            for(int i =0;i<lit_saved_sop.length;i++){
                simped_sop_string.append(Literals.lit_to_SOP(lit_saved_sop[i]));
                if(i<lit_saved_sop.length-1){
                    simped_sop_string.append("+");
                }
            }

             */
            ArrayList<ArrayList<String[]>> t = Main.simplifysop(Literals.mapCircuitToSOPNum(Canonical_SOP.SOP_generation_byIndex(index)));
            /*
            for(int i=0;i<t.size();i++){
                for(int j=0;j<t.get(i).size();j++){
                    System.out.println(Arrays.toString(t.get(i).get(j)));
                }
            }

             */
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

            System.out.println("This is simplifed terms:"+circuit_sb_simp.toString());

          //  System.out.println("This is literal to num"+Arrays.deepToString(lit_SOP_num));
            int num_epi = t.get(0).size()+t.get(1).size()+t.get(2).size()+t.get(3).size()+t.get(4).size();
            System.out.println("This is the number of Essential PIs:"+num_epi);
            System.out.println("Prime Implicant:"+ num_epi);
            int q = Literals.literalSaved_SOP(lit_SOP,Split_Command.sopToSplitSOP4(circuit_sb_simp.toString()));
            System.out.println("This is the literal saved:"+q);

            System.out.println("This is inverse of the SOP:" + SOP_Inverse.SOP_Inverse_Generation(index));
            ArrayList<ArrayList<String[]>> t1 = Main.simplifysop(Literals.mapCircuitToSOPNum(SOP_Inverse.SOP_Inverse_Generation(index)));
            StringBuilder circuit_sb_simp1 = new StringBuilder();
            ArrayList<String> st1 = new ArrayList<>();
            for(int i=0;i<t1.size();i++){
                for(int k=0;k<t1.get(i).size();k++){
                    st1.add(Literals.map4litsoptostring(Literals.map4numto4sop(t1.get(i).get(k))));
                }
            }
            for(int i=0;i<st1.size();i++){
                if(i<st1.size()-1){
                    circuit_sb_simp1.append(st1.get(i));
                    circuit_sb_simp1.append("+");
                }
                if(i==st.size()-1){
                    circuit_sb_simp1.append(st1.get(i));
                }
            }
            System.out.println("This is inverse simplification:"+circuit_sb_simp1);
            System.out.println("This is inverse of the POS:" + POS_Inverse.POS_Inverse_Generation(index));
            System.out.println(Canonical_SOP.SOP_onset(index));
            System.out.println(Canonical_SOP.SOP_offset(index));
            //String[][] tx = new String[][]{{"1","X","0","X"}};
           // String[] term = new String[]{"1","X","0","X"};
           // String[][] termerm = LUT4.findmatchpattern(term);
           // System.out.println(term[0]);
           // System.out.println(term[1]);
           // System.out.println(term[2]);
           // System.out.println(term[3]);
           // System.out.println(Arrays.deepToString(termerm));

        }
/*
        if(Objects.equals(term_form, "MAX")) {
            sb.append(args[1]);

            String S3 = sb.toString();

            String[] s3 =StringUtils.split(S3,",");
            System.out.println(Arrays.toString(s3));
            int[] index = new int[s3.length];
            for (int i = 0; i < index.length; i++) {
                index[i] = Integer.parseInt(s3[i]);

            }

            for(int i =0;i<index.length;i++){
                System.out.println(index[i]);
            }

            int[] index1 = SOP_Inverse.getInverseIndex(index);
            for (int j : index1) {
                System.out.println(j);
            }
          System.out.println(Canonical_POS.POS_generation_byIndex(index));

          System.out.println(POS_Inverse.POS_Inverse_Generation(index));
          System.out.println(Canonical_POS.POS_onset(index));
          System.out.println(Canonical_POS.POS_offset(index));
        }

*/
        //Only fit for 2 level circuit
        if (Objects.equals(args[0], "CIRCUIT")) {
            Scanner scanner = new Scanner(Arrays.toString(args));
            ArrayList<String> andgate_level = new ArrayList<>();
            StringBuilder circuit_sb = new StringBuilder();


            for (int i = 1; i < args.length; i++) {
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

            System.out.println("Input 2-level circuit as shown:" + circuit_sb.toString());
           // System.out.println(Arrays.deepToString(Split_Command.circuitToSOP(circuit_sb.toString())));
           // System.out.println(Arrays.deepToString(Literals.mapCircuitToSOPNum(circuit_sb.toString())));

           ArrayList<ArrayList<String[]>> t = Main.simplifysop(Literals.mapCircuitToSOPNum(circuit_sb.toString()));

           for(int i=0;i<t.size();i++){
                for(int j=0;j<t.get(i).size();j++){
                   System.out.println(Arrays.toString(t.get(i).get(j)));
               }
            }
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

           System.out.println("This is simplifed circuit:"+circuit_sb_simp.toString());

        }

        // System.out.println(Literals.simplifyLiteral(Literals.getLiteral("(a'*b*c*d')"),Literals.getLiteral("(a*b*c*d)")));
        //System.out.println(Literals.literalSaved("(a'*b'*c'*d) + (a'*b'*c*d') + (a'*b'*c*d) + (a'*b*c'*d') + (a'*b*c'*d) + (a'*b*c*d') + (a'*b*c*d)"));
        //int[] out_or = Gate_or.OR_3(i2,i3,i5);
        //for (int i=0;i<out_or.length;i++){
        //System.out.println(out_or[i]);
        //}
        //System.out.println(Gate_and.AND_3_byTerm("a","b",Gate_and.AND_2_byTerm("a",Gate_and.AND_3_byTerm("a","b","c'"))));
        //System.out.println(Gate_and.AND_2_byTerm("a",Gate_and.AND_3_byTerm("a","b","c'")));
        // System.out.println(Gate_and.AND_4_byTerm(Gate_and.AND_2_byTerm("a",Gate_and.AND_3_byTerm("a","b","c'")), "b", "c'", Gate_and.AND_2_byTerm("a",Gate_and.AND_3_byTerm("a","b","c'"))));

/*
        String terms = "(a'*b'*c'*d') + (a'*b'*c*d') + (a'*b'*c*d) + (a'*b*c*d') + (a'*b*c*d) + (a*b'*c'*d) + (a*b'*c*d) + (a*b*c'*d') + (a*b*c'*d) + (a*b*c*d')";
        String[] terms_separated = terms.split("\\+");
        System.out.println(Arrays.toString(terms_separated));
        System.out.println((Split_Command.split_SOP(terms))[1]);

        System.out.println((Arrays.toString(Split_Command.split_SOP_literal(Split_Command.split_SOP(terms)[1]))));
        */

    }
    public static ArrayList<ArrayList<String[]>> simplifycircuitsop(String[][] circuitsop){
        ArrayList<ArrayList<String[]>> sortcircuit = new ArrayList<>();

        return sortcircuit;
    }

    public static String simplifiedtoString(ArrayList<ArrayList<String[]>> t){
        for(int i=0;i<t.size();i++){
            for(int j=0;j<t.get(i).size();j++){
                System.out.println(Arrays.toString(t.get(i).get(j)));
            }
        }
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
        return circuit_sb_simp.toString();
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
                Main.simplifysort(sortsop);
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
        for (int i = 0; i < sortsop.size(); i++) {
            for (int k = 0; k < sortsop.get(i).size(); k++) {
                for (int j = 0; j < sortsop.get(i+1).size(); j++) {
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


    }




