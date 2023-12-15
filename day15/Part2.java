package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("day15\\input.txt");
        Scanner myReader = new Scanner(myObj);
        String[] strings = myReader.nextLine().split(",");
        Map<Integer, ArrayList<Lens>> map = new HashMap<>();
        int sum = 0;
        for (String str : strings) {
            analyzeString(map, str);
        }
        for (int key : map.keySet()) {
            System.out.print("Box " + key + ": ");
            for (Lens lens : map.get(key)) {
                System.out.print(lens.toString() + "");
            }
            System.out.println();
        }
        sum = analyzeMap(map);
        myReader.close();
        System.out.println(sum);
    }

    private static void analyzeString(Map<Integer, ArrayList<Lens>> map, String str) {
        int sign = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '=' || str.charAt(i) == '-') {
                sign = i;
                break;
            }
        }
        String label = str.substring(0, sign);
        int boxNum = holydayASCIIhash(label);
        if (str.charAt(sign) == '-' && map.containsKey(boxNum)) {
            ArrayList<Lens> lenses = map.get(boxNum);
            if (lenses != null) {
                for (Lens lens : lenses) {
                    if (lens.getLabel().equals(label)) {
                        lenses.remove(lens);
                        if (lenses.isEmpty()) {
                            map.remove(boxNum);
                        }
                        break;
                    }
                }
            }
        } else if (str.charAt(sign) == '=') {
            ArrayList<Lens> lenses = map.get(boxNum);
            boolean notFound = true;
            if (lenses != null) {
                for (Lens lens : lenses) {
                    if (lens.getLabel().equals(label)) {
                        notFound = false;
                        lens.setNumber(Integer.parseInt(str.substring(sign + 1, str.length())));
                        break;
                    }
                }
            } else {
                lenses = new ArrayList<>();
                map.put(boxNum, lenses);
            }
            if (notFound) {
                Lens newLens = new Lens(label, Integer.parseInt(str.substring(sign + 1, str.length())));
                lenses.add(newLens);
            }
        }
    }

    private static int holydayASCIIhash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash + str.charAt(i)) * 17 % 256;
        }
        return hash;
    }

    private static int analyzeMap(Map<Integer, ArrayList<Lens>> map) {
        int sum = 0;
        for (int boxNum : map.keySet()) {
            for (Lens lens : map.get(boxNum)) {
                sum += (boxNum + 1) * (map.get(boxNum).indexOf(lens) + 1) * lens.getNumber();
            }
        }
        return sum;
    }
}
