import java.text.DecimalFormat;

public class Surface {
  String name;
  Double area;
  Paint paint;
  DecimalFormat df = new DecimalFormat("0.00");

  public Surface(String name, Double area, Paint paint){
    this.name = name;
    this.area = area;
    this.paint = paint;
  }

  public String getFormattedString(){
    return name
         + ": has an area of " + df.format(getArea())
         + "m^2 in paint " + paint.getName()
         + " and costs: £" + df.format(getPrice());
  }

  public Double getPrice() {
    return (double) area * paint.getPricePerArea();
  }

  public Double getArea() {
    return area;
  }

  public String getName() {
    return name;
  }

  public Paint getPaint() {
    return paint;
  }
  
}


