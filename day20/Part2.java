package day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day20\\input.txt");
        Scanner myReader = new Scanner(myObj);
        
        ArrayList<Module> modules = new ArrayList<>();
        loadLinesAndModules(myReader, modules);

        Module lcm = new Broadcaster();
        ArrayList<Module> lcms = new ArrayList<>();
        for (Module module : modules) {
            for (Module module2 : module.getOutputs()) {
                if (module2.getName().equals("rx")) {
                    lcm = module;
                    break;
                }
            }
        }
        for (Module module : modules) {
            for (Module module2 : module.getOutputs()) {
                if (module2.equals(lcm)) {
                    lcms.add(module);
                    break;
                }
            }
        }
        int[] lcmss = new int[lcms.size()];
        int counter = 1;
        while (true) {
            if (analyseCurcuit(modules, lcms, lcmss, counter)) {
                break;
            }
            counter++;
        }
        myReader.close();
        long sum = calculateLCM(lcmss);
        System.out.println(sum);
    }

    private static void loadLinesAndModules(Scanner myReader, ArrayList<Module> modules) {
        ArrayList<String> lines = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            lines.add(line);
        }
        while (!lines.isEmpty()) {
            String line = lines.getFirst();
            lines.remove(0);
            String[] split = line.split(" -> ");
            String[] outputs = split[1].split(", ");
            Module newModule = null;
            boolean found = false;
            for (Module module : modules) {
                if (module.getName().equals(split[0].substring(1)) || module.getName().equals(split[0])) {
                    found = true;
                    newModule = module;
                    break;
                }
            }
            if (!found && split[0].equals("broadcaster")) {
                newModule = new Broadcaster();
                modules.add(newModule);
            } else if (!found && split[0].charAt(0) == '%') {
                newModule = new FlipFlop(split[0].substring(1));
                modules.add(newModule);
            } else if (!found && split[0].charAt(0) == '&') {
                newModule = new Conjunction(split[0].substring(1));
                modules.add(newModule);
            }
            ArrayList<String> newOutputs = new ArrayList<>();
            for (String output : outputs) {
                if (output.equals("rx")) {
                    newModule.addOutput(new FlipFlop(output));
                    continue;
                }
                boolean found1 = false;
                for (Module module : modules) {
                    if (module.getName().equals(output)) {
                        found1 = true;
                        newModule.addOutput(module);
                        if (module instanceof Conjunction) {
                            ((Conjunction) module).addInput(newModule);
                        }
                        break;
                    }
                }
                if (!found1) {
                    newOutputs.add(output);
                }
            }
            if (newOutputs.size() != 0) {
                String newLine = split[0] + " -> ";
                for (String newOutput : newOutputs) {
                    newLine += newOutput + ", ";
                }
                newLine = newLine.substring(0, newLine.length() - 2);
                lines.add(newLine);
            }
        }
    }

    private static boolean analyseCurcuit(ArrayList<Module> modules, ArrayList<Module> lcms, int[] lcmss, int counter) {
        ArrayList<Module> trigger = new ArrayList<>();
        for (Module module : modules) {
            if (module.getName().equals("broadcaster")) {
                trigger.add(module);
                break;
            }
        }
        while (!trigger.isEmpty()) {
            Module module = trigger.remove(0);
            boolean pulse = module.getOutput();
            for (int i = 0; i < lcms.size(); i++) {
                if (module.equals(lcms.get(i)) && pulse && lcmss[i] == 0) {
                    lcmss[i] = counter;
                    boolean found = true;
                    for (int j = 0; j < lcms.size(); j++) {
                        if (lcmss[j] == 0) {
                            found = false;
                        }
                    }
                    if (found) {
                        return true;
                    }
                }
            }
            ArrayList<Module> outputs = module.getOutputs();
            for (Module outputModule : outputs) {
                if (outputModule instanceof FlipFlop) {
                    if (!((FlipFlop) outputModule).handleInput(pulse)) {
                        continue;
                    }
                } else {
                    ((Conjunction) outputModule).handleInput(module);
                }
                trigger.add(outputModule);
            }
        }
        return false;
    }

    private static long calculateLCM(int[] lcmss) {
        long LCM = lcmss[0];
        for (int i = 1; i < lcmss.length; i++) {
            LCM = LCM*lcmss[i]/calculateGCD(LCM, lcmss[i]);
        }
        return LCM;
    }

    private static long calculateGCD(long a, long b) {
        if (b == 0) {
          return a;
        }
        return calculateGCD(b, a % b);
    }
}