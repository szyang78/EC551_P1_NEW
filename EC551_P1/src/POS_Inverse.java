import java.util.ArrayList;
import java.util.List;

public class POS_Inverse {
    public static int[] getInverseIndex(int[] index){
        Integer[] term_index = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Integer[] index_input = new Integer[index.length];
        for(int i =0;i<index_input.length;i++){
            index_input[i]=index[i];
        }
        ArrayList<Integer> index_list = new ArrayList<>(List.of(index_input));
        ArrayList<Integer> term_index_list = new ArrayList<>(List.of(term_index));

        term_index_list.removeAll(index_list);
        int[] returned_index = new int[term_index_list.size()];
        for(int j=0;j<returned_index.length;j++){
            returned_index[j]=term_index_list.get(j);
        }
        return returned_index;
    }

    public static String POS_Inverse_Generation(int[] index){
        StringBuilder sb = new StringBuilder();
        sb.append(Canonical_POS.POS_generation_byIndex(POS_Inverse.getInverseIndex(index)));
        return sb.toString();
    }
}
