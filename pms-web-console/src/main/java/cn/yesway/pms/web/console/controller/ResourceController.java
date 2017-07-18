package cn.yesway.pms.web.console.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.yesway.pms.common.page.PageBean;
import cn.yesway.pms.common.page.PageParam;
import cn.yesway.pms.common.web.entity.WebAppResult;
import cn.yesway.pms.common.web.enums.ResultStatus;
import cn.yesway.pms.core.cast.resource.biz.ResourceBiz;
import cn.yesway.pms.core.cast.resource.entity.Resource;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    private ResourceBiz resourceBiz;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebAppResult create(@RequestBody Resource resource) {
        if (resourceBiz.create(resource)) {
            return new WebAppResult(ResultStatus.SUCCESS, "保存成功", null);
        }
        return new WebAppResult(ResultStatus.FAIL, "保存失败", null);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public WebAppResult update(@RequestBody Resource resource) {
        if (resourceBiz.update(resource)) {
            return new WebAppResult(ResultStatus.SUCCESS, "编辑成功", null);
        }
        return new WebAppResult(ResultStatus.FAIL, "编辑失败", null);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public WebAppResult delete(@RequestBody Long[] ids) {
        if (resourceBiz.deleteById(ids)) {
            return new WebAppResult(ResultStatus.SUCCESS, "删除成功", null);
        }
        return new WebAppResult(ResultStatus.FAIL, "删除失败", null);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public WebAppResult findById(@PathVariable Long id) {
        Resource resource = resourceBiz.findById(id);
        return new WebAppResult(ResultStatus.SUCCESS, "", resource);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebAppResult listByPage(@RequestBody Map<String, Object> map) {
        PageParam page = new PageParam(Integer.parseInt(map.get("pageNum").toString()),
                Integer.parseInt(map.get("numPerPage").toString()));
        PageBean<Resource> datas = resourceBiz.listByPageAndParam(page, map);
        return new WebAppResult(ResultStatus.SUCCESS, "", datas);
    }

    @RequestMapping(value = "/list/param", method = RequestMethod.GET)
    public WebAppResult listByParam() {
        List<Resource> datas = resourceBiz.listByParam(null);
        return new WebAppResult(ResultStatus.SUCCESS, "", datas);
    }

    @RequestMapping(value = "/isExists", method = RequestMethod.POST)
    public WebAppResult isExists(@RequestBody Map<String, Object> map) {
        if (resourceBiz.isExists(map)) {
            return new WebAppResult(ResultStatus.FAIL, "编号已存在", null);
        }
        return new WebAppResult(ResultStatus.SUCCESS, "", null);
    }

}
