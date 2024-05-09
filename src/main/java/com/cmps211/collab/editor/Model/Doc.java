package com.cmps211.collab.editor.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(collection = "Doc")
public class Doc {
    @Id
    @Getter @Setter private String docID;
    @Getter @Setter private String docName;
    @Getter @Setter private String authorName;
    @Getter @Setter private char[] content;
    @Getter @Setter private String[] editors;
    @Getter @Setter private String[] viewers;
}
