package com.example.shardingtable.dal.dataobject;


/**
 * The table TP_STUDY_ASSIGNMENT
 */
public class StudyAssignmentDO{

    /**
     * id ID.
     */
    private Integer id;
    /**
     * studyNo STUDY_NO.
     */
    private String studyNo;
    /**
     * studentNo STUDENT_NO.
     */
    private String studentNo;
    /**
     * status STATUS.
     */
    private Integer status;

    /**
     * Set id ID.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set studyNo STUDY_NO.
     */
    public void setStudyNo(String studyNo){
        this.studyNo = studyNo;
    }

    /**
     * Get studyNo STUDY_NO.
     *
     * @return the string
     */
    public String getStudyNo(){
        return studyNo;
    }

    /**
     * Set studentNo STUDENT_NO.
     */
    public void setStudentNo(String studentNo){
        this.studentNo = studentNo;
    }

    /**
     * Get studentNo STUDENT_NO.
     *
     * @return the string
     */
    public String getStudentNo(){
        return studentNo;
    }

    /**
     * Set status STATUS.
     */
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
     * Get status STATUS.
     *
     * @return the string
     */
    public Integer getStatus(){
        return status;
    }
}
