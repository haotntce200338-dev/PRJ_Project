/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Movie {
    private int movieID;
    private String title;
    private String description;
    private int duration;
    private String releaseDate;
    private String poster;
    private String trailer;
    private String status;
    private Genre genre;

    public Movie() {
    }

    public Movie(int movieID, String title, String description, int duration, String releaseDate, String poster, String trailer, String status, Genre genre) {
        this.movieID = movieID;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.trailer = trailer;
        this.status = status;
        this.genre = genre;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getStatus() {
        return status;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
    
}
