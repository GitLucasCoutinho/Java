//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    //var music = MusicPlayer.music;
    //var video = VideoPlayer.video;

        var musicPlayer = new MusicPlayer() {
            @Override
            public void playMusic() {
                System.out.println("Tocando a música: " + music);
            }

            @Override
            public void pausaMusic() {
                System.out.println("Música pausada.");
            }

            @Override
            public void StopMusic() {
                System.out.println("Música parada.");
            }
        };
        musicPlayer.playMusic();
        System.out.println("music player -> "+ musicPlayer.getClass());

        System.out.println("music player -> "+ musicPlayer.getClass());
    }
}