package stackoverflow.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
    private String id;
    private String name;
}
