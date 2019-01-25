package hibernate;
// Generated 24-ene-2019 21:45:19 by Hibernate Tools 4.3.1



/**
 * FilmText generated by hbm2java
 */
public class FilmText  implements java.io.Serializable {


     private Short filmId;
     private String title;
     private String description;

    public FilmText() {
    }

	
    public FilmText(String title) {
        this.title = title;
    }
    public FilmText(String title, String description) {
       this.title = title;
       this.description = description;
    }
   
    public Short getFilmId() {
        return this.filmId;
    }
    
    public void setFilmId(Short filmId) {
        this.filmId = filmId;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


