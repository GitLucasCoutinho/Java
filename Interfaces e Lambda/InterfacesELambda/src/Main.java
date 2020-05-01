//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    //var music = MusicPlayer.music;
    //var video = VideoPlayer.video;
        Computer computerMusicVideoPlayer = new Computer();

        runMusicPlayer(computerMusicVideoPlayer);
        runVideoPlayer(computerMusicVideoPlayer);
    }
/*
Tanto desta forma tipando o parâmetro com a interface VideoPlayer quanto
MusicPlayer quanto com a classe Computer funciona normalmente. Pois a classe
Computer implementa a interface VideoPlayer que por sua vez herda da interface MusicPlayer.

    private static void runVideoPlayer(VideoPlayer videoPlayer) {
        videoPlayer.playVideo();
    }


*/

    private static void runVideoPlayer(Computer videoPlayer) {
        videoPlayer.playVideo();
    }

    private static void runMusicPlayer(Computer musicPlayer) {
        musicPlayer.playMusic();
    }
}