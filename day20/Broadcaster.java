package day20;

public class Broadcaster extends Module {

    public Broadcaster() {
        super("broadcaster");
    }

    @Override
    public String toString() {
        return this.getName() + " -low-> " + this.outputsToString();
    }

    @Override
    public boolean getOutput() {
        return false;
    }
}
