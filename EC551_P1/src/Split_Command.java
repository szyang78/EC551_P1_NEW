import org.apache.commons.lang3.*;

import java.util.Arrays;

public class Split_Command {
        public static String[] split_SOP(String SOP){
            return SOP.split("\\+");
        }
        public static String split_SOP_term(String SOP){
            String sb_1 = StringUtils.substringBetween(SOP,"(",")");
            return sb_1;
        }


        public static String[] split_SOP_literal(String SOP_term) {

            return SOP_term.split("\\*");
        }
        public static String[] split_POS(String POS){
            return POS.split("\\*");
        }

        public static String[] split_POS_literal(String POS_term){
            String sb_1 = StringUtils.substringBetween(POS_term,"(",")");
            String[] literal= sb_1.split("\\+");
            System.out.println(Arrays.toString(literal));
            return literal;
        }

        public static String[] scan_command(String command){

            return command.split(",");
        }

        public static String[] scan_SOP(String SOP){

            return SOP.split(",");

        }

        public static String scan_Gate(String[] command){
            for(int i =0;i<command.length;i++){
                switch(command[0]){
                    case "AND2":
                        return Gate_and.AND_2_byTerm(command[1],command[2]);
                    case "AND3":
                        return Gate_and.AND_3_byTerm(command[1],command[2],command[3]);
                    case "AND4":
                        return Gate_and.AND_4_byTerm(command[1],command[2],command[3],command[4]);
                    case "OR2":
                        break;
                    case "OR3":
                        break;
                    case "OR4":
                        break;
                    case "NAND":
                        break;
                    case "NOR":
                        break;
                    case "XOR":
                        break;
                }
            }
            return " ";
        }

        public static String[][] circuitToSOP(String args){
            String[] splitByPlusSign_Parentheses = StringUtils.split(args,"+");
            for(int i =0;i<splitByPlusSign_Parentheses.length;i++){
                splitByPlusSign_Parentheses[i]=StringUtils.substringBetween(splitByPlusSign_Parentheses[i],"(",")");
            }
            String[][] splitByMultiSign = new String[splitByPlusSign_Parentheses.length][4];
            for(int i=0;i<splitByMultiSign.length;i++){
                String[] temp = new String[4];
                splitByMultiSign[i]=splitByPlusSign_Parentheses[i].split("\\*");
                for(int k=0;k<splitByMultiSign[i].length;k++){
                    switch(splitByMultiSign[i][k]){
                        case "a":temp[0]="a";break;
                        case "a'":temp[0]="a'";break;
                        case "b":temp[1]="b";break;
                        case "b'":temp[1]="b'";break;
                        case "c":temp[2]="c";break;
                        case "c'":temp[2]="c'";break;
                        case "d":temp[3]="d";break;
                        case "d'":temp[3]="d'";break;
                    }
                }
                for(int k=0;k<temp.length;k++){
                    if(temp[k]==null){
                        temp[k]="X";
                    }
                }
                splitByMultiSign[i]=temp;
            }
            return splitByMultiSign;
        }

        public static String[][] sopToSplitSOP(String SOP){
            String[] splitByParentheses = StringUtils.split(SOP,"+");
            String[][] splitByMultiSign = new String[splitByParentheses.length][4];
            for(int i=0;i<splitByMultiSign.length;i++){
                splitByParentheses[i]=StringUtils.substringBetween(splitByParentheses[i],"(",")");
            }
            for(int i=0;i<splitByMultiSign.length;i++){
                splitByMultiSign[i]=splitByParentheses[i].split("\\*");
            }
            return splitByMultiSign;
        }

        public static void main(String[] args){
            System.out.println(Arrays.deepToString(Split_Command.sopToSplitSOP(Canonical_SOP.SOP_generation_byIndex(new int[]{1, 5, 6, 12, 13, 14, 15}))));


        }
}
