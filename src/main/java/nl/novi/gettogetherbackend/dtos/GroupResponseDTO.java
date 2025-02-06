//package nl.novi.gettogetherbackend.dtos;
//
//import nl.novi.gettogetherbackend.models.User;
//import nl.novi.gettogetherbackend.models.Weekend;
//
//import java.util.List;
//
//public class GroupResponseDTO {
//    private Long id;
//    private User user;
//    private Weekend weekend;
//    private List<User> users;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//    public User getUser() {
//        return user;
//    }
//
//    public Weekend getWeekend() {
//        return weekend;
//    }
//
//    public void setWeekend(Weekend weekend) {
//        this.weekend = weekend;
//    }
//
//    public Long getWeekendId() {
//        return weekend.getId();
//    }
//
//    public void setWeekendId(Long weekendId) {
//        this.weekend.setId(weekendId);
//    }
//
//    public Long getUserId(){
//        return user.getId();
//    }
//
//    public void setUserId(Long userId){
//        this.user.setId(userId);
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//}