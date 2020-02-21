package com.zyc.service;

import com.zyc.entity.*;
import com.zyc.mapper.FactoryMapper;
import com.zyc.mapper.GameMapper;
import com.zyc.mapper.KindMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @ProjectName: WebProject
 * @Package com.zyc.service
 * @Description: 游戏game类service层接口实现类
 * @author: Zeke
 * @date: 2019/12/24 14:01
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 后台
     */

    @Override
    //查询所有
    public List<Game> selectAll() {
        return gameMapper.selectAll();
    }

    @Override
    public List<Game> selectAllByKindName(int page, String kindName){
        //分页操作,每页展示五条数据
        RowBounds rowBuouds = new RowBounds((page-1)*Game.gamePage,Game.gamePage);
        List<Game> games = gameMapper.selectByRowBounds(new Game().setKind(kindName), rowBuouds);
        return games;
    }

    @Override
    //查询总数据量
    public int selectAllSum() {
        return gameMapper.selectCount(new Game());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    //添加
    public void insert(MultipartFile[] files, HttpSession session, Game game) {
        //上传文件
        this.upload(files, session, game);
        //设置游戏id
        game.setId(UUID.randomUUID().toString());
        //设置添加时间
        game.setCreateDate(new Date());
        //设置状态
        game.setState("正常");
        //添加
        gameMapper.insert(game);
        //获取当前类别
        System.out.println(game);
        Kind kind = kindMapper.selectOne(new Kind().setName(game.getKind()));
        //修改游戏数量
        kindMapper.updateByPrimaryKeySelective(kind.setGameNum(kind.getGameNum()+1));
        //获取当前厂商
        Factory factory = factoryMapper.selectOne(new Factory().setName(game.getFactory()));
        //修改游戏数量
        factoryMapper.updateByPrimaryKeySelective(factory.setGameNum(factory.getGameNum()+1));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id, HttpSession session) {
        //获取要删除的对象
        Game game = gameMapper.selectOne(new Game().setId(id));
        //获取项目路径
        String realPath = session.getServletContext().getRealPath("/static");
        //获取文件路径
        String[] filePath = new String[6];
        //获取视频路径
        filePath[0] = realPath+game.getVedioPath();
        //获取图片路径
        filePath[1] = realPath+game.getShowGamePath();
        filePath[2] = realPath+game.getRecommendedGamePath();
        filePath[3] = realPath+game.getListGamePath();
        filePath[4] = realPath+game.getCutGamePath1();
        filePath[5] = realPath+game.getCutGamePath2();
        //删除文件
        for (String s : filePath) {
            File file = new File(s);
            file.delete();
        }
        /**
         * 修改类别和厂商数据
         */
        //获取类别对象
        Kind kind = kindMapper.selectOne(new Kind().setName(game.getKind()));
        //修改游戏数量
        kind.setGameNum(kind.getGameNum()-1);
        kindMapper.updateByPrimaryKeySelective(kind);
        //获取厂商对象
        Factory factory = factoryMapper.selectOne(new Factory().setName(game.getFactory()));
        //修改游戏数量
        factory.setGameNum(factory.getGameNum()-1);
        factoryMapper.updateByPrimaryKeySelective(factory);
        //删除数据库数据
        gameMapper.delete(game);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    //全局修改
    public void update(MultipartFile[] files, HttpSession session ,Game game) {
        //上传文件
        this.upload(files, session, game);
        //设置更新时间
        game.setUpdateDate(new Date());
        //更新
        gameMapper.updateByPrimaryKeySelective(game);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    //修改评论关注数量
    public void updateNum(Game game) {
        //更新
        gameMapper.updateByPrimaryKeySelective(game);
    }

    @Override
    //上传文件
    public void upload(MultipartFile[] files, HttpSession session, Game game) {
        //获取项目路径
        String realPath = session.getServletContext().getRealPath("/static");
        try {
        /**
         * files按照顺序设置数据库字段
         * files上传文件
         */
         if(!files[0].getOriginalFilename().equals("")) {
             //获取视频文件名称
             String vedidName = "/vedio/" + files[0].getOriginalFilename();
             //设置视频路径
             game.setVedioPath(vedidName);
             //创建文件
             File f0 = new File(realPath + vedidName);
             //上传文件
             files[0].transferTo(f0);
         }
        if(!files[1].getOriginalFilename().equals("")) {
            //获取游戏详情页面图片名称
            String showGameName = "/img/showgame/" + files[1].getOriginalFilename();
            //设置游戏详情页面图片路径
            game.setShowGamePath(showGameName);
            //创建文件
            File f1 = new File(realPath+showGameName);
            //上传文件
            files[1].transferTo(f1);
        }
        if(!files[2].getOriginalFilename().equals("")) {
            //获取特别推荐页面图片名称
            String recommendedGameName = "/img/cutgame/" + files[2].getOriginalFilename();
            //设置特别推荐页面图片路径
            game.setRecommendedGamePath(recommendedGameName);
            //创建文件
            File f2 = new File(realPath+recommendedGameName);
            //上传文件
            files[2].transferTo(f2);
        }
        if(!files[3].getOriginalFilename().equals("")) {
            //获取首页按钮展示页面图片名称
            String listGameName = "/img/listgame/" + files[3].getOriginalFilename();
            //设置首页按钮展示页面图片路径
            game.setListGamePath(listGameName);
            //创建文件
            File f3 = new File(realPath+listGameName);
            //上传文件
            files[3].transferTo(f3);
        }
        if(!files[4].getOriginalFilename().equals("")) {
            //获取游戏截图1图片名称
            String cutGameName1 = "/img/listgame/little/" + files[4].getOriginalFilename();
            //设置游戏截图1图片路径
            game.setCutGamePath1(cutGameName1);
            //创建文件
            File f4 = new File(realPath+cutGameName1);
            //上传文件
            files[4].transferTo(f4);
        }
        if(!files[5].getOriginalFilename().equals("")) {
            //获取游戏截图2图片名称
            String cutGameName2 = "/img/listgame/little/"+files[5].getOriginalFilename();
            //设置游戏截图2图片路径
            game.setCutGamePath2(cutGameName2);
            //创建文件
            File f5= new File(realPath+cutGameName2);
            //上传文件
            files[5].transferTo(f5);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //根据id查询
    public Game selectById(String id) {
        return gameMapper.selectOne(new Game().setId(id));
    }

    @Override
    //根据类别名称查询
    public List<Game> selectByKindName(String kindName) {
        return gameMapper.select(new Game().setKind(kindName));
    }

    /**
     * 前台
     */

    @Override
    //根据名称查询游戏
    public Game selectByName(String name){
        Game game = gameMapper.selectOne(new Game().setName(name));
        return game;
    }

    @Override
    //特别推荐：随机查询3条数据
    public List<Game> selectThreeByRandom() {
        return gameMapper.selectThreeByRandom();
    }

    @Override
    //实时热搜榜：根据redis评分查询6条数据
    public Set<Game> selectByRedisScore() {
        //获取zset用于获得热门排行榜
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        //获取评分高的6条数据
        Set<Game> hotGame = zSetOperations.reverseRange(GameRedis.GAME_GAME, 0, 5);
        return hotGame;
    }

    @Override
    //最高评分：根据评分查询6条数据
    public List<Game> selectByScore(){
        Example example = new Example(Game.class);
        //根据评分降序
        example.orderBy("score").desc();
        //获取6条数据
        RowBounds rowBounds = new RowBounds(0,6);
        List<Game> games = gameMapper.selectByExampleAndRowBounds(example , rowBounds);
        return games;
    }

    @Override
    //最近更新：根据上架时间查询6条数据
    public List<Game> selectByCreateDate(){
        Example example = new Example(Game.class);
        //根据上架时间降序
        example.orderBy("createDate").desc();
        //获取6条数据
        RowBounds rowBounds = new RowBounds(0,6);
        List<Game> games = gameMapper.selectByExampleAndRowBounds(example,rowBounds);
        return games;
    }

    @Override
    //最多好评：根据好评数量查询6条数据
    public List<Game> selectByGoodConmmentsNum(){
        Example example = new Example(Game.class);
        //根据好评数量降序
        example.orderBy("goodCommentsNum").desc();
        //获取6条数据
        RowBounds rowBounds = new RowBounds(0,6);
        List<Game> games = gameMapper.selectByExampleAndRowBounds(example,rowBounds);
        return games;
    }

    @Override
    //精选：随机查询12条数据
    public List<Game> selectTwelveByRandom() {
        List<Game> games = gameMapper.selectTwelveByRandom();
        return games;
    }

    @Override
    //探险家：根据类别查询5条数据
    public List<Game> selectFiveByKind(String kind){
        return gameMapper.select(new Game().setKind(kind));
    }

    @Override
    //最近更新：根据更新时间查询12条数据
    public List<Game> selectTwelveByUpdateDate() {
        Example example = new Example(Game.class);
        //根据更新时间降序
        example.orderBy("updateDate").desc();
        List<Game> games = gameMapper.selectByExample(example);
        return games;
    }

    @Override
    //时下热门：根据redis评分查询12条数据
    public Set<Game> selectTwelveByRedisScore() {
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        //获取评分高的12条数据
        Set<Game> games = zSetOperations.reverseRange(GameRedis.GAME_GAME, 0, 11);
        return games;
    }

    @Override
    //最多关注：根据关注人数查询12条数据
    public List<Game> selectTwelveByAttentionNum() {
        Example example = new Example(Game.class);
        //根据关注人数降序
        example.orderBy("attentionNum").desc();
        List<Game> games = gameMapper.selectByExample(example);
        return games;
    }

    @Override
    //即将推出：查询状态为开发的12款游戏
    public List<Game> selectTwelveWhereStateIsDevelopment() {
        Example example = new Example(Game.class);
        //根据更新时间降序
        example.orderBy("createDate").desc();
        //查询状态为开发
        example.createCriteria().andEqualTo("state","开发中");
        List<Game> games = gameMapper.selectByExample(example);
        return games;
    }

    @Override
    //抢先测试：查询状态为测试的12款游戏：
    public List<Game> selectTwelveWhereStateIsTest() {
        Example example = new Example(Game.class);
        //根据更新时间降序
        example.orderBy("createDate").desc();
        //查询状态为测试
        example.createCriteria().andEqualTo("state","测试");
        List<Game> games = gameMapper.selectByExample(example);
        return games;
    }

    @Override
    //根据id查询单个游戏
    public Game selecyById(String id) {
        return gameMapper.selectOne(new Game().setId(id));
    }

    @Override
    //根据类别分页查询数据
    public Map<String , Object> selectByKindAndFactoryAndScorePage(String kind , String factory , String score , Integer page){
        //创建返回值数据
        Map<String , Object> map = new HashMap<>();
        if(page == null)
            page=1;
        //添加页码
        map.put("page",page);
        Example example = new Example(Game.class);
        Example.Criteria criteria = example.createCriteria();
        List<Game> games;
        //数据分页：每页展示12条数据
        RowBounds rowBounds = new RowBounds((page-1)* Page.gamePage,Page.gamePage);
        //如果kind不为all则查询指定类别
        if(kind != null && ! (kind.equals("all"))) {
            //根据类别查询数据
            criteria.andEqualTo("kind", kind);
        }
        //如果factory不为all则查询指定厂商
        if(factory != null && ! (factory.equals("all"))) {
            criteria.andEqualTo("factory", factory);
        }
        if(score != null && ! (score.equals("all"))) {
            criteria.andBetween("score", Double.parseDouble(score) - 1, Double.parseDouble(score));
        }
        //查询数据
        games = gameMapper.selectByExampleAndRowBounds(example, rowBounds);
        map.put("games",games);
        return map;
    }

    @Override
    //查询指定类别的总数据量
    public int selectSumByKindAndFactoryDateAndScore(String kind , String factory , String score) {

        Example example = new Example(Game.class);
        Example.Criteria criteria = example.createCriteria();
        //如果kind不为all则查询指定类别
        if(kind != null && ! (kind.equals("all")))
            //根据类别查询数据
            criteria.andEqualTo("kind", kind);
        //如果factory不为all则查询指定厂商
        if(factory != null && ! (factory.equals("all"))) {
            criteria.andEqualTo("factory", factory);
        }
        if(score != null && ! (score.equals("all")))
            criteria.andBetween("score",Double.parseDouble(score)-1,Double.parseDouble(score));
        //查询数据
        int sum = gameMapper.selectCountByExample(example);
        return sum;
    }

}
