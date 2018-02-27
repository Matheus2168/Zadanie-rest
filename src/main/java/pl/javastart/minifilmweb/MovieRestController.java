package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getAllMovies() {
        System.out.println("Odpytanie o wszystkie filmy");
        return movieRepository.findAll();
    }

    @GetMapping("/api/movies/{id}")
    public Movie getMovie(@PathVariable Long id){
        return movieRepository.findOne(id);
    }

    @PostMapping("/api/movies")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/api/movies/{id}")
    public void updateMovie(@PathVariable Long id,
                            @RequestBody Movie movie){
        Movie one = movieRepository.findOne(id);
        one.setActors(movie.getActors());
        one.setPremiereDate(movie.getPremiereDate());
        one.setTitle(movie.getTitle());
        movieRepository.save(one);

    }

    @DeleteMapping("/api/movies/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieRepository.delete(id);
    }

}
