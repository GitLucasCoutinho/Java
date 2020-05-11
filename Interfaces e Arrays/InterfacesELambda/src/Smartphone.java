public class Smartphone implements MusicPlayer, VideoPlayer{
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

    @Override
    public void playVideo() {
        System.out.println("Tocando o vídeo: " + video);
    }

    @Override
    public void pauseVideo() {
        System.out.println("Vídeo pausado.");
    }

    @Override
    public void stopVideo() {
        System.out.println("Vídeo parado.");
    }
}
