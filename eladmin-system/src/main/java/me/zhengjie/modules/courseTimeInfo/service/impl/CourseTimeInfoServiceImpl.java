package me.zhengjie.modules.courseTimeInfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.courseTimeInfo.domain.CourseTimeInfo;
import me.zhengjie.modules.courseTimeInfo.service.CourseTimeInfoService;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoDto;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoQueryParam;
import me.zhengjie.modules.courseTimeInfo.service.mapper.CourseTimeInfoMapper;
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
// @CacheConfig(cacheNames = CourseTimeInfoService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseTimeInfoServiceImpl extends CommonServiceImpl<CourseTimeInfoMapper, CourseTimeInfo> implements CourseTimeInfoService {

    // private final RedisUtils redisUtils;
    private final CourseTimeInfoMapper courseTimeInfoMapper;

    @Override
    public PageInfo<CourseTimeInfoDto> queryAll(CourseTimeInfoQueryParam query, Pageable pageable) {
        IPage<CourseTimeInfo> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<CourseTimeInfo> page = courseTimeInfoMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));

        PageInfo<CourseTimeInfoDto> list = ConvertUtil.convertPage(page, CourseTimeInfoDto.class);
        list.getContent().forEach(courseTimeInfoDto -> {
            Integer cid = courseTimeInfoDto.getCid();
            String cname = courseTimeInfoMapper.findCourseNameById(cid);
            courseTimeInfoDto.setCname(cname);
        });
        return list;
    }

    @Override
    public List<CourseTimeInfoDto> queryAll(CourseTimeInfoQueryParam query){
        return ConvertUtil.convertList(courseTimeInfoMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), CourseTimeInfoDto.class);
    }

    @Override
    public CourseTimeInfo getById(Integer id) {
        return courseTimeInfoMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public CourseTimeInfoDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), CourseTimeInfoDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CourseTimeInfoDto resources) {
        CourseTimeInfo entity = ConvertUtil.convert(resources, CourseTimeInfo.class);
        return courseTimeInfoMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CourseTimeInfoDto resources){
        CourseTimeInfo entity = ConvertUtil.convert(resources, CourseTimeInfo.class);
        int ret = courseTimeInfoMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return courseTimeInfoMapper.deleteBatchIds(ids);
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
    public void download(List<CourseTimeInfoDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (CourseTimeInfoDto courseTimeInfo : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("课程编号", courseTimeInfo.getCid());
              map.put("周", courseTimeInfo.getWeek());
              map.put("星期", courseTimeInfo.getDay());
              map.put("时段", courseTimeInfo.getTrap());
              map.put("上课地址", courseTimeInfo.getAdress());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/

    @Override
    public Object getCourseInfo() {

        List<Map<String, Object>> list = courseTimeInfoMapper.getCourseInfo();
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getStudentCourseInfo() {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getStudentCourseInfo(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getStudentCourseWeek() {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getStudentCourseWeek(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getStudentCourseInfoByWeek(String week) {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getStudentCourseInfoByWeek(username,week);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getTeacherCourseInfo(){
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getTeacherCourseInfo(username);
        if (list == null) {
            return "暂时无课程信息";
        }
        return list;
    }

    @Override
    public Object getTeacherCourseWeek() {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getTeacherCourseWeek(username);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    public Object getTeacherCourseInfoByWeek(String week) {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseTimeInfoMapper.getTeacherCourseInfoByWeek(username,week);
        if (list == null) {
            return "暂时无本学期相关任课信息";
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseInfo(Integer timeid,String week,String day,String trap,String address) {
        courseTimeInfoMapper.updateCourseInfo(timeid,week, day, trap, address);
    }

    @Override
    public Integer getCourseId(Integer id){
        return courseTimeInfoMapper.findCourseIdByTimeId(id);
    }

    @Override
    public String getCourseName(Integer id){
        return courseTimeInfoMapper.findCourseNameById(id);
    }

    @Override
    public String getTeacherName(Integer id){
        return courseTimeInfoMapper.findTeacherNameById(id);
    }

    @Override
    public String getOldWeek(Integer id){
        return courseTimeInfoMapper.findOldWeekById(id);
    }

    @Override
    public String getOldDay(Integer id){
        return courseTimeInfoMapper.findOldDayById(id);
    }

    @Override
    public String getOldTrap(Integer id){
        return courseTimeInfoMapper.findOldTrapById(id);
    }

    @Override
    public String getOldAddress(Integer id){
        return courseTimeInfoMapper.findOldAddressById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMessageInfo(String title,String content,String createtime,String author) {
        courseTimeInfoMapper.insertMessageInfo(title, content, createtime, author);
    }
}
