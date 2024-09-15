package org.cnu.realcoding.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiParam;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    @ApiParam(value = "dog name", required = true)
    private String name;

    @ApiParam(value = "dog kind", required = true)
    private String kind;

    @ApiParam(value = "dog ownerName", required = true)
    private String ownerName;

    @ApiParam(value = "dog ownerPhoneNumber", required = true)
    private String ownerPhoneNumber;

    @ApiParam(value = "dog name", required = true)
    private List<String> medicalRecords;

}
