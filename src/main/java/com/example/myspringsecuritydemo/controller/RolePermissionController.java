package com.example.myspringsecuritydemo.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myspringsecuritydemo.beans.RolePermission;
import com.example.myspringsecuritydemo.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2024-01-05 20:59:29
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param rolePermission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<RolePermission> page, RolePermission rolePermission) {
        return success(this.rolePermissionService.page(page, new QueryWrapper<>(rolePermission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.rolePermissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param rolePermission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody RolePermission rolePermission) {
        return success(this.rolePermissionService.save(rolePermission));
    }

    /**
     * 修改数据
     *
     * @param rolePermission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody RolePermission rolePermission) {
        return success(this.rolePermissionService.updateById(rolePermission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.rolePermissionService.removeByIds(idList));
    }
}

