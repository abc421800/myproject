package com.cost168.costaudit.mapper.cost;

import com.cost168.costaudit.pojo.cost.CostAttachment;
import com.cost168.costaudit.pojo.cost.CostAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CostAttachmentMapper {
    int countByExample(CostAttachmentExample example);

    int deleteByExample(CostAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(CostAttachment record);

    int insertSelective(CostAttachment record);

    List<CostAttachment> selectByExample(CostAttachmentExample example);

    CostAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CostAttachment record, @Param("example") CostAttachmentExample example);

    int updateByExample(@Param("record") CostAttachment record, @Param("example") CostAttachmentExample example);

    int updateByPrimaryKeySelective(CostAttachment record);

    int updateByPrimaryKey(CostAttachment record);

    int updateById(CostAttachment record);
}