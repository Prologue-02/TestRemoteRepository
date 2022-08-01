package me.zhengjie.modules.message.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.message.domain.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Repository
public interface MessageMapper extends CommonMapper<Message> {
    List<Map<String, Object>> queryMessage();
}
