package com.cmps211.collab.editor.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
@Document(collection = "Doc")
public class Doc {
    @Id
    @Getter @Setter Integer docID;
    @Getter @Setter String docName;
}
