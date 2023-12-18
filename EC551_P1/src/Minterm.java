public class Minterm {

    public static minterm getMinterm(int index){
        return Minterm.minterm.creMinterm(index);
    }

    public enum minterm{
        m0("(a'*b'*c'*d')","m0",0,"0000"),

        m1("(a'*b'*c'*d)","m1",1,"0001"),
        m2("(a'*b'*c*d')","m2",2,"0010"),
        m3("(a'*b'*c*d)","m3",3,"0011"),
        m4("(a'*b*c'*d')","m4",4,"0100"),
        m5("(a'*b*c'*d)","m5",5,"0101"),
        m6("(a'*b*c*d')","m6",6,"0110"),
        m7("(a'*b*c*d)","m7",7,"0111"),
        m8("(a*b'*c'*d')","m8",8,"1000"),
        m9("(a*b'*c'*d)","m9",9,"1001"),
        m10("(a*b'*c*d')","m10",10,"1010"),
        m11("(a*b'*c*d)","m11",11,"1011"),
        m12("(a*b*c'*d')","m12",12,"1100"),
        m13("(a*b*c'*d)","m13",13,"1101"),
        m14("(a*b*c*d')","m14",14,"1110"),
        m15("(a*b*c*d)","m15",15,"1111");
        private final String term;
        private final String term_name;
        private final int index;

        private final String binary;
        String getTerm(){
            return this.term;
        }
        String getTermName(){
            return this.term_name;
        }
        int getIndex(){
            return this.index;
        }

        String getBinary(){
            return this.binary;
        }

        static minterm creMinterm(int index){
            return switch (index) {
                case 0 -> m0;
                case 1 -> m1;
                case 2 -> m2;
                case 3 -> m3;
                case 4 -> m4;
                case 5 -> m5;
                case 6 -> m6;
                case 7 -> m7;
                case 8 -> m8;
                case 9 -> m9;
                case 10 -> m10;
                case 11 -> m11;
                case 12 -> m12;
                case 13 -> m13;
                case 14 -> m14;
                case 15 -> m15;
                default -> null;
            };
        }

        minterm(String term,String term_name,int index,String binary) {
            this.term=term;
            this.term_name = term_name;
            this.index=index;
            this.binary=binary;
        }
    }


}
