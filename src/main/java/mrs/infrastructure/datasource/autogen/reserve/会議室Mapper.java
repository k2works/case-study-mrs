package mrs.infrastructure.datasource.autogen.reserve;

import mrs.domain.model.autogen.reserve.会議室;
import org.apache.ibatis.annotations.*;

public interface 会議室Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    @Delete({
            "delete from reserve.会議室",
            "where 会議室番号 = #{会議室番号,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer 会議室番号);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    @Insert({
            "insert into reserve.会議室 (会議室番号, 会議室名, ",
            "登録日時)",
            "values (#{会議室番号,jdbcType=INTEGER}, #{会議室名,jdbcType=VARCHAR}, ",
            "#{登録日時,jdbcType=TIMESTAMP})"
    })
    int insert(会議室 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    int insertSelective(会議室 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "会議室番号, 会議室名, 登録日時",
            "from reserve.会議室",
            "where 会議室番号 = #{会議室番号,jdbcType=INTEGER}"
    })
    @ResultMap("mrs.infrastructure.datasource.autogen.reserve.会議室Mapper.BaseResultMap")
    会議室 selectByPrimaryKey(Integer 会議室番号);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(会議室 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reserve.会議室
     *
     * @mbg.generated
     */
    @Update({
            "update reserve.会議室",
            "set 会議室名 = #{会議室名,jdbcType=VARCHAR},",
            "登録日時 = #{登録日時,jdbcType=TIMESTAMP}",
            "where 会議室番号 = #{会議室番号,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(会議室 record);
}
