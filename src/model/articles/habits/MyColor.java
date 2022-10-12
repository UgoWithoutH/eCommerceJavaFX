package model.articles.habits;

import java.io.Serializable;
import java.util.Objects;

public class MyColor implements Serializable {
    double red;
    double green;
    double blue;
    double opacity;

    public MyColor(double red, double green, double blue, double opacity) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.opacity = opacity;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyColor myColor = (MyColor) o;
        return Double.compare(myColor.red, red) == 0 && Double.compare(myColor.green, green) == 0 && Double.compare(myColor.blue, blue) == 0 && Double.compare(myColor.opacity, opacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, opacity);
    }
}
