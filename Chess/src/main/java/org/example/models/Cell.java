package org.example.models;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the smallest unit of the chessboard.
 * <p> A 1*1 square having x and y coordinates.</p>
 * <p> We are having a deep equals for this object because we need to find the values inside the object to be equal instead of checking the hashcode</p>
 */

@Getter @Setter
public class Cell {
    int x;
    int y;

    public Cell(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void parseCoordinates(char xValue, char yValue){
        this.x = xValue;
        this.y = yValue;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj!=null && (this.getX()==((Cell) obj).getX() && this.getY()==((Cell) obj).getY()));
    }
}
