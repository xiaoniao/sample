package com.example.shardingtable.dal.dataobject;


/**
 * The table TP_STUDY_ASSIGNMENT
 */
public class StudyAssignmentBaseStudyDO {

    /**
     * id ID.
     */
    private Long id;
    /**
     * studyNo STUDY_NO.
     */
    private Long studyNo;
    /**
     * studentNo STUDENT_NO.
     */
    private Long studentNo;
    /**
     * status STATUS.
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(Long studyNo) {
        this.studyNo = studyNo;
    }

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
