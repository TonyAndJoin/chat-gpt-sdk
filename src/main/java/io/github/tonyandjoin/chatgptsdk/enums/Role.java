package io.github.tonyandjoin.chatgptsdk.enums;

/**
 * @author liansx
 */

public enum Role {

    SYSTEM("system"),

    USER("user"),

    ASSISTANT("assistant"),;

    private String name;

    public String getName()
    {
        return this.name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    Role(String name)
    {
        this.name = name;
    }
}
