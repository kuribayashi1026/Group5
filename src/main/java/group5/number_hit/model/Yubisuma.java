package group5.number_hit.model;

public class Yubisuma {
  public int userhand;

  public Yubisuma(int hand) {

    if (hand == 2) {
      userhand = 2;
    } else if (hand == 1) {
      userhand = 1;
    } else {
      userhand = 0;
    }
  }
}
