package io.github.tonyandjoin.chatgptsdk.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author liansx
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEditFileDTO {

    private File file;

    private String fileName;
}
