package day20;

import java.util.HashMap;
import java.util.Map;

public class Conjunction extends Module {

    private Map<String, Boolean> inputs;

    public Conjunction(String name) {
        super(name);
        inputs = new HashMap<>();
    }
    
    public String toString() {
        return this.getName() + (getOutput() ? " -high-> " : " -low-> ") + this.outputsToString() + this.inputsToString() ;
    }

    public String inputsToString() {
        String string = "Inputs are ";
        for (String input : inputs.keySet()) {
            string += input + ", ";
        }
        string = string.substring(0, string.length() - 2);
        string += ". ";
        return string;
    }

    public void addInput(Module module) {
        this.inputs.put(module.getName(), false);
    }

    public void handleInput(Module module) {
        this.inputs.replace(module.getName(), module.getOutput());
    }

    @Override
    public boolean getOutput() {
        boolean output = false;
        for (boolean input : inputs.values()) {
            if (!input) {
                output = true;
                break;
            }
        }
        return output;
    }
}
