package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner {

    private int id;
    private String name;
    private String userName;
    Set<Folder> folders;

    public Owner() {
    }

    public Owner(String name, String userName) {
        this.name = name;
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }
}
