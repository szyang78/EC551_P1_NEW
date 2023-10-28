import org.apache.commons.lang3.*;

import java.util.Arrays;

public class Split_Command {
        public static String[] split_SOP(String SOP){
            return SOP.split("\\+");
        }

        public static String[] split_SOP_literal(String SOP_term) {
            String sb_1 = StringUtils.substringBetween(SOP_term,"(",")");

            return sb_1.split("\\*");
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
}
