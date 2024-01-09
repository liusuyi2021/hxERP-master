package com.ruoyi.wh.task;

import com.ruoyi.wh.service.IWhStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: miki
 * @modified By：注意：本内容仅限于湖南索孚威尔科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 * @time: 2021-06-21 20:51
 */
@Component("storageTask")
public class StorageTask {

    @Autowired
    private IWhStorageService whStorageService;

    public void updateisEmptyTask()
    {
        whStorageService.updateisEmptyTask();
        System.out.println("执行无参方法");
    }
}
