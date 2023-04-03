package io.github.tonyandjoin.chatgptsdk.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liansx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslationReq {

    private String model;
}
