package me.zhengjie.modules.mark.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.MarkService;
import me.zhengjie.modules.mark.service.dto.MarkDto;
import me.zhengjie.modules.mark.service.dto.MarkQueryParam;
import me.zhengjie.modules.mark.service.mapper.MarkMapper;
import me.zhengjie.utils.SecurityUtils;
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
@Slf4j
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = MarkService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MarkServiceImpl extends CommonServiceImpl<MarkMapper, Mark> implements MarkService {

    // private final RedisUtils redisUtils;
    private final MarkMapper markMapper;

    @Override
    public PageInfo<MarkDto> queryAll(MarkQueryParam query, Pageable pageable) {
        IPage<Mark> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Mark> page = markMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));

        PageInfo<MarkDto> list = ConvertUtil.convertPage(page, MarkDto.class);
        list.getContent().forEach(markDto -> {
            Integer sid = markDto.getId();
            String sname = markMapper.findStudentNameById(sid);
            markDto.setSname(sname);
            Integer cid = markDto.getCid();
            String cname = markMapper.findCourseNameById(cid);
            markDto.setCname(cname);
            String tname = markMapper.findTeacherNameById(cid);
            markDto.setTname(tname);
            String year = markMapper.findCourseYearById(cid);
            markDto.setYear(year);
            Integer tearm = markMapper.findCourseTearmById(cid);
            markDto.setTearm(tearm);
        });
        return list;
    }

    @Override
    public List<MarkDto> queryAll(MarkQueryParam query){
        return ConvertUtil.convertList(markMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), MarkDto.class);
    }

    @Override
    public Mark getById(Integer id) {
        return markMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public MarkDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), MarkDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(MarkDto resources) {
        Mark entity = ConvertUtil.convert(resources, Mark.class);
        return markMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(MarkDto resources){
        Mark entity = ConvertUtil.convert(resources, Mark.class);
        int ret = markMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return markMapper.deleteBatchIds(ids);
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
    public void download(List<MarkDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (MarkDto mark : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("学生编号", mark.getId());
              map.put("课程编号", mark.getCid());
              map.put("成绩", mark.getGrade());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/

    @Override
    public Object getCourseGradeByStudent() {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = markMapper.queryMarkBySid(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getAllUnMarkedStudent() {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = markMapper.getAllUnMarkedStudent(username);
        if (list == null) {
            return "暂时学生信息";
        }
        return list;
    }

    @Override
    public Object getlUnMarkedStudentByCid(Integer cid) {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = markMapper.getlUnMarkedStudentByCid(Integer.valueOf(username),cid);
        if (list == null) {
            return "暂时学生信息";
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertStudentMark(Integer cid,Integer sid,Integer mark) {
        markMapper.insertStudentMark(cid,sid,mark);
    }
}
