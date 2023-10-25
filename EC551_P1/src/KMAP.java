

public class KMAP {

    public static String PIs(int[] index){
        int row_0;
        int row_1;
        int row_2;
        int row_3;
        int col_0;
        int col_1;
        int col_2;
        int col_3;
        StringBuilder sb_00 = new StringBuilder();
        StringBuilder sb_01 = new StringBuilder();
        StringBuilder sb_02 = new StringBuilder();
        StringBuilder sb_03 = new StringBuilder();

        StringBuilder sb_10 = new StringBuilder();
        StringBuilder sb_11 = new StringBuilder();
        StringBuilder sb_12 = new StringBuilder();
        StringBuilder sb_13 = new StringBuilder();

        StringBuilder sb_20 = new StringBuilder();
        StringBuilder sb_21 = new StringBuilder();
        StringBuilder sb_22 = new StringBuilder();
        StringBuilder sb_23 = new StringBuilder();

        StringBuilder sb_30 = new StringBuilder();
        StringBuilder sb_31 = new StringBuilder();
        StringBuilder sb_32 = new StringBuilder();
        StringBuilder sb_33 = new StringBuilder();

        StringBuilder[][] sb = new StringBuilder[][]{
                {sb_00,sb_01,sb_02,sb_03},
                {sb_10,sb_11,sb_12,sb_13},
                {sb_20,sb_21,sb_22,sb_23},
                {sb_30,sb_31,sb_32,sb_33}
        };

        String[][] k_map_string = new String[4][4];

        Integer[][] k_map = new Integer[4][4];
        for(int i=0;i<index.length;i++){
            switch(index[i]){
                case 0:
                    k_map[0][0]=index[i];
                    break;
                case 1:
                    k_map[0][1]=index[i];
                    break;
                case 2:
                    k_map[0][3]=index[i];
                    break;
                case 3:
                    k_map[0][2]=index[i];
                    break;
                case 4:
                    k_map[1][0]=index[i];
                    break;
                case 5:
                    k_map[1][1]=index[i];
                    break;
                case 6:
                    k_map[1][3]=index[i];
                    break;
                case 7:
                    k_map[1][2]=index[i];
                    break;
                case 8:
                    k_map[3][0]=index[i];
                    break;
                case 9:
                    k_map[3][1]=index[i];
                    break;
                case 10:
                    k_map[3][3]=index[i];
                    break;
                case 11:
                    k_map[3][2]=index[i];
                    break;
                case 12:
                    k_map[2][0]=index[i];
                    break;
                case 13:
                    k_map[2][1]=index[i];
                    break;
                case 14:
                    k_map[2][3]=index[i];
                    break;
                case 15:
                    k_map[2][2]=index[i];
                    break;
            }
        }



        return "Number of PIs:"+" ";
    }
}
