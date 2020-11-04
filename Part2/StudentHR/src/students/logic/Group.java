package students.logic;
 
public class Group {
 
    // ïîëå ÈÄ ÃĞÓÏÏÛ
    private int groupId;
    // ïîëå ÈÌß ÃĞÓÏÏÛ
    private String nameGroup;
    // ïîëå ÊÓĞÀÒÎĞ
    private String curator;
    // ïîëå ÑÏÅÖÈÀËÜÍÎÑÒÜ
    private String speciality;
 
    // get/set äëÿ ÊÓĞÀÒÎĞ
    public String getCurator() {
        return curator;
    }
 
    public void setCurator(String curator) {
        this.curator = curator;
    }
 
    // get/set äëÿ ÈÄ ÃĞÓÏÏÛ
    public int getGroupId() {
        return groupId;
    }
 
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
 
    // get/set äëÿ ÈÌß ÃĞÓÏÏÛ
    public String getNameGroup() {
        return nameGroup;
    }
 
    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
 
    // get/set äëÿ ÑÏÅÖÈÀËÜÍÎÑÒÜ
    public String getSpeciality() {
        return speciality;
    }
 
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
 
    public String toString() {
        return nameGroup;
    }
}
