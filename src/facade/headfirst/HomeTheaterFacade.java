package facade.headfirst;

import facade.headfirst.hometheather.*;

public class HomeTheaterFacade {
  Amplifier amplifier;
  Tuner tuner;
  DvdPlayer dvdPlayer;
  CdPlayer cdPlayer;
  Projector projector;
  Light light;
  PopcornPopper popper;
  Screen screen;

  public HomeTheaterFacade(Amplifier amplifier, Tuner tuner, DvdPlayer dvdPlayer, CdPlayer cdPlayer, Projector projector, Light light, PopcornPopper popper, Screen screen) {
    this.amplifier = amplifier;
    this.tuner = tuner;
    this.dvdPlayer = dvdPlayer;
    this.cdPlayer = cdPlayer;
    this.projector = projector;
    this.light = light;
    this.popper = popper;
    this.screen = screen;
  }

  public void watchMovie() {
    System.out.println("Get ready to watch a movie...");
    popper.on();
    popper.pop();
    light.dim(10);
    screen.down();
    projector.on();
    projector.setInput(dvdPlayer);
    projector.wideScreenMode();
    amplifier.on();
    amplifier.setDvd(dvdPlayer);
    amplifier.setSurroundSound();
    amplifier.setVolume();
    dvdPlayer.on();
    dvdPlayer.play();
  }

  public void endMovie() {
    System.out.println("Shutting movie theater down...");
    popper.off();
    light.on();
    screen.up();
    projector.off();
    amplifier.off();
    dvdPlayer.stop();
    dvdPlayer.eject();
    dvdPlayer.off();
  }
}
