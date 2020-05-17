public interface MusicPlayer {


    // Obs. Sempre que for criado uma variável dentro de uma interface,
    // ela é automaticamente pública, estática e final.
    //public static final String music = "Parabéns pra você";
    public String music = "Parabéns pra você";

    void playMusic();

    void pausaMusic();

    void StopMusic();


}
