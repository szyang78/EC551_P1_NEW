public class Gate_or {

    public static int[] OR_2(int[] i1,int[] i2){
        int[] outcome_2 = new int[4];

        for(int i = 0;i<outcome_2.length;i++){
            if(i1[i]==0&&i2[i]==0){
                outcome_2[i]=0;
            }
            else
                if( (i1[i]==0&&i2[i]==1) || (i1[i]==1&&i2[i]==0)){
                    outcome_2[i]=1;
                }
                else
                    outcome_2[i]=1;
        }

        return outcome_2;

    }

    public static int[] OR_3(int[] i1,int[] i2,int[] i3){
        int[] outcome_3 = new int[4];
        int[] intermedia = new int[4];

        for(int i = 0;i<intermedia.length;i++){
            if(i1[i]==0&&i2[i]==0){
                intermedia[i]=0;
            }
            else
            if( (i1[i]==0&&i2[i]==1) || (i1[i]==1&&i2[i]==0)){
                intermedia[i]=1;
            }
            else
                intermedia[i]=1;
        }

        for(int i = 0;i<outcome_3.length;i++){
            if(intermedia[i]==0&&i3[i]==0){
                outcome_3[i]=0;
            }
            else
            if( (intermedia[i]==0&&i3[i]==1) || (intermedia[i]==1&&i3[i]==0)){
                outcome_3[i]=1;
            }
            else
                outcome_3[i]=1;
        }


        return outcome_3;
    }

    public static int[] OR_4(int[] i1,int[] i2,int[] i3,int[] i4){
        int[] outcome_4 = new int[4];
        int[] intermedia_1 = new int[4];
        int[] intermedia_2 = new int[4];

        for(int i = 0;i<intermedia_1.length;i++){
            if(i1[i]==0&&i2[i]==0){
                intermedia_1[i]=0;
            }
            else
            if( (i1[i]==0&&i2[i]==1) || (i1[i]==1&&i2[i]==0)){
                intermedia_1[i]=1;
            }
            else
                intermedia_1[i]=1;
        }

        for(int i = 0;i<intermedia_2.length;i++){
            if(intermedia_1[i]==0&&i3[i]==0){
                intermedia_2[i]=0;
            }
            else
            if( (intermedia_1[i]==0&&i3[i]==1) || (intermedia_1[i]==1&&i3[i]==0)){
                intermedia_2[i]=1;
            }
            else
                intermedia_2[i]=1;
        }

        for(int i = 0;i<outcome_4.length;i++){
            if(intermedia_2[i]==0&&i4[i]==0){
                outcome_4[i]=0;
            }
            else
            if( (intermedia_2[i]==0&&i4[i]==1) || (intermedia_2[i]==1&&i4[i]==0)){
                outcome_4[i]=1;
            }
            else
                outcome_4[i]=1;
        }

        return outcome_4;
    }


}
