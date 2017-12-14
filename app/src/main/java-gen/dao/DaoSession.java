package dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import bean.UserBean;
import bean.ArticleBean;

import dao.UserBeanDao;
import dao.ArticleBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userBeanDaoConfig;
    private final DaoConfig articleBeanDaoConfig;

    private final UserBeanDao userBeanDao;
    private final ArticleBeanDao articleBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        articleBeanDaoConfig = daoConfigMap.get(ArticleBeanDao.class).clone();
        articleBeanDaoConfig.initIdentityScope(type);

        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);
        articleBeanDao = new ArticleBeanDao(articleBeanDaoConfig, this);

        registerDao(UserBean.class, userBeanDao);
        registerDao(ArticleBean.class, articleBeanDao);
    }
    
    public void clear() {
        userBeanDaoConfig.clearIdentityScope();
        articleBeanDaoConfig.clearIdentityScope();
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

    public ArticleBeanDao getArticleBeanDao() {
        return articleBeanDao;
    }

}