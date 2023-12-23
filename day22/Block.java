package day22;

import java.util.HashSet;
import java.util.Set;

public class Block {
    
    private int z;
    private Set<Block> supportedBy;
    private Set<Block> supportFor;

    public Block(int z) {
        this.z = z;
        this.supportedBy = new HashSet<>();
        this.supportFor = new HashSet<>();
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void addSupportedBy(Block block) {
        supportedBy.add(block);
    }

    public void addSupportFrom(Block block) {
        supportFor.add(block);
    }

    public int getZ() {
        return this.z;
    }

    public Set<Block> getSupportedBy() {
        return this.supportedBy;
    }
    
    public Set<Block> getSupportFor() {
        return this.supportFor;
    }
}
