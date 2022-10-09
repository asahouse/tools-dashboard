package org.codeworks.web.toolsdashboardaatool.dto.material;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class MaterialTemplateDateListDTO implements Serializable {

    @Builder.Default
    private List<MaterialTemplateListDTO> items = new ArrayList<>();
    private Integer date;
}
