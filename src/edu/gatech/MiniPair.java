package edu.gatech;

import java.util.Objects;

public class MiniPair {
    private Integer pairID;
    private Integer pairValue;

    public MiniPair(int inputID, int inputValue) {
        pairID = inputID;
        pairValue = inputValue;
    }

    public MiniPair() {
        pairID = 0;
        pairValue = 0;
    }

    public Integer getID() { return pairID; }
    public Integer getValue() { return pairValue; }
    
    @Override
    public int hashCode() {
        return Objects.hash(pairID, pairValue);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        // object must be MiniPair at this point
        MiniPair test = (MiniPair) obj;
        return test.getID() == pairID && test.getValue() == pairValue;
    }
}
