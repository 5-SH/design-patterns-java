package facade.headfirst.hometheather;

import facade.headfirst.HomeTheaterFacade;

public class HomeTheaterTestDrive {
  public static void main(String[] args) {
    DvdPlayer dvdPlayer = new DvdPlayer();
    Amplifier amplifier = new Amplifier();
    Light light = new Light();
    PopcornPopper popper = new PopcornPopper();
    Screen screen = new Screen();
    Projector projector = new Projector();

    // facade
    HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, new Tuner(), dvdPlayer, new CdPlayer(), projector, light, popper, screen);
    homeTheater.watchMovie();
    homeTheater.endMovie();

//    popper.on();
//    popper.pop();
//
//    light.dim(10);
//
//    screen.down();
//
//    projector.on();
//    projector.setInput(dvdPlayer);
//    projector.wideScreenMode();
//
//    amplifier.on();
//    amplifier.setDvd(dvdPlayer);
//    amplifier.setSurroundSound();
//    amplifier.setVolume();
//
//    dvdPlayer.on();
//    dvdPlayer.play();
  }
}
