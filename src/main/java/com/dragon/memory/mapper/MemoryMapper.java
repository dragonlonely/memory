package com.dragon.memory.mapper;
/**
 * @Classname LogMapper
 * @Description TODO
 * @Date 2019/1/7 19:45
 * @Created by Administrator
 */

import com.dragon.memory.picture.pojo.Memory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/1/7 19:45
 * @Classname LogMapper
 */
public interface MemoryMapper {

    void addPictures(Memory memory);

    List<Memory> getAllPictures(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    long getCount();

    List<Memory> findByName(String picturesname);

    Memory getPicturesDetailById(Integer id);

    void deletePictureById(Integer id);

    List<Memory> showAllPictures();

    void updatePictures(Memory memory);

    List<Memory> queryPictures(@Param("pname") String pname,@Param("createtime") String createtime);
}
