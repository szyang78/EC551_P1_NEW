import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Literals {

    public static String[] map6litto6num(String[] lit){
        String[] num= new String[6];
        for(int i=0;i<num.length;i++){
            switch (lit[i]){
                case "a","b","c","d","e","f":num[i]="1";break;
                case "a'","b'","c'","d'","e'","f'":num[i]="0";break;
                case "X":num[i]="X";break;
            }
        }
        return num;
    }

    public static String map4numto4litstring(String[] num){
        StringBuilder sb = new StringBuilder();
        String[] sop = Literals.map4numto4sop(num);
        sb.append("(");
        for(int i=0;i<sop.length;i++){
            if(i<sop.length-1){
                if(!Objects.equals(sop[i], "X")){
                    sb.append(sop[i]);
                    sb.append("*");
                }
                }
            if(i==sop.length-1){
                if(!Objects.equals(sop[i], "X")){
                    sb.append(sop[i]);
                }
            }
            }
        sb.append(")");
        return sb.toString();
    }

    public static String map6numto6litstring(String[] num){
        StringBuilder sb = new StringBuilder();
        String[] sop = Literals.map6numto6sop(num);
        sb.append("(");
        if( !Objects.equals(sop[0],"X")) {
            sb.append(sop[0]);
        }
        if(!Objects.equals(sop[1],"X")){
            if(!Objects.equals(sop[0],"X")){
            sb.append("*");
            }
            sb.append(sop[1]);
        }
        if(!Objects.equals(sop[2],"X")){
            if(!Objects.equals(sop[1],"X")){
                sb.append("*");
            }
            sb.append(sop[2]);
        }
        if(!Objects.equals(sop[3],"X")){
            if(!Objects.equals(sop[2],"X")){
                sb.append("*");
            }
            sb.append(sop[3]);
        }
        if(!Objects.equals(sop[4],"X")){
            sb.append("*");
            sb.append(sop[4]);
        }
        if(!Objects.equals(sop[5],"X")){
            sb.append("*");
            sb.append(sop[5]);
        }
        sb.append(")");
        return sb.toString();
    }

    public static String map4litsoptostring(String[] sop){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        /*
        for(int i=0;i<sop.length;i++){
            if(i<sop.length-1) {
                if (!Objects.equals(sop[i], "X")) {
                    sb.append(sop[i]);
                    if(!Objects.equals(sop[i+1],"X")){
                    sb.append("*");
                    }
                }
            }
            if(i==sop.length-1){
                if (!Objects.equals(sop[i], "X")) {
                    sb.append(sop[i]);
                }
            }
        }
         */
        if( !Objects.equals(sop[0],"X")) {
            sb.append(sop[0]);
        }
        if(!Objects.equals(sop[1],"X")){
            if(!Objects.equals(sop[0],"X")){
                sb.append("*");
            }
            sb.append(sop[1]);
        }
        if(!Objects.equals(sop[2],"X")){

                sb.append("*");

            sb.append(sop[2]);
        }
        if(!Objects.equals(sop[3],"X")){

                sb.append("*");

            sb.append(sop[3]);
        }
        sb.append(")");
        return sb.toString();
    }

    public static String map6litsoptostring(String[] sop){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        /*
        for(int i=0;i<sop.length;i++){
            if(i<sop.length-1) {
                if (!Objects.equals(sop[i], "X")) {
                    sb.append(sop[i]);
                    if(!Objects.equals(sop[i+1],"X")){
                    sb.append("*");
                    }
                }
            }
            if(i==sop.length-1){
                if (!Objects.equals(sop[i], "X")) {
                    sb.append(sop[i]);
                }
            }
        }
         */
        if( !Objects.equals(sop[0],"X")) {
            sb.append(sop[0]);
        }
        if(!Objects.equals(sop[1],"X")){
            if(!Objects.equals(sop[0],"X")){
                sb.append("*");
            }
            sb.append(sop[1]);
        }
        if(!Objects.equals(sop[2],"X")){

            sb.append("*");

            sb.append(sop[2]);
        }
        if(!Objects.equals(sop[3],"X")){

            sb.append("*");

            sb.append(sop[3]);
        }
        if(!Objects.equals(sop[4],"X")){

            sb.append("*");

            sb.append(sop[4]);
        }
        if(!Objects.equals(sop[5],"X")){

            sb.append("*");

            sb.append(sop[5]);
        }
        sb.append(")");
        return sb.toString();
    }
    public static String[] map4numto4sop(String[] num){
        String[] sop = new String[4];
        for(int i=0;i<num.length;i++){
            switch(i){
                case 0:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="a";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="a'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 1:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="b";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="b'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 2:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="c";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="c'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 3:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="d";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="d'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
            }
        }
        return sop;
    }

    public static String[] map6numto6sop(String[] num){
        String[] sop = new String[6];
        for(int i=0;i<num.length;i++){
            switch(i){
                case 0:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="a";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="a'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 1:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="b";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="b'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 2:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="c";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="c'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 3:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="d";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="d'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 4:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="e";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="e'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
                case 5:
                    if(Objects.equals(num[i], "1")){
                        sop[i]="f";
                    }
                    if(Objects.equals(num[i], "0")){
                        sop[i]="f'";
                    }
                    if(Objects.equals(num[i], "X")){
                        sop[i]="X";
                    }
                    break;
            }
        }
        return sop;
    }

    public static String[] map4sopto4num(String[] term){
        String[] soptonum = new String[4];
        for(int i=0;i<soptonum.length;i++){
            switch(term[i]){
                case "a","b","c","d": soptonum[i]="1";break;
                case "a'","b'","c'","d'":soptonum[i]="0";break;
                case "X":soptonum[i]="X";break;
            }
        }
        return soptonum;
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

    public static int numof1slit(String[] term){
        int num=0;
        for(int i=0;i<term.length;i++){
            switch(term[i]){
                case "a","b","c","d":num+=1;break;
            }
        }
        return num;
    }

    public static String[][] mapCircuitToSOPNum(String circuit){
        String[][] circuit_sop = Split_Command.circuitToSOP(circuit);
        return Literals.mapsop4tonum(circuit_sop);
    }
    public static String[][] mapsop4tonum(String[][] lit_sop){
        String[][] map4num = new String[lit_sop.length][4];
        for(int i=0;i<lit_sop.length;i++){
        map4num[i]=Literals.mapsoptonum(lit_sop[i]);
        }
        return map4num;
    }
    public static String[] mapsoptonum(String[] term){
        String[] mapsoptonum=new String[4];
        for(int i=0;i<mapsoptonum.length;i++){
            switch(term[i]){
                case "a","b","c","d": mapsoptonum[i]="1";break;
                case "a'","b'","c'","d'":mapsoptonum[i]="0";break;
                case "X":mapsoptonum[i]="X";break;
            }
        }
        return  mapsoptonum;
    }

    public static int numof1s(String[] term){
        int num=0;
        for(int i=0;i<term.length;i++){
            if (term[i].equals("1")) {
                num += 1;
            }
        }
        return num;
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
    public static boolean isdiffbyX1(String[] term1,String[] term2){
        boolean isdiffbyX=false;
        int diffbyxnum=0;
        for(int i=0;i<term1.length;i++){
            if(Objects.equals(term1[i], term2[i]) && !Objects.equals(term1[i], "X") && !Objects.equals(term2[i], "X")){
                isdiffbyX=false;
            }
            else
                if( (Objects.equals(term1[i], "X") || Objects.equals(term2[i], "X"))&&(!Objects.equals(term1[i], term2[i])) ){
                    diffbyxnum+=1;
                }
        }
        if(diffbyxnum==1){
            isdiffbyX=true;
        }
        else{
            isdiffbyX=false;
        }
        return isdiffbyX;
    }

    public static boolean isdiffbyX2(String[] term1,String[] term2){
        boolean isdiffbyX2=false;
        int diffbyxnum=0;
        for(int i=0;i<term1.length;i++){
            if(Objects.equals(term1[i], term2[i]) && !Objects.equals(term1[i], "X") && !Objects.equals(term2[i], "X")){
                isdiffbyX2=false;
            }
            else
            if( (Objects.equals(term1[i], "X") || Objects.equals(term2[i], "X"))&&(!Objects.equals(term1[i], term2[i])) ){
                diffbyxnum+=1;
            }
        }
        if(diffbyxnum==2){
            isdiffbyX2=true;
        }
        else{
            isdiffbyX2=false;
        }
        return isdiffbyX2;
    }

    public static boolean isdiffbyX3(String[] term1,String[] term2){
        boolean isdiffbyX3=false;
        int diffbyxnum=0;
        for(int i=0;i<term1.length;i++){
            if(Objects.equals(term1[i], term2[i]) && !Objects.equals(term1[i], "X") && !Objects.equals(term2[i], "X")){
                isdiffbyX3=false;
            }
            else
            if( (Objects.equals(term1[i], "X") || Objects.equals(term2[i], "X"))&&(!Objects.equals(term1[i], term2[i])) ){
                diffbyxnum+=1;
            }
        }
        if(diffbyxnum==3){
            isdiffbyX3=true;
        }
        else{
            isdiffbyX3=false;
        }
        return isdiffbyX3;
    }
    public static boolean is1litdiffX(String[] term1,String[] term2){
        boolean is1litdiffX=false;
        for(int i =0;i<term1.length;i++){
            if(Objects.equals(term1[i], term2[i])){
                is1litdiffX=false;
            }
            if(!Objects.equals(term1[i], term2[i]) &&(Objects.equals(term1[i], "X") || Objects.equals(term2[i], "X"))){
                is1litdiffX=true;
            }
        }
        return is1litdiffX;
    }
    public static boolean is2litdiffSOP(String[] term1,String[] term2){
        boolean is2litdiff=false;
        int litdiffnum=0;
        for(int i=0;i<term1.length;i++){
            
        }
        return is2litdiff;
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
    public static boolean isonlydiffbyX(String[] term1, String[] term2) {
        boolean isdiffbyX = false;
        for (int i = 0; i < term1.length; i++) {
            if (Objects.equals(term1[i], term2[i])) {
                if (Objects.equals(term1[i], "X") && !Objects.equals(term2[i], "X")) {
                    isdiffbyX = true;
                }
            }
        }
        return isdiffbyX;
    }

    public static boolean is1diff(String[] term1, String[] term2) {
        boolean is1diff;
        int diffnum = 0;
        for (int i = 0; i < term1.length; i++) {
            if (!Objects.equals(term1[i], term2[i])) {
                diffnum += 1;
            }
        }
        is1diff = (diffnum == 1);
        return is1diff;
    }

    public static boolean isnodiff(String[] term1, String[] term2) {
        boolean isnodiff = false;
        for (int i = 0; i < term1.length; i++) {
            isnodiff = Objects.equals(term1[i], term2[i]);
        }
        return isnodiff;
    }

    public static int num_of_1s(String[] term) {
        int num = 0;
        for (int i = 0; i < term.length; i++) {
            if (Objects.equals(term[i], "1")) {
                num += 1;
            }
        }
        return num;
    }

    public static String[] simplify_if1diff(String[] term1, String[] term2) {
        String[] simplified_term = new String[4];
        if (Literals.is1diff(term1, term2)) {
            for (int i = 0; i < simplified_term.length; i++) {
                if (Objects.equals(term1[i], term2[i])) {
                    simplified_term[i] = term1[i];
                } else {
                    simplified_term[i] = "X";
                }
            }
        }
        return simplified_term;
    }

    public static String[] simplify_ifonlydiffbyX(String[] term1, String[] term2) {
        String[] simplified_term = new String[4];
        if (Literals.isonlydiffbyX(term1, term2)) {
            for (int i = 0; i < simplified_term.length; i++) {
                if (!Objects.equals(term1[i], "X")) {
                    simplified_term[i] = term1[i];
                }
            }
        }
        if(simplified_term[0]==null){
            simplified_term[0]="X";
        }
        if(simplified_term[1]==null){
            simplified_term[1]="X";
        }
        if(simplified_term[2]==null){
            simplified_term[2]="X";
        }
        if(simplified_term[3]==null){
            simplified_term[3]="X";
        }
        return simplified_term;
    }
    public static String[] numtosop(String[] term){
        String[] numtosop = new String[4];
        if(Objects.equals(term[0], "1")){
            numtosop[0]="a";
        }
        if(Objects.equals(term[0], "0")){
            numtosop[0]="a'";
        }
        if(Objects.equals(term[0], "X")){
            numtosop[0]="X";
        }
        if(Objects.equals(term[1], "1")){
            numtosop[1]="b";
        }
        if(Objects.equals(term[1], "0")){
            numtosop[1]="b'";
        }
        if(Objects.equals(term[1], "X")){
            numtosop[1]="X";
        }
        if(Objects.equals(term[2], "1")){
            numtosop[2]="c";
        }
        if(Objects.equals(term[2], "0")){
            numtosop[2]="c'";
        }
        if(Objects.equals(term[2], "X")){
            numtosop[2]="X";
        }
        if(Objects.equals(term[3], "1")){
            numtosop[3]="d";
        }
        if(Objects.equals(term[3], "0")){
            numtosop[3]="d'";
        }
        if(Objects.equals(term[3], "X")){
            numtosop[3]="X";
        }
        return numtosop;
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

        /*
        public static void main(String[] args){
            String[] term1 = new String[]{"a","X","X","X"};
            String[] term2 = new String[]{"a","X","X","X"};
            String[] term3 = Literals.numtosop(new String[]{"1","0","X","1"});
            //System.out.println("It is differ by 2 X"+Literals.isdiffbyX2(term1,term2));
            //System.out.println("It is differ by 1 X"+Literals.isdiffbyX1(term1,term2));
           // System.out.println("It is differ by 3 X"+Literals.isdiffbyX3(term1,term2));
            //System.out.println(Literals.is1litdiffX(term1,term2));
            System.out.println(Arrays.toString(term3));
        }
*/
        private final String literal_name;

        literal(String literal_name){
            this.literal_name=literal_name;
        }
        private String getLiteral_name(){
            return this.literal_name;
        }

    }

    public static boolean ifisnull(String[] term){
        boolean isnull=false;
        for(int i=0;i<term.length;i++){
            if(Objects.equals(term[i],"X")){
                isnull=true;
            }
            else{
                isnull=false;
            }
        }
        return isnull;
    }
    public static void main(String[] args){
        String[] term1 = new String[]{"a","X","X","X"};
        String[] term2 = new String[]{"a","X","X","X"};
        String[] term = new String[4];
        term[0]="1";
        term[1]="0";
        term[2]="X";
        term[3]="1";
        String[] term3 = Literals.numtosop(term);
        //System.out.println("It is differ by 2 X"+Literals.isdiffbyX2(term1,term2));
        //System.out.println("It is differ by 1 X"+Literals.isdiffbyX1(term1,term2));
        // System.out.println("It is differ by 3 X"+Literals.isdiffbyX3(term1,term2));
        //System.out.println(Literals.is1litdiffX(term1,term2));
        System.out.println(Arrays.toString(term3));
        System.out.println(Literals.map4numto4litstring(term));
    }
}
