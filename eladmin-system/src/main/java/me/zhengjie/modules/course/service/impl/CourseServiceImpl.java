package me.zhengjie.modules.course.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.CourseService;
import me.zhengjie.modules.course.service.dto.CourseDto;
import me.zhengjie.modules.course.service.dto.CourseQueryParam;
import me.zhengjie.modules.course.service.mapper.CourseMapper;
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
// @CacheConfig(cacheNames = CourseService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseServiceImpl extends CommonServiceImpl<CourseMapper, Course> implements CourseService {

    // private final RedisUtils redisUtils;
    private final CourseMapper courseMapper;

    @Override
    public PageInfo<CourseDto> queryAll(CourseQueryParam query, Pageable pageable) {
        IPage<Course> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Course> page = courseMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));

        PageInfo<CourseDto> list = ConvertUtil.convertPage(page, CourseDto.class);
        list.getContent().forEach(courseDto -> {
            Integer tid =courseDto.getTid();
            String name = courseMapper.findTeacherNameById(tid);
            courseDto.setTname(name);
        });

        return list;
    }

    @Override
    public List<CourseDto> queryAll(CourseQueryParam query){
        return ConvertUtil.convertList(courseMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), CourseDto.class);
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public CourseDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), CourseDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CourseDto resources) {
        Course entity = ConvertUtil.convert(resources, Course.class);
        return courseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CourseDto resources){
        Course entity = ConvertUtil.convert(resources, Course.class);
        int ret = courseMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return courseMapper.deleteBatchIds(ids);
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
    public void download(List<CourseDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (CourseDto course : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("课程名", course.getCname());
              map.put("课时", course.getClasstime());
              map.put("周数", course.getClassweek());
              map.put("教师编号", course.getTid());
              map.put("学期", course.getTearm());
              map.put("学年", course.getYear());
              map.put("状态", course.getStatus());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
    @Override
    public Object getCourseInfoByStudent(){
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseMapper.queryCourseInfoByStudent(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object selectCourse(){
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseMapper.selectCourse(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void InsertStudentCourse(Integer cid) {
        String sid = SecurityUtils.getCurrentUsername();
        courseMapper.InsertStudentCourse(Integer.valueOf(sid), cid);
    }

    @Override
    public Object getCourseInfoByTeacher(){
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseMapper.queryCourseInfoByTeacher(username);
        if (list == null) {
            return "暂时无信息";
        }
        return list;
    }
}
