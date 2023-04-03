package io.github.tonyandjoin.chatgptsdk.enums;

import lombok.Getter;

/**
 * @author liansx
 */

@Getter
public enum ImageSize {

    BIG("1024x1024"),
    MIN("512x512"),
    LIT("256x256"),

    ;

    private String name;

    ImageSize(String name) {
        this.name = name;
    }
}
