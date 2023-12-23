package day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    
    public Part1() throws FileNotFoundException {
        File myObj = new File("day20\\input.txt");
        Scanner myReader = new Scanner(myObj);
        
        ArrayList<Module> modules = new ArrayList<Module>();
        loadLinesAndModules(myReader, modules);
        int[] lowHigh = {0, 0};

        for (int i = 0; i < 1000; i++) {
            analyseCurcuit(modules, lowHigh);
        }
        myReader.close();
        System.out.println(lowHigh[0] * lowHigh[1]);
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

    private static void analyseCurcuit(ArrayList<Module> modules, int[] lowHigh) {
        lowHigh[0]++;
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
            ArrayList<Module> outputs = module.getOutputs();
            if (!pulse) {
                lowHigh[0] += outputs.size();
            } else {
                lowHigh[1] += outputs.size();
            }
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
    }
}