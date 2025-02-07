package nl.novi.gettogetherbackend.models;

import jakarta.persistence.*;

// Checked

@Entity
@Table(name = "images")
public class Image {

    @Id
    private String fileName;

    public Image(String fileName) {
        this.fileName = fileName;
    }
    public Image() {}
    public String getFileName() {
        return fileName;
    }
}
