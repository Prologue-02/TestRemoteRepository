package me.zhengjie.modules.mnt.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.mnt.domain.Server;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author jinjin
* @date 2020-09-27
*/
@Repository
public interface ServerMapper extends CommonMapper<Server> {
    List<Server> selectAllByDeployId(Long id);
}
