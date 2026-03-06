package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.dto.UserDTO;
import org.example.mapper.UserMapper;
import org.example.model.UserModel;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;

public class Main {
    private static final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public static void main(String[] args) {
        var model = new UserModel();
        model.setCode(1);
        model.setUserName("James");
        model.setBirthday(LocalDate.now().minusYears(20));
        System.out.println(mapper.toDTO(model));

        var dto = new UserDTO();
        dto.setId(2);
        dto.setName("Maria");
        dto.setBirthday(LocalDate.now().minusYears(30));
        System.out.println(mapper.toModel(dto));
    }
}
