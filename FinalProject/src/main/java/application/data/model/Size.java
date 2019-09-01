package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_size")
public class Size {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "size_id")
    private int size_id;
    @Column(name = "name")
    private String name;

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return  name;
    }
}
