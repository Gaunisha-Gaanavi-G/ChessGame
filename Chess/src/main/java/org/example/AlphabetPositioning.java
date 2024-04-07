package org.example;

public enum AlphabetPositioning {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);
    AlphabetPositioning(int position){
        this.position=position;
    }

    private int position;

    public int getPosition() {
        return position;
    }
}
