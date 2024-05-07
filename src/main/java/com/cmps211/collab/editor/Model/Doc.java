package com.cmps211.collab.editor.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Document(collection = "Doc")
public class Doc {
    @Id
    @Getter @Setter private String docID;
    @Getter @Setter private String docName;
    @Getter @Setter private String authorName;
    @Getter @Setter private ArrayList<Byte> content;
    @Getter @Setter private ArrayList<String> users;
    @Getter @Setter private ArrayList<Boolean> sharePermissions;
}
