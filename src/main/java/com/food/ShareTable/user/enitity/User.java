package com.food.ShareTable.user.enitity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;


@Setter
@JsonPropertyOrder({"id", "email", "password", "contactNum", "userName"})
@NoArgsConstructor
@JsonRootName(value = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @JsonSetter("id")
    private String id;
    @JsonSetter("userName")
    private String userName;
    @JsonSetter("email")
    private String email;
    @JsonSetter("password")
    private String password;
    @JsonSetter("contactNum")
    private String contactNum;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSetter("createDate")
    private Date createDate;

    //using json property in constructor
    public User(@JsonProperty("id") String id, @JsonProperty("user") String userName, @JsonProperty("mail") String email,   @JsonProperty("pw") String password, @JsonProperty("number") String contactNum, @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss") @JsonProperty("date") Date createDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.contactNum = contactNum;
        this.createDate = createDate;
    }

    @JsonGetter
    public String getId() {
        return id;
    }

    @JsonGetter
    public String getUserName() {
        return userName;
    }

    @JsonGetter
    public String getEmail() {
        return email;
    }

    @JsonGetter
    public String getPassword() {
        return password;
    }

    @JsonGetter
    public String getContactNum() {
        return contactNum;
    }

    @JsonGetter
    public Date getCreateDate() {
        return createDate;
    }
}
