package net.stackoverflow.blog.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 分类VO
 *
 * @author 凉衫薄
 */
public class CategoryVO {

    @NotNull(message = "缺少主键字段", groups = {CategoryVO.DeleteGroup.class, CategoryVO.UpdateGroup.class})
    private String id;

    @NotNull(message = "缺少分类名字段", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    @Size(min = 1, max = 100, message = "分类名长度只能在1到100之间", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    @Pattern(regexp = "^[\\u4e00-\\u9fa50-9a-zA-Z_]+$", message = "分类名只能包含中文数字字母下划线", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    private String name;

    @NotNull(message = "缺少编码字段", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    @Size(min = 1, max = 100, message = "编码长度只能在1到100之间", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "编码只能为字母数字下划线", groups = {CategoryVO.InsertGroup.class, CategoryVO.UpdateGroup.class})
    private String code;

    private Integer deleteAble;
    private Date date;

    //以下为扩展字段
    private Integer articleCount;
    private String deleteStr;

    public interface InsertGroup {
    }

    public interface UpdateGroup {
    }

    public interface DeleteGroup {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(Integer deleteAble) {
        this.deleteAble = deleteAble;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getDeleteStr() {
        return deleteStr;
    }

    public void setDeleteStr(String deleteStr) {
        this.deleteStr = deleteStr;
    }
}
