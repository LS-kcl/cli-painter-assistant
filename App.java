import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.text.DecimalFormat;

public class App{
  Scanner input = new Scanner(System.in);
  Vector<Surface> surfaces = new Vector<>();
  Vector<Paint> paints = new Vector<>();
  DecimalFormat df = new DecimalFormat("0.00");

  public void main() {
    boolean running = true;

    initialisePaints();
    
    while (running) {
      System.out.println("Please enter an option:");
      System.out.println("LIST: list all surfaces currently measured");
      System.out.println("MAKE: Make a surface to add to the list");
      System.out.println("RMVE: remove a surface from the list");
      System.out.println("PRIC: return the total price of the project");
      System.out.println("SHOP: return the paints needed for purchase");
      System.out.println("EXIT: exit the program");
      System.out.println(""); // Formatting
      
      String option = getStringInput();

      switch (option) {
        case "LIST":
          listSurfaces();
          break;

        case "MAKE":
          makeSurface();
          break;

        case "RMVE":
          removeSurface();
          break;

        case "SHOP":
          getPaintsNeeded();
          break;

        case "PRIC":
          getPrice();
          break;

        case "EXIT":
          System.out.println("Thank you for painting!");
          running = false;
          break;

        default:
          System.out.println("Error: This is not a valid input");
          break;
      }

      // Formatting line break
      System.out.println("");
    }
  }

  private void getPaintsNeeded(){
    // Stream to find paints used, then print
    surfaces.stream()
            .map(s -> s.getPaint())
            .distinct()
            .forEach(p->System.out.println(p.getName()));

    // Formatting
    System.out.println("");
  }

  private void initialisePaints(){
    paints.addAll(Arrays.asList(
      new Paint("Dulux Simple White", 2.5),
      new Paint("Dulux Simple Grey", 3.0),
      new Paint("Dulux Simple Blue", 3.0),
      new Paint("Dulux Simple Green", 3.0),
      new Paint("Dulux Simple Orange", 3.0),
      new Paint("Armstead Glossy Green", 5.42),
      new Paint("Armstead Perfect Purple", 5.70),
      new Paint("Armstead Rosy Red", 5.34),
      new Paint("Armstead Bechamel Brown", 5.93),
      new Paint("Sadolin Wood Finisher", 10.50),
      new Paint("Sadolin Varnish", 11.45)
    ));
  }

  public Paint choosePaint(){
     for (int i=0; i<paints.size(); i++){
       System.out.println(i+1 + ": " + paints.get(i).getName());
     }
    // Formatting
    System.out.println("");
    System.out.println("Enter the number for the paint you'd like:");
    
    int option = getIntInput();
    option--;

    // Check if within valid input range
    while (0 > option || option >= paints.size()){
      System.out.println("This is not a valid paint choice, please choose again");
      option = getIntInput();
      option--;
    } 

    // Return paint found
    return paints.get(option);
  }

  public void getPrice(){
    double total = 0;
    for (Surface s : surfaces){
      total += s.getPrice();
    }
    System.out.println("Total Price: £" + df.format(total));
  }

  public void makeSurface() {
    // Take name of surface to add
    System.out.println("Enter the name of the surface you are painting");
    System.out.println("For example, Ceiling, Back Wall");
    String name = getStringInput();
    Paint p = choosePaint();
    
    // Take dimensions of surface to add
    System.out.println("");
    System.out.println("Enter the width of the surface you are painting");
    double w = getDoubleInput();

    System.out.println("Enter the length of the surface you are painting");
    double l = getDoubleInput();

    // Create surface and add to surfaces
    Surface surface = new Surface(name, w*l, p);
    surfaces.add(surface);
    System.out.println("The surface has been added!");
  }

  public void listSurfaces(){
    for (Surface s : surfaces){
      System.out.println(s.getFormattedString());
    }
  }

  public void removeSurface() {
    
     for (int i=0; i<surfaces.size(); i++){
       System.out.println(i+1 + ": " + surfaces.get(i).getName());
     }
    // Formatting
    System.out.println("");
    System.out.println("Enter the number to remove");
    
    int option = getIntInput();
    option--;

    // Check if valid input
    if (0 <= option && option < surfaces.size()){
      surfaces.remove(option);
      System.out.println("The surface has been removed");
    } else {
      System.out.println("This is not a valid surface number to delete");
    }

  }

  public String getStringInput(){
     return input.nextLine();
  }

  public int getIntInput(){
    // Loop until valid number to return
    while (true) {
            String str = input.next();
            int res = 0;
            try {
                res = Integer.parseInt(str);
                return res;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again:");
            }
        }
  }
  
  public double getDoubleInput(){
    // Loop until valid double to return
    while (true) {
            String str = input.next();
            double res = 0;
            try {
                res = Double.parseDouble(str);
                return res;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again:");
            }
        }
  }
}
