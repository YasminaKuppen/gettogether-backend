package nl.novi.gettogetherbackend.dtos;

public class ActivityResponseDTO {


    private String title;
    private String description;
    private String addedBy;
    private Long id;
//    private int votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

//    public int getVotes() {
//        return votes;
//    }

//    public void setVotes(int votes) {
//        this.votes = votes;
//    }

}
