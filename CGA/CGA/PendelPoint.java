package assignment1;

import org.amcgala.Framework;
import org.amcgala.RGBColor;
import org.amcgala.animation.Updatable;
import org.amcgala.math.Vertex3f;
import org.amcgala.scenegraph.Node;
import org.amcgala.shape.Line;
import org.amcgala.shape.Point;

import java.util.UUID;

/**
 * Ein PendelPoint ist der Punkt, der im Rahmen des Harmonischen Oszillators als nächstes gezeichnet
 * werden soll, um eine Schleifenform zu erreichen. Um den Harmonischen Oszillator besser vorführen
 * zu können, werden die einzelnen Punkte miteinander zu einer Linie verbunden.
 *
 * Die x- und y-Koordinaten zum Zeitpunkt t können mithilfe der folgenden Gleichung bestimmt werden:
 * xp = ax * x+ bx * xp
 * x1 = x + xp
 *
 * yp = ay * y + by * yp
 * y1 = y + yp
 *
 * xp und yp stehen für die Geschwindigkeit, ax und ay für den Beschleunigungskoeffizienten und
 * bx und by für die Dämpfung.
 *
 * @author Alex Dobrynin
 */
public class PendelPoint extends Point implements Updatable {

    private Node node;
    private String name;

    /**
     * Ein PendelPoint besteht aus einer x-, y- und z-Koordinate.
     * @param x Koordinate
     * @param y Koordinate
     * @param z Koordinate
     */
    public PendelPoint(int x, int y, int z) {
        super(x, y, z);
        this.name = "Point_" + UUID.randomUUID().toString();
        this.node = new Node(name);

        // TODO Übergabeparameter entgegennehmen und zwischenspeichern
    }

    /**
     * Die update Methode wird 1 Mal pro Frame aufgerufen. Mit jedem Aufruf soll die neue Position
     * des Pendels berechnet werden. Danach soll die aktuelle Position des PendelPoints auf die neue Position 
     * verschoben werden. Hierfür müssen Sie den Knoten um eine jeweilige Translation anreichern. Anschließend 
     * soll eine Linie zwischen der vorherigen und der neuen Position gezogen werden, um die Schleifenform zu erkennen.
     */
    @Override
    public void update() {
        // TODO Mithilfe der Gleichung den nächsten Punkt berechnen

        // TODO Vom vorherigen Punkt eine Linie zum nächsten Punkt zeichnen. Hierfür müssen der drawLine() Methode  
        // nur noch die richtigen Koordinaten übergeben werden
        drawLine(previousX, previousY, currentX, currentY);
    }

    /**
     * Zeichnet eine Linie zwischen previousX und currentX und previousY und currentY.
     * @param previousX vorherige x-Koordinate
     * @param previousY vorherige y-Koordinate
     * @param currentX aktuelle x-Koordinate
     * @param currentY aktuelle y-Koordinate
     */
    private void drawLine(int previousX, int previousY, int currentX, int currentY) {
        Line line = new Line(new Vertex3f(previousX, previousY, 1), new Vertex3f(currentX, currentY, 1));
        line.setColor(RGBColor.RED);
        Framework.getInstance().getActiveScene().addShape(line, new Node(line + UUID.randomUUID().toString()));
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gibt die aktuelle Position des Punktes zurück. Die x- und y-Werte können über den Vertex ausgelesen werden.
     * @return Vertex3f die aktuelle Position
     */
    private Vertex3f getCurrentPosition() {
        return this.getNode().getTransformMatrix().times(getPoint().toVector().toMatrix()).toVertex3f();
    }
}
