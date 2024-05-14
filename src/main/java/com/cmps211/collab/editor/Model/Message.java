package com.cmps211.collab.editor.Model;

import lombok.Data;
import lombok.Setter;
import lombok.Getter;

@Data
public class Message {

    @Getter
    @Setter
    private Integer operation;
    @Getter
    @Setter
    private char character;
    @Getter
    @Setter
    private Integer startIndex;
    @Getter
    @Setter
    private Integer endIndex;
    @Getter
    @Setter
    private boolean isBold;
    @Getter
    @Setter
    private boolean isItalic;
    @Getter
    @Setter
    private String sessionID;
    // @Getter
    // @Setter
    // private String to;
}
