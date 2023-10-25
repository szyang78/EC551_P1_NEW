public class Maxterm {

    public static maxterm getMaxterm(int index){
        return Maxterm.maxterm.creMaxterm(index);
    }
    public enum maxterm{
        M0("(a+b+c+d)","M0",0,0000),
        M1("(a+b+c+d')","M1",1,0001),
        M2("(a+b+c'+d)","M2",2,0010),
        M3("(a+b+c'+d')","M3",3,0011),
        M4("(a+b'+c+d)","M4",4,0100),
        M5("(a+b'+c'+d)","M5",5,0101),
        M6("(a+b'+c'+d)","M6",6,0110),
        M7("(a+b'+c'+d')","M7",7,0111),
        M8("(a'+b+c+d)","M8",8,1000),
        M9("(a'+b+c+d')","M9",9,1001),
        M10("(a'+b+c'+d)","M10",10,1010),
        M11("(a'+b'+c'+d')","M11",11,1011),
        M12("(a'+b'+c+d)","M12",12,1100),
        M13("(a'+b'+c+d')","M13",13,1101),
        M14("(a'+b'+c'+d)","M14",14,1110),
        M15("(a'+b'+c'+d')","M15",15,1111);
        private final String term;
        private final String term_name;
        private final int index;

        private final int binary;

        public String getTerm(){
            return this.term;
        }
        String getTermName(){
            return this.term_name;
        }
        int getIndex(){
            return this.index;
        }

        int getBinary(){
            return this.binary;
        }
        static maxterm creMaxterm(int index){
            return switch (index) {
                case 0 -> M0;
                case 1 -> M1;
                case 2 -> M2;
                case 3 -> M3;
                case 4 -> M4;
                case 5 -> M5;
                case 6 -> M6;
                case 7 -> M7;
                case 8 -> M8;
                case 9 -> M9;
                case 10 -> M10;
                case 11 -> M11;
                case 12 -> M12;
                case 13 -> M13;
                case 14 -> M14;
                case 15 -> M15;
                default -> null;
            };
        }
        maxterm(String term,String term_name,int index,int binary) {
            this.term=term;
            this.term_name = term_name;
            this.index=index;
            this.binary=binary;
        }
    }
}
