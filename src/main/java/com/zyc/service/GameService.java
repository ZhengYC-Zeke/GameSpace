package com.zyc.service;

import com.zyc.entity.Game;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 游戏game类service层接口
 * @author: Zeke
 * @date: 2019/12/24 9:16
 */
public interface GameService {

    /**
     * 后台
     */
    //查询所有
    List<Game> selectAll();
    //根据类别分页查询
    List<Game> selectAllByKindName(int page,String kindName);
    //分页查询总数
    int selectAllSum();
    //添加
    void insert(MultipartFile[] files, HttpSession session, Game game);
    //删除
    void delete(String id , HttpSession session);
    //全局修改
    void update(MultipartFile[] files, HttpSession session,Game game);
    //修改评论关注数量
    void updateNum(Game game);
    //文件上传
    void upload(MultipartFile[] files , HttpSession session , Game game);
    //根据id单个查询
    Game selectById(String id);
    //根据类别名称查询
    List<Game> selectByKindName(String kindName);

    /**
     * 前台
     */
    //根据名称查询游戏
    Game selectByName(String name);

    //特别推荐：随机查询3条数据
    List<Game> selectThreeByRandom();

    //实时热搜榜：根据redis评分查询6条数据
    Set<Game> selectByRedisScore();
    //最高评分：根据评分查询6条数据
    List<Game> selectByScore();
    //最新上架：根据上架时间查询6条数据
    List<Game> selectByCreateDate();
    //最多好评：根据好评数量查询6条数据
    List<Game> selectByGoodConmmentsNum();

    //精选：随机查询12条数据
    List<Game> selectTwelveByRandom();
    //探险家：根据类别随机查询5条数据
    List<Game> selectFiveByKind(String kind);

    //最近更新：根据更新时间查询12条数据
    List<Game> selectTwelveByUpdateDate();

    //时下热门：根据redis评分查询12条数据
    Set<Game> selectTwelveByRedisScore();
    //最多关注：根据关 注人数查询12条数据
    List<Game> selectTwelveByAttentionNum();
    //即将推出：查询状态为开发的12款游戏
    List<Game> selectTwelveWhereStateIsDevelopment();
    //抢先测试：查询状态为测试的12款游戏：
    List<Game> selectTwelveWhereStateIsTest();

    //根据id查询单个游戏
    Game selecyById(String id);

    //根据类别，厂商，评分分页查询数据
    Map<String , Object> selectByKindAndFactoryAndScorePage(String kind , String factory , String score ,  Integer page);
    //查询指定条件的总数据量
    int selectSumByKindAndFactoryDateAndScore(String kind , String factory , String score);

}
