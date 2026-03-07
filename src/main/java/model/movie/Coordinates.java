package model.movie;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    
    @XmlAttribute(required = true)
    private long x;
    
    @XmlAttribute(required = true)
    private float y;
    
    public Coordinates() {}
    
    public Coordinates(long x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public long getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(long x) {
        this.x = x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
}