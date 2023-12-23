package day20;

import java.util.ArrayList;

public abstract class Module {
    
    private String name;
    private ArrayList<Module> outputs;

    public Module(String name) {
        this.name = name;
        outputs = new ArrayList<>();
    }

    @Override
    public abstract String toString();

    public String outputsToString() {
        String string = "Outputs: ";
        for (Module output : outputs) {
            string += output.getName() + ", ";
        }
        string = string.substring(0, string.length() - 2);
        string += ". ";
        return string;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOutput(Module module) {
        outputs.add(module);
    }

    public String getName() {
        return name;
    }

    public abstract boolean getOutput();

    public ArrayList<Module> getOutputs() {
        return outputs;
    }
}
