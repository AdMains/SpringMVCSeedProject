package com.zhangzhihao.SpringMVCSeedProject.Service;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.SHAUtils.getSHA_256;


@Service
public class UserService extends BaseService<User> {
    /**
     * 保存对象
     *
     * @param model 需要添加的对象
     */
    @Override
    public void save(@NotNull User model) throws Exception {
        model.setPassWord(getSHA_256(model.getUserName()+model.getPassWord()));
        super.save(model);
    }

    /**
     * 批量保存对象
     *
     * @param modelList 需要增加的对象的集合
     *                  失败会抛异常
     */
    @Override
    public void saveAll(@NotNull List<User> modelList) throws Exception {
        modelList.forEach(
                model -> model.setPassWord(
                        getSHA_256(model.getUserName()+model.getPassWord())
                )
        );
        super.saveAll(modelList);
    }


    /**
     * 更新或保存对象的PassWord
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    public void updatePassWord(@NotNull User model) throws Exception {
        model.setPassWord(getSHA_256(model.getUserName()+model.getPassWord()));
        super.saveOrUpdate(model);
    }

    /**
     * 批量更新或保存对象的PassWord
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    public void updateAllPassWord(@NotNull List<User> modelList) throws Exception {
        modelList.forEach(
                model -> model.setPassWord(
                        getSHA_256(model.getUserName()+model.getPassWord())
                )
        );
        super.saveOrUpdateAll(modelList);
    }

    /**
     * 更新或保存对象，不允许通过此方法修改密码！
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    @Override
    public void saveOrUpdate(@NotNull User model) throws Exception {
        super.saveOrUpdate(model);
    }

    /**
     * 批量更新或保存对象，不允许通过此方法修改密码！
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    @Override
    public void saveOrUpdateAll(@NotNull List<User> modelList) throws Exception {
        super.saveOrUpdateAll(modelList);
    }
}
