package com.example.demo.assembler;

import com.example.demo.data.User;
import com.example.demo.data.UserDetail;
import com.example.demo.data.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Function:
 *
 * @author wenzeng
 * @date 2023/8/4
 */
@Mapper(uses = {WorkAssemblerImpl.class})
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    /**
     * user to userInfoVO
     * @param user
     * @return
     */
    @Mapping(source = "name", target = "userName")
    @Mapping(target = "created", source = "created", dateFormat = "yyyy-MM-dd")
    UserInfoVO userToVO(User user);

    /**
     * turn to userInfoVO
     * @param user
     * @param detail
     * @return
     */
    UserInfoVO turnToUser(User user, UserDetail detail);

}
