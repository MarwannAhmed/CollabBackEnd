package com.cmps211.collab.editor.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Data;
import lombok.Getter;

@Data
@Document(collection = "User")
public class User {
    @Id
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
}
