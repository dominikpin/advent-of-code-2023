package day20;

public class FlipFlop extends Module {
    
    private boolean onOff;

    public FlipFlop(String name) {
        super(name);
        this.onOff = false;
    }

    @Override
    public String toString() {
        return this.getName() + (onOff ? " -high-> " : " -low-> ") + this.outputsToString();
    }

    public boolean handleInput(boolean input) {
        if (input) {
            return false;
        }
        this.onOff = !onOff;
        return true;
    }

    @Override
    public boolean getOutput() {
        return this.onOff;
    }
}
