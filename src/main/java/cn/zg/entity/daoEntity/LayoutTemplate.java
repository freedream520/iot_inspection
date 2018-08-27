package cn.zg.entity.daoEntity;

import java.io.Serializable;
import java.util.Date;

public class LayoutTemplate implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.tiid
     *
     * @mbg.generated
     */
    private String tiid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.layout_template_name
     *
     * @mbg.generated
     */
    private String layoutTemplateName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.layout_template_createtime
     *
     * @mbg.generated
     */
    private Date layoutTemplateCreatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.layout_template_createperson
     *
     * @mbg.generated
     */
    private String layoutTemplateCreateperson;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.layout_template_url
     *
     * @mbg.generated
     */
    private String layoutTemplateUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column layout_template.layout_picture_url
     *
     * @mbg.generated
     */
    private String layoutPictureUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table layout_template
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.tiid
     *
     * @return the value of layout_template.tiid
     *
     * @mbg.generated
     */
    public String getTiid() {
        return tiid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.tiid
     *
     * @param tiid the value for layout_template.tiid
     *
     * @mbg.generated
     */
    public void setTiid(String tiid) {
        this.tiid = tiid == null ? null : tiid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.layout_template_name
     *
     * @return the value of layout_template.layout_template_name
     *
     * @mbg.generated
     */
    public String getLayoutTemplateName() {
        return layoutTemplateName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.layout_template_name
     *
     * @param layoutTemplateName the value for layout_template.layout_template_name
     *
     * @mbg.generated
     */
    public void setLayoutTemplateName(String layoutTemplateName) {
        this.layoutTemplateName = layoutTemplateName == null ? null : layoutTemplateName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.layout_template_createtime
     *
     * @return the value of layout_template.layout_template_createtime
     *
     * @mbg.generated
     */
    public Date getLayoutTemplateCreatetime() {
        return layoutTemplateCreatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.layout_template_createtime
     *
     * @param layoutTemplateCreatetime the value for layout_template.layout_template_createtime
     *
     * @mbg.generated
     */
    public void setLayoutTemplateCreatetime(Date layoutTemplateCreatetime) {
        this.layoutTemplateCreatetime = layoutTemplateCreatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.layout_template_createperson
     *
     * @return the value of layout_template.layout_template_createperson
     *
     * @mbg.generated
     */
    public String getLayoutTemplateCreateperson() {
        return layoutTemplateCreateperson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.layout_template_createperson
     *
     * @param layoutTemplateCreateperson the value for layout_template.layout_template_createperson
     *
     * @mbg.generated
     */
    public void setLayoutTemplateCreateperson(String layoutTemplateCreateperson) {
        this.layoutTemplateCreateperson = layoutTemplateCreateperson == null ? null : layoutTemplateCreateperson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.layout_template_url
     *
     * @return the value of layout_template.layout_template_url
     *
     * @mbg.generated
     */
    public String getLayoutTemplateUrl() {
        return layoutTemplateUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.layout_template_url
     *
     * @param layoutTemplateUrl the value for layout_template.layout_template_url
     *
     * @mbg.generated
     */
    public void setLayoutTemplateUrl(String layoutTemplateUrl) {
        this.layoutTemplateUrl = layoutTemplateUrl == null ? null : layoutTemplateUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column layout_template.layout_picture_url
     *
     * @return the value of layout_template.layout_picture_url
     *
     * @mbg.generated
     */
    public String getLayoutPictureUrl() {
        return layoutPictureUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column layout_template.layout_picture_url
     *
     * @param layoutPictureUrl the value for layout_template.layout_picture_url
     *
     * @mbg.generated
     */
    public void setLayoutPictureUrl(String layoutPictureUrl) {
        this.layoutPictureUrl = layoutPictureUrl == null ? null : layoutPictureUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table layout_template
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tiid=").append(tiid);
        sb.append(", layoutTemplateName=").append(layoutTemplateName);
        sb.append(", layoutTemplateCreatetime=").append(layoutTemplateCreatetime);
        sb.append(", layoutTemplateCreateperson=").append(layoutTemplateCreateperson);
        sb.append(", layoutTemplateUrl=").append(layoutTemplateUrl);
        sb.append(", layoutPictureUrl=").append(layoutPictureUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}