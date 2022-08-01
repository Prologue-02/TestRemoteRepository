package me.zhengjie.modules.teacher.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.TeacherService;
import me.zhengjie.modules.teacher.service.dto.TeacherDto;
import me.zhengjie.modules.teacher.service.dto.TeacherQueryParam;
import me.zhengjie.modules.teacher.service.mapper.TeacherMapper;
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
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = TeacherService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TeacherServiceImpl extends CommonServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    // private final RedisUtils redisUtils;
    private final TeacherMapper teacherMapper;

    @Override
    public PageInfo<TeacherDto> queryAll(TeacherQueryParam query, Pageable pageable) {
        IPage<Teacher> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Teacher> page = teacherMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> queryAll(TeacherQueryParam query){
        return ConvertUtil.convertList(teacherMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), TeacherDto.class);
    }

    @Override
    public Teacher getById(Integer id) {
        return teacherMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public TeacherDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), TeacherDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(TeacherDto resources) {
        Teacher entity = ConvertUtil.convert(resources, Teacher.class);
        return teacherMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(TeacherDto resources){
        Teacher entity = ConvertUtil.convert(resources, Teacher.class);
        int ret = teacherMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return teacherMapper.deleteBatchIds(ids);
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
    public void download(List<TeacherDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (TeacherDto teacher : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("用户名", teacher.getUsername());
              map.put("姓名", teacher.getTname());
              map.put("年龄", teacher.getAge());
              map.put("性别", teacher.getSex());
              map.put("地址", teacher.getAddress());
              map.put("手机号", teacher.getPhone());
              map.put("邮箱", teacher.getEmail());
              map.put("状态", teacher.getStatus());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
    @Override
    public Object getTeacherInfo() {
        List<Map<String, Object>> list = teacherMapper.getTeacherInfo();
        if (list == null) {
            return "暂时无教师信息";
        }
        return list;
    }
}
