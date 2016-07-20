package com.zhangzhihao.SpringMVCSeedProject.Dao;


import com.zhangzhihao.SpringMVCSeedProject.Model.Permission;
import com.zhangzhihao.SpringMVCSeedProject.Model.Role;
import com.zhangzhihao.SpringMVCSeedProject.Model.User;
import com.zhangzhihao.SpringMVCSeedProject.Service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zhangzhihao.SpringMVCSeedProject.Utils.LogUtils.LogToDB;

/**
 * Realm是专用于安全框架的DAO
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权访问
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的用户名
        String userName = (String) super.getAvailablePrincipal(principals);

        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();
        User user = null;
        try {
            user = userService.getById(userName);
        } catch (Exception e) {
            LogToDB(e);
        }
        if (user != null) {
            if (user.getRoleList() != null && user.getRoleList().size() > 0) {
                for (Role role : user.getRoleList()) {
                    roles.add(role.getName());
                    if (role.getPermissionList() != null && role.getPermissionList().size() > 0) {
                        permissions.addAll(
                                role.getPermissionList().stream()
                                        .filter(permission -> !StringUtils.isEmpty(permission.getPermission()))
                                        .map(Permission::getPermission)
                                        .collect(Collectors.toList()
                                        )
                        );
                    }
                }
            }
        } else {
            throw new AuthorizationException();
        }
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 登录认证
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param authToken the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = null;
        try {
            user = userService.getById(token.getUsername());
        } catch (Exception e) {
            LogToDB(e);
            return null;
        }
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUserName(), user
                    .getPassWord(), user.toString()
            );
        } else {
            return null;
        }
    }
}
