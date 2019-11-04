package com.cost168.costaudit.pojo.yaohao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YaohaoCandidateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public YaohaoCandidateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIsNull() {
            addCriterion("enterprise_code is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIsNotNull() {
            addCriterion("enterprise_code is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeEqualTo(String value) {
            addCriterion("enterprise_code =", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotEqualTo(String value) {
            addCriterion("enterprise_code <>", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeGreaterThan(String value) {
            addCriterion("enterprise_code >", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_code >=", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLessThan(String value) {
            addCriterion("enterprise_code <", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLessThanOrEqualTo(String value) {
            addCriterion("enterprise_code <=", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLike(String value) {
            addCriterion("enterprise_code like", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotLike(String value) {
            addCriterion("enterprise_code not like", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIn(List<String> values) {
            addCriterion("enterprise_code in", values, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotIn(List<String> values) {
            addCriterion("enterprise_code not in", values, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeBetween(String value1, String value2) {
            addCriterion("enterprise_code between", value1, value2, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotBetween(String value1, String value2) {
            addCriterion("enterprise_code not between", value1, value2, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andYearAssessIsNull() {
            addCriterion("year_assess is null");
            return (Criteria) this;
        }

        public Criteria andYearAssessIsNotNull() {
            addCriterion("year_assess is not null");
            return (Criteria) this;
        }

        public Criteria andYearAssessEqualTo(String value) {
            addCriterion("year_assess =", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessNotEqualTo(String value) {
            addCriterion("year_assess <>", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessGreaterThan(String value) {
            addCriterion("year_assess >", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessGreaterThanOrEqualTo(String value) {
            addCriterion("year_assess >=", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessLessThan(String value) {
            addCriterion("year_assess <", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessLessThanOrEqualTo(String value) {
            addCriterion("year_assess <=", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessLike(String value) {
            addCriterion("year_assess like", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessNotLike(String value) {
            addCriterion("year_assess not like", value, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessIn(List<String> values) {
            addCriterion("year_assess in", values, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessNotIn(List<String> values) {
            addCriterion("year_assess not in", values, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessBetween(String value1, String value2) {
            addCriterion("year_assess between", value1, value2, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearAssessNotBetween(String value1, String value2) {
            addCriterion("year_assess not between", value1, value2, "yearAssess");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaIsNull() {
            addCriterion("year_win_numA is null");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaIsNotNull() {
            addCriterion("year_win_numA is not null");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaEqualTo(Integer value) {
            addCriterion("year_win_numA =", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaNotEqualTo(Integer value) {
            addCriterion("year_win_numA <>", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaGreaterThan(Integer value) {
            addCriterion("year_win_numA >", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaGreaterThanOrEqualTo(Integer value) {
            addCriterion("year_win_numA >=", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaLessThan(Integer value) {
            addCriterion("year_win_numA <", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaLessThanOrEqualTo(Integer value) {
            addCriterion("year_win_numA <=", value, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaIn(List<Integer> values) {
            addCriterion("year_win_numA in", values, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaNotIn(List<Integer> values) {
            addCriterion("year_win_numA not in", values, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaBetween(Integer value1, Integer value2) {
            addCriterion("year_win_numA between", value1, value2, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumaNotBetween(Integer value1, Integer value2) {
            addCriterion("year_win_numA not between", value1, value2, "yearWinNuma");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbIsNull() {
            addCriterion("year_win_numB is null");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbIsNotNull() {
            addCriterion("year_win_numB is not null");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbEqualTo(Integer value) {
            addCriterion("year_win_numB =", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbNotEqualTo(Integer value) {
            addCriterion("year_win_numB <>", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbGreaterThan(Integer value) {
            addCriterion("year_win_numB >", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbGreaterThanOrEqualTo(Integer value) {
            addCriterion("year_win_numB >=", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbLessThan(Integer value) {
            addCriterion("year_win_numB <", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbLessThanOrEqualTo(Integer value) {
            addCriterion("year_win_numB <=", value, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbIn(List<Integer> values) {
            addCriterion("year_win_numB in", values, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbNotIn(List<Integer> values) {
            addCriterion("year_win_numB not in", values, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbBetween(Integer value1, Integer value2) {
            addCriterion("year_win_numB between", value1, value2, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYearWinNumbNotBetween(Integer value1, Integer value2) {
            addCriterion("year_win_numB not between", value1, value2, "yearWinNumb");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIsNull() {
            addCriterion("yaohao_year is null");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIsNotNull() {
            addCriterion("yaohao_year is not null");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearEqualTo(String value) {
            addCriterion("yaohao_year =", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotEqualTo(String value) {
            addCriterion("yaohao_year <>", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearGreaterThan(String value) {
            addCriterion("yaohao_year >", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearGreaterThanOrEqualTo(String value) {
            addCriterion("yaohao_year >=", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLessThan(String value) {
            addCriterion("yaohao_year <", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLessThanOrEqualTo(String value) {
            addCriterion("yaohao_year <=", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearLike(String value) {
            addCriterion("yaohao_year like", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotLike(String value) {
            addCriterion("yaohao_year not like", value, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearIn(List<String> values) {
            addCriterion("yaohao_year in", values, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotIn(List<String> values) {
            addCriterion("yaohao_year not in", values, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearBetween(String value1, String value2) {
            addCriterion("yaohao_year between", value1, value2, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoYearNotBetween(String value1, String value2) {
            addCriterion("yaohao_year not between", value1, value2, "yaohaoYear");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIsNull() {
            addCriterion("yaohao_grade is null");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIsNotNull() {
            addCriterion("yaohao_grade is not null");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeEqualTo(String value) {
            addCriterion("yaohao_grade =", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotEqualTo(String value) {
            addCriterion("yaohao_grade <>", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeGreaterThan(String value) {
            addCriterion("yaohao_grade >", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeGreaterThanOrEqualTo(String value) {
            addCriterion("yaohao_grade >=", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLessThan(String value) {
            addCriterion("yaohao_grade <", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLessThanOrEqualTo(String value) {
            addCriterion("yaohao_grade <=", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeLike(String value) {
            addCriterion("yaohao_grade like", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotLike(String value) {
            addCriterion("yaohao_grade not like", value, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeIn(List<String> values) {
            addCriterion("yaohao_grade in", values, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotIn(List<String> values) {
            addCriterion("yaohao_grade not in", values, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeBetween(String value1, String value2) {
            addCriterion("yaohao_grade between", value1, value2, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andYaohaoGradeNotBetween(String value1, String value2) {
            addCriterion("yaohao_grade not between", value1, value2, "yaohaoGrade");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andRoundNumIsNull() {
            addCriterion("round_num is null");
            return (Criteria) this;
        }

        public Criteria andRoundNumIsNotNull() {
            addCriterion("round_num is not null");
            return (Criteria) this;
        }

        public Criteria andRoundNumEqualTo(Integer value) {
            addCriterion("round_num =", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumNotEqualTo(Integer value) {
            addCriterion("round_num <>", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumGreaterThan(Integer value) {
            addCriterion("round_num >", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("round_num >=", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumLessThan(Integer value) {
            addCriterion("round_num <", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumLessThanOrEqualTo(Integer value) {
            addCriterion("round_num <=", value, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumIn(List<Integer> values) {
            addCriterion("round_num in", values, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumNotIn(List<Integer> values) {
            addCriterion("round_num not in", values, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumBetween(Integer value1, Integer value2) {
            addCriterion("round_num between", value1, value2, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRoundNumNotBetween(Integer value1, Integer value2) {
            addCriterion("round_num not between", value1, value2, "roundNum");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andBidFlagIsNull() {
            addCriterion("bid_flag is null");
            return (Criteria) this;
        }

        public Criteria andBidFlagIsNotNull() {
            addCriterion("bid_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBidFlagEqualTo(String value) {
            addCriterion("bid_flag =", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagNotEqualTo(String value) {
            addCriterion("bid_flag <>", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagGreaterThan(String value) {
            addCriterion("bid_flag >", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagGreaterThanOrEqualTo(String value) {
            addCriterion("bid_flag >=", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagLessThan(String value) {
            addCriterion("bid_flag <", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagLessThanOrEqualTo(String value) {
            addCriterion("bid_flag <=", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagLike(String value) {
            addCriterion("bid_flag like", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagNotLike(String value) {
            addCriterion("bid_flag not like", value, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagIn(List<String> values) {
            addCriterion("bid_flag in", values, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagNotIn(List<String> values) {
            addCriterion("bid_flag not in", values, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagBetween(String value1, String value2) {
            addCriterion("bid_flag between", value1, value2, "bidFlag");
            return (Criteria) this;
        }

        public Criteria andBidFlagNotBetween(String value1, String value2) {
            addCriterion("bid_flag not between", value1, value2, "bidFlag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}