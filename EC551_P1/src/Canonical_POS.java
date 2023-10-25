public class Canonical_POS {
    public static String POS_generation_byIndex(int[] index){
        StringBuilder sb = new StringBuilder();

        for (int i =0;i<index.length;i++) {


            sb.append(Maxterm.getMaxterm(index[i]).getTerm());

            if(i==index.length-1){
                return sb.toString();
            }else {
                sb.append(" * ");
            }

        }
        return sb.toString();
    }

    public static String POS_onset(int[] index){
        return "ON-set Number of POS" + ":" + index.length;
    }

    public static String POS_offset(int[] index){
        return "OFF-set Number of POS" + ":" + (16-index.length);
    }
}
