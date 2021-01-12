package group5.number_hit.model;

import org.springframework.stereotype.Component;

@Component
public class Endgame {
  private int v;

  public int getV() {
    return v;
  }

  public void setV(int v) {
    this.v = v;
  }

  public Endgame() {
    this.v = 0;
  }
}
