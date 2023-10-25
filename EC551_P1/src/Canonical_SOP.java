public class Canonical_SOP{

    public static String SOP_generation_byIndex(int[] index){
        StringBuilder sb = new StringBuilder();

        for (int i =0;i<index.length;i++) {

            sb.append(Minterm.getMinterm(index[i]).getTerm());

            if(i==index.length-1){
                return sb.toString();
            }else {
                sb.append(" + ");
            }

        }
        return sb.toString();
    }

    public static String SOP_onset(int[] index){
        return "ON-set Number of SOP:"+ index.length;
    }

    public static String SOP_offset(int[] index){
        return "OFF-set Number of SOP:"+ (16-index.length);
    }

    public static String SOP_minimal(int[] index){

        return "Number of literals saved:";
    }

    }


