package io.github.tonyandjoin.chatgptsdk.vo;

import io.github.tonyandjoin.chatgptsdk.enums.Role;
import lombok.Data;

/**
 * @author liansx
 */
@Data
public class ChatCreateCompletionQuery {

    private Role role;

    private String message;
}
