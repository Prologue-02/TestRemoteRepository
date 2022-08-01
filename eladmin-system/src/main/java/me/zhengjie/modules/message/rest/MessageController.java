package me.zhengjie.modules.message.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.message.domain.Message;
import me.zhengjie.modules.message.service.MessageService;
import me.zhengjie.modules.message.service.dto.MessageDto;
import me.zhengjie.modules.message.service.dto.MessageQueryParam;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "message管理")
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    @Log("查询message")
    @ApiOperation("查询message")
    @PreAuthorize("@el.check('message:list')")
    public ResponseEntity query(MessageQueryParam query, Pageable pageable){
        return new ResponseEntity<>(messageService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增message")
    @ApiOperation("新增message")
    @PreAuthorize("@el.check('message:add')")
    public ResponseEntity create(@Validated @RequestBody MessageDto resources){
        return new ResponseEntity<>(messageService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改message")
    @ApiOperation("修改message")
    @PreAuthorize("@el.check('message:edit')")
    public ResponseEntity update(@Validated @RequestBody MessageDto resources){
        messageService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除message")
    @ApiOperation("删除message")
    @PreAuthorize("@el.check('message:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        messageService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出message")
    @ApiOperation("导出message")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('message:list')")
    public void download(HttpServletResponse response, MessageQueryParam query) throws IOException {
        messageService.download(messageService.queryAll(query), response);
    }*/

    @Log("用户查看公告栏")
    @ApiOperation("用户查看公告栏")
    @GetMapping(value = "/queryMessage")
    public ResponseEntity<Object> queryMessage() {

        return new ResponseEntity<>(messageService.queryMessage(), HttpStatus.OK);
    }

}
