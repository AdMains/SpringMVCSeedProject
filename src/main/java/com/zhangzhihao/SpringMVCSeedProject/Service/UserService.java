package com.zhangzhihao.SpringMVCSeedProject.Service;

import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.SHA_1Utils.getSHA_1;

@Service
public class UserService extends BaseService<User> {
    /**
     * 保存对象
     *
     * @param model 需要添加的对象
     */
    @Override
    public void save(@NotNull User model) throws Exception {
        model.setPassWord(getSHA_1(model.getPassWord()));
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
                model->model.setPassWord(
                        getSHA_1(model.getPassWord())
                )
        );
        super.saveAll(modelList);
    }


    /**
     * 更新或保存对象
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    public void update(@NotNull User model) throws Exception {
        model.setPassWord(getSHA_1(model.getPassWord()));
        super.saveOrUpdate(model);
    }

    /**
     * 批量更新或保存对象
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    public void updateAll(@NotNull List<User> modelList) throws Exception {
        modelList.forEach(
                model->model.setPassWord(
                        getSHA_1(model.getPassWord())
                )
        );
        super.saveOrUpdateAll(modelList);
    }


    /**
     * 更新或保存对象；
     * 因为涉及密码加密的逻辑问题，这个方法在userService中已废弃，其它实体正常使用
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    @Deprecated
    @Override
    public void saveOrUpdate(@NotNull User model) throws Exception {
        throw new Exception("因为涉及密码加密的逻辑问题，这个方法在userService中已废弃，其它实体正常使用");
    }

    /**
     * 批量更新或保存对象；
     * 因为涉及密码加密的逻辑问题，这个方法在userService中已废弃，其它实体正常使用
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    @Deprecated
    @Override
    public void saveOrUpdateAll(@NotNull List<User> modelList) throws Exception {
        throw new Exception("因为涉及密码加密的逻辑问题，这个方法在userService中已废弃，其它实体正常使用");
    }
}
