package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @NotNull
    @Column(unique=true)
    public String userName;

    @NotNull
    public String password;

    @NotNull
    public String fullName;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
}