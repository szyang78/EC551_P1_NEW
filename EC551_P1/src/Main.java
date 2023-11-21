import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;


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
        if(Objects.equals(term_form, "SOP")){
        String[] term= Split_Command.scan_SOP(gate_form);
        String[][] term_literals = new String[term.length][4];
        for(int i =0;i<term_literals.length;i++){
            term_literals[i][0]="0";
        }

        }

        if(Objects.equals(term_form, "MIN")) {
            sb.append(args[1]);
            StringBuilder sb_simplified = new StringBuilder();
            String S3 = sb.toString();

            String[] s3 =StringUtils.split(S3,",");
            System.out.println(Arrays.toString(s3));
            int[] index = new int[s3.length];
            for (int i = 0; i < index.length; i++) {
                index[i] = Integer.parseInt(s3[i]);

            }

          System.out.println("This is the minterm representation:"+Canonical_SOP.SOP_generation_byIndex(index));

            String SOP= Canonical_SOP.SOP_generation_byIndex(index);

            String[] splited_SOP= Split_Command.split_SOP(SOP);

            for(int i =0;i<splited_SOP.length;i++){
                splited_SOP[i]=Split_Command.split_SOP_term(splited_SOP[i]);
            }

            String[][] literal_SOP=new String[splited_SOP.length][4];

            for(int i =0;i<splited_SOP.length;i++){
                literal_SOP[i]=Split_Command.split_SOP_literal(splited_SOP[i]);
            }

            ArrayList<String[]> sim_SOP = new ArrayList<>();

            ArrayList<String[]> not_sim_SOP = new ArrayList<>(Arrays.asList(literal_SOP));

            System.out.println("This is not simplified term:"+ Arrays.toString(not_sim_SOP.get(0)));


                for (int j = 1, i = 0; i < not_sim_SOP.size(); i++){
                    while (j < not_sim_SOP.size()) {
                        if (Literals.is1litdiffSOP(not_sim_SOP.get(i), not_sim_SOP.get(j))) {
                            sim_SOP.add(Literals.simplify_1litdiffSOP(not_sim_SOP.get(i), not_sim_SOP.get(j)));
                            not_sim_SOP.remove(i);
                            not_sim_SOP.remove(j);
                        }
                        j++;
                    }
                    j=i+1;
                }
            StringBuilder sb_sim_sop = new StringBuilder();
            System.out.println("simplifed"+ Arrays.toString(sim_SOP.get(0)));
            System.out.println("simplifed"+ Arrays.toString(sim_SOP.get(1)));
            System.out.println("simplifed"+ Arrays.toString(sim_SOP.get(2)));
            System.out.println("nsimplifed"+ Arrays.toString(not_sim_SOP.get(0)));
            System.out.println("nsimplifed"+ Arrays.toString(not_sim_SOP.get(1)));

            System.out.println("Not simplified term"+not_sim_SOP.size());
            System.out.println("simplified term"+sim_SOP.size());

            String[][] not_sim_tostring = new String[not_sim_SOP.size()][4];
            for(int i=0;i<not_sim_tostring.length;i++){
                not_sim_tostring[i]=not_sim_SOP.get(i);
            }
            String[][] sim_tostring = new String[sim_SOP.size()][4];
            for(int i=0;i<not_sim_tostring.length;i++){
                sim_tostring[i]=sim_SOP.get(i);
            }

            System.out.println(not_sim_tostring.length);
            System.out.println(sim_tostring.length);
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

            int totalPI = sim_tostring.length+not_sim_tostring.length;
            System.out.println("This is the number of Essential PIs:"+totalPI);
            System.out.println("This is the literal saved:"+Literals.literalSaved_SOP(literal_SOP,sim_tostring));

          System.out.println("This is inverse of the SOP:"+SOP_Inverse.SOP_Inverse_Generation(index));
          System.out.println("This is inverse of the POS:"+POS_Inverse.POS_Inverse_Generation(index));
          System.out.println(Canonical_SOP.SOP_onset(index));
          System.out.println(Canonical_SOP.SOP_offset(index));

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
        if(Objects.equals(args[0], "CIRCUIT")) {
            Scanner scanner = new Scanner(Arrays.toString(args));
            ArrayList<String> andgate_level = new ArrayList<>();
            StringBuilder circuit_sb = new StringBuilder();


            for(int i=1;i<args.length;i++){
                if(StringUtils.contains(args[i],"AND")){
                String[] split_input = StringUtils.split(args[i],",");
                if(split_input.length==3){
                    andgate_level.add(Gate_and.AND_2_byTerm(split_input[1],split_input[2]));
                }
                    if(split_input.length==4){
                        andgate_level.add(Gate_and.AND_3_byTerm(split_input[1],split_input[2],split_input[3]));
                    }
                    if(split_input.length==5){
                        andgate_level.add(Gate_and.AND_4_byTerm(split_input[1],split_input[2],split_input[3],split_input[4]));
                    }

                }

                if(StringUtils.contains(args[i],"OR")){
                    for(int k=0;k<andgate_level.size();k++){
                        circuit_sb.append(andgate_level.get(k));
                        if(k<andgate_level.size()-1){
                            circuit_sb.append("+");
                        }
                    }
                }

            }

            System.out.println("Input 2-level circuit as shown:"+circuit_sb.toString());

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
}