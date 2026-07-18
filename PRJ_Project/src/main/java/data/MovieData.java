package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieData {

    public static List<Movie> getMovies() {

        List<Movie> list = new ArrayList<>();

        Movie m;

        m = new Movie();
        m.setMovieID(1);
        m.setTitle("Avengers: Endgame");
        m.setDescription("The Avengers reunite to defeat Thanos.");
        m.setDuration(181);
        m.setReleaseDate(LocalDate.of(2019, 4, 26));
        m.setLanguage("English");
        m.setAgeRating("PG-13");
        m.setPoster("assets/images/avengers.jpg");
        m.setTrailer("https://youtu.be/TcMBFSGVi1c");
        m.setRating(8.9);
        m.setGenres("Action,Adventure,Sci-Fi");
        m.setDirector("Anthony Russo, Joe Russo");
        m.setCast("Robert Downey Jr., Chris Evans, Scarlett Johansson, Chris Hemsworth");
        m.setOverview(
                "After the devastating events of Infinity War, "
                + "the remaining Avengers gather for one final mission "
                + "to reverse Thanos' snap and restore balance to the universe."
        );
        list.add(m);

        // ================= Spider-Man =================
        m = new Movie();
        m.setMovieID(2);
        m.setTitle("Spider-Man: No Way Home");
        m.setDescription("Peter Parker faces villains from multiple universes.");
        m.setDuration(148);
        m.setReleaseDate(LocalDate.of(2021, 12, 17));
        m.setLanguage("English");
        m.setAgeRating("PG-13");
        m.setPoster("assets/images/spiderman.jpg");
        m.setTrailer("https://youtu.be/JfVOs4VSpmA");
        m.setRating(8.3);
        m.setGenres("Action,Fantasy");
        m.setDirector("Jon Watts");
        m.setCast("Tom Holland, Zendaya, Benedict Cumberbatch");
        m.setOverview(
                "Peter Parker asks Doctor Strange to erase the world's memory "
                + "of his identity, but the spell goes wrong and opens the multiverse."
        );
        list.add(m);

        m = new Movie();
        m.setMovieID(3);
        m.setTitle("The Batman");
        m.setDescription("Batman investigates a series of murders in Gotham.");
        m.setDuration(176);
        m.setReleaseDate(LocalDate.of(2022, 3, 4));
        m.setLanguage("English");
        m.setAgeRating("PG-13");
        m.setPoster("assets/images/batman.jpg");
        m.setTrailer("https://youtu.be/mqqft2x_Aa4");
        m.setRating(8.2);
        m.setGenres("Action,Crime,Mystery");
        m.setDirector("Matt Reeves");
        m.setCast("Robert Pattinson, Zoë Kravitz, Paul Dano");
        m.setOverview(
                "Batman uncovers corruption in Gotham City while pursuing "
                + "the mysterious serial killer known as the Riddler."
        );
        list.add(m);

        m = new Movie();
        m.setMovieID(4);
        m.setTitle("Inception");
        m.setDescription("A thief enters dreams to steal valuable secrets.");
        m.setDuration(148);
        m.setReleaseDate(LocalDate.of(2010, 7, 16));
        m.setLanguage("English");
        m.setAgeRating("PG-13");
        m.setPoster("assets/images/inception.jpg");
        m.setTrailer("https://youtu.be/YoHD9XEInc0");
        m.setRating(8.8);
        m.setGenres("Sci-Fi,Action,Thriller");
        m.setDirector("Christopher Nolan");
        m.setCast("Leonardo DiCaprio, Joseph Gordon-Levitt, Tom Hardy");
        m.setOverview(
                "Dom Cobb specializes in stealing information through dreams. "
                + "His final mission is not to steal an idea, but to plant one."
        );
        list.add(m);

        m = new Movie();
        m.setMovieID(5);
        m.setTitle("Interstellar");
        m.setDescription("A team travels through a wormhole to save humanity.");
        m.setDuration(169);
        m.setReleaseDate(LocalDate.of(2014, 11, 7));
        m.setLanguage("English");
        m.setAgeRating("PG-13");
        m.setPoster("assets/images/interstellar.jpg");
        m.setTrailer("https://youtu.be/zSWdZVtXT7E");
        m.setRating(8.7);
        m.setGenres("Sci-Fi,Adventure,Drama");
        m.setDirector("Christopher Nolan");
        m.setCast("Matthew McConaughey, Anne Hathaway, Jessica Chastain");
        m.setOverview(
                "As Earth becomes uninhabitable, a group of astronauts travels "
                + "through a wormhole searching for a new home for mankind."
        );
        list.add(m);

        return list;
    }

    public static Movie getMovieById(int id) {

        for (Movie m : getMovies()) {

            if (m.getMovieID() == id) {
                return m;
            }

        }

        return null;
    }
}
