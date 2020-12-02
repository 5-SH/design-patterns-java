package facade.headfirst.hometheather;

public class Amplifier {
  private Tuner tuner;
  private CdPlayer cdPlayer;
  private DvdPlayer dvdPlayer;

  public void on() {
    System.out.println("Amplifier On");
  }

  public void off() {
    System.out.println("Amplifier Off");
  }

  public void setCd() {
    System.out.println("Amplifier Set CD");
  }

  public void setDvd(DvdPlayer dvdPlayer) {
    this.dvdPlayer = dvdPlayer;
    System.out.println("Amplifier Set DVD");
  }

  public void setTuner(Tuner tuner) {
    this.tuner = tuner;
    System.out.println("Amplifier Set Tuner");
  }

  public void setStereoSound() {
    System.out.println("Amplifier Set Stereo Sound");
  }

  public void setSurroundSound() {
    System.out.println("Amplifier Set Surround Sound");
  }

  public void setVolume() {
    System.out.println("Amplifier Set Volume");
  }
}
