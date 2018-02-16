package evahsu.simplemovieapi.web.movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evahsu on 2018/2/16.
 */

public class Credits {
    private ArrayList<Cast> cast = null;
    private ArrayList<Crew> crew = null;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }
}
