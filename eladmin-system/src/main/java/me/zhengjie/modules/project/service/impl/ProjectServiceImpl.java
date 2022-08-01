package me.zhengjie.modules.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import me.zhengjie.base.PageInfo;
import me.zhengjie.base.QueryHelpMybatisPlus;
import me.zhengjie.base.impl.CommonServiceImpl;
import me.zhengjie.utils.ConvertUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.modules.project.domain.Project;
import me.zhengjie.modules.project.service.ProjectService;
import me.zhengjie.modules.project.service.dto.ProjectDto;
import me.zhengjie.modules.project.service.dto.ProjectQueryParam;
import me.zhengjie.modules.project.service.mapper.ProjectMapper;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author mazhijian
* @date 2022-06-01
*/
@Service
@AllArgsConstructor
// @CacheConfig(cacheNames = ProjectService.CACHE_KEY)
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ProjectServiceImpl extends CommonServiceImpl<ProjectMapper, Project> implements ProjectService {

    // private final RedisUtils redisUtils;
    private final ProjectMapper projectMapper;

    @Override
    public PageInfo<ProjectDto> queryAll(ProjectQueryParam query, Pageable pageable) {
        IPage<Project> queryPage = PageUtil.toMybatisPage(pageable);
        IPage<Project> page = projectMapper.selectPage(queryPage, QueryHelpMybatisPlus.getPredicate(query));
        return ConvertUtil.convertPage(page, ProjectDto.class);
    }

    @Override
    public List<ProjectDto> queryAll(ProjectQueryParam query){
        return ConvertUtil.convertList(projectMapper.selectList(QueryHelpMybatisPlus.getPredicate(query)), ProjectDto.class);
    }

    @Override
    public Project getById(Integer id) {
        return projectMapper.selectById(id);
    }

    @Override
    // @Cacheable(key = "'id:' + #p0")
    public ProjectDto findById(Integer id) {
        return ConvertUtil.convert(getById(id), ProjectDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(ProjectDto resources) {
        Project entity = ConvertUtil.convert(resources, Project.class);
        return projectMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateById(ProjectDto resources){
        Project entity = ConvertUtil.convert(resources, Project.class);
        int ret = projectMapper.updateById(entity);
        // delCaches(resources.id);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeByIds(Set<Integer> ids){
        // delCaches(ids);
        return projectMapper.deleteBatchIds(ids);
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
    public void download(List<ProjectDto> all, HttpServletResponse response) throws IOException {
      List<Map<String, Object>> list = new ArrayList<>();
      for (ProjectDto project : all) {
        Map<String,Object> map = new LinkedHashMap<>();
              map.put("标题", project.getTitle());
              map.put("内容", project.getContent());
              map.put("创建时间", project.getCreatetime());
              map.put("作者", project.getAuthor());
        list.add(map);
      }
      FileUtil.downloadExcel(list, response);
    }*/
    @Override
    public String getTeacherName(Integer id){
        return projectMapper.findTeacherNameById(id);
    }

    @Override
    public Object getProjectInfoById() {
        String username = SecurityUtils.getCurrentUsername();
        String tname  = projectMapper.findTeacherNameById(Integer.valueOf(username));
        List<Map<String, Object>> list = projectMapper.getProjectInfoById(tname);
        if (list == null) {
            return "暂时无教改项目信息";
        }
        return list;
    }

    @Override
    public Object getProjectStatusInfo() {
        String username = SecurityUtils.getCurrentUsername();
        String tname  = projectMapper.findTeacherNameById(Integer.valueOf(username));
        List<Map<String, Object>> list = projectMapper.getProjectStatusInfoById(tname);
        if (list == null) {
            return "暂时无教改项目信息";
        }
        return list;
    }

    @Override
    public Object getProjectInfoByStatus(String status) {
        String username = SecurityUtils.getCurrentUsername();
        String tname  = projectMapper.findTeacherNameById(Integer.valueOf(username));
        List<Map<String, Object>> list = projectMapper.getProjectInfoByStatus(tname,status);
        if (list == null) {
            return "暂时无教改项目信息";
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProjectInfo(String title,String content) {
        String username = SecurityUtils.getCurrentUsername();
        String tname  = projectMapper.findTeacherNameById(Integer.valueOf(username));
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = formatter.format(current);
        String status = "审核中";
        projectMapper.insertProjectInfo(title,content,createtime,tname,status);
    }
}
