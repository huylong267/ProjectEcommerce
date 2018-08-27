package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_color")
public class Color {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "color_id")
    private int color_id;
    @Column(name = "name")
    private String name;

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
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
