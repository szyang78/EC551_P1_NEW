import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Literals {

    public static String[] getLiteral(String term){

        String sb_1 = StringUtils.substringBetween(term,"(",")");
        String[] literal= sb_1.split("\\*");
        System.out.println(Arrays.toString(literal));

        return literal;
    }

    public static String simplifyLiteral(String[] term1,String[] term2){
        StringBuilder sb = new StringBuilder();
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
        String[] pattern = new String[]{"a,a'","b,b'","c,c'","d,d'"};

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
                term_compare[0][1]="X";
                }
                break;
                case "b,b'":
                    term_1_check_1=StringUtils.contains(term_1,"b");
                    term_2_check_1=StringUtils.contains(term_2,"b'");
                    term_1_check_2=StringUtils.contains(term_1,"b'");
                    term_2_check_2=StringUtils.contains(term_2,"b");
                    if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                    {
                        term_compare[1][0]="X";
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
                        term_compare[2][0]="X";
                        term_compare[2][1]="X";
                    }
                    break;
                case "d,d'":
                    term_1_check_1=StringUtils.contains(term_1,"d");
                    term_2_check_1=StringUtils.contains(term_2,"d'");
                    term_1_check_2=StringUtils.contains(term_1,"d'");
                    term_2_check_2=StringUtils.contains(term_2,"d");
                    if( (term_1_check_1 && term_2_check_1) || (term_1_check_2&&term_2_check_2))
                    {
                        term_compare[3][0]="X";
                        term_compare[3][1]="X";
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


    public static int literalSaved(String terms){
        String[] sb_1 = terms.split("\\+");
        String[][] sb_2 = new String[sb_1.length][4];
        for(int i=0;i<sb_2.length;i++){
            for(int j=0;j<sb_2[i].length;j++){
            sb_2[i][j]=Split_Command.split_SOP_literal(terms)[j];
            }
        }

        for(int i=0;i<sb_1.length;i++){

        }
        return 0;
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
