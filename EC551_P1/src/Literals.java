import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Literals {

    public static String[] getLiteral_MIN_MAX(String term){

        String sb_1 = StringUtils.substringBetween(term,"(",")");
        String[] literal= sb_1.split("\\*");
        System.out.println(Arrays.toString(literal));

        return literal;
    }

    public static String[] getLiteral_SOP(String term){
        String sb_1 = StringUtils.substringBetween(term,"(",")");
        String[] literal= sb_1.split("\\*");
        String[] returned_literal = new String[4];

        for (int i=0;i<literal.length;i++){
            if(Objects.equals(literal[i], "a") || Objects.equals(literal[i], "a'")){
                returned_literal[0]=literal[i];
            }
            if(Objects.equals(literal[i], "b") || Objects.equals(literal[i], "b'")){
                returned_literal[1]=literal[i];
            }
            if(Objects.equals(literal[i], "c") || Objects.equals(literal[i], "c'")){
                returned_literal[2]=literal[i];
            }
            if(Objects.equals(literal[i], "c") || Objects.equals(literal[i], "c'")){
                returned_literal[3]=literal[i];
            }
        }
        for(int i=0;i<returned_literal.length;i++){
            if(returned_literal[i]==null){
                returned_literal[i]="X";
            }
        }

        return returned_literal;
    }
    public static String[] simplify_1litdiffSOP(String[] term1,String[] term2){
        StringBuilder sb = new StringBuilder();
        String[] pattern = new String[]{"a,a'","b,b'","c,c'","d,d'"};
        String[] compared_term = new String[4];
        for(int i=0;i<term1.length;i++){
            if(Objects.equals(term1[i], term2[i])){
                compared_term[i]=term1[i];
            }
            else{
                compared_term[i]="X";
            }
        }
        return new String[]{compared_term[0],compared_term[1],compared_term[2],compared_term[3]};
    }

    public static boolean isSameSOP(String[] term1,String[] term2){
        boolean isSameSOP = true;
        for(int i =0;i<term1.length;i++) {
            if (!Objects.equals(term1[i], term2[i])) {
                return false;
            }
        }

        return isSameSOP;
    }
    public static String lit_to_SOP(String[] term){
        StringBuilder sb = new StringBuilder();

        for(int i =0;i<term.length;i++){
            if(!Objects.equals(term[i], "X")){
                sb.append(term[i]);
                if(i<term.length-1){
                    sb.append("*");
                }
            }
        }
        return "("+sb.toString()+")";
    }
    public static boolean is1litdiffSOP(String[] term1,String[] term2){
        boolean is1litdiff;
        int litdiff_num=0;
        for(int i=0;i<term1.length;i++){
            if(!Objects.equals(term1[i], term2[i])){
                litdiff_num+=1;
            }
        }
        if(litdiff_num==1){
            is1litdiff=true;
        }
        else{
            is1litdiff=false;
        }
        return is1litdiff;
    }

    public static String simplifyLiteral(String[] term1,String[] term2){
        StringBuilder sb = new StringBuilder();
        String[] pattern = new String[]{"a,a'","b,b'","c,c'","d,d'"};
        String[][] term_compare = new String[4][4];
        StringBuilder term_1_sb = new StringBuilder();
        StringBuilder term_2_sb = new StringBuilder();
        term_1_sb.append(Arrays.toString(term1));
        term_2_sb.append(Arrays.toString(term2));
        int i =0;
        int j=0;

       while(i<4){
           term_compare[0][i]=term1[i];
           i++;
       }
        while(j<4){
            term_compare[1][j]=term1[j];
            j++;
        }

        String term_1 = term_1_sb.toString();
        String term_2 = term_2_sb.toString();


        boolean term_1_check_1;
        boolean term_2_check_1;
        boolean term_1_check_2;
        boolean term_2_check_2;

        for(int k=0;k<pattern.length;k++){
            switch(pattern[k]){
                case "a,a'":
                term_1_check_1=StringUtils.contains(term_1,"a");
                term_2_check_1=StringUtils.contains(term_2,"a'");
                term_1_check_2=StringUtils.contains(term_1,"a'");
                term_2_check_2=StringUtils.contains(term_2,"a");
                if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                {
                term_compare[0][0]="X";
                term_compare[1][0]="X";
                }
                break;
                case "b,b'":
                    term_1_check_1=StringUtils.contains(term_1,"b");
                    term_2_check_1=StringUtils.contains(term_2,"b'");
                    term_1_check_2=StringUtils.contains(term_1,"b'");
                    term_2_check_2=StringUtils.contains(term_2,"b");
                    if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                    {
                        term_compare[0][1]="X";
                        term_compare[1][1]="X";
                    }
                    break;
                case "c,c'":
                    term_1_check_1=StringUtils.contains(term_1,"c");
                    term_2_check_1=StringUtils.contains(term_2,"c'");
                    term_1_check_2=StringUtils.contains(term_1,"c'");
                    term_2_check_2=StringUtils.contains(term_2,"c");
                    if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                    {
                        term_compare[0][2]="X";
                        term_compare[1][2]="X";
                    }
                    break;
                case "d,d'":
                    term_1_check_1=StringUtils.contains(term_1,"d");
                    term_2_check_1=StringUtils.contains(term_2,"d'");
                    term_1_check_2=StringUtils.contains(term_1,"d'");
                    term_2_check_2=StringUtils.contains(term_2,"d");
                    if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                    {
                        term_compare[0][3]="X";
                        term_compare[1][3]="X";
                    }
                    break;
            }
        }

        StringBuilder final_sb = new StringBuilder();

        for(int k=0;k<term_compare.length;k++){
            if(!Objects.equals(term_compare[0][k], "X")){
            final_sb.append(term_compare[0][k]);
            if(k<term_compare[0].length-1){
            final_sb.append("*");
            }
            }
        }

        final_sb.append(" + ");
        for(int k=0;k<term_compare.length;k++){
            if(!Objects.equals(term_compare[1][k], "X")){
                final_sb.append(term_compare[1][k]);
                if(k<term_compare[1].length-1){
                    final_sb.append("*");
                }
            }
        }


        return final_sb.toString();
    }


    public static int literalSaved_SOP(String[][] origin_term,String[][] simplified_terms){
        int ori_num=0;
        int simp_num=0;
        for(int i =0;i<origin_term.length;i++){
            ori_num+=4;
        }
        for(int i=0;i<simplified_terms.length;i++){
            for(int k=0;k<simplified_terms[i].length;k++){
                if(!Objects.equals(simplified_terms[i][k], "X")){
                    simp_num+=1;
                }
            }
        }

        return ori_num-simp_num;
    }


    public enum literal{
        a("a"),
        b("b"),
        c("c"),
        d("d"),
        an("a'"),
        bn("b'"),
        cn("c'"),
        dn("d'");


        private final String literal_name;

        literal(String literal_name){
            this.literal_name=literal_name;
        }
        private String getLiteral_name(){
            return this.literal_name;
        }
    }
}
