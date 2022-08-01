package me.zhengjie.modules.studentCourseInfo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.studentCourseInfo.domain.CourseStudentInfo;
import me.zhengjie.modules.studentCourseInfo.service.CourseStudentInfoService;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoDto;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoQueryParam;
import me.zhengjie.modules.studentCourseInfo.service.mapper.CourseStudentInfoMapper;
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
* @date 2022-05-26
*/
@Slf4j
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = CourseStudentInfoService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseStudentInfoServiceImpl extends CommonServiceImpl<CourseStudentInfoMapper, CourseStudentInfo> implements CourseStudentInfoService {

    // private final RedisUtils redisUtils;
    private final CourseStudentInfoMapper courseStudentInfoMapper;

    @Override
    public PageInfo<CourseStudentInfoDto> queryAll(CourseStudentInfoQueryParam query, Pageable pageable) {
        IPage<CourseStudentInfo> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<CourseStudentInfo> page = courseStudentInfoMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, CourseStudentInfoDto.class);
    }

    @Override
    public List<CourseStudentInfoDto> queryAll(CourseStudentInfoQueryParam query){
        return ConvertUtil.convertList(courseStudentInfoMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), CourseStudentInfoDto.class);
    }

    @Override
    public CourseStudentInfo getById(Integer id) {
        return courseStudentInfoMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public CourseStudentInfoDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), CourseStudentInfoDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(CourseStudentInfoDto resources) {
        CourseStudentInfo entity = ConvertUtil.convert(resources, CourseStudentInfo.class);
        return courseStudentInfoMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(CourseStudentInfoDto resources){
        CourseStudentInfo entity = ConvertUtil.convert(resources, CourseStudentInfo.class);
        int ret = courseStudentInfoMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return courseStudentInfoMapper.deleteBatchIds(ids);
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
    public void download(List<CourseStudentInfoDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (CourseStudentInfoDto courseStudentInfo : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("学生编号", courseStudentInfo.getSid());
              map.put("课程编号", courseStudentInfo.getCid());
              map.put("教学评价", courseStudentInfo.getJudge());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
    @Override
    public Object getJudgeByStudent(Integer cid) {
        String username = SecurityUtils.getCurrentUsername();

        List<Map<String, Object>> list = courseStudentInfoMapper.getJudgeByStudent(Integer.valueOf(username),cid);
        if (list == null) {
            return "暂时无未评价的教师";
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertJudgeByStudent(Integer cid,Integer judge) {
        String sid = SecurityUtils.getCurrentUsername();
        courseStudentInfoMapper.insertJudgeByStudent(Integer.valueOf(sid), cid,judge);
    }
}
