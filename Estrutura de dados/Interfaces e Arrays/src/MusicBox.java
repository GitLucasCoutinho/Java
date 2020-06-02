public class MusicBox implements MusicPlayer
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
    }{
}
