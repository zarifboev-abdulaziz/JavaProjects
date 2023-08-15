package uz.pdp.springsecuritydemocrud.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDto {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer roleId;
    @NotNull
    private List<Integer> permissionIds;
}
