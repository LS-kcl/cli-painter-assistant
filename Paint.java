public class Paint {
  String name;
  double price;

  Paint(String name, double price){
    this.name = name;
    this.price = price;
  }

  public double getPricePerArea(){
    return price;
  }

  public String getName() {
    return name;
  }
}
