import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

public class LUT4 {

    private String outputs;
    public static final String[] inputs = {"a", "b", "c", "d"};

    private ArrayList<String> sram = new ArrayList<>(16);

    public LUT4() {
        for (int i = 0; i < 16; i++) {
            this.sram.add(null);
        }

    }

    public String[] getInputs() {
        return this.inputs;
    }

    public ArrayList<String> getSram() {
        return this.sram;
    }

    public void setSram(int[] index) {
        for (int i = 0; i < index.length; i++) {
            this.getSram().set(index[i], "1");
        }

        for (int i = 0; i < this.sram.size(); i++) {
            if (this.sram.get(i) == null) {
                this.sram.set(i, "0");
            }
        }

    }

    public void setSram(String[] index) {
        for (int i = 0; i < index.length; i++) {
            this.getSram().set(Integer.parseInt(index[i]), "1");
        }
        for (int i = 0; i < this.sram.size(); i++) {
            if (this.sram.get(i) == null) {
                this.sram.set(i, "0");
            }
        }
    }

    public String getLUTS4value(int index) {
        return this.sram.get(index);
    }

    public static void main(String[] args) throws IOException {
        if(Objects.equals(args[0], "LUT4")){
            System.out.println(LUT4.circuitRepresent(args));
        }
        /*Following function read args, if it's "write"
        * Then use args[2] and following as [minterm numbers,minterm numbers,....]
        * to specify how many functions you want to implement
        * Then use args[1] to specify the number of luts you want to write to
        * It will map to the following LUTs
        * */
        if (Objects.equals(args[0], "write")) {
            int argslength_forindex = 2;

            String[] args_index = new String[args.length - 2];

            for (int i = 0; i < args_index.length; i++) {
                args_index[i] = args[argslength_forindex];
                argslength_forindex++;
            }

            for (int i = 0; i < args_index.length; i++) {
                args_index[i] = StringUtils.substringBetween(args_index[i], "[", "]");
            }


            ArrayList<String[]> lutindex_fromstring = new ArrayList<>();
            for (int i = 0; i < args_index.length; i++) {
                lutindex_fromstring.add(StringUtils.split(args_index[i], ","));
            }

            int[] lutnum = new int[argslength_forindex];
            for (int i = 0; i < lutnum.length; i++) {
                lutnum[i] = i;
            }

            ArrayList<LUT4> luts_write = new ArrayList<>();

            for (int i = 0; i < lutindex_fromstring.size(); i++) {
                luts_write.add(new LUT4());
                luts_write.get(i).setSram(lutindex_fromstring.get(i));
            }

            File bitstream = new File("bitstream.txt");
            FileWriter fw = new FileWriter(bitstream);
            for (int i = 0; i < luts_write.size(); i++) {
                fw.write(luts_write.get(i).getSram().toString());
                fw.write("\n");
            }
            fw.close();

            for (int i = 2; i < args.length; i++) {

                System.out.println("Functions to be mapped" + args[i]);
                int[] index = new int[lutindex_fromstring.get(i - 2).length];
                for (int k = 0; k < index.length; k++) {
                    index[k] = Integer.parseInt(lutindex_fromstring.get(i - 2)[k]);
                }
                System.out.println(Canonical_SOP.SOP_generation_byIndex(index));

            }
            System.out.println("LUT inputs:" + " " + "a,b,c,d");
            System.out.println("Number of Luts written:" + args_index.length);
            System.out.println("File written to:" + "bitstream.txt");
        }


        if (Objects.equals(args[0], "read")) {
            File fw = new File(args[1]);
            String name = FileUtils.readFileToString(fw, StandardCharsets.UTF_8);
            String[] readline_fromname = StringUtils.split(name, "\n");
            System.out.println("Read bitstreams from written file");
            System.out.println(Arrays.toString(readline_fromname));

            String[][] lut_mapping = new String[readline_fromname.length][16];

            int[][] lut_index_mapping = new int[readline_fromname.length][16];

            for (int i = 0; i < lut_mapping.length; i++) {
                for (int k = 0; k < lut_mapping[i].length; k++) {
                    lut_mapping[i][k] = StringUtils.split(StringUtils.substringBetween(readline_fromname[i], "[", "]"), ", ")[k];
                }
            }

            for (int i = 0; i < lut_index_mapping.length; i++) {
                for (int k = 0; k < lut_index_mapping[i].length; k++) {
                    lut_index_mapping[i][k] = Integer.parseInt(lut_mapping[i][k]);
                }
            }


            ArrayList<ArrayList<Integer>> tomap = new ArrayList<>();
            for (int i = 0; i < readline_fromname.length; i++) {
                tomap.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < lut_index_mapping.length; i++) {
                for (int k = 0; k < lut_index_mapping[i].length; k++) {
                    if (lut_index_mapping[i][k] == 1) {
                        tomap.get(i).add(k);
                    }
                }
            }

            ArrayList<int[]> mappedindex = new ArrayList<>();

            for (int i = 0; i < tomap.size(); i++) {
                int[] index = new int[tomap.get(i).size()];
                for (int k = 0; k < index.length; k++) {
                    index[k] = tomap.get(i).get(k);
                }
                mappedindex.add(index);
            }


            for (int i = 0, j = 1; i < tomap.size(); i++, ++j) {
                System.out.println("minterms to be mapped to lut" + j + ":" + tomap.get(i));
            }

            for (int i = 0, j = 1; i < mappedindex.size(); i++, ++j) {
                System.out.println("Mapping bitstream to lut" + j + "With Function:" + Canonical_SOP.SOP_generation_byIndex(mappedindex.get(i)));
            }
            //end of lut4 "read" operation
        }

        //end of LUT4 main function
    }

    /*
    * Following function is used to print out any circuit that we want
    * constraint: 2 level circuit :SOP form
    * */
    public static String circuitRepresent(String[] args) {
        String circuit = "";
        if (Objects.equals(args[1], "CIRCUIT")) {
            Scanner scanner = new Scanner(Arrays.toString(args));
            ArrayList<String> andgate_level = new ArrayList<>();
            StringBuilder circuit_sb = new StringBuilder();


            for (int i = 2; i < args.length; i++) {
                if (StringUtils.contains(args[i], "AND")) {
                    String[] split_input = StringUtils.split(args[i], ",");
                    if (split_input.length == 3) {
                        andgate_level.add(Gate_and.AND_2_byTerm(split_input[1], split_input[2]));
                    }
                    if (split_input.length == 4) {
                        andgate_level.add(Gate_and.AND_3_byTerm(split_input[1], split_input[2], split_input[3]));
                    }
                    if (split_input.length == 5) {
                        andgate_level.add(Gate_and.AND_4_byTerm(split_input[1], split_input[2], split_input[3], split_input[4]));
                    }

                }

                if (StringUtils.contains(args[i], "OR")) {
                    for (int k = 0; k < andgate_level.size(); k++) {
                        circuit_sb.append(andgate_level.get(k));
                        if (k < andgate_level.size() - 1) {
                            circuit_sb.append("+");
                        }
                    }
                }

            }
            circuit=circuit_sb.toString();
        }
        return circuit;
        //end of circuit presentation function in lut4
    }

    //end of LUT4 class
}


