package goingmerry.cent.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
@NoArgsConstructor
public class teamLogoDto {

    private Resource resource;

    private String filePath;


}
