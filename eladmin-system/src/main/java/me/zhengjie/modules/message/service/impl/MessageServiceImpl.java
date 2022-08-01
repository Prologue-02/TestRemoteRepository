package me.zhengjie.modules.message.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.message.domain.Message;
import me.zhengjie.modules.message.service.MessageService;
import me.zhengjie.modules.message.service.dto.MessageDto;
import me.zhengjie.modules.message.service.dto.MessageQueryParam;
import me.zhengjie.modules.message.service.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import java.util.*;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = MessageService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MessageServiceImpl extends CommonServiceImpl<MessageMapper, Message> implements MessageService {

    // private final RedisUtils redisUtils;
    private final MessageMapper messageMapper;

    @Override
    public PageInfo<MessageDto> queryAll(MessageQueryParam query, Pageable pageable) {
        IPage<Message> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Message> page = messageMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, MessageDto.class);
    }

    @Override
    public List<MessageDto> queryAll(MessageQueryParam query){
        return ConvertUtil.convertList(messageMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), MessageDto.class);
    }

    @Override
    public Message getById(Integer id) {
        return messageMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public MessageDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), MessageDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(MessageDto resources) {
        Message entity = ConvertUtil.convert(resources, Message.class);
        return messageMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(MessageDto resources){
        Message entity = ConvertUtil.convert(resources, Message.class);
        int ret = messageMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return messageMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeById(Integer id){
        Set<Integer> set = new HashSet<>(1);
        set.add(id);
        return this.removeByIds(set);
    }

    /*
    private void delCaches(Integer id) {
        redisUtils.delByKey(CACHE_KEY + "::id:", id);
    }

    private void delCaches(Set<Integer> ids) {
        for (Integer id: ids) {
            delCaches(id);
        }
    }*/

    /*
    @Override
    public void download(List<MessageDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (MessageDto message : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("标题", message.getTitle());
              map.put("内容", message.getContent());
              map.put("发布时间", message.getCreatetime());
              map.put("状态", message.getStatus());
              map.put("作者", message.getAuthor());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/

    @Override
    public Object queryMessage() {
        List<Map<String, Object>> list = messageMapper.queryMessage();
        if (list == null) {
            return "暂时无公告";
        }
        return list;
    }
}
