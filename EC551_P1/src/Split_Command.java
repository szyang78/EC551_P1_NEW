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
}
