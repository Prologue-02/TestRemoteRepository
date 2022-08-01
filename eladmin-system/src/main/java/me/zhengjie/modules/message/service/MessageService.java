package me.zhengjie.modules.message.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.message.domain.Message;
import me.zhengjie.modules.message.service.dto.MessageDto;
import me.zhengjie.modules.message.service.dto.MessageQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface MessageService extends CommonService<Message>  {

    static final String CACHE_KEY = "message";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<MessageDto>
    */
    PageInfo<MessageDto> queryAll(MessageQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<MessageDto>
    */
    List<MessageDto> queryAll(MessageQueryParam query);

    Message getById(Integer id);
    MessageDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(MessageDto resources);
    int updateById(MessageDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<MessageDto> all, HttpServletResponse response) throws IOException;

    /**
     * */
    Object queryMessage();

}
