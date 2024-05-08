package com.cmps211.collab.editor.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Document(collection = "Doc")
@CompoundIndexes({
    @CompoundIndex(name = "docName_authorName", def = "{'docName': 1, 'authorName': 1}", unique = true)
})
public class Doc {
    @Id
    @Getter @Setter private String docID;
    @Getter @Setter private String docName;
    @Getter @Setter private String authorName;
    @Getter @Setter private String content;
    @Getter @Setter private String[] users;
    @Getter @Setter private boolean[] sharePermissions;
}
