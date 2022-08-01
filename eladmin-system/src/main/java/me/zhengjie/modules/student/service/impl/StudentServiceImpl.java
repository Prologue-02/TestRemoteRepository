package me.zhengjie.modules.student.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.StudentService;
import me.zhengjie.modules.student.service.dto.StudentDto;
import me.zhengjie.modules.student.service.dto.StudentQueryParam;
import me.zhengjie.modules.student.service.mapper.StudentMapper;
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
// @CacheConfig(cacheNames = StudentService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentServiceImpl extends CommonServiceImpl<StudentMapper, Student> implements StudentService {

    // private final RedisUtils redisUtils;
    private final StudentMapper studentMapper;

    @Override
    public PageInfo<StudentDto> queryAll(StudentQueryParam query, Pageable pageable) {
        IPage<Student> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Student> page = studentMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, StudentDto.class);
    }

    @Override
    public List<StudentDto> queryAll(StudentQueryParam query){
        return ConvertUtil.convertList(studentMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), StudentDto.class);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public StudentDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), StudentDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(StudentDto resources) {
        Student entity = ConvertUtil.convert(resources, Student.class);
        return studentMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(StudentDto resources){
        Student entity = ConvertUtil.convert(resources, Student.class);
        int ret = studentMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return studentMapper.deleteBatchIds(ids);
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
    public void download(List<StudentDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (StudentDto student : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("用户名", student.getUsername());
              map.put("姓名", student.getName());
              map.put("性别", student.getSex());
              map.put("年龄", student.getAge());
              map.put("专业", student.getMajor());
              map.put("家庭地址", student.getAddress());
              map.put("邮箱", student.getEmail());
              map.put("手机号", student.getTel());
              map.put("状态", student.getStatus());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
}
